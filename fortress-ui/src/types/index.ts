
export interface User {
    id: string,
    username: string
    password: string
    nickname: string
    sex: number
    phone: string
    email: string
    post: string
    dept: string
    orderNum: number
}

export interface UserVO extends User {
    roles: Role[]
}

export interface UserDTO extends User {
    roles: string[]
}

export interface LoginUser {
    loginId: string
    type: string
    account: string
    password: string
    validateCode: string
    rememberMe: boolean
}

export interface Post {
    id: string
    name: string
    orderNum: number
}

export interface Dept {
    id: string
    parentId: string
    name: string
    orderNum: number
    posts?: Post[]
}

export interface Role {
    id: string
    name: string
    identify: string
    status: number
    orderNum: number
}

export interface RoleVO extends Role {
    auths?: Auth[]
    menus?: Menu[]
}

export interface RoleDTO extends Role {
    auths?: string[]
    menus?: string[]
}

export interface Group {
    id: string
    parentId: string
    name: string
    auths?: Auth[]
    users?: User[]
}

export interface Auth extends TreeNode<string, Auth> {
    id: string
    parent: string
    name: string
    identify: string
    status: number
    orderNum: number
}

export interface Menu extends TreeNode<string, Menu> {
    id: string
    parent: string
    name: string
    iconName: string
    routeName: string
    pageTitle: string
    pagePath: string
    type: number
    componentPath: string
    status: number
    description: string
    orderNum: number
}

export interface ApiResult<T = {} | []> {
    code: number
    message: string
    data?: T
}

export interface Page<T = {} | []> {
    records: T[]
    total: number
    size: number
    current: number
}

export interface TreeNode<K, T> {
    id: K
    parent: K
    children?: T[]
    hasChildren: boolean
}

export interface SystemInfo {
    systemName: string
    initialized: boolean
    initializeTime: string
}

export interface ServerInfo {
    hostAddress: string
    hostName: string
    javaVendor: string
    javaVersion: number
    osArch: string
    osName: string
    osVersion: string
    realTimeCPUInfo: CPUInfo
    realTimeMemoryInfo: MemoryInfo
}

export interface CPUInfo {
    cpuModel: string
    cpuNum: number
    toTal: number
    sys: number
    user: number
    wait: number
    free: number
}

export interface MemoryInfo {
    freeMemory: number
    machineAvailableMemory: number
    machineMemoryPageSize: number
    machineTotalMemory: number
    maxMemory: number
    totalMemory: number
    usableMemory: number
}