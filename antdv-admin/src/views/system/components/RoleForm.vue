<template>
  <a-modal
    v-model:open="show"
    :title="title"
    :destroyOnClose="true"
    @ok="handleOk"
  >
    <a-form
      ref="formRef"
      :model="role"
      :rules="rules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <a-form-item label="角色名称" name="roleName">
        <a-input v-model:value="role.roleName" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { reactive, ref, toRaw, onMounted } from "vue";
import type { UnwrapRef } from "vue";
import type { Rule } from "ant-design-vue/es/form";

interface Role {
  roleId?: string;
  roleName: string;
}

const emit = defineEmits<{
  saved: [value: Role];
}>();

let title = ref<string>("");
let role: UnwrapRef<Role>;
let show = ref<boolean>(false);
const open = (model: Role) => {
  console.log(`output->model`, model);
  show.value = true;
  role = reactive({ ...model });
  title.value = role?.roleId ? "编辑角色" : "新增角色";
};
const confirmLoading = ref<boolean>(false);

const handleOk = () => {
  onSubmit(() => {
    confirmLoading.value = true;
    console.log("values", role, toRaw(role));

    setTimeout(() => {
      show.value = false;
      confirmLoading.value = false;
      emit("saved", role);
    }, 2000);
  });
};

const formRef = ref();
const labelCol = { span: 6 };
const wrapperCol = { span: 13 };

const rules: Record<string, Rule[]> = {
  roleName: [
    {
      required: true,
      message: "Please input 角色名称",
      trigger: "change",
    }
  ],
};
const onSubmit = (resolve) => {
  formRef.value
    .validate()
    .then(resolve)
    .catch((error) => {
      console.log("error", error);
    });
};

defineExpose({
  open,
});
</script>
