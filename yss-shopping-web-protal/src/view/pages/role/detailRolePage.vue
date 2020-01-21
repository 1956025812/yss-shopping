<template>
  <div id="roleDetailModalDiv">
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openRoleDetailModal"
    >
      <Icon type="md-Detail" size="25"/>
    </Button>
    <Modal v-model="roleDetailModal" title="角色详情" :footer-hide="true">
      <Form label-position="right" :label-width="100">
        <FormItem label="角色名称：">
          <Input v-model="roleName" readonly/>
        </FormItem>
        <FormItem label="所属系统：">
          <Input v-model="systemName" readonly/>
        </FormItem>
        <FormItem label="上级角色：">
          <Input v-model="parentRoleName" readonly/>
        </FormItem>
        <FormItem label="状态：">
          <Input v-model="roleStateName" readonly/>
        </FormItem>
        <FormItem label="创建人：">
          <Input v-model="createName" readonly/>
        </FormItem>
        <FormItem label="创建时间：">
          <Input v-model="createTime" readonly/>
        </FormItem>
        <FormItem label="修改人：">
          <Input v-model="updateName" readonly/>
        </FormItem>
        <FormItem label="修改时间：">
          <Input v-model="updateTime" readonly/>
        </FormItem>
        <FormItem label="角色描述：">
          <Input
            v-model="description"
            type="textarea"
            :autosize="{minRows: 2,maxRows: 5}"
            readonly
          />
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>



<script>
import { getToken } from "@/libs/util";
import { selectRoleDetailAPI } from "@/api/role/role";

export default {
  name: "roleDetailModalPageComponent",
  data() {
    return {
      roleDetailModal: false,
      roleName: "",
      systemName: "",
      parentRoleName: "",
      roleStateName: "",
      createTime: "",
      createName: "",
      updateName: "",
      updateTime: "",
      description: ""
    };
  },

  methods: {
    openRoleDetailModal(rid) {
      this.roleDetailModal = true;

      let params = new Object();
      params.loginUid = getToken();
      params.rid = rid;

      selectRoleDetailAPI(params).then(res => {
        if (res.data.code == 1) {
          this.roleName = res.data.data.roleName;
          this.systemName = res.data.data.systemName;
          this.parentRoleName = res.data.data.parentRoleName;
          this.roleStateName = res.data.data.roleStateName;
          this.createName = res.data.data.createName;
          this.createTime = res.data.data.createTime;
          this.updateName = res.data.data.updateName;
          this.updateTime = res.data.data.updateTime;
          this.description = res.data.data.description;
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
