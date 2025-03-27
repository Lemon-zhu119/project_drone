<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="轮播图名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入轮播图名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="轮播图地址" prop="url">
        <el-input v-model="queryParams.url" placeholder="请输入轮播图地址" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="轮播图页数" prop="page">
        <el-input v-model="queryParams.page" placeholder="请输入轮播图页数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['swiper:picture:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['swiper:picture:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['swiper:picture:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['swiper:picture:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pictureList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="轮播图名称" align="center" prop="name" />
      <el-table-column label="轮播图地址" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.url" alt="轮播图" class="table-image" />
        </template>
      </el-table-column>
      <el-table-column label="轮播图页码" align="center" prop="page" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['swiper:picture:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['swiper:picture:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改轮播图管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="轮播图名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入轮播图名称" />
        </el-form-item>
        <el-form-item label="轮播图地址" prop="url">
          <el-upload class="avatar-uploader" :action="uploadAction" :headers="headers" :on-success="handleSuccess"
            :before-upload="beforeUpload" :on-remove="handleRemove" :file-list="fileList" list-type="picture">
            <img v-if="form.url" :src="form.url" class="avatar">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="轮播图页码" prop="name">
          <el-input v-model="form.page" placeholder="请输入页码" />
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
import { listPicture, getPicture, delPicture, addPicture, updatePicture, upload } from "@/api/swiper/picture";
import http from "highlight.js/lib/languages/http";

export default {
  name: "Picture",
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
      // 轮播图管理表格数据
      pictureList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      uploadAction: 'http://116.62.160.24:8080/swiper/picture/upload',
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        url: null,
        page: null,
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
    /** 查询轮播图管理列表 */
    getList() {
      this.loading = true;
      listPicture(this.queryParams).then(response => {
        this.pictureList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleSuccess(response, file) {
      console.log(response)

      // 上传成功的回调
      this.form.url = response.data; // 使用URL.createObjectURL来获取文件的URL

      // 如果你的后端返回了文件的URL，你也可以直接设置 this.form.url = response.url;
    },
    handleRemove(file, fileList) {
      // 移除文件的回调
      this.form.url = ''; // 清除已存储的图片URL
    },
    upload() {
      upload
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
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        url: null,
        page: null,
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
      this.title = "添加轮播图管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPicture(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改轮播图管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePicture(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPicture(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除轮播图管理编号为"' + ids + '"的数据项？').then(function () {
        return delPicture(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('swiper/picture/export', {
        ...this.queryParams
      }, `picture_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
