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
          <a>角色</a>
        </span>
      </template>
    </template>
  </a-table>
  <user-form ref="userRef" @saved="saved"></user-form>
</template>
<script lang="ts" setup>
import { ref } from "vue";
import UserForm from "./components/UserForm.vue";

const newUser = () => {
  return {
    userName: "",
    password: "",
  };
};

const editUserObj = {
  userId: "1",
  userName: "hand",
  password: "666",
};

const userRef = ref();
const addUser = () => showModal(newUser());
const editUser = (record) => showModal(record);
const showModal = (user) => {
  // formRef.value.open(newUser())
  userRef.value.open(user);
};
const saved = (savedUser: any): void =>
  console.log(`output->savedUser`, savedUser);

const dataSource = [
  {
    userId: "1",
    userName: "胡彦斌",
    password: "32",
  },
  {
    userId: "2",
    userName: "胡彦祖",
    password: "42",
  },
];

const columns = [
  {
    title: "姓名",
    dataIndex: "userName",
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
