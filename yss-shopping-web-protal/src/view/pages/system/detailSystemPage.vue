<template>
  <div id="systemDetailModalDiv">
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openSystemDetailModal"
    >
      <Icon type="md-Detail" size="25"/>
    </Button>
    <Modal v-model="systemDetailModal" title="系统详情" :footer-hide="true">
      <Input v-model="hidden_sid" style="display:none"/>
      <Form label-position="right" :label-width="100">
        <FormItem label="系统名称：">
          <Input v-model="systemName" readonly/>
        </FormItem>
        <FormItem label="系统标识：">
          <Input v-model="systemKey" readonly/>
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
        <FormItem label="系统描述：">
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
import { selectSystemDetailAPI } from "@/api/system/system";

export default {
  name: "SystemDetailModalPageComponent",
  data() {
    return {
      systemDetailModal: false,
      hidden_sid: "",
      systemName: "",
      systemKey: "",
      createName: "",
      createTime: "",
      updateName: "",
      updateTime: "",
      description: ""
    };
  },

  methods: {
    openSystemDetailModal(sid) {
      this.systemDetailModal = true;

      let params = new Object();
      params.loginUid = getToken();
      params.sid = sid;

      selectSystemDetailAPI(params).then(res => {
        if (res.data.code == 1) {
          this.hidden_sid = res.data.data.sid;
          this.systemName = res.data.data.systemName;
          this.systemKey = res.data.data.systemKey;
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
