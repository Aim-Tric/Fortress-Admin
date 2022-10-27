import type { Auth, Page } from "@/types"
import { request } from "@/utils/request"

export const add = (auth: Auth): Promise<Auth> => {
    return request.post('/auth', JSON.stringify(auth))
}

export const remove = (id: string): Promise<Auth> => {
    return request.delete(`/auth/${id}`)
}

export const update = (auth: Auth): Promise<Auth> => {
    return request.put('/auth', JSON.stringify(auth))
}

export const page = (page: number, size = 10): Promise<Page<Auth>> => {
    return request.get(`/auth/p/${page}/${size}`)
}

export const getById = (id: string): Promise<Auth> => {
    return request.get(`/auth/${id}`)
}