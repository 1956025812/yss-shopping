<template>
  <div>
    <!-- 查询条件 -->
    <Card :bordered="false" title="系统列表">
      <Row>
        <div style="display: inline;">系统名称：
          <Input v-model="systemName" style="width: 200px"/>
        </div>
        <div style="display: inline;  margin-left:50px;">系统标识：
          <Input v-model="systemKey" style="width: 200px"/>
        </div>
        <div style="display: inline;  margin-left:50px;">创建时间：
          <DatePicker
            v-model="createTimeRange"
            type="datetimerange"
            style="width: 300px"
            format="yyyy-MM-dd HH:mm:ss"
            @on-change="createTimeRange=$event"
          ></DatePicker>
        </div>
        <div style="display: inline; margin-left:50px;">
          <Button class="search-btn" @click="query()" type="primary">
            <Icon type="search"/>查询
          </Button>
          <Button class="reset-btn" @click="reset" type="primary" style="margin-left:10px;">
            <Icon type="search"/>重置
          </Button>
        </div>
      </Row>
    </Card>
    <br>

    <!-- 操作按钮 -->
    <div>
      <Tooltip placement="top" content="新增">
        <SystemAddModalPageComponent @parentReset="reset"></SystemAddModalPageComponent>
      </Tooltip>
      <Tooltip placement="top" content="删除">
        <Button
          class="export-btn"
          style="border: none; appearance:none; margin-bottom: 5px;"
          @click="openDelSystemBatchModal()"
        >
          <Icon type="md-close" size="25"/>
        </Button>
      </Tooltip>
      <Tooltip placement="top" content="导入">
        <Button class="export-btn" style="border: none; appearance:none; margin-bottom: 5px;">
          <Icon type="md-arrow-round-forward" size="25"/>
        </Button>
      </Tooltip>
      <Tooltip placement="top" content="导出">
        <Button
          class="export-btn"
          style="border: none; appearance:none; margin-bottom: 5px;"
          @click="exportSystem()"
        >
          <Icon type="md-download" size="25"/>
        </Button>
      </Tooltip>
    </div>
    <br>

    <!-- 表格 -->
    <div>
      <Table
        id="id_table_system"
        ref="systemTableRef"
        border
        :columns="columns"
        :data="systemTableData"
        @on-select="selectRow"
        @on-select-cancel="cancelSelectRow"
      ></Table>
    </div>
    <br>

    <!-- 分页 -->
    <div style="float: right;">
      <Page
        :total="totalCount"
        :current="currentPage"
        :page-size="pageSize"
        show-total
        show-elevator
        show-sizer
        @on-change="changePage"
        @on-page-size-change="changePageSize"
      ></Page>
    </div>

    <!-- 系统详情弹窗子组件 -->
    <SystemDetailModalPageComponent ref="systemDetailModalRef" style="display:none"></SystemDetailModalPageComponent>
    <!-- 修改系统详情弹窗子组件 -->
    <SystemEditModalPageComponent
      ref="systemEditModalRef"
      style="display:none"
      @parentReset="reset"
    ></SystemEditModalPageComponent>
  </div>
</template>
  

<script>
import SystemAddModalPageComponent from "_p/system/addSystemPage";
import SystemDetailModalPageComponent from "_p/system/detailSystemPage";
import SystemEditModalPageComponent from "_p/system/editSystemPage";
import { setToken, getToken, removeArrayElement } from "@/libs/util";
import { selectSystemPageAPI, delSystemAPI } from "@/api/system/system";

export default {
  components: {
    SystemAddModalPageComponent,
    SystemDetailModalPageComponent,
    SystemEditModalPageComponent
  },
  data() {
    return {
      // 初始化变量
      systemName: "",
      systemKey: "",
      createTimeRange: "",
      totalCount: 0,
      currentPage: 1,
      pageSize: 10,
      selectRowSids: [],

      columns: [
        {
          type: "selection",
          key: "sid",
          width: 50,
          align: "center"
        },
        {
          type: "index",
          width: 80,
          align: "center"
        },
        {
          title: "系统名称",
          key: "systemName",
          align: "center"
        },
        {
          title: "系统标识",
          key: "systemKey",
          align: "center"
        },
        {
          title: "创建人",
          key: "createName",
          align: "center"
        },
        {
          title: "创建时间",
          key: "createTime",
          align: "center"
        },
        {
          title: "操作",
          key: "action",
          align: "center",
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
                      this.$refs.systemDetailModalRef.openSystemDetailModal(
                        params.row.sid
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
                      this.$refs.systemEditModalRef.openSystemEditModal(
                        params.row.sid
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
                      this.openDelSystemSingleModal(params.row);
                    }
                  }
                },
                "删除"
              )
            ]);
          }
        }
      ],
      systemTableData: []
    };
  },

  methods: {
    /**
     * 查询分页函数
     */
    querySystemPage(currentPage, pageSize) {
      let params = new Object();
      params.currentPage = currentPage;
      params.pageSize = pageSize;
      params.loginUid = getToken();
      params.systemName = this.systemName;
      params.systemKey = this.systemKey;
      if (this.createTimeRange != null && this.createTimeRange != "") {
        params.startTime = this.createTimeRange.toString().split(",")[0];
        params.endTime = this.createTimeRange.toString().split(",")[1];
      }

      selectSystemPageAPI(params).then(res => {
        if (res.data.code == 1) {
          this.totalCount = res.data.data.totalCount;
          this.currentPage = res.data.data.currentPage;
          this.pageSize = res.data.data.pageSize;
          this.systemTableData = res.data.data.items;

          // 处理复选框回显
          if (res.data.data.items.length > 0) {
            res.data.data.items.forEach(eachSystem => {
              if (this.selectRowSids.indexOf(eachSystem.sid) != -1) {
                eachSystem._checked = true;
              }
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
     * 换页
     */
    changePage(currentPage) {
      this.$options.methods.querySystemPage.bind(this)(
        currentPage,
        this.pageSize
      );
    },

    /**
     * 更换每页显示数据量
     */
    changePageSize(pageSize) {
      this.$options.methods.querySystemPage.bind(this)(1, pageSize);
    },

    /**
     * 选中复选框
     */
    selectRow(selection, row) {
      if (this.selectRowSids.indexOf(row.sid) == -1) {
        this.selectRowSids.push(row.sid);
      }
    },

    /**
     * 取消选中复选框
     */
    cancelSelectRow(selection, row) {
      removeArrayElement(this.selectRowSids, row.sid);
    },

    /**
     * 查询按钮查询
     */
    query() {
      this.selectRowSids = [];
      this.$options.methods.querySystemPage.bind(this)(1, 10);
    },

    /**
     * 重置查询条件
     */
    reset() {
      this.systemName = "";
      this.systemKey = "";
      this.createTimeRange = "";
      this.selectRowSids = [];
      this.$options.methods.querySystemPage.bind(this)(1, 10);
    },

    /**
     * 删除单条系统
     */
    openDelSystemSingleModal(row) {
      this.$Modal.confirm({
        title: "删除系统",
        content: "确认删除系统【" + row.systemName + "】么？",
        onOk: () => {
          let params = new Object();
          params.loginUid = getToken;
          params.sid = row.sid;
          delSystemAPI(params).then(res => {
            if (res.data.code == 1) {
              this.$Notice.success({
                desc: res.data.msg
              });
              this.reset();
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
     * 批量删除
     */
    openDelSystemBatchModal() {
      if (this.selectRowSids.length == 0) {
        this.$Notice.warning({
          title: "请选中要删除的记录后再进行删除操作"
        });
        return;
      }

      this.$Modal.confirm({
        title: "批量删除系统",
        content: "确认要批量删除这些系统么？",
        onOk: () => {
          let params = new Object();
          params.loginUid = getToken;
          params.sids = this.selectRowSids;
          delSystemAPI(params).then(res => {
            if (res.data.code == 1) {
              this.$Notice.success({
                desc: res.data.msg
              });
              this.reset();
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
     * 导出系统列表EXCEL TODO 这个链接怎么改成动态的 或者用 js中写
     */
    exportSystem() {
      let paramsStr = "";
      paramsStr += "loginUid=" + getToken();
      if (null != this.systemName && "" != this.systemName.trim()) {
        paramsStr += "&systemName=" + this.systemName;
      }
      if (null != this.systemKey && "" != this.systemKey.trim()) {
        paramsStr += "&systemKey=" + this.systemKey;
      }
      if (this.createTimeRange != null && this.createTimeRange != "") {
        paramsStr +=
          "&startTime=" + this.createTimeRange.toString().split(",")[0];
        paramsStr +=
          "&endTime=" + this.createTimeRange.toString().split(",")[1];
      }

      window.location.href = "http://localhost:8001/system/export?" + paramsStr;
    }
  },

  /**
   * 初始化页面 页面加载的时候执行
   */
  created() {
    this.$options.methods.querySystemPage.bind(this)(
      this.currentPage,
      this.pageSize
    );
  }
};
</script>
