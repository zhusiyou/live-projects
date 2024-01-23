import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { resolve } from "path";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue({
    script: {
      defineModel: true
    }
  })],
  resolve: {
    alias: {
      // 根路径别名
      "@": resolve(__dirname, "./src"),
    },
  },
});
