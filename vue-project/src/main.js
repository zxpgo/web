import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import axios from "axios"

const app = createApp(App)

axios.get('/config.json').then((res) => {
  app.config.globalProperties.$config = res.data
})

export let globals = app.config.globalProperties


app.use(router)
app.use(ElementPlus, {
  locale: zhCn,
})

app.mount('#app')

