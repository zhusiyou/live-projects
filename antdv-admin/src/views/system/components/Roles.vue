<template>
    <a-modal
      v-model:open="open"
      title="选择角色"
      :confirm-loading="confirmLoading"
      :destroyOnClose="true"
      @ok="handleOk"
    >
      <a-form>
        <a-checkbox-group
          v-model:value="values"
          :options="options"
        ></a-checkbox-group>
      </a-form>
    </a-modal>
  </template>
  <script lang="ts" setup>
  import { list } from "@/api/system/role";
  import { ref, onMounted } from "vue";
  
  const values = defineModel()
  const emit = defineEmits(['ok'])
  const open = defineModel<boolean>('open');

  const options = ref();
  const loadRoles = () => {
    list().then((res) => {
      options.value = res.data.map(
        (item: Role) => {
          return { label: item.roleName, value: item.roleId };
        }
      );
    });
  };
  onMounted(() => {
    loadRoles();
  }); 
  const confirmLoading = ref<boolean>(false);
  const handleOk = () => {
    emit('ok')
  };
  </script>
  