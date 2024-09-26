<template>
    <a-page-header title="Title">
      <template #extra>
        <a-button key="1" type="primary" @click="addPermission">新增权限</a-button>
      </template>
    </a-page-header>
    <a-table :dataSource="dataSource" :columns="columns">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <span>
            <a @click="editPermission(record)">编辑</a>
            <a-divider type="vertical" />
          <a @click="openRoleForm(record)">角色</a>
          </span>
        </template>
      </template>
    </a-table>
    <permission-form ref="permissionRef" @saved="saved"></permission-form>
    <roles v-model:open="showRolesForm" v-model="roleIdArray" @ok="handleRoles"></roles>
  </template>
  <script lang="ts" setup>
  import { onMounted, ref } from "vue";
  import PermissionForm from "./components/PermissionForm.vue";
  import Roles from './components/Roles.vue'
  import { list, roles, saveRoles } from "@/api/system/permission";
  
  const newPermission = () => {
    return {
      url: '',
      permissionName: ''
    };
  };
  
  let showRolesForm = ref<boolean>(false)
  const permissionRef = ref()
  const roleIdArray = ref<string[]>([])
  let current: Permission

  const openRoleForm = (permission: Permission) => {
    roles(permission).then(res => {
      roleIdArray.value = res.data
    })
    current = permission
    showRolesForm.value = true
  }

  const handleRoles = async () => {
    showRolesForm.value = false
    await saveRoles(roleIdArray.value, current.permissionId)
    console.log(`output-> roleIdArray`, roleIdArray)
  }
  const addPermission = () => {
   showModal(newPermission());
  };
  const editPermission = (permission: any)=>{
    showModal(permission)
  }
  const showModal = (permission: { permissionName: string; })=>{
    permissionRef.value.open(permission)
  }
  const saved = (permission: any): void =>{
    console.log(`output->saved permission`, permission);
    loadPermissions()
  }
  
  const dataSource = ref([]);
  const loadPermissions = () => {
    list().then((res) => {
      dataSource.value = res.data;
    });
  };
  onMounted(loadPermissions);
  
  const columns = [
    {
      title: "权限名称",
      dataIndex: "permissionName",
      key: "permissionName",
    },
    {
        title: 'url',
        dataIndex: 'url',
        key: 'url',
    },
    {
      title: "操作",
      key: "action",
    },
  ];
  </script>
  