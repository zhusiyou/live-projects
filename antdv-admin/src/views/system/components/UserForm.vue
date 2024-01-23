<template>
  <a-modal
    v-model:open="show"
    :title="title"
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
      <a-form-item label="用户名" name="userName">
        <a-input v-model:value="user.userName" />
      </a-form-item>
      <a-form-item label="密码" name="password">
        <a-input type="password" v-model:value="user.password" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { reactive, ref, toRaw } from "vue";
import type { UnwrapRef } from "vue";
import type { Rule } from "ant-design-vue/es/form";
import { addUser } from "@/api/system/user";

interface User {
  userId?: string;
  userName: string;
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
  addUser(user).then((data) => {
    console.log(`output->data`, data);

    onSubmit(() => {
      confirmLoading.value = true;
      console.log("values", user, toRaw(user));

      setTimeout(() => {
        show.value = false;
        confirmLoading.value = false;
        emit("saved", user);
      }, 2000);
    });
  });
};

const formRef = ref();
const labelCol = { span: 6 };
const wrapperCol = { span: 13 };

const rules: Record<string, Rule[]> = {
  userName: [
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
const onSubmit = (resolve: () => void) => {
  formRef.value
    .validate()
    .then(resolve)
    .catch((error: any) => {
      console.log("error", error);
    });
};

defineExpose({
  open,
});
</script>
