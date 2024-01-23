<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <div class="logo" />
      <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="inline"
        @click="menuClick"
        :open-keys="state.openKeys"
        @openChange="onOpenChange"
      >
        <a-sub-menu key="system">
          <template #title>
            <span>
              <user-outlined />
              <span>system</span>
            </span>
          </template>
          <a-menu-item key="/user">用户管理</a-menu-item>
          <a-menu-item key="/role">角色管理</a-menu-item>
          <a-menu-item key="permission">权限管理</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub1">
          <template #title>
            <span>
              <user-outlined />
              <span>User</span>
            </span>
          </template>
          <a-menu-item key="3">Tom</a-menu-item>
          <a-menu-item key="4">Bill</a-menu-item>
          <a-menu-item key="5">Alex</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
            <span>
              <team-outlined />
              <span>Team</span>
            </span>
          </template>
          <a-menu-item key="6">Team 1</a-menu-item>
          <a-menu-item key="8">Team 2</a-menu-item>
        </a-sub-menu>
        <a-menu-item key="9">
          <file-outlined />
          <span>File</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout :style="{ width: '100%', height: '100%' }">
      <a-layout-header style="background: #fff; padding: 0" />
      <a-layout-content style="margin: 0 16px; padding-top: 16px">
        <!-- <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>User</a-breadcrumb-item>
          <a-breadcrumb-item>Bill</a-breadcrumb-item>
        </a-breadcrumb> -->
        
        <a-tabs
          v-model:activeKey="activeKey"
          hide-add
          type="editable-card"
          @edit="onEdit"
          @change="tabChange"
        >
          <a-tab-pane
            v-for="pane in panes"
            :key="pane.key"
            :tab="pane.title"
            :closable="pane.closable"
          >
          </a-tab-pane>
        </a-tabs>
        <div
          :style="{ background: '#fff', minHeight: '360px', height: '100%' }"
        >
        <router-view v-slot="{ Component }" v-if="panes.length">
          <keep-alive>
            <component :is="Component" />
          </keep-alive>
        </router-view>
        </div>
      </a-layout-content>
      <!-- <a-layout-footer style="text-align: center">
          Ant Design ©2018 Created by Ant UED
        </a-layout-footer> -->
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import {
  PieChartOutlined,
  DesktopOutlined,
  UserOutlined,
  TeamOutlined,
  FileOutlined,
} from "@ant-design/icons-vue";
import { reactive, ref, computed, onMounted } from "vue";
import { useRouter, useRoute, onBeforeRouteUpdate } from "vue-router";

const route = useRoute();
const router = useRouter();
const arr = ["/user", "/role"];
const nameArr = ["用户", "角色"];
const panes = ref<{ title: string; key: string; closable?: boolean }[]>([]);

const changePanes = (to, from) => {
  addPane(to);
  activeKey.value = to.path;
  selectedKeys.value[0] = to.path;
};

onMounted(() => changePanes(route));
onBeforeRouteUpdate(changePanes);

const tabChange = router.push;
const activeKey = ref<string>("");

// const newTabIndex = ref(0);

const addPane = (to) => {
  let exist = false;
  for (let i = 0; i < panes.value.length; i++) {
    const item = panes.value[i];
    if (item.key === to.path) {
      exist = true;
      break;
    }
  }
  if (!exist) {
    panes.value.push({
      title: to.meta.title,
      key: to.path,
    });
  }
};

const remove = (targetKey: string) => {
  let lastIndex = 0;
  panes.value.forEach((pane, i) => {
    if (pane.key === targetKey) {
      lastIndex = i - 1;
    }
  });
  panes.value = panes.value.filter((pane) => pane.key !== targetKey);
  if (panes.value.length && activeKey.value === targetKey) {
    if (lastIndex >= 0) {
      activeKey.value = panes.value[lastIndex].key;
    } else {
      activeKey.value = panes.value[0].key;
    }
  }
};

const onEdit = (targetKey: string) => {
  remove(targetKey);
};

const collapsed = ref<boolean>(false);
const selectedKeys = ref<string[]>(["1"]);

const menuClick = ({ key }) => {
  router.push(key);
};
const state = reactive({
  rootSubmenuKeys: ['system', 'sub1', 'sub2'],
  openKeys: ['system'],
})
const onOpenChange = (openKeys: string[]) => {
  const latestOpenKey = openKeys.find(key => state.openKeys.indexOf(key) === -1);
  if (state.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
    state.openKeys = openKeys;
  } else {
    state.openKeys = latestOpenKey ? [latestOpenKey] : [];
  }
};
</script>
<style scoped>
/* #components-layout-demo-side */
.logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}
[data-theme="dark"] .site-layout .site-layout-background {
  background: #141414;
}
</style>
