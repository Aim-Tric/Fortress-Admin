import { defineComponent, resolveComponent, h } from "vue"

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

const menus: Menu[] = [{
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
    }]
}]

export default defineComponent({
    setup() {
        const handleOpen = (key: string, keyPath: string[]) => {
            console.log(key, keyPath)
        }

        const handleClose = (key: string, keyPath: string[]) => {
            console.log(key, keyPath)
        }

        return () => (
            <el-menu
                router={true}
                default-active="0"
                style={
                    { minHeight: '100vh' }
                }
                onOpen={handleOpen}
                onClose={handleClose}
            >
                {menus.map((item) => generateMenu(item))}
            </el-menu>
        )
    }
})