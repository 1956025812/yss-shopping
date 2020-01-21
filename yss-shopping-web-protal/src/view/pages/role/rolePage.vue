<template>
  <div class="demo-split">
    <Split v-model="leftRightSplit" min="280px">
      <div slot="left" class="demo-split-pane">
        <!-- 左：角色树列表组件 -->
        <RoleTreePageComponent
          ref="RoleTreePageComponentRef"
          v-on:transmitClickedTreeNode="transmitClickedTreeNode"
        ></RoleTreePageComponent>
      </div>

      <div slot="right" class="demo-split-pane">
        <!-- 右上：系统或角色详情组件 -->
        <div>
          <DetailSystemOrRolePageComponent ref="DetailSystemOrRolePageComponentRef"></DetailSystemOrRolePageComponent>
        </div>

        <br>

        <!-- 右下: 子角色列表组件  -->
        <div>
          <ChildrenRoleListTableComponent ref="ChildrenRoleListTableComponentRef"></ChildrenRoleListTableComponent>
        </div>
      </div>
    </Split>
  </div>
</template>



<script>
import RoleTreePageComponent from "_p/role/roleTreePage";
import DetailSystemOrRolePageComponent from "_p/role/detailSystemOrRolePage";
import ChildrenRoleListTableComponent from "_p/role/childrenRoleListTable";

export default {
  components: {
    RoleTreePageComponent,
    DetailSystemOrRolePageComponent,
    ChildrenRoleListTableComponent
  },
  data() {
    return {
      leftRightSplit: 0.2
    };
  },

  methods: {
    /**
     * 角色树列表子组件点击传递过来的选中节点
     */
    transmitClickedTreeNode(node) {
      // 查询右上侧系统或角色详情
      this.$options.methods.selectSystemOrRoleDetail.bind(this)(node);

      // 查询右下侧子角色列表
      this.$options.methods.selectChildrenRoleList.bind(this)(node);
    },

    /**
     * 查询右上侧系统或角色详情
     */
    selectSystemOrRoleDetail(node) {
      this.$refs.DetailSystemOrRolePageComponentRef.selectSystemOrRoleDetail(
        node
      );
    },

    /**
     * 查询右下侧子角色列表
     */
    selectChildrenRoleList(node) {
      this.$refs.ChildrenRoleListTableComponentRef.selectChildrenRoleList(node);
    }
  }
};
</script>
<style>
.demo-split {
  height: 100%;
  border: 1px solid #dcdee2;
}
.demo-split-pane {
  padding: 10px;
}
</style>