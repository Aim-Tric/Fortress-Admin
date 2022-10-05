

import type { ApiResult, LoginUser } from "@/types"

export const login = (loginUser: LoginUser): Promise<ApiResult> => {
    console.log("doing login", loginUser)
    return new Promise((resolve, reject) => {
        resolve({
            code: 200,
            message: '成功'
        })
    })
}
