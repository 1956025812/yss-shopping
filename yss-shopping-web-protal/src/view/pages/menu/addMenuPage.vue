<template>
  <div>
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openMenuAddModal"
    >
      <Icon type="md-Add" size="25"/>
    </Button>
    <Modal
      v-model="menuAddModal"
      title="新增菜单"
      ok-text="保存"
      @on-ok="addMenu()"
      :mask-closable="false"
      :closable="false"
    >
      <Form label-position="right" :label-width="100">
        <FormItem label="菜单名称：">
          <Input v-model="menuName"/>
        </FormItem>
        <FormItem label="菜单描述：">
          <Input v-model="description" type="textarea" :autosize="{minRows: 2,maxRows: 5}"/>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>


 
<script>
import { getToken } from "@/libs/util";
import { saveMenuAPI } from "@/api/menu/menu";

export default {
  name: "MenuAddModalPageComponent",
  data() {
    return {
      menuAddModal: false,
      node: Object,
      parentRid: "",
      parentMenuLevel: "",
      menuName: "",
      description: ""
    };
  },

  methods: {
    /**
     * 打开新增菜单弹窗
     */
    openMenuAddModal(node) {
      this.menuAddModal = true;
      this.node = node;
      this.menuName = "";
      this.description = "";
    },

    /**
     * 新增菜单
     */
    addMenu() {
      let params = new Object();
      params.loginUid = getToken();
      params.systemKey = this.node.systemKey;
      params.parentMid = this.node.mid;
      params.level = parseInt(this.node.level) + 1;
      params.menuName = this.menuName;
      params.description = this.description;

      saveMenuAPI(params).then(res => {
        if (res.data.code == 1) {
          // 刷新右下侧子菜单列表
          this.$parent.selectChildrenMenuList(this.node);
          // 刷新左侧树列表菜单节点子节点名称(清空并收起展开即可，下次再次发请求加载, 直接改改不了)
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











