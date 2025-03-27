<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入标题" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="考卷描述" prop="description">
        <el-input v-model="queryParams.description" placeholder="请输入考卷描述" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="创建时间" prop="creatTime">
        <el-date-picker clearable v-model="queryParams.creatTime" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['ExamPaper:paper:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['ExamPaper:paper:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['ExamPaper:paper:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['ExamPaper:paper:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="paperList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="考卷描述" align="center" prop="description" />
      <el-table-column label="创建时间" align="center" prop="creatTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.creatTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="题目操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['ExamPaper:paper:edit']">修改基本属性</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['ExamPaper:paper:remove']">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column label="试卷操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdateDetail(scope.row)"
            v-hasPermi="['ExamPaper:paper:edit']">修改题目内容
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="openQuestionListDialog(scope.row)"
            v-hasPermi="['ExamPaper:paperDetail:insert']" :disabled="scope.row.haveDetail">随机生成题目内容
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改考卷管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="考卷描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入考卷描述" />
        </el-form-item>
        <el-form-item label="考卷时长" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入考卷时长" />
        </el-form-item>
        <el-form-item label="创建时间" prop="creatTime">
          <el-date-picker clearable v-model="form.creatTime" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--随机生成题目管理对话框-->
    <el-dialog :title="'随机生成题目配置'" :visible.sync="openDetail" width="800px" append-to-body>
      <el-form ref="detailForm" :model="detailForm" :rules="detailRules" label-width="80px">
        <!-- 动态题目类型和数量 -->
        <div v-for="(item, index) in detailForm.typeConfigs" :key="index" style="margin-bottom: 10px;">
          <el-row :gutter="10">
            <el-col :span="14">
              <el-form-item :label="'类型' + (index + 1)" :prop="'typeConfigs.' + index + '.type'">
                <el-select v-model="item.type" placeholder="请选择题目类型" style="width: 100%;">
                  <el-option v-for="type in getAvailableTypes(index)" :key="type.type" :label="type.type"
                    :value="type.type"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item :label="'数量'" :prop="'typeConfigs.' + index + '.count'" style="width: 100%;">
                <el-input v-model.number="item.count" type="number" placeholder="数量" :min="1"
                  style="width: 100%;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="2" style="display: flex; align-items: center;">
              <el-button type="danger" icon="el-icon-delete" circle size="mini"
                @click="removeTypeConfig(index)"></el-button>
            </el-col>
          </el-row>
        </div>

        <div style="text-align: center; margin-bottom: 20px;">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="addTypeConfig">添加题型</el-button>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitDetailForm">确 定</el-button>
        <el-button @click="cancelDetail">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 题目列表对话框 -->
    <el-dialog :title="'已绑定的题目列表'" :visible.sync="openQuestionList" width="1200px" append-to-body>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="题目内容">
          <el-input v-model="questionParams.content" placeholder="搜索题目内容" clearable @input="filterDetails" />
        </el-form-item>
        <el-form-item label="题目类型">
          <el-autocomplete v-model="questionParams.type" :fetch-suggestions="querySearchType" placeholder="请输入题目类型"
            @select="handleSelectType" clearable>
            <template slot-scope="{ item }">
              <div class="value">{{ item.value }}</div>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="题目分数" style="width: 30%;">
          <el-input v-model="questionParams.score" placeholder="搜索题目分数" clearable @input="filterDetails" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="filterDetails">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetSearchQueries">重置</el-button>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="handleRemainQuestionList" style="float: right;">添加题目</el-button>
      <el-table :data="questions" style="width: 100%">
        <el-table-column label="序号" align="center">
          <template slot-scope="scope">
            {{ (questionParams.pageNum - 1) * questionParams.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column label="题目内容" align="center" prop="content" />
        <el-table-column label="类型" align="center" prop="type" />
        <el-table-column label="分数" align="center" prop="score" />
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="handleDeleteDetail(scope.row)" style="color: red;"
              v-hasPermi="['ExamPaperDetail:paper:remove']">将该题移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination v-show="totalQuestions > 0" :total="totalQuestions" :page.sync="questionParams.pageNum"
        :limit.sync="questionParams.pageSize" @pagination="loadDetails" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeQuestionList">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 未绑定的题目列表对话框 -->
    <el-dialog :title="'未绑定的题目列表'" :visible.sync="openUnboundQuestionList" width="1200px" append-to-body>
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="题目内容">
          <el-input v-model="unboundQuestionParams.content" placeholder="搜索题目内容" clearable
            @input="filterUnboundDetails" />
        </el-form-item>
        <el-form-item label="题目类型">
          <el-autocomplete v-model="unboundQuestionParams.type" :fetch-suggestions="querySearchType"
            placeholder="请输入题目类型" @select="handleSelectType" clearable>
            <template slot-scope="{ item }">
              <div class="value">{{ item.value }}</div>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="题目分数" style="width: 30%;">
          <el-input v-model="unboundQuestionParams.score" placeholder="搜索题目分数" clearable
            @input="filterUnboundDetails" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="filterUnboundDetails">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetUnboundSearchQueries">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table :data="unboundQuestions" style="width: 100%">
        <el-table-column label="序号" align="center">
          <template slot-scope="scope">
            {{ (unboundQuestionParams.pageNum - 1) * unboundQuestionParams.pageSize + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column label="题目内容" align="center" prop="content" />
        <el-table-column label="类型" align="center" prop="type" />
        <el-table-column label="分数" align="center" prop="score" />
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="addQuestionToPaper(scope.row)"
              v-hasPermi="['ExamPaperDetail:paper:add']">添加到试卷
            </el-button>
          </template>

        </el-table-column>
      </el-table>
      <pagination v-show="totalUnboundQuestions > 0" :total="totalUnboundQuestions"
        :page.sync="unboundQuestionParams.pageNum" :limit.sync="unboundQuestionParams.pageSize"
        @pagination="loadUnboundQuestions" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeUnboundQuestionList">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPaper, getPaper, delPaper, addPaper, updatePaper, randomGenerate, getDetailList, getRemainQuestionList } from "@/api/ExamPaper/paper";
import { getType, getTypeAndCount, listQuestion } from "@/api/question/question";
import { listPaperDetail, getPaperDetail, addPaperDetail, updatePaperDetail, delPaperDetail, delPaperDetailByDetail } from "@/api/exampaperdetail/exampaperdetail";
export default {
  name: "Paper",
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
      // 考卷管理表格数据
      paperList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        description: null,
        creatTime: null,
      },
      // 表单参数
      form: {
        id: null,
        title: null,
        description: null,
        creatTime: null,
        typeConfigs: []
      },
      // 绑定关系对象数据
      paperDetail: {
        id: null,
        questionId: null,
        examPaperId: null,
        score: null,
      },
      // 表单校验
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入考卷描述', trigger: 'blur' }
        ],
        duration: [
          { required: true, message: '请输入考卷时长', trigger: 'blur' }
        ],
        creatTime: [
          { required: true, message: '请选择创建时间', trigger: 'change' }
        ]
      },
      types: [],
      // 是否显示随机生成弹出层
      openDetail: false,
      // 随机生成表单参数
      detailForm: {
        examPaperId: null,
        typeConfigs: []
      },
      // 随机生成表单校验
      detailRules: {
        'typeConfigs.*.type': [
          { required: true, message: '请选择题目类型', trigger: 'change' }
        ],
        'typeConfigs.*.count': [
          { required: true, message: '请输入题目数量', trigger: 'blur' },
          { type: 'number', min: 1, message: '数量必须大于0', trigger: 'blur' }
        ]
      },
      openQuestionList: false,
      openUnboundQuestionList: false,
      questionParams: {
        pageNum: 1,
        pageSize: 10,
        content: '',
        type: '',
        score: '',
        examPaperId: null
      },
      unboundQuestionParams: {
        pageNum: 1,
        pageSize: 10,
        content: '',
        type: '',
        score: '',
        examPaperId: null
      },
      questionTypes: [],
      totalQuestions: 0,
      totalUnboundQuestions: 0,
      questions: [],
      unboundQuestions: [],
      filteredQuestions: [],
    };
  },
  created() {
    this.getList();
    this.loadQuestions();
  },
  methods: {
    /** 查询考卷管理列表 */
    getList() {
      this.loading = true;
      listPaper(this.queryParams).then(response => {
        this.paperList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        title: null,
        description: null,
        creatTime: null,
        duration: 120,
        typeConfigs: []
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加考卷管理";
    },
    /**随机生成题目打开弹窗 */
    openQuestionListDialog(row) {
      this.resetDetail();
      this.detailForm.examPaperId = row.id;

      getTypeAndCount().then(res => {
        this.types = res.data.map(item => ({
          type: item.type,
          count: item.count
        }));
        this.initializeTypeConfigs();
        this.openDetail = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPaper(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改考卷管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePaper(this.form).then(response => {
              if (response.code == 200) {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.$modal.msgError(response.msg);
              }
            });
          } else {
            addPaper(this.form).then(response => {
              if (response.code == 200) {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.$modal.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除考卷管理编号为"' + ids + '"的数据项？').then(function () {
        return delPaper(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ExamPaper/paper/export', {
        ...this.queryParams
      }, `paper_${new Date().getTime()}.xlsx`)
    },
    // 取消随机生成按钮
    cancelDetail() {
      this.openDetail = false;
      this.resetDetail();
    },
    // 随机生成表单重置
    resetDetail() {
      this.detailForm = {
        examPaperId: null,
        typeConfigs: []
      };
      this.resetForm("detailForm");
    },
    // 获取可用的类型选项
    getAvailableTypes(index) {
      const selectedTypes = this.detailForm.typeConfigs.map(config => config.type);
      return this.types.filter(type => !selectedTypes.includes(type.type) || selectedTypes.indexOf(type.type) === index);
    },
    // 添加新的类型配置
    addTypeConfig() {
      this.detailForm.typeConfigs.push({
        type: '',
        count: 1
      });
    },
    // 移除类型配置
    removeTypeConfig(index) {
      this.detailForm.typeConfigs.splice(index, 1);
    },
    // 提交随机生成表单
    submitDetailForm() {
      this.$refs["detailForm"].validate(valid => {
        if (valid) {
          // 验证每个类型的数量和类型选择
          for (let config of this.detailForm.typeConfigs) {
            if (!config.type) {
              this.$modal.msgError("请为每个配置选择题目类型");
              return;
            }

            const dbType = this.types.find(type => type.type === config.type);
            if (dbType && config.count > dbType.count) {
              this.$modal.msgError(`类型 ${config.type} 的数量不能大于数据库中的数量 ${dbType.count}`);
              return;
            }
          }

          // 如果所有验证通过，调用后端API进行随机生成题目
          randomGenerate(this.detailForm).then(response => {
            if (response.code == 200) {
              this.$modal.msgSuccess("配置成功");
              this.openDetail = false;
              this.getList();
            } else {
              this.$modal.msgError(response.msg);
            }
          });
        }
      });
    },
    // 加载题目列表
    loadQuestions() {
      listQuestion(this.questionParams).then(response => {
        this.questions = response.rows;
        this.totalQuestions = response.total;
      });
    },
    // 加载试卷绑定的题目列表
    loadDetails() {
      getDetailList(this.questionParams).then(response => {
        this.questions = response.rows;
        this.totalQuestions = response.total;
      });
    },
    // 打开题目列表
    handleUpdateDetail(row) {
      this.openQuestionList = true;
      this.loadQuestionTypes();
      this.questionParams.examPaperId = row.id;
      getDetailList(this.questionParams).then(response => {
        this.questions = response.rows;
        this.totalQuestions = response.total;
      });
    },
    // 关闭题目列表
    closeQuestionList() {
      this.openQuestionList = false;
    },
    // 过滤题目
    filterDetails() {
      this.questionParams.pageNum = 1;
      this.loadDetails();
    },
    // 过滤未绑定题目
    filterUnboundDetails() {
      this.unboundQuestionParams.pageNum = 1;
      this.loadUnboundQuestions();
    },
    // 删除绑定关系
    handleDeleteDetail(row) {
      this.paperDetail.questionId = row.id;
      this.paperDetail.examPaperId = this.questionParams.examPaperId;
      this.paperDetail.score = row.score;
      console.log(this.paperDetail);
      this.$modal.confirm('是否确认从该试卷中移除该题目?').then(() => {
        return delPaperDetailByDetail(this.paperDetail);
      }).then(() => {
        this.loadDetails();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    // 添加题目到试卷
    addQuestionToPaper(row) {
      this.paperDetail.questionId = row.id;
      this.paperDetail.examPaperId = this.questionParams.examPaperId;
      this.paperDetail.score = row.score;
      console.log(this.paperDetail);
      this.$modal.confirm('是否确认将该题目添加到该试卷?').then(() => {
        return addPaperDetail(this.paperDetail);
      }).then(() => {
        this.loadDetails();
        this.loadUnboundQuestions();
        this.openUnboundQuestionList = false;
        this.$modal.msgSuccess("添加成功");
      }).catch(() => { });
    },
    resetSearchQueries() {
      this.questionParams.content = '';
      this.questionParams.type = '';
      this.questionParams.score = '';
      this.filterDetails();
    },
    resetUnboundSearchQueries() {
      this.unboundQuestionParams.content = '';
      this.unboundQuestionParams.type = '';
      this.unboundQuestionParams.score = '';
      this.filterUnboundDetails();
    },
    querySearchType(queryString, cb) {
      // 模拟从服务器获取数据的异步操作
      const types = this.questionTypes;
      const results = queryString ? types.filter(type => type.label.toLowerCase().includes(queryString.toLowerCase())) : types;
      // 调用回调函数返回建议列表的数据
      cb(results.map(type => ({ value: type.label })));
    },
    handleSelectType(item) {
      // 当用户选择一个选项时触发
      console.log('Selected type:', item);
    },
    loadQuestionTypes() {
      getType().then(response => {
        if (response.code === 200) {
          // 假设后端返回的数据是一个包含题目类型的数组
          this.questionTypes = response.data.map((type, index) => ({
            value: index, // 使用数组索引作为value
            label: type // 使用类型字符串作为label
          }));
          console.log(this.questionTypes);
        } else {
          console.error("Failed to load question types:", response.msg);
        }
      }).catch(error => {
        console.error("Error fetching question types:", error);
      });
    },
    handleRemainQuestionList() {
      this.openUnboundQuestionList = true;
      this.loadUnboundQuestions();
    },
    loadUnboundQuestions() {
      this.unboundQuestionParams.examPaperId = this.questionParams.examPaperId;
      console.log(this.unboundQuestionParams);
      getRemainQuestionList(this.unboundQuestionParams).then(response => {
        this.unboundQuestions = response.rows;
        this.totalUnboundQuestions = response.total;
      });
    },
    closeUnboundQuestionList() {
      this.openUnboundQuestionList = false;
    },
    // 当类型数据更新时调用此方法
    initializeTypeConfigs() {
      const typeCount = this.types.length;
      const baseCount = Math.floor(100 / typeCount);
      const remainder = 100 % typeCount;

      this.detailForm.typeConfigs = this.types.map((type, index) => ({
        type: type.type,
        count: baseCount + (index < remainder ? 1 : 0)  // 前remainder个类型额外加1
      }));
    },

    // 假设你有一个方法来加载或更新类型数据
    loadTypes() {
      // 加载类型数据的逻辑...
      // 假设加载完成后更新this.types
      this.initializeTypeConfigs();  // 加载完成后初始化类型配置
    },
  },
};
</script>
