import type { Menu } from "@/types"
import { request } from "@/utils/request"

export const add = (menu: Menu): Promise<Menu> => {
    return request.post('/menu', JSON.stringify(menu))
}

export const remove = (id: string): Promise<Menu> => {
    return request.delete(`/menu/${id}`)
}

export const update = (menu: Menu): Promise<Menu> => {
    return request.put('/menu', JSON.stringify(menu))
}

export const getAsTree = (): Promise<Menu[]> => {
    return request.get(`/menu/tree`)
}

export const getById = (id: string): Promise<Menu> => {
    return request.get(`/menu/${id}`)
}

export const bindRole = (id: string, menus: Menu[]): Promise<Menu> => {
    return request.post(`/menu/${id}`, JSON.stringify(menus))
}