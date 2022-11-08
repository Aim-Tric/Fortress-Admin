import { defineComponent, resolveComponent, h, onMounted, ref } from "vue"
import { getAsTree } from "@/api/Menu"
import type { Menu } from "@/types"

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

export default defineComponent({
    setup() {
        const menus = ref<Menu[]>([{
            id: "1",
            parent: "",
            name: "首页",
            iconName: "Document",
            routeName: "Index",
            pageTitle: "首页",
            pagePath: "home",
            type: 0,
            componentPath: "",
            status: 0,
            description: "",
            orderNum: 1,
            hasChildren: false
        }, {
            id: "2",
            parent: "",
            name: "系统管理",
            iconName: "Document",
            routeName: "SystemManager",
            pageTitle: "系统管理",
            pagePath: "system",
            type: 0,
            componentPath: "",
            status: 0,
            description: "",
            orderNum: 1,
            hasChildren: true,
            children: [{
                id: "3",
                parent: "",
                name: "用户管理",
                iconName: "Document",
                routeName: "UserManager",
                pageTitle: "用户管理",
                pagePath: "user",
                type: 0,
                componentPath: "",
                status: 0,
                description: "",
                orderNum: 1,
                hasChildren: false
            }, {
                id: "4",
                parent: "",
                name: "角色管理",
                iconName: "Document",
                routeName: "RoleManager",
                pageTitle: "角色管理",
                pagePath: "role",
                type: 0,
                componentPath: "",
                status: 0,
                description: "",
                orderNum: 1,
                hasChildren: false
            }, {
                id: "5",
                parent: "",
                name: "权限管理",
                iconName: "Document",
                routeName: "AuthManager",
                pageTitle: "权限管理",
                pagePath: "auth",
                type: 0,
                componentPath: "",
                status: 0,
                description: "",
                orderNum: 1,
                hasChildren: false
            }, {
                id: "5",
                parent: "",
                name: "菜单管理",
                iconName: "Document",
                routeName: "MenuManager",
                pageTitle: "菜单管理",
                pagePath: "menu",
                type: 0,
                componentPath: "",
                status: 0,
                description: "",
                orderNum: 1,
                hasChildren: false
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