<template>
  <div>
    <div v-if="show">
      <Card>
        <p slot="title">子角色列表</p>
        <Button slot="extra" type="primary" @click="openAddRoleModal()">新增</Button>

        <!-- 子角色列表 -->
        <div>
          <Table border :columns="columns" :data="childrenRoleListTableData"></Table>
        </div>
      </Card>
    </div>

    <!-- 角色详情弹窗组件 -->
    <RoleDetailModalPageComponent ref="RoleDetailModalPageComponentRef" style="display: none"></RoleDetailModalPageComponent>
    <!-- 新增角色弹窗组件-->
    <RoleAddModalPageComponent ref="RoleAddModalPageComponentRef" style="display: none"></RoleAddModalPageComponent>
    <!-- 修改角色弹窗组件 -->
    <RoleEditModalPageComponent ref="RoleEditModalPageComponentRef" style="display: none"></RoleEditModalPageComponent>
    <!-- 分配权限弹窗组件 -->
    <DistributePrivilegePageComponent
      ref="DistributePrivilegePageComponentRef"
      style="display: none"
    ></DistributePrivilegePageComponent>
  </div>
</template>



<script>
import { getToken } from "@/libs/util";
import { selectRolePageAPI, delRoleAPI } from "@/api/role/role";
import RoleDetailModalPageComponent from "_p/role/detailRolePage";
import RoleAddModalPageComponent from "_p/role/addRolePage";
import RoleEditModalPageComponent from "_p/role/editRolePage";
import DistributePrivilegePageComponent from "_p/role/distributePrivilegePage";

export default {
  name: "ChildrenRoleListTableComponent",
  components: {
    RoleDetailModalPageComponent,
    RoleAddModalPageComponent,
    RoleEditModalPageComponent,
    DistributePrivilegePageComponent
  },
  data() {
    return {
      show: false,
      node: Object,
      columns: [
        {
          type: "index",
          width: 80,
          align: "center"
        },
        {
          title: "角色名称",
          key: "roleName",
          align: "center"
        },
        {
          title: "父角色名称",
          key: "parentRoleName",
          align: "center"
        },
        {
          title: "角色级别",
          key: "level",
          align: "center"
        },
        {
          title: "状态",
          key: "roleStateName",
          align: "center"
        },
        {
          title: "操作",
          key: "action",
          align: "center",
          width: 300,
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      // 在父组件中通过ref调用子组件的方法
                      this.$refs.RoleDetailModalPageComponentRef.openRoleDetailModal(
                        params.row.rid
                      );
                    }
                  }
                },
                "详情"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      // 在父组件中通过ref调用子组件的方法
                      this.node.source = "childrenRoleListTablePage";
                      this.node.row = params.row;
                      this.$refs.RoleEditModalPageComponentRef.openRoleEditModal(
                        this.node
                      );
                    }
                  }
                },
                "修改"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.node.row = params.row;
                      this.$refs.DistributePrivilegePageComponentRef.openDistributePrivilegeModal(
                        this.node
                      );
                    }
                  }
                },
                "分配权限"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      // 在父组件中通过ref调用子组件的方法
                      this.node.source = "childrenRoleListTablePage";
                      this.node.row = params.row;
                      this.openDelRoleSingleModal(this.node);
                    }
                  }
                },
                "删除"
              )
            ]);
          }
        }
      ],
      childrenRoleListTableData: []
    };
  },

  methods: {
    /**
     * 查询子角色列表
     */
    selectChildrenRoleList(node) {
      this.show = true;
      this.node = node;

      let params = new Object();
      params.loginUid = getToken();
      params.currentPage = 1;
      params.pageSize = 20;
      params.systemKey = node.systemKey;
      if (node.type == "system") {
        params.level = 1;
      } else if (node.type == "role") {
        params.parentRid = node.rid;
      }
      selectRolePageAPI(params).then(res => {
        if (res.data.code == 1) {
          this.childrenRoleListTableData = res.data.data.items;
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    },

    /**
     * 删除单条角色
     */
    openDelRoleSingleModal() {
      this.$Modal.confirm({
        title: "删除角色",
        content: "确认要删除角色[" + this.node.row.roleName + "]么？",
        onOk: () => {
          let params = new Object();
          params.loginUid = getToken();
          params.rid = this.node.row.rid;
          delRoleAPI(params).then(res => {
            if (res.data.code == 1) {
              this.$Notice.success({
                desc: res.data.msg
              });

              // 刷新左侧树列表角色节点子节点名称(清空并收起展开即可，下次再次发请求加载, 直接改改不了)
              this.node.expand = false;
              this.node.children = [];

              // 刷新右下侧子角色列表
              this.$options.methods.selectChildrenRoleList.bind(this)(
                this.node
              );
            } else if (res.data.code == 0) {
              this.$Notice.error({
                desc: res.data.msg
              });
            }
          });
        }
      });
    },

    /**
     * 打开新增子角色Modal弹窗
     */
    openAddRoleModal() {
      this.$refs.RoleAddModalPageComponentRef.openRoleAddModal.bind(this)(
        this.node
      );
    }
  },

  created() {
    // 监听全局事件hideChildrenRoleListTableComponentEvent  隐藏右下侧子角色列表组件
    let _this = this;
    this.bus.$on("hideChildrenRoleListTableComponentEvent", function(data) {
      _this.show = false;
    });
  }
};
</script>











