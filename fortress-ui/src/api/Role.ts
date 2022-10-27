import type { Role, Page } from "@/types"
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