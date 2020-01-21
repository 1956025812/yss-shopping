<template>
  <div>
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openRoleAddModal"
    >
      <Icon type="md-Add" size="25"/>
    </Button>
    <Modal
      v-model="roleAddModal"
      title="新增角色"
      ok-text="保存"
      @on-ok="addRole()"
      :mask-closable="false"
      :closable="false"
    >
      <Form label-position="right" :label-width="100">
        <FormItem label="角色名称：">
          <Input v-model="roleName"/>
        </FormItem>
        <FormItem label="角色描述：">
          <Input v-model="description" type="textarea" :autosize="{minRows: 2,maxRows: 5}"/>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>


 
<script>
import { getToken } from "@/libs/util";
import { saveRoleAPI } from "@/api/role/role";

export default {
  name: "RoleAddModalPageComponent",
  data() {
    return {
      roleAddModal: false,
      node: Object,
      parentRid: "",
      parentRoleLevel: "",
      roleName: "",
      description: ""
    };
  },

  methods: {
    /**
     * 打开新增角色弹窗
     */
    openRoleAddModal(node) {
      this.roleAddModal = true;
      this.node = node;
      this.roleName = "";
      this.description = "";
    },

    /**
     * 新增角色
     */
    addRole() {
      let params = new Object();
      params.loginUid = getToken();
      params.systemKey = this.node.systemKey;
      params.parentRid = this.node.rid;
      params.level = parseInt(this.node.level) + 1;
      params.roleName = this.roleName;
      params.description = this.description;

      saveRoleAPI(params).then(res => {
        if (res.data.code == 1) {
          // 刷新右下侧子角色列表
          this.$parent.selectChildrenRoleList(this.node);
          // 刷新左侧树列表角色节点子节点名称(清空并收起展开即可，下次再次发请求加载, 直接改改不了)
          this.node.expand = false;
          this.node.children = [];

          this.$Notice.success({
            desc: res.data.msg
          });
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    }
  },

  created() {}
};
</script>











