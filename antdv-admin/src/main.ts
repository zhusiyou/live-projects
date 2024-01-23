import { createApp } from 'vue'
// import './style.css'
import App from './App.vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'
import { createPinia } from 'pinia'

import router from './router'

console.log(router.getRoutes())

const app = createApp(App)
const pinia = createPinia()
app
.use(Antd)
.use(router)
.use(pinia)
.mount('#app')
