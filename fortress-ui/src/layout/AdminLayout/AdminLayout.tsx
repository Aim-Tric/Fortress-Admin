import { defineComponent } from "vue";

import AdminMenu from "./modules/AdminMenu";

export default defineComponent({
    setup() {

        return () => (
            <el-container>
                <el-aside width="220px"><AdminMenu /></el-aside>
                <el-main>
                    <router-view />
                </el-main>
            </el-container>
        )
    }
})