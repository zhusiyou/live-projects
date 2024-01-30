<template>
  <a-modal
    v-model:open="show"
    :title="title"
    :confirm-loading="confirmLoading"
    :destroyOnClose="true"
    @ok="handleOk"
  >
    <a-form
      ref="formRef"
      :model="user"
      :rules="rules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <a-form-item label="用户名" name="username">
        <a-input v-model:value="user.username" />
      </a-form-item>
      <a-form-item label="密码" name="password">
        <a-input type="password" v-model:value="user.password" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { reactive, ref } from "vue";
import type { UnwrapRef } from "vue";
import type { Rule } from "ant-design-vue/es/form";
import { addUser, editUser } from "@/api/system/user";

interface User {
  userId?: string;
  username: string;
  password: string;
}

const emit = defineEmits<{
  saved: [value: User];
}>();

let title = ref<string>("");
let user: UnwrapRef<User>;
let show = ref<boolean>(false);
const open = (model: User) => {
  console.log(`output->model`, model);
  show.value = true;
  user = reactive({ ...model });
  title.value = user?.userId ? "编辑用户" : "新增用户";
};
const confirmLoading = ref<boolean>(false);

const handleOk = () => {
  formRef.value
    .validate()
    .then(save)
    .catch((error: any) => {
      console.log("error", error);
    });
};

const save = () => {
  confirmLoading.value = true;
  (user?.userId ? editUser : addUser)(user).then((data) => {
    console.log(`output->data`,data)
    show.value = false;
    confirmLoading.value = false;
    emit("saved", user);
  });
};

const formRef = ref();
const labelCol = { span: 6 };
const wrapperCol = { span: 13 };

const rules: Record<string, Rule[]> = {
  username: [
    {
      required: true,
      message: "Please input 用户名",
      trigger: "change",
    },
    { min: 3, max: 50, message: "Length should be 3 to 50", trigger: "blur" },
  ],
  password: [
    {
      required: true,
      message: "Please input 密码",
      trigger: "change",
    },
  ],
};

defineExpose({
  open,
});
</script>
