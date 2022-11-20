import type { Role, RoleDTO, Page, RoleVO } from "@/types"
import { request } from "@/utils/request"

export const add = (role: Role): Promise<Role> => {
    return request.post('/role', JSON.stringify(role))
}

export const remove = (id: string): Promise<Role> => {
    return request.delete(`/role/${id}`)
}

export const update = (role: Role): Promise<Role> => {
    return request.put('/role', JSON.stringify(role))
}

export const page = (page: number, size = 10): Promise<Page<Role>> => {
    return request.get(`/role/p/${page}/${size}`)
}

export const getById = (id: string): Promise<Role> => {
    return request.get(`/role/${id}`)
}

export const dispatchMenu = (roleDTO: RoleDTO): Promise<Role> => {
    return request.post('/role/menu', JSON.stringify(roleDTO))
}

export const authorization = (roleDTO: RoleDTO): Promise<Role> => {
    return request.post('/role/auth', JSON.stringify(roleDTO))
}

export const listAll = (): Promise<Role[]> => {
    return request.get("/role/all")
}

export const getRoleAuthById = (id: string): Promise<RoleVO> => {
    return request.get(`/role/auth/${id}`)
}

export const getRoleMenuById = (id: string): Promise<RoleVO> => {
    return request.get(`/role/menu/${id}`)
}