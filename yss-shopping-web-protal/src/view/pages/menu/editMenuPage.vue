<template>
  <div>
    <Button
      class="export-btn"
      style="border: none; appearance:none; margin-bottom: 5px;"
      @click="openMenuEditModal"
    >
      <Icon type="md-Edit" size="25"/>
    </Button>
    <Modal
      v-model="menuEditModal"
      title="修改菜单详情"
      ok-text="保存"
      @on-ok="editMenu()"
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
import { selectSysMenuDetailAPI, editMenuAPI } from "@/api/menu/menu";

export default {
  name: "MenuEditModalPageComponent",
  data() {
    return {
      menuEditModal: false,
      node: Object,
      menuName: "",
      description: ""
    };
  },

  methods: {
    /**
     * 打开修改菜单弹窗
     */
    openMenuEditModal(node) {
      this.menuEditModal = true;
      this.node = node;

      let params = new Object();
      params.loginUid = getToken();

      if (this.node.source == "detailSystemOrMenuPage") {
        params.mid = this.node.mid;
      } else if (this.node.source == "childrenMenuListTablePage") {
        params.mid = this.node.row.mid;
      }

      selectSysMenuDetailAPI(params).then(res => {
        if (res.data.code == 1) {
          this.menuName = res.data.data.menuName;
          this.description = res.data.data.description;
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    },

    /**
     * 修改菜单
     */
    editMenu() {
      let params = new Object();
      params.loginUid = getToken();

      if (this.node.source == "detailSystemOrMenuPage") {
        params.mid = this.node.mid;
      } else if (this.node.source == "childrenMenuListTablePage") {
        params.mid = this.node.row.mid;
      }

      params.menuName = this.menuName;
      params.description = this.description;

      editMenuAPI(params).then(res => {
        if (res.data.code == 1) {
          if (this.node.source == "detailSystemOrMenuPage") {
            // 刷新左侧树列表菜单节点名称
            this.node.title = this.menuName;
            // 刷新右上侧菜单详情
            this.$parent.selectSystemOrMenuDetail(this.node);
          } else if (this.node.source == "childrenMenuListTablePage") {
            // 刷新右下侧子菜单列表
            this.$parent.selectChildrenMenuList(this.node);
            // 刷新左侧树列表菜单节点子节点名称(清空并收起展开即可，下次再次发请求加载, 直接改改不了)
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











