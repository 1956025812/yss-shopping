<template>
  <Tree :data="systemTreeData" :load-data="loadChildrenMenuNodes" @on-select-change="clickTreeNode"></Tree>
</template>
<script>
import { getToken } from "@/libs/util";
import { selectSystemListAPI } from "@/api/system/system";
import { selectSysMenuListAPI } from "@/api/menu/menu";

export default {
  name: "MenuTreePageComponent",
  data() {
    return {
      systemTreeData: []
    };
  },

  methods: {
    /**
     * 初始化系统树数据
     */
    initSysSystemTreeData() {
      this.systemTreeData = [];

      let systemParams = new Object();
      systemParams.loginUid = getToken();
      selectSystemListAPI(systemParams).then(res => {
        if (res.data.code == 1) {
          // 拼接系统树结构
          let sysSystemVOList = res.data.data;
          if (null != sysSystemVOList && sysSystemVOList.length > 0) {
            sysSystemVOList.forEach(eachSysSystemVO => {
              let systemTreeNode = new Object();
              systemTreeNode.type = "system";
              systemTreeNode.loading = false;
              systemTreeNode.expand = false;
              systemTreeNode.title = eachSysSystemVO.systemName;
              systemTreeNode.systemKey = eachSysSystemVO.systemKey;
              systemTreeNode.systemName = eachSysSystemVO.systemName;
              systemTreeNode.children = [];
              this.systemTreeData.push(systemTreeNode);
            });
          }
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    },

    /**
     * 异步加载子菜单节点
     */
    loadChildrenMenuNodes(item, callback) {
      let params = new Object();
      params.loginUid = getToken();
      if (item.type == "system") {
        params.systemKey = item.systemKey;
        params.level = 1;
      } else if (item.type == "menu") {
        params.systemKey = item.systemKey;
        params.parentMid = item.mid;
        params.level = parseInt(item.level) + 1;
      }

      selectSysMenuListAPI(params).then(res => {
        if (res.data.code == 1) {
          const childrenMenuNodes = [];
          let menuList = res.data.data;
          if (menuList.length > 0) {
            menuList.forEach(eachMenu => {
              let menuTreeNode = new Object();
              menuTreeNode.type = "menu";
              menuTreeNode.loading = false;
              menuTreeNode.expand = false;
              menuTreeNode.mid = eachMenu.mid;
              menuTreeNode.level = eachMenu.level;
              menuTreeNode.title = eachMenu.menuName;
              menuTreeNode.systemKey = eachMenu.systemKey;
              menuTreeNode.children = [];
              childrenMenuNodes.push(menuTreeNode);
            });
          } else {
            this.$Notice.info({ title: "已经是最后一级，没有子菜单了" });
          }
          callback(childrenMenuNodes);
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    },

    /**
     * 点击菜单树节点
     */
    clickTreeNode(nodes, node) {
      // 通过自定义的方法传递选中的节点数据到父组件中
      this.$emit("transmitClickedTreeNode", node);
    }
  },

  created() {
    // 初始化左侧系统树数据
    this.$options.methods.initSysSystemTreeData.bind(this)();

    // 监听全局事件initSysSystemTreeDataEvent  刷新左侧角色树列表
    let _this = this;
    this.bus.$on("initSysSystemTreeDataEvent", function(data) {
      _this.$options.methods.initSysSystemTreeData.bind(_this)();
    });
  }
};
</script>