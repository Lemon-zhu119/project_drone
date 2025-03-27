<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户姓名" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入用户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试卷名称" prop="paperName">
        <el-input
          v-model="queryParams.paperName"
          placeholder="请输入试卷名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="得分" prop="nowScore">
        <el-input
          v-model="queryParams.nowScore"
          placeholder="请输入得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['exam:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="examRecords" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="id" />
      <el-table-column label="用户姓名" align="center" prop="customerName" />
      <el-table-column label="试卷名称" align="center" prop="paperName" />
      <el-table-column label="试卷编号" align="center" prop="examPaperId" />
      <el-table-column label="实际得分" align="center" prop="nowScore">
        <template slot-scope="scope">
          {{ scope.row.nowScore }} 分
        </template>
      </el-table-column>
      <el-table-column label="总分" align="center" prop="score">
        <template slot-scope="scope">
          {{ scope.row.score }} 分
        </template>
      </el-table-column>
      <el-table-column label="考试时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total > 0"
      :total="Number(total)"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listExamRecords, exportExamRecords } from '@/api/question/records'
import { parseTime } from '@/utils/ruoyi'

export default {
  name: 'ExamRecords',
  data() {
    return {
      // 遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 考试记录表格数据
      examRecords: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerName: undefined,
        paperName: undefined,
        nowScore: undefined
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询考试记录列表 */
    getList() {
      this.loading = true
      listExamRecords(this.queryParams).then(response => {
        console.log('API Response:', response)
        if (response.code === 200) {
          this.examRecords = response.data || []
          this.total = this.examRecords.length
        } else {
          this.$modal.msgError(response.msg || '获取数据失败')
          this.examRecords = []
          this.total = 0
        }
      }).catch(error => {
        console.error('Error:', error)
        this.$modal.msgError('获取数据失败')
        this.examRecords = []
        this.total = 0
      }).finally(() => {
        this.loading = false
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 导出按钮操作 */
    handleExport() {
      this.$modal.confirm('是否确认导出所有考试记录数据项？').then(() => {
        this.$modal.loading("正在导出数据，请稍候...");
        return exportExamRecords(this.queryParams);
      }).then(() => {
        this.$modal.msgSuccess("导出成功");
      }).catch(() => {});
    },
    parseTime
  }
}
</script>

<style scoped>
.mb8 {
  margin-bottom: 8px;
}
</style> 