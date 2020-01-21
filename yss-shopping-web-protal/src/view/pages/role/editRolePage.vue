<template>
  <div>
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openRoleEditModal"
    >
      <Icon type="md-Edit" size="25"/>
    </Button>
    <Modal
      v-model="roleEditModal"
      title="修改角色详情"
      ok-text="保存"
      @on-ok="editRole()"
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
import { selectRoleDetailAPI, editRoleAPI } from "@/api/role/role";

export default {
  name: "RoleEditModalPageComponent",
  data() {
    return {
      roleEditModal: false,
      node: Object,
      roleName: "",
      description: ""
    };
  },

  methods: {
    /**
     * 打开修改角色弹窗
     */
    openRoleEditModal(node) {
      this.roleEditModal = true;
      this.node = node;

      let params = new Object();
      params.loginUid = getToken();

      if (this.node.source == "detailSystemOrRolePage") {
        params.rid = this.node.rid;
      } else if (this.node.source == "childrenRoleListTablePage") {
        params.rid = this.node.row.rid;
      }

      selectRoleDetailAPI(params).then(res => {
        if (res.data.code == 1) {
          this.roleName = res.data.data.roleName;
          this.description = res.data.data.description;
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    },

    /**
     * 修改角色
     */
    editRole() {
      let params = new Object();
      params.loginUid = getToken();

      if (this.node.source == "detailSystemOrRolePage") {
        params.rid = this.node.rid;
      } else if (this.node.source == "childrenRoleListTablePage") {
        params.rid = this.node.row.rid;
      }

      params.roleName = this.roleName;
      params.description = this.description;

      editRoleAPI(params).then(res => {
        if (res.data.code == 1) {
          if (this.node.source == "detailSystemOrRolePage") {
            // 刷新左侧树列表角色节点名称
            this.node.title = this.roleName;
            // 刷新右上侧角色详情
            this.$parent.selectSystemOrRoleDetail(this.node);
          } else if (this.node.source == "childrenRoleListTablePage") {
            // 刷新右下侧子角色列表
            this.$parent.selectChildrenRoleList(this.node);
            // 刷新左侧树列表角色节点子节点名称(清空并收起展开即可，下次再次发请求加载, 直接改改不了)
            this.node.expand = false;
            this.node.children = [];
          }

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











