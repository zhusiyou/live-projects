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
      :model="permission"
      :rules="rules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <a-form-item label="权限名称" name="permissionName">
        <a-input v-model:value="permission.permissionName" />
      </a-form-item>
      <a-form-item label="Url规则" name="url">
        <a-input v-model:value="permission.url" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { reactive, ref } from "vue";
import type { UnwrapRef } from "vue";
import type { Rule } from "ant-design-vue/es/form";
import { addPermission, editPermission } from "@/api/system/permission";

interface Permission {
  permissionId?: string;
  permissionName: string;
  url: string;
}

const emit = defineEmits<{
  saved: [value: Permission];
}>();

let title = ref<string>("");
let permission: UnwrapRef<Permission>;
let show = ref<boolean>(false);
const open = (model: Permission) => {
  console.log(`output->model`, model);
  show.value = true;
  permission = reactive({ ...model });
  title.value = permission?.permissionId ? "编辑权限" : "新增权限";
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
  (permission?.permissionId ? editPermission : addPermission)(permission).then(() => {
    show.value = false;
    confirmLoading.value = false;
    emit("saved", permission);
  });
};

const formRef = ref();
const labelCol = { span: 6 };
const wrapperCol = { span: 13 };

const rules: Record<string, Rule[]> = {
  permissionName: [
    {
      required: true,
      message: "Please input 权限名称",
      trigger: "change",
    },
  ],
  url: [
    {
      required: true,
      message: '请输入url规则',
      trigger: 'change',
    }
  ]
};

defineExpose({
  open,
});
</script>
