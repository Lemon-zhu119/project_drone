<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="答案" prop="answer">
        <el-input v-model="queryParams.answer" placeholder="请输入答案" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="类型" prop="type">
        <el-input v-model="queryParams.type" placeholder="请输入类型" clearable @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="类型" prop="type">
        <el-autocomplete v-model="queryParams.type" class="inline-input" :fetch-suggestions="querySearchAsyncType"
          style="width: 100%;" placeholder="请输入类型" @select="handleSelectType" clearable
          @keyup.enter.native="handleQuery">
          <template slot-scope="{ item }">
            <div class="value">{{ item.value }}</div>
          </template>
        </el-autocomplete>
      </el-form-item>
      <el-form-item label="分数" prop="score">
        <el-input v-model="queryParams.score" placeholder="请输入分数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['question:question:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['question:question:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['question:question:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="el-icon-upload" size="mini" @click="openImportTable"
          v-hasPermi="['question:question:import']">导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['question:question:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="questionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="题目内容" align="center" prop="content" />
      <el-table-column label="答案" align="center" prop="answer" />
      <el-table-column label="类型" align="center" prop="type" />
      <el-table-column label="分数" align="center" prop="score" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['question:question:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['question:question:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改题目管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="题目内容">
          <editor v-model="form.content" :min-height="192" />
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input v-model="form.answer" placeholder="请输入答案" />
        </el-form-item>
        <el-form-item label="A:" prop="answerA">
          <el-input v-model="form.answerA" placeholder="请输入答案内容" />
        </el-form-item>
        <el-form-item label="B:" prop="answerB">
          <el-input v-model="form.answerB" placeholder="请输入答案内容" />
        </el-form-item>
        <el-form-item label="C:" prop="answerC">
          <el-input v-model="form.answerC" placeholder="请输入答案内容" />
        </el-form-item>
        <el-form-item label="D:" prop="answerD">
          <el-input v-model="form.answerD" placeholder="请输入答案内容" />
        </el-form-item>
        <!-- <el-form-item label="类型" prop="type">
          <el-input v-model="form.type" placeholder="请输入类型" />
        </el-form-item> -->
        <el-form-item label="类型" prop="type">
          <el-autocomplete v-model="form.type" class="inline-input" :fetch-suggestions="querySearchAsyncType"
            style="width: 100%;" placeholder="请输入内容" @select="handleSelectType" clearable
            @keyup.enter.native="handleQuery">
            <template slot-scope="{ item }">
              <div class="value">{{ item.value }}</div>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="分数" prop="score">
          <el-input v-model="form.score" placeholder="请输入分数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!--导入题库弹窗-->
    <el-dialog title="导入题目" :visible.sync="dialogVisibleImport" width="30%" :before-close="handleCloseImport">
      <div>
        <!-- localAction:"http://localhost:8080/question/uploadExcel",
        Action:"http://116.62.160.24:8080/question/uploadExcel" , -->
        <el-upload class="upload-demo" drag action="http://116.62.160.24:8080/question/uploadExcel" multiple
          :headers="headers">
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">
            只能上传excel文件，且不超过500kb
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleImport = false">取 消</el-button>
        <el-button type="primary" @click="handleImportQuestion()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  listQuestion,
  getQuestion,
  delQuestion,
  addQuestion,
  updateQuestion,
  getType
} from "@/api/question/question";
import { getToken } from "@/utils/auth";
export default {
  name: "Question",
  data() {
    return {
      // 遮罩层
      loading: true,
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
      // 题目管理表格数据
      questionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        content: null,
        answer: null,
        type: null,
        score: null,
      },

      // 表单参数
      form: {
        answers: [],
        answerA: null,
        answerB: null,
        answerC: null,
        answerD: null,
      },
      types: [],
      headers: {
        Authorization: "Bearer " + getToken()
      },
      // 表单校验
      rules: {},
      //导入
      dialogVisibleImport: false
    };
  },
  created() {
    this.getList();

  },
  methods: {
    delHtmlTag(str) {
      return str.replace(/<[^>]+>/g, "");
    },
    /** 查询题目管理列表 */
    getList() {
      this.loading = true;
      listQuestion(this.queryParams).then((response) => {
        this.questionList = response.rows;
        // this.questionList.forEach((data)=>{
        //   data.answers = this.split(data.option);
        // })
        this.total = response.total;
        this.loading = false;
        // response.rows.forEach((data) => {
        //   data.answers = this.split(data.option);
        // });
        console.log(response);
      });
      getType().then((response) => {
        this.types = response.data
        console.log(this.types)
      })
    },
    split(str) {
      const keys = ["A", "B", "C", "D"];
      const answers = [];
      keys.forEach((key) => {
        const regex = new RegExp(`${key}:([^;]+)`);
        const match = regex.exec(str);
        if (match) {
          answers.push(match[1].trim());
        }
      });
      return answers;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        content: null,
        answer: null,
        answerA: null,
        answerB: null,
        answerC: null,
        answerD: null,
        type: null,
        score: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加题目管理";
    },
    handleSelectType(item) {
      console.log(item)
    },
    //获取并筛选类型
    querySearchAsyncType(queryString, cb) {
      var dataItemList = this.types;
      var results = queryString
        ? dataItemList.filter(this.createTypeFilter(queryString))
        : dataItemList;
      results = results.map((data) => {
        return {
          value: data,
        };
      });
      cb(results);
    },
    createTypeFilter(queryString) {
      return (state) => {
        return (
          state.toLowerCase().indexOf(queryString.toLowerCase()) === 0 ||
          state.toLowerCase().includes(queryString.toLowerCase())
        );
      };
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getQuestion(id).then((response) => {
        console.log(response);
        this.form = response.data;
        this.form.answers = this.split(response.data.option);
        // 设置 ABCD 选项
        this.$set(
          this.form,
          "answerA",
          this.form.answers[0] ? this.form.answers[0] : ""
        );
        this.$set(
          this.form,
          "answerB",
          this.form.answers[1] ? this.form.answers[1] : ""
        );
        this.$set(
          this.form,
          "answerC",
          this.form.answers[2] ? this.form.answers[2] : ""
        );
        this.$set(
          this.form,
          "answerD",
          this.form.answers[3] ? this.form.answers[3] : ""
        );
        
        // 如果存在type，将其添加到types数组中确保可以被选择
        if (this.form.type && !this.types.includes(this.form.type)) {
          this.types.push(this.form.type);
        }
        
        console.log(this.form);
        this.open = true;
        this.title = "修改题目管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.content = this.delHtmlTag(this.form.content);
      this.$refs["form"].validate((valid) => {
        console.log(this.form);
        if (valid) {
          if (this.form.id != null) {
            let option = null;
            this.form.option = `${this.form.answerA ? "A:" + this.form.answerA + ";" : ""
              }${this.form.answerB ? "B:" + this.form.answerB + ";" : ""}${this.form.answerC ? "C:" + this.form.answerC + ";" : ""
              }${this.form.answerD ? "D:" + this.form.answerD + ";" : ""}`;
            console.log(option);
            updateQuestion(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.content = this.delHtmlTag(this.form.content);
            let option = null;
            this.form.option = `${this.form.answerA ? "A:" + this.form.answerA + ";" : ""
              }${this.form.answerB ? "B:" + this.form.answerB + ";" : ""}${this.form.answerC ? "C:" + this.form.answerC + ";" : ""
              }${this.form.answerD ? "D:" + this.form.answerD + ";" : ""}`;
            console.log(option);
            addQuestion(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除题目管理编号为"' + ids + '"的数据项？')
        .then(function () {
          return delQuestion(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "question/question/export",
        {
          ...this.queryParams,
        },
        `question_${new Date().getTime()}.xlsx`
      );
    },
    // 导入按钮操作
    openImportTable() {
      this.dialogVisibleImport = true;
    },
    handleCloseImport() {
      this.dialogVisibleImport = false;
    },
    handleImportQuestion() {
      this.getList();
      this.dialogVisibleImport = false;
    },
  },
};
</script>
