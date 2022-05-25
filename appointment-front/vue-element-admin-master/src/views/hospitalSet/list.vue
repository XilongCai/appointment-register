<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.hosname" placeholder="Name" />
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchObj.hoscode" placeholder="No." />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()"
        >Search</el-button
      >
    </el-form>

    <!-- 工具条 -->
    <div>
      <el-button type="danger" size="mini" @click="removeRows()"
        >批量删除</el-button
      >
    </div>

    <!-- banner列表 -->
    <el-table
      :data="list"
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column type="index" width="50" />
      <el-table-column prop="hosname" label="医院名称" />
      <el-table-column prop="hoscode" label="医院编号" />
      <el-table-column prop="apiUrl" label="api基础路径" width="200" />
      <el-table-column prop="contactsName" label="联系人姓名" />
      <el-table-column prop="contactsPhone" label="联系人手机" />
      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === 1 ? "可用" : "不可用" }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status == 1"
            type="primary"
            size="mini"
            icon="el-icon-success"
            @click="lockHospSet(scope.row.id, 0)"
            >Lock</el-button
          >
          <el-button
            v-if="scope.row.status == 0"
            type="danger"
            size="mini"
            icon="el-icon-error"
            @click="lockHospSet(scope.row.id, 1)"
            >Unlock</el-button
          >
          <router-link :to="'/hospSet/edit/' + scope.row.id">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
            ></el-button>
          </router-link>

          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
          >
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="current"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>
</template>

<script>
// 引入接口定义的js文件
import hospset from "@/api/hosp/hospitalSet";

export default {
  // 定义变量和初始值
  data() {
    return {
      current: 1, // 当前页
      limit: 3, // 每页显示记录数
      searchObj: {}, // 条件封装对象
      list: [], // 每页数据集合
      total: 0, // 总记录数
      multipleSelection: [], // 批量选择中选择的记录列表
    };
  },
  created() {
    // 在页面渲染之前执行
    // 一般调用methods定义的方法，得到数据
    this.getList();
  },
  methods: {
    // 定义方法，进行请求接口调用
    // 医院设置列表
    getList(page = 1) {
      // 添加当前页参数
      this.current = page;
      hospset
        .getPageList(this.current, this.limit, this.searchObj)
        .then((response) => {
          // 请求成功response是接口返回数据
          // 返回集合赋值list
          this.list = response.data.data.records;
          console.log(response);
          // 总记录数
          this.total = response.data.data.total;
        })
        .catch((error) => {
          // 请求失败
          console.log(error);
        });
    },

    removeDataById(id) {
      this.$confirm("此操作将永久删除医院是设置信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        hospset.deleteHospSet(id).then((response) => {
          this.$message({
            type: "success",
            message: "delete successfully!",
          });
          this.getList(1);
        });
      });
    },

    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },

    removeRows() {
      this.$confirm("此操作将永久删除医院是设置信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        var idList = [];
        for (var i = 0; i < this.multipleSelection.length; ++i) {
          var obj = this.multipleSelection[i];
          var id = obj.id;
          idList.push(id);
        }
        hospset.removeRows(idList).then((response) => {
          this.$message({
            type: "success",
            message: "delete successfully!",
          });
          this.getList(1);
        });
      });
    },

    lockHospSet(id, status) {
      hospset.lockHospSet(id, status).then((response) => {
        this.getList(this.current);
      });
    },
  },
};
</script>
