import { createApp } from 'vue'
import App from './App'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import * as ElIcons from '@element-plus/icons-vue'

import 'element-plus/dist/index.css'
import "./style/theme.sass"

const app = createApp(App)

Object.entries(ElIcons).forEach(([name, component]) => {
    app.component(name, component)
});

app.use(ElementPlus, { size: 'small', zIndex: 3000, locale: zhCn })
app.use(store)
app.use(router)

app.mount('#app')
