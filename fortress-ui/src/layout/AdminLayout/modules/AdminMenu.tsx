import { defineComponent, resolveComponent, h, onMounted, ref } from "vue"
import { getAsTree } from "@/api/Menu"

export interface Menu {
    id: string,
    name: string,
    title?: string,
    to?: string,
    icon?: string,
    meta?: Map<string, unknown>,
    component?: string,
    parent?: string,
    children?: Menu[]
}

const iconTitle = (item: Menu) => {
    return (
        <>
            {
                item.icon &&
                <el-icon> {h(resolveComponent(item.icon))} </el-icon>
            }
            <span>{item.name}</span>
        </>
    )
}

const generateMenu = (item: Menu) => {
    if (!item.children) {
        return (
            <el-menu-item index={item.to}> {iconTitle(item)} </el-menu-item>
        )
    }
    return (
        <el-sub-menu index={item.to} v-slots={{
            title: () => iconTitle(item)
        }}>
            {item.children.map((child) => generateMenu(child))}
        </el-sub-menu>
    );
}

export default defineComponent({
    setup() {
        const menus = ref<Menu[]>([{
            id: "1",
            name: "首页",
            to: "home",
            icon: "Document"
        }, {
            id: "2",
            name: "系统管理",
            to: "system",
            icon: "Document",
            children: [{
                id: "3",
                name: "用户管理",
                to: "user",
                icon: "Document"
            }, {
                id: "4",
                name: "角色管理",
                to: "role",
                icon: "Document"
            }, {
                id: "5",
                name: "权限管理",
                to: "auth",
                icon: "Document"
            }, {
                id: "6",
                name: "菜单管理",
                to: "menu",
                icon: "Document"
            }]
        }])
        onMounted(() => {
            getAsTree().then(data => {
                data.forEach(menu => menus.value.push(menu))
            })
        })
        return () => (
            <el-menu
                router={true}
                default-active="0"
                style={
                    { minHeight: '100vh' }
                }
            >
                {menus.value.map((item) => generateMenu(item))}
            </el-menu>
        )
    }
})