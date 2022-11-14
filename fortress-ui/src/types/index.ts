
export interface User {
    id: string,
    username: string,
    password: string,
    nickname: string,
    sex: number,
    phone: string,
    email: string,
    post: string,
    dept: string,
    role: string,
    orderNum: number
}

export interface LoginUser {
    loginId: string,
    type: string,
    account: string,
    password: string,
    validateCode: string
}

export interface Post {
    id: string,
    name: string,
    orderNum: number
}

export interface Dept {
    id: string,
    parentId: string,
    name: string,
    orderNum: number,
    posts?: Post[]
}

export interface Role {
    id: string,
    name: string,
    identify: string,
    status: number,
    orderNum: number,
    auths?: Auth[]
    menus?: Menu[]
}

export interface Group {
    id: string,
    parentId: string,
    name: string,
    auths?: Auth[],
    users?: User[]
}

export interface Auth extends TreeNode<string, Auth> {
    id: string,
    parent: string,
    name: string,
    identify: string,
    status: number,
    orderNum: number
}

export interface Menu extends TreeNode<string, Menu> {
    id: string,
    parent: string,
    name: string,
    iconName: string,
    routeName: string,
    pageTitle: string,
    pagePath: string,
    type: number,
    componentPath: string,
    status: number,
    description: string,
    orderNum: number
}

export interface ApiResult<T = {} | []> {
    code: number,
    message: string,
    data?: T
}

export interface Page<T = {} | []> {
    records: T[],
    total: number,
    size: number,
    current: number
}

export interface TreeNode<K, T> {
    id: K,
    parent: K,
    children?: T[],
    hasChildren: boolean
}