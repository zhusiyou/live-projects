import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
    {
        path: "/", 
        name: 'home',
        component: ()=>import('../layout/index.vue'),
        meta: {
            title: '系统管理'
        },
        children: [
            {
                path: 'user',
                meta: {
                    title: '用户管理'
                },
                component: ()=>import('@/views/system/user.vue')
            },
            {
                path: 'role',
                meta: {
                    title: '角色管理'
                },
                component: ()=>import('@/views/system/role.vue')
            },
        ]
    },
    {
        path: "/login",
        component: ()=>import('@/views/Login.vue')
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router