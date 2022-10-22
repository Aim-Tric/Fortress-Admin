import type { User, LoginUser, ApiResult, Page } from "@/types"
import { request } from "@/utils/request"

export const page = (page: number, size = 10): Promise<ApiResult<Page<User>>> => {
    return request.get(`/user/p/${page}/${size}`)
}

export const getById = (id: string): Promise<ApiResult<User>> => {
    return request.get(`/user/${id}`)
}

export const add = (user: User): Promise<ApiResult<User>> => {
    return request.post(`/user`, JSON.stringify(user))
}

export const update = (user: User): Promise<ApiResult<User>> => {
    return request.put(`/user`, JSON.stringify(user))
}

export const remove = (id: string): Promise<ApiResult<User>> => {
    return request.delete(`/user/${id}`)
}

export const login = (user: LoginUser): Promise<ApiResult<User>> => {
    return request.post(`/login`, JSON.stringify(user))
}

export const register = (user: User): Promise<ApiResult<User>> => {
    return request.post(`/register`, JSON.stringify(user))
}