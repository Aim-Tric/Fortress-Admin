
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
    order: number
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
    order: number
}

export interface Dept {
    id: string,
    parentId: string,
    name: string,
    order: number,
    posts: Post[]
}

export interface Role {
    id: string,
    name: string,
    order: number,
    auths: Auth[]
}

export interface Group {
    id: string,
    parentId: string,
    name: string,
    auths: Auth[],
    users: User[]
}

export interface Auth {
    id: string,
    name: string,
    identity: string,
    order: number
}


export interface ApiResult<T = {} | []> {
    code: number,
    message: string,
    data?: T
}