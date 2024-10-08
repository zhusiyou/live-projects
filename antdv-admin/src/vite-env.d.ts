/// <reference types="vite/client" />

// 找不到模块“./***.vue”或其相应的类型声明
declare module '*.vue' {
    import type { DefineComponent } from "vue";
    const vueComponent: DefineComponent<{}, {}, any>
    export default vueComponent
}