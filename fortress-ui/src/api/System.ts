import { request } from "@/utils/request";

export const getSystemConfig = (): Promise<void> => {
    return request.get('')
}