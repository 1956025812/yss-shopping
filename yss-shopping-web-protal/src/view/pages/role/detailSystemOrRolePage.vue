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
    <div v-if="showDetailType == 'role'">
      <Card :bordered="false" title="角色详情">
        <Form :label-width="80">
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="角色名称">
                <Input readonly v-model="roleRoleName"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="所属系统">
                <Input readonly v-model="roleSystemName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="上级角色">
                <Input readonly v-model="parentRoleName"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="创建人">
                <Input readonly v-model="roleCreateName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="创建时间">
                <Input readonly v-model="roleCreateTime"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10" style="float: left">
              <FormItem label="修改人">
                <Input readonly v-model="roleUpdateName"/>
              </FormItem>
            </Col>
            <Col span="10" style="float: right">
              <FormItem label="修改时间">
                <Input readonly v-model="roleUpdateTime"/>
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
                v-model="roleDescription"
              />
            </FormItem>
          </Row>
          <Row>
            <FormItem>
              <Button type="primary">菜单权限</Button>
              <Button type="primary" style="margin-left: 8px" @click="openRoleEditModal()">修改</Button>
              <Button type="primary" style="margin-left: 8px" @click="openDelRoleSingleModal()">删除</Button>
            </FormItem>
          </Row>
        </Form>
      </Card>
    </div>

    <!-- 修改角色详情弹窗组件 -->
    <RoleEditModalPageComponent ref="RoleEditModalPageComponentRef" style="display: none"></RoleEditModalPageComponent>
  </div>
</template>



<script>
import { getToken } from "@/libs/util";
import { selectSystemDetailAPI } from "@/api/system/system";
import { selectRoleDetailAPI, delRoleAPI } from "@/api/role/role";
import RoleEditModalPageComponent from "_p/role/editRolePage";

export default {
  name: "DetailSystemOrRolePageComponent",
  components: {
    RoleEditModalPageComponent
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
      roleRid: "",
      roleRoleName: "",
      roleSystemName: "",
      parentRoleName: "",
      roleCreateName: "",
      roleCreateTime: "",
      roleUpdateName: "",
      roleUpdateTime: "",
      roleDescription: ""
    };
  },

  methods: {
    /**
     * 查询系统或角色详情
     */
    selectSystemOrRoleDetail(node) {
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
      } else if (node.type == "role") {
        this.roleRid = node.rid;

        let params = new Object();
        params.loginUid = getToken();
        params.rid = node.rid;
        selectRoleDetailAPI(params).then(res => {
          if (res.data.code == 1) {
            this.roleRoleName = res.data.data.roleName;
            this.roleSystemName = res.data.data.systemName;
            this.parentRoleName = res.data.data.parentRoleName;
            this.roleCreateName = res.data.data.createName;
            this.roleCreateTime = res.data.data.createTime;
            this.roleUpdateName = res.data.data.updateName;
            this.roleUpdateTime = res.data.data.updateTime;
            this.roleDescription = res.data.data.description;
          } else if (res.data.code == 0) {
            this.$Notice.error({
              desc: res.data.msg
            });
          }
        });
      }
    },

    /**
     * 删除角色
     */
    openDelRoleSingleModal() {
      this.$Modal.confirm({
        title: "删除角色",
        content: "确认要删除角色[" + this.node.title + "]么？",
        onOk: () => {
          let params = new Object();
          params.loginUid = getToken();
          params.rid = this.node.rid;
          delRoleAPI(params).then(res => {
            if (res.data.code == 1) {
              this.$Notice.success({
                desc: res.data.msg
              });

              // 调用全局事件来刷新左侧角色树列表
              this.bus.$emit("initSysSystemTreeDataEvent");

              // 关闭右上侧详情展示
              this.showDetailType = "";

              // 调用全局事件来将右下侧子角色列表组件展示关闭
              this.bus.$emit("hideChildrenRoleListTableComponentEvent");
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
     * 打开修改角色弹窗组件
     */
    openRoleEditModal() {
      // 在父组件中通过ref调用子组件的方法
      this.node.source = "detailSystemOrRolePage";
      this.$refs.RoleEditModalPageComponentRef.openRoleEditModal(this.node);
    }
  },

  created() {}
};
</script>
