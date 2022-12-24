import { defineComponent, resolveComponent, h, onMounted, ref } from "vue"
import { getUserMenus } from "@/api/User"
import type { Menu } from "@/types"
import { useEventPool } from "@/store"

const iconTitle = (item: Menu) => {
    return (
        <>
            {
                item.iconName &&
                <el-icon> {h(resolveComponent(item.iconName))} </el-icon>
            }
            <span>{item.name}</span>
        </>
    )
}

const generateMenu = (item: Menu) => {
    if (!item.children) {
        return (
            <el-menu-item index={item.pagePath}> {iconTitle(item)} </el-menu-item>
        )
    }
    return (
        <el-sub-menu index={item.pagePath} v-slots={{
            title: () => iconTitle(item)
        }}>
            {item.children.map((child) => generateMenu(child))}
        </el-sub-menu>
    );
}

const BASE_MENU_TEMPLATE: Menu[] = []

export default defineComponent({
    props: {
        collapse: {
            type: Boolean,
            required: false,
            default: false
        }
    },
    setup(props) {
        const menus = ref<Menu[]>(JSON.parse(JSON.stringify(BASE_MENU_TEMPLATE)))
        const eventPool = useEventPool()

        const loadAsTree = () => {
            getUserMenus().then(data => {
                const newMenu = JSON.parse(JSON.stringify(BASE_MENU_TEMPLATE))
                data.forEach(menu => newMenu.push(menu))
                menus.value = newMenu
            })
        }
        onMounted(() => {
            console.log("mounted")
            eventPool.subscribe("flushMenu", () => loadAsTree())
            loadAsTree()
        })
        return () => (
            <el-menu
                collapse={props.collapse}
                collapse-transition={false}
                router={true}
                default-active="0"
            >
                {menus.value.map((item) => generateMenu(item))}
            </el-menu>
        )
    }
})