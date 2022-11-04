import type { Menu } from "@/types"
import { request } from "@/utils/request"

export const add = (Menu: Menu): Promise<Menu> => {
    return request.post('/menu', JSON.stringify(Menu))
}

export const remove = (id: string): Promise<Menu> => {
    return request.delete(`/menu/${id}`)
}

export const update = (Menu: Menu): Promise<Menu> => {
    return request.put('/menu', JSON.stringify(Menu))
}

export const getAsTree = (): Promise<Menu[]> => {
    return request.get(`/menu/tree`)
}

export const getById = (id: string): Promise<Menu> => {
    return request.get(`/menu/${id}`)
}