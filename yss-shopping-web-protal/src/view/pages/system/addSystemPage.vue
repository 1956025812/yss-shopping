<template>
  <div id="systemAddModalDiv">
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openSystemAddModal"
    >
      <Icon type="md-add" size="25"/>
    </Button>
    <Modal
      v-model="systemAddModal"
      title="新增系统"
      ok-text="保存"
      @on-ok="saveSystem"
      :mask-closable="false"
      :closable="false"
    >
      <Row>
        <div>系统名称：
          <Input v-model="systemName" style="width: 70%"/>
        </div>
      </Row>
      <br>
      <Row>
        <div>系统标识：
          <Input v-model="systemKey" style="width: 70%"/>
        </div>
      </Row>
      <br>
      <Row>
        <div>系统描述：
          <Input v-model="description" type="textarea" :autosize="true" style="width: 70%"/>
        </div>
      </Row>
    </Modal>
  </div>
</template>



<script>
import { getToken } from "@/libs/util";
import { saveSystemAPI } from "@/api/system/system";

export default {
  name: "SystemAddModalPageComponent",
  data() {
    return {
      systemAddModal: false,
      systemName: "",
      systemKey: "",
      description: ""
    };
  },

  methods: {
    openSystemAddModal() {
      this.systemAddModal = true;
      this.systemName = null;
      this.systemKey = null;
      this.description = null;
      // alert("弹窗初始化操作可以在此处进行");
    },

    saveSystem() {
      let params = new Object();
      params.loginUid = getToken();
      params.systemName = this.systemName;
      params.systemKey = this.systemKey;
      params.description = this.description;

      saveSystemAPI(params).then(res => {
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
