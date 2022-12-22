import { SystemInfo } from "@/types";
import { request } from "@/utils/request";

export const getSystemConfig = (): Promise<SystemInfo> => {
    return request.get('/system')
}

export const updateSystemConfig = (systemInfo: SystemInfo): Promise<void> => {
    return request.put('/system', JSON.stringify(systemInfo))
}