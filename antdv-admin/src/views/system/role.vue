<template>
    <a-page-header title="Title">
      <template #extra>
        <a-button key="3" type="primary" @click="showModal">新增角色</a-button>
        <!-- <a-button key="2">Operation</a-button>
            <a-button key="1" type="primary">Primary</a-button> -->
      </template>
    </a-page-header>
    <role-form ref="roleRef" @saved="saved"></role-form>
  </template>
  <script lang="ts" setup>
  import { ref } from "vue";
  import RoleForm from "./components/RoleForm.vue";
  
  const newRole = () => {
    return {
      roleName: "",
    }
  }
  
  const roleRef = ref();
  const showModal = () => {
    roleRef.value.open(newRole())
  };
  const saved = (savedRole: any): void =>
    console.log(`output->savedRole`, savedRole);
  </script>
  