<template>
  <a-page-header title="Title">
    <template #extra>
      <a-button key="3" type="primary" @click="addRole">新增角色</a-button>
      <!-- <a-button key="2">Operation</a-button>
            <a-button key="1" type="primary">Primary</a-button> -->
    </template>
  </a-page-header>
  <a-table :dataSource="dataSource" :columns="columns">
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'action'">
        <span>
          <a @click="editRole(record)">编辑</a>
        </span>
      </template>
    </template>
  </a-table>
  <role-form ref="roleRef" @saved="saved"></role-form>
</template>
<script lang="ts" setup>
import { onMounted, ref } from "vue";
import RoleForm from "./components/RoleForm.vue";
import { list } from "@/api/system/role";

const newRole = () => {
  return {
    roleName: "",
  };
};

const roleRef = ref();
const addRole = () => {
 showModal(newRole());
};
const editRole = (role: any)=>{
  showModal(role)
}
const showModal = (role: { roleName: string; })=>{
  roleRef.value.open(role)
}
const saved = (savedRole: any): void =>{
  console.log(`output->savedRole`, savedRole);
  loadRoles()
}

const dataSource = ref([]);
const loadRoles = () => {
  list().then((res) => {
    dataSource.value = res.data;
  });
};
onMounted(loadRoles);

const columns = [
  {
    title: "角色名称",
    dataIndex: "roleName",
    key: "name",
  },
  {
    title: "操作",
    key: "action",
  },
];
</script>
