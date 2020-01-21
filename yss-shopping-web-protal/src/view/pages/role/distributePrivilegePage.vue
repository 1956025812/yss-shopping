<template>
  <div>
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openDistributePrivilegeModal"
    >
      <Icon type="md-Add" size="25"/>
    </Button>
    <Modal
      v-model="distributePrivilegeModal"
      title="分配权限"
      ok-text="保存"
      @on-ok="distributePrivilege()"
      :mask-closable="false"
      :closable="false"
    ></Modal>
  </div>
</template>


 
<script>
import { getToken } from "@/libs/util";

export default {
  name: "DistributePrivilegePageComponent",
  data() {
    return {
      distributePrivilegeModal: false
    };
  },

  methods: {
    /**
     * 打开分配权限弹窗
     */
    openDistributePrivilegeModal(node) {
      this.distributePrivilegeModal = true;
    },

    /**
     * 分配权限
     */
    distributePrivilege() {}
  },

  created() {}
};
</script>











