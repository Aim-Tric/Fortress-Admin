import type { Menu } from "@/types"
import { request } from "@/utils/request"

export const add = (Menu: Menu): Promise<Menu> => {
    return request.post('/Menu', JSON.stringify(Menu))
}

export const remove = (id: string): Promise<Menu> => {
    return request.delete(`/Menu/${id}`)
}

export const update = (Menu: Menu): Promise<Menu> => {
    return request.put('/Menu', JSON.stringify(Menu))
}

export const getAsTree = (): Promise<Menu[]> => {
    return request.get(`/Menu/tree`)
}

export const getById = (id: string): Promise<Menu> => {
    return request.get(`/Menu/${id}`)
}