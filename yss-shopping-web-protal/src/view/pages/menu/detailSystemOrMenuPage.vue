<template>
  <div>
    <div v-if="showDetailType == 'system'">
      <Card :bordered="false" title="系统详情">
        <Form :label-width="80">
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="系统名称">
                <Input readonly v-model="systemSystemName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="系统标识">
                <Input readonly v-model="systemSystemKey"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="创建人">
                <Input readonly v-model="systemCreateName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="创建时间">
                <Input readonly v-model="systemCreateTime"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="修改人">
                <Input readonly v-model="systemUpdateName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="修改时间">
                <Input readonly v-model="systemUpdateTime"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <FormItem label="描述">
              <Input
                type="textarea"
                :autosize="true"
                style="width: 100%"
                readonly
                v-model="systemDescription"
              />
            </FormItem>
          </Row>
        </Form>
      </Card>
    </div>
    <div v-if="showDetailType == 'menu'">
      <Card :bordered="false" title="菜单详情">
        <Form :label-width="80">
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="菜单名称">
                <Input readonly v-model="menuMenuName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="类型">
                <Input readonly v-model="menuTypeName"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="所属系统">
                <Input readonly v-model="menuSystemName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="上级菜单">
                <Input readonly v-model="parentMenuName"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="创建人">
                <Input readonly v-model="menuCreateName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="创建时间">
                <Input readonly v-model="menuCreateTime"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="修改人">
                <Input readonly v-model="menuUpdateName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="修改时间">
                <Input readonly v-model="menuUpdateTime"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <FormItem label="描述">
              <Input
                type="textarea"
                :autosize="true"
                style="width: 100%"
                readonly
                v-model="menuDescription"
              />
            </FormItem>
          </Row>
          <Row>
            <FormItem>
              <Button type="primary" style="margin-left: 8px" @click="openMenuEditModal()">修改</Button>
              <Button type="primary" style="margin-left: 8px" @click="openDelMenuSingleModal()">删除</Button>
            </FormItem>
          </Row>
        </Form>
      </Card>
    </div>

    <!-- 修改菜单详情弹窗组件 -->
    <MenuEditModalPageComponent ref="MenuEditModalPageComponentRef" style="display: none"></MenuEditModalPageComponent>
  </div>
</template>



<script>
import { getToken } from "@/libs/util";
import { selectSystemDetailAPI } from "@/api/system/system";
import { selectSysMenuDetailAPI, delMenuAPI } from "@/api/menu/menu";
import MenuEditModalPageComponent from "_p/menu/editMenuPage";

export default {
  name: "DetailSystemOrMenuPageComponent",
  components: {
    MenuEditModalPageComponent
  },
  data() {
    return {
      showDetailType: "",
      node: Object,
      systemSystemKey: "",
      systemSystemName: "",
      systemCreateName: "",
      systemCreateTime: "",
      systemUpdateName: "",
      systemUpdateTime: "",
      systemDescription: "",
      menuMid: "",
      menuMenuName: "",
      menuTypeName: "",
      menuSystemName: "",
      parentMenuName: "",
      menuCreateName: "",
      menuCreateTime: "",
      menuUpdateName: "",
      menuUpdateTime: "",
      menuDescription: ""
    };
  },

  methods: {
    /**
     * 查询系统或菜单详情
     */
    selectSystemOrMenuDetail(node) {
      this.node = node;
      this.showDetailType = node.type;

      if (node.type == "system") {
        let params = new Object();
        params.loginUid = getToken();
        params.systemKey = node.systemKey;
        selectSystemDetailAPI(params).then(res => {
          if (res.data.code == 1) {
            this.systemSystemName = res.data.data.systemName;
            this.systemSystemKey = res.data.data.systemKey;
            this.systemCreateName = res.data.data.createName;
            this.systemCreateTime = res.data.data.createTime;
            this.systemUpdateName = res.data.data.updateName;
            this.systemUpdateTime = res.data.data.updateTime;
            this.systemDescription = res.data.data.description;
          } else if (res.data.code == 0) {
            this.$Notice.error({
              desc: res.data.msg
            });
          }
        });
      } else if (node.type == "menu") {
        this.menuMid = node.mid;

        let params = new Object();
        params.loginUid = getToken();
        params.mid = node.mid;
        selectSysMenuDetailAPI(params).then(res => {
          if (res.data.code == 1) {
            this.menuMenuName = res.data.data.menuName;
            this.menuTypeName = res.data.data.menuTypeName;
            this.menuSystemName = res.data.data.systemName;
            this.parentMenuName = res.data.data.parentMenuName;
            this.menuCreateName = res.data.data.createName;
            this.menuCreateTime = res.data.data.createTime;
            this.menuUpdateName = res.data.data.updateName;
            this.menuUpdateTime = res.data.data.updateTime;
            this.menuDescription = res.data.data.description;
          } else if (res.data.code == 0) {
            this.$Notice.error({
              desc: res.data.msg
            });
          }
        });
      }
    },

    /**
     * 删除菜单
     */
    openDelMenuSingleModal() {
      this.$Modal.confirm({
        title: "删除菜单",
        content: "确认要删除菜单[" + this.node.title + "]么？",
        onOk: () => {
          let params = new Object();
          params.loginUid = getToken();
          params.mid = this.node.mid;
          delMenuAPI(params).then(res => {
            if (res.data.code == 1) {
              this.$Notice.success({
                desc: res.data.msg
              });

              // 调用全局事件来刷新左侧菜单树列表
              this.bus.$emit("initSysSystemTreeDataEvent");

              // 关闭右上侧详情展示
              this.showDetailType = "";

              // 调用全局事件来将右下侧子菜单列表组件展示关闭
              this.bus.$emit("hideChildrenMenuListTableComponentEvent");
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
     * 打开修改菜单弹窗组件
     */
    openMenuEditModal() {
      // 在父组件中通过ref调用子组件的方法
      this.node.source = "detailSystemOrMenuPage";
      this.$refs.MenuEditModalPageComponentRef.openMenuEditModal(this.node);
    }
  },

  created() {}
};
</script>
