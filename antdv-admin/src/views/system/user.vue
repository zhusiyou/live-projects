<template>
  <a-page-header title="Title">
    <template #extra>
      <a-button key="3" type="primary" @click="addUser">新增用户</a-button>
      <!-- <a-button key="2">Operation</a-button>
          <a-button key="1" type="primary">Primary</a-button> -->
    </template>
  </a-page-header>
  <a-table :dataSource="dataSource" :columns="columns">
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'action'">
        <span>
          <a @click="editUser(record)">编辑</a>
          <a-divider type="vertical" />
          <a @click="editRole(record)">角色</a>
        </span>
      </template>
    </template>
  </a-table>
  <user-form ref="userRef" @saved="saved"></user-form>
  <user-role-form ref="userRoleRef"></user-role-form>
</template>
<script lang="ts" setup>
import { onMounted, ref } from "vue";
import UserForm from "./components/UserForm.vue";
import { list} from '@/api/system/user'
import UserRoleForm from '@/views/system/components/UserRoleForm.vue'

const newUser = () => {
  return {
    username: "",
    password: "",
  };
};

const userRef = ref();
const userRoleRef= ref()
const addUser = () => showModal(newUser());
const editUser = (record: any) => showModal(record);
const showModal = (user: { username: string; password: string; }) => {
  // formRef.value.open(newUser())
  userRef.value.open(user);
};
const saved = (savedUser: any): void =>{
  console.log(`output->savedUser`, savedUser);
  load()
}

const editRole = (user: { userId: any }) => {
  userRoleRef.value.open(user.userId)
}


const dataSource = ref([])

const load = ()=>{
  list().then(data=>{
    console.log(`output->data`,data)
    dataSource.value = data.data
  })
}

onMounted(load)

const columns = [
  {
    title: "姓名",
    dataIndex: "username",
    key: "name",
  },
  {
    title: "密码",
    dataIndex: "password",
    key: "password",
  },
  {
    title: "操作",
    key: "action",
  },
];
</script>
