import { request } from "@/utils/request";
import { ServerInfo } from "@/types";

export const getServerInfo = (): Promise<ServerInfo> => {
    return request.get('/monitor')
}