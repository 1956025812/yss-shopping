<template>
  <div id="systemEditModalDiv">
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openSystemEditModal"
    >
      <Icon type="md-Edit" size="25"/>
    </Button>
    <Modal
      v-model="systemEditModal"
      title="修改系统详情"
      ok-text="保存"
      @on-ok="editSystem"
      :mask-closable="false"
      :closable="false"
    >
      <Input v-model="hidden_sid" style="display:none"/>
      <Form label-position="right" :label-width="100">
        <FormItem label="系统名称：">
          <Input v-model="systemName"/>
        </FormItem>
        <FormItem label="系统标识：">
          <Input v-model="systemKey" disabled/>
        </FormItem>
        <FormItem label="系统描述：">
          <Input v-model="description" type="textarea" :autosize="{minRows: 2,maxRows: 5}"/>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>



<script>
import { getToken } from "@/libs/util";
import { selectSystemDetailAPI, editSystemAPI } from "@/api/system/system";

export default {
  name: "SystemEditModalPageComponent",
  data() {
    return {
      systemEditModal: false,
      hidden_sid: "",
      systemName: "",
      systemKey: "",
      description: ""
    };
  },

  methods: {
    openSystemEditModal(sid) {
      this.systemEditModal = true;

      let params = new Object();
      params.loginUid = getToken();
      params.sid = sid;

      selectSystemDetailAPI(params).then(res => {
        if (res.data.code == 1) {
          this.hidden_sid = res.data.data.sid;
          this.systemName = res.data.data.systemName;
          this.systemKey = res.data.data.systemKey;
          this.description = res.data.data.description;
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    },

    editSystem() {
      let params = new Object();
      params.loginUid = getToken();
      params.sid = this.hidden_sid;
      params.systemName = this.systemName;
      params.description = this.description;

      editSystemAPI(params).then(res => {
        if (res.data.code == 1) {
          this.$Notice.success({
            desc: res.data.msg
          });
          // 通过自定义事件调用父窗口的重置按钮刷新分页列表
          this.$emit("parentReset");
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











