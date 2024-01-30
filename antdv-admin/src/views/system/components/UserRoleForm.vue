<template>
  <a-modal
    v-model:open="show"
    title="设置用户角色"
    :confirm-loading="confirmLoading"
    :destroyOnClose="true"
    @ok="handleOk"
  >
    <a-form ref="formRef">
      <a-checkbox-group
        v-model:value="userRoles"
        :options="options"
      ></a-checkbox-group>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { list } from "@/api/system/role";
import { getUserRoles, edit } from "@/api/system/user-role";
import { ref, onMounted } from "vue";

const show = ref<boolean>(false);
// const roles = ref([]);
const options = ref<string[]>();
const loadRoles = () => {
  list().then((res) => {
    // roles.value = res.data;
    options.value = res.data.map(
      (item: { roleName: string; roleId: string }) => {
        return { label: item.roleName, value: item.roleId };
      }
    );
  });
};
onMounted(() => {
  console.log(`output->onMounted`);
  loadRoles();
});

let userId: string = "";
const open = (id: string) => {
  show.value = true;
  userId = id;
  loadUserRoles();
};
const userRoles = ref<string[]>([]);
const loadUserRoles = () => {
  getUserRoles(userId).then((res) => {
    userRoles.value = res.data.map((item: { roleId: string }) => item.roleId);
  });
};

const confirmLoading = ref<boolean>(false);
const handleOk = () => {
  console.log(`output->userRoles`, userRoles);
  confirmLoading.value = true;
  edit(userId, userRoles.value).then((res) => {
    if (res.data) {
      show.value = false;
      confirmLoading.value = false;
    }
  });
};

defineExpose({
  open,
});
</script>
