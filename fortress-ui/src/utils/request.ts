import { ApiResult } from "@/types";
import axios from "axios"

export const request = axios.create({
    baseURL: '/api/',
    timeout: 6000,
});


request.interceptors.request.use(function (config) {
    if(config.method !== "get") {
        config.headers = {
            "content-type": "application/json;charset=utf-8"
        }
    }
    return config;
}, function (error) {
    return Promise.reject(error);
});


request.interceptors.response.use(function (response) {
    if(response.status === 200) {
        const result = response.data as ApiResult
        if(result.code == 200) {
            return result.data;
        }
    }
    return Promise.reject(new Error("请求异常"))
}, function (error) {
    return Promise.reject(error)
});
