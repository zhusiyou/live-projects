import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
    {
        path: "/", 
        name: 'home',
        component: ()=>import('../layout/index.vue'),
        meta: {
            title: '系统管理',
            level: 0
        },
        children: [
            {
                path: '/user',
                meta: {
                    title: '用户管理'
                },
                component: ()=>import('@/views/system/user.vue')
            },
            {
                path: '/role',
                meta: {
                    title: '角色管理'
                },
                component: ()=>import('@/views/system/role.vue')
            },
        ]
    },
    {
        path: "/login",
        component: ()=>import('@/views/login.vue')
    }
]
const pathName = '/permission'
const title = '权限管理'
const permission = 'permission'
routes[0].children?.push({
    path: `${pathName}`,
    meta: {
        title: `${title}`
    },
    component: ()=>import(`@/views/system/${permission}.vue`)
})

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router