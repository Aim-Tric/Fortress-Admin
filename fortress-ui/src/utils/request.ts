import axios from "axios"

export const request = axios.create({
    baseURL: '/api/',
    timeout: 6000,
});


request.interceptors.request.use(function (config) {
    return config;
}, function (error) {
    return Promise.reject(error);
});


request.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    return Promise.reject(error);
});
