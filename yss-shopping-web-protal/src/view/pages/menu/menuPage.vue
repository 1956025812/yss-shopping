<template>
  <div class="demo-split">
    <Split v-model="leftRightSplit" min="280px">
      <div slot="left" class="demo-split-pane">
        <!-- 左：菜单树列表组件 -->
        <MenuTreePageComponent
          ref="MenuTreePageComponentRef"
          v-on:transmitClickedTreeNode="transmitClickedTreeNode"
        ></MenuTreePageComponent>
      </div>

      <div slot="right" class="demo-split-pane">
        <!-- 右上：系统或菜单详情组件 -->
        <div>
          <DetailSystemOrMenuPageComponent ref="DetailSystemOrMenuPageComponentRef"></DetailSystemOrMenuPageComponent>
        </div>

        <br>

        <!-- 右下: 子菜单列表组件  -->
        <div>
          <ChildrenMenuListTableComponent ref="ChildrenMenuListTableComponentRef"></ChildrenMenuListTableComponent>
        </div>
      </div>
    </Split>
  </div>
</template>



<script>
import MenuTreePageComponent from "_p/menu/menuTreePage";
import DetailSystemOrMenuPageComponent from "_p/menu/detailSystemOrMenuPage";
import ChildrenMenuListTableComponent from "_p/menu/childrenMenuListTable";

export default {
  components: {
    MenuTreePageComponent,
    DetailSystemOrMenuPageComponent,
    ChildrenMenuListTableComponent
  },
  data() {
    return {
      leftRightSplit: 0.2
    };
  },

  methods: {
    /**
     * 菜单树列表子组件点击传递过来的选中节点
     */
    transmitClickedTreeNode(node) {
      // 查询右上侧系统或菜单详情
      this.$options.methods.selectSystemOrMenuDetail.bind(this)(node);

      // 查询右下侧子菜单列表
      this.$options.methods.selectChildrenMenuList.bind(this)(node);
    },

    /**
     * 查询右上侧系统或菜单详情
     */
    selectSystemOrMenuDetail(node) {
      this.$refs.DetailSystemOrMenuPageComponentRef.selectSystemOrMenuDetail(
        node
      );
    },

    /**
     * 查询右下侧子菜单列表
     */
    selectChildrenMenuList(node) {
      this.$refs.ChildrenMenuListTableComponentRef.selectChildrenMenuList(node);
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