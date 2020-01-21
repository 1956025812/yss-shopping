<template>
  <div>
    <div v-if="show">
      <Card>
        <p slot="title">子菜单或按钮列表</p>
        <Button slot="extra" type="primary" @click="openAddMenuModal()">新增</Button>

        <!-- 子菜单列表 -->
        <div>
          <Table border :columns="columns" :data="childrenMenuListTableData"></Table>
        </div>
      </Card>
    </div>

    <!-- 菜单详情弹窗组件 -->
    <MenuDetailModalPageComponent ref="MenuDetailModalPageComponentRef" style="display: none"></MenuDetailModalPageComponent>
    <!-- 新增菜单弹窗组件-->
    <MenuAddModalPageComponent ref="MenuAddModalPageComponentRef" style="display: none"></MenuAddModalPageComponent>
    <!-- 修改菜单弹窗组件 -->
    <MenuEditModalPageComponent ref="MenuEditModalPageComponentRef" style="display: none"></MenuEditModalPageComponent>
  </div>
</template>



<script>
import { getToken } from "@/libs/util";
import { selectSysMenuPageAPI, delMenuAPI } from "@/api/menu/menu";
import MenuDetailModalPageComponent from "_p/menu/detailMenuPage";
import MenuAddModalPageComponent from "_p/menu/addMenuPage";
import MenuEditModalPageComponent from "_p/menu/editMenuPage";

export default {
  name: "ChildrenMenuListTableComponent",
  components: {
    MenuDetailModalPageComponent,
    MenuAddModalPageComponent,
    MenuEditModalPageComponent
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
          title: "菜单名称",
          key: "menuName",
          align: "center"
        },
        {
          title: "父菜单名称",
          key: "parentMenuName",
          align: "center"
        },
        {
          title: "菜单级别",
          key: "level",
          align: "center"
        },
        {
          title: "状态",
          key: "menuStateName",
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
                      this.$refs.MenuDetailModalPageComponentRef.openMenuDetailModal(
                        params.row.mid
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
                      this.node.source = "childrenMenuListTablePage";
                      this.node.row = params.row;
                      this.$refs.MenuEditModalPageComponentRef.openMenuEditModal(
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
                  on: {
                    click: () => {
                      // 在父组件中通过ref调用子组件的方法
                      this.node.source = "childrenMenuListTablePage";
                      this.node.row = params.row;
                      this.openDelMenuSingleModal(this.node);
                    }
                  }
                },
                "删除"
              )
            ]);
          }
        }
      ],
      childrenMenuListTableData: []
    };
  },

  methods: {
    /**
     * 查询子菜单列表
     */
    selectChildrenMenuList(node) {
      this.show = true;
      this.node = node;

      let params = new Object();
      params.loginUid = getToken();
      params.currentPage = 1;
      params.pageSize = 20;
      params.systemKey = node.systemKey;
      if (node.type == "system") {
        params.level = 1;
      } else if (node.type == "menu") {
        params.parentMid = node.mid;
      }
      selectSysMenuPageAPI(params).then(res => {
        if (res.data.code == 1) {
          this.childrenMenuListTableData = res.data.data.items;
        } else if (res.data.code == 0) {
          this.$Notice.error({
            desc: res.data.msg
          });
        }
      });
    },

    /**
     * 删除单条菜单
     */
    openDelMenuSingleModal() {
      this.$Modal.confirm({
        title: "删除菜单",
        content: "确认要删除菜单[" + this.node.row.menuName + "]么？",
        onOk: () => {
          let params = new Object();
          params.loginUid = getToken();
          params.mid = this.node.row.mid;
          delMenuAPI(params).then(res => {
            if (res.data.code == 1) {
              this.$Notice.success({
                desc: res.data.msg
              });

              // 刷新左侧树列表菜单节点子节点名称(清空并收起展开即可，下次再次发请求加载, 直接改改不了)
              this.node.expand = false;
              this.node.children = [];

              // 刷新右下侧子菜单列表
              this.$options.methods.selectChildrenMenuList.bind(this)(
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
     * 打开新增子菜单Modal弹窗
     */
    openAddMenuModal() {
      this.$refs.MenuAddModalPageComponentRef.openMenuAddModal.bind(this)(
        this.node
      );
    }
  },

  created() {
    // 监听全局事件hideChildrenMenuListTableComponentEvent  隐藏右下侧子菜单列表组件
    let _this = this;
    this.bus.$on("hideChildrenMenuListTableComponentEvent", function(data) {
      _this.show = false;
    });
  }
};
</script>











