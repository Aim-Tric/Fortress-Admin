import type { User, LoginUser, Page, UserDTO, UserVO, Menu } from "@/types"
import { request } from "@/utils/request"

export const page = (page: number, size = 10): Promise<Page<UserDTO>> => {
    return request.get(`/user/p/${page}/${size}`)
}

export const getById = (id: string): Promise<UserVO> => {
    return request.get(`/user/${id}`)
}

export const add = (user: User): Promise<UserDTO> => {
    return request.post(`/user`, JSON.stringify(user))
}

export const update = (user: User): Promise<UserDTO> => {
    return request.put(`/user`, JSON.stringify(user))
}

export const remove = (id: string): Promise<UserDTO> => {
    return request.delete(`/user/${id}`)
}

export const login = (user: LoginUser): Promise<UserDTO> => {
    return request.post(`/login`, JSON.stringify(user))
}

export const register = (user: User): Promise<UserDTO> => {
    return request.post(`/register`, JSON.stringify(user))
}

export const isLogin = (): Promise<boolean> => {
    return request.get(`/isLogin`)
}

export const currentUser = (): Promise<UserVO> => {
    return request.get(`/userInfo`)
}

export const logout = (): Promise<void> => {
    return request.get(`/logout`)
}

export const getUserMenus = (): Promise<Menu[]> => {
    return request.get(`/menu/user`)
}

export const updatePassword = (oldPassword: string, newPassword: string): Promise<void> => {
    return request.put(`/account`, JSON.stringify({
        oldPassword,
        newPassword
    }))
}