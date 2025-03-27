<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="省份" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入省份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位名称" prop="companyname">
        <el-input
          v-model="queryParams.companyname"
          placeholder="请输入单位名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="统一社会信用代码" prop="creditcode">
        <el-input
          v-model="queryParams.creditcode"
          placeholder="请输入统一社会信用代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法人名称" prop="legalperson">
        <el-input
          v-model="queryParams.legalperson"
          placeholder="请输入法人名称"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['institution:institutionmore:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['institution:institutionmore:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['institution:institutionmore:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['institution:institutionmore:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="institutionmoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="省份" align="center" prop="province" />
      <el-table-column label="单位名称" align="center" prop="companyname" />
      <el-table-column label="基地" align="center" prop="base" />
      <el-table-column label="办公室地址" align="center" prop="office" />
      <el-table-column label="统一社会信用代码" align="center" prop="creditcode" />
      <el-table-column label="法人名称" align="center" prop="legalperson" />
      <el-table-column label=" 项目负责人" align="center" prop="responsibleperson" />
      <el-table-column label="项目负责人联系电话" align="center" prop="phone" />
      <el-table-column label=" 项目负责人" align="center" prop="responsibleperson" />
      <el-table-column label="项目负责人联系电话" align="center" prop="phone" />
      <el-table-column label=" 项目负责人" align="center" prop="responsibleperson" />
      <el-table-column label="项目负责人联系电话" align="center" prop="phone" />
      <el-table-column label=" 经度" align="center" prop="latitude" />
      <el-table-column label="纬度" align="center" prop="longitude" />
      <el-table-column label="介绍" align="center" prop="introduce" />
      <el-table-column label="图片" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.url" alt="轮播图" class="table-image" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评分" align="center" prop="score" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['institution:institutionmore:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['institution:institutionmore:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改机构信息详情管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图片" prop="url">
          <el-upload class="avatar-uploader" :action="uploadAction" :headers="headers" :on-success="handleSuccess"
            :before-upload="beforeUpload" :on-remove="handleRemove" :file-list="fileList" list-type="picture">
            <img v-if="form.url" :src="form.url" class="avatar">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="省份" prop="province">
          <el-input v-model="form.province" placeholder="请输入省份" />
        </el-form-item>
        <el-form-item label="单位名称" prop="companyname">
          <el-input v-model="form.companyname" placeholder="请输入单位名称" />
        </el-form-item>
        <el-form-item label="基地" prop="base">
          <el-input v-model="form.base" placeholder="请输入基地" />
        </el-form-item>
        <el-form-item label="办公室地址" prop="office">
          <el-input v-model="form.office" placeholder="请输入办公室地址" />
        </el-form-item>
        <el-form-item label="统一社会信用代码" prop="creditcode">
          <el-input v-model="form.creditcode" placeholder="请输入统一社会信用代码" />
        </el-form-item>
        <el-form-item label="法人名称" prop="legalperson">
          <el-input v-model="form.legalperson" placeholder="请输入法人名称" />
        </el-form-item>
        <el-form-item label=" 项目负责人" prop="responsibleperson">
          <el-input v-model="form.responsibleperson" placeholder="请输入 项目负责人" />
        </el-form-item>
        <el-form-item label="项目负责人联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入项目负责人联系电话" />
        </el-form-item>
        <el-form-item label="纬度" prop="phone">
          <el-input v-model="form.latitude" placeholder="请输入项目负责人联系电话" />
        </el-form-item>
        <el-form-item label="经度" prop="phone">
          <el-input v-model="form.longitude" placeholder="请输入项目负责人联系电话" />
        </el-form-item>
        <el-form-item label="机构介绍" prop="phone">
          <el-input v-model="form.introduce" placeholder="请输入项目负责人联系电话" />
        </el-form-item>
        <el-form-item label="评分" prop="score">
          <el-input v-model="form.score" placeholder="请输入评分" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'
import { listInstitutionmore, getInstitutionmore, delInstitutionmore, addInstitutionmore, updateInstitutionmore } from "@/api/institution/institutionmore";

export default {
  name: "Institutionmore",
  data() {
    return {
      uploadAction:'http://116.62.160.24:8080/swiper/picture/upload',
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
      // 机构信息详情管理表格数据
      institutionmoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        province: null,
        companyname: null,
        creditcode: null,
        legalperson: null,
      },
      // 表单参数
      form: {},
      fileList: [],
      // 表单校验
      rules: {
      }
    };
  },
  computed: {
    headers() {
      console.log(getToken())
      return { "Authorization": 'Bearer ' + getToken() }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询机构信息详情管理列表 */
    getList() {
      this.loading = true;
      listInstitutionmore(this.queryParams).then(response => {
        console.log(response)
        this.institutionmoreList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    beforeUpload(file) {
      // 上传前的回调，用于文件类型和大小的校验
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isLt500K = file.size / 1024 / 1024 < 0.5;

      if (!isJPG && !isPNG) {
        this.$message.error('上传图片只能是 JPG/PNG 格式!');
      }
      if (!isLt500K) {
        this.$message.error('上传图片大小不能超过 500KB!');
      }
      return (isJPG || isPNG) && isLt500K;
    },
    handleRemove(file, fileList) {
      // 移除文件的回调
      this.form.url = ''; // 清除已存储的图片URL
    },
    handleSuccess(response, file) {
      console.log(response); // 打印整个response对象，检查其结构
    if (response.data) {
      this.form.url=`http://116.62.160.24:8080/`+response.data // 使用后端返回的URL
        console.log(response.data);
    } else {
        console.error("响应中没有data字段", response);
    }
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
        province: null,
        companyname: null,
        base: null,
        office: null,
        creditcode: null,
        legalperson: null,
        responsibleperson: null,
        phone: null,
        createTime: null,
        updateTime: null,
        score: null,
        url:null,
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加机构信息详情管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInstitutionmore(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改机构信息详情管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInstitutionmore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInstitutionmore(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除机构信息详情管理编号为"' + ids + '"的数据项？').then(function() {
        return delInstitutionmore(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('institution/institutionmore/export', {
        ...this.queryParams
      }, `institutionmore_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
