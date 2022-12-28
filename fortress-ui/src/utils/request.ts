import { ApiResult } from "@/types";
import axios from "axios"
import { useRouter } from "vue-router";

export const request = axios.create({
    baseURL: '/api/',
    timeout: 6000,
});

request.interceptors.request.use(function (config) {
    if (config.method !== "get") {
        config.headers = {
            "content-type": "application/json;charset=utf-8"
        }
    }
    return config
}, function (error) {
    return Promise.reject(error)
});


request.interceptors.response.use(function (response) {
    const router = useRouter()
    if (response.status === 200) {
        const result = response.data as ApiResult
        if (result.code == 200) {
            return result.data
        } else if (result.code == 501) {
            console.log("requestUtil", router)
            router.push({ name: 'Login' })
            return Promise.reject(new Error(result.message))
        }
    }
    return Promise.reject(new Error("请求异常"))
}, function (error) {
    return Promise.reject(error)
});
