<template>
  <Tree :data="systemTreeData" :load-data="loadChildrenRoleNodes" @on-select-change="clickTreeNode"></Tree>
</template>
<script>
import { getToken } from "@/libs/util";
import { selectSystemListAPI } from "@/api/system/system";
import { selectRoleListAPI } from "@/api/role/role";

export default {
  name: "RoleTreePageComponent",
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
     * 异步加载子角色节点
     */
    loadChildrenRoleNodes(item, callback) {
      let params = new Object();
      params.loginUid = getToken();
      if (item.type == "system") {
        params.systemKey = item.systemKey;
        params.level = 1;
      } else if (item.type == "role") {
        params.systemKey = item.systemKey;
        params.parentRid = item.rid;
        params.level = parseInt(item.level) + 1;
      }

      selectRoleListAPI(params).then(res => {
        if (res.data.code == 1) {
          const childrenRoleNodes = [];
          let roleList = res.data.data;
          if (roleList.length > 0) {
            roleList.forEach(eachRole => {
              let roleTreeNode = new Object();
              roleTreeNode.type = "role";
              roleTreeNode.loading = false;
              roleTreeNode.expand = false;
              roleTreeNode.rid = eachRole.rid;
              roleTreeNode.level = eachRole.level;
              roleTreeNode.title = eachRole.roleName;
              roleTreeNode.systemKey = eachRole.systemKey;
              roleTreeNode.children = [];
              childrenRoleNodes.push(roleTreeNode);
            });
          } else {
            this.$Notice.info({ title: "已经是最后一级，没有子角色了" });
          }
          callback(childrenRoleNodes);
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    },

    /**
     * 点击角色树节点
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