<!-- <script setup lang="ts">
import HelloWorld from './components/HelloWorld.vue'
</script> -->

<template>
  <!-- <div>
    <a href="https://vitejs.dev" target="_blank">
      <img src="/vite.svg" class="logo" alt="Vite logo" />
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
    </a>
  </div>
  <HelloWorld msg="Vite + Vue" />
  <a-button type="primary">hello antdv</a-button> -->

  <a-config-provider :locale="locale">
    <router-view></router-view>
  </a-config-provider>
</template>
<script lang="ts" setup>
import zhCN from "ant-design-vue/es/locale/zh_CN";
const locale = zhCN;
</script>
<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
