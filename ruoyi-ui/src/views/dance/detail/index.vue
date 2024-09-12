<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="课程标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入课程标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="detailList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="课程id" align="center" prop="courseId" />
      <el-table-column label="课程标题" align="center" prop="title" />
      <!-- <el-table-column label="视频链接" align="center" prop="url" /> -->
      <el-table-column
        prop="url"
        label="课程视频"
        align="center"
        show-overflow-tooltip
      >
        <template slot-scope="scope">
          <div class="video">
            <!-- 页面table显示的video标签 -->
            <video
              :src="videoUpload.url2 + scope.row.url"
              width="100"
              preload
            ></video>
            <i
              style="font-size: 25px"
              class="el-icon-video-play playIcon"
              @click="handleCheck(scope.row)"
            ></i>
          </div>
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['dance:detail:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['dance:detail:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      title="播放热点视频"
      width="72%"
      append-to-body
      top="20px"
      :visible.sync="dialogVisible"
    >
      <!--                   @closed="closeDialog"
 -->
      <!-- <h3>{{ scope.row.title }}</h3> -->
      <video
        width="100%"
        autoplay=""
        :src="videoUpload.url2 + playvideo"
        :poster="videoUpload.url2 + playvideo"
        controls="controls"
        id="video"
        preload
      ></video>
    </el-dialog>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改课程详情表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程id" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程id" />
        </el-form-item>
        <el-form-item label="课程标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入课程标题" />
        </el-form-item>
        <el-form-item label="上传视频" prop="url">
          <el-upload
            class="avatar-uploader el-upload--text"
            :headers="videoUpload.headers"
            :action="videoUpload.url"
            :show-file-list="false"
            accept=".mp4"
            :on-success="handleVideoSuccess"
            :before-upload="beforeUploadVideo"
            :on-progress="uploadVideoProcess"
          >
            <!--视频区域，:src里面存放视频上传成功后的存储地址-->
            <video
              style="width: 230px"
              v-if="this.showVideoPath != '' && !videoFlag"
              :src="videoUpload.url2 + this.showVideoPath"
              class="avatar video-avatar"
              controls="controls"
            >
              您的浏览器不支持视频播放
            </video>

            <!-- 变量showVideoPath如果不存在，就不显示，存在就显示视频 -->
            <i
              v-else-if="this.showVideoPath == '' && !videoFlag"
              class="el-icon-plus avatar-uploader-icon"
            >
            </i>
            <!--上传进度条区域-->
            <el-progress
              :stroke-width="10"
              class="progressType"
              v-if="videoFlag == true"
              type="circle"
              :percentage="videoUploadPercent"
              style="margin-top: 30px"
            >
            </el-progress>

            <!--此为视频点击上传按钮 isShowUploadVideo代表按钮显示与否-->
            <el-button
              class="video-btn"
              slot="trigger"
              size="small"
              v-if="isShowUploadVideo"
              type="primary"
              >点击上传视频</el-button
            >
          </el-upload>
        </el-form-item>
        <!--form.courseUrl 代表成功上传返回的视频路径赋值给表单的这个属性，随后通过方法提交保存在对应的表中-->
        <el-form-item style="position: relative">
          <el-input
            type="hidden"
            v-model="form.url"
            readonly
            class="noAlert"
            placeholder=""
          />
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
import {
  listDetail,
  getDetail,
  delDetail,
  addDetail,
  updateDetail,
} from "@/api/dance/detail";
import { getToken } from "@/utils/auth";

export default {
  name: "Detail",
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
      // 课程详情表表格数据
      detailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
      },
      courseId: 1,
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      // 视频上传
      //拿到当前环境的请求前缀
      form: {
        courseUrl: null,
      },

      videoUpload: {
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/common/uploadVideo",
        url2: process.env.VUE_APP_BASE_API,
      },
      showVideoPath: "",
      uploadUrl: "", //你要上传视频到你后台的地址
      videoFlag: false, //是否显示进度条
      videoUploadPercent: "", //进度条的进度，
      isShowUploadVideo: false, //显示上传按钮
      // 表格视频
      dialogVisible: false, // 视频播放弹窗
      playvideo: null, // 存储用户点击的视频播放链接
      playvideoName: null, // 存储正在播放视频的名称
    };
  },
  created() {
    const courseId = this.$route.params && this.$route.params.id;
    this.courseId = courseId;
    this.getList();
    console.log(this.videoUpload.url2);
    const videos = document.querySelectorAll("video");
    videos.forEach((video) => {
      if (video !== this.$refs.dialogVideo) {
        video.pause(); // 暂停其他视频
      }
    });
  },
  methods: {
    /** 查询课程详情表列表 */

    getlistById(courseId) {
      this.loading = true;
      this.queryParams.courseId = courseId;
      console.log(this.queryParams);
      listDetail(this.queryParams).then((response) => {
        this.detailList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    handleCheck(row) {
      this.dialogVisible = false;

      console.log(row);
      this.playvideo = row.url; // 存储用户点击的视频播放链接
      this.playvideoName = row.title; // 存储用户点击的视频播放链接
      this.dialogVisible = true;
    },

    getList() {
      this.getlistById(this.courseId);
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
        courseId: null,
        title: null,
        url: null,
        createTime: null,
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
      this.title = "添加课程详情表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getDetail(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程详情表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateDetail(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDetail(this.form).then((response) => {
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
        .confirm('是否确认删除课程详情表编号为"' + ids + '"的数据项？')
        .then(function () {
          return delDetail(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "dance/detail/export",
        {
          ...this.queryParams,
        },
        `detail_${new Date().getTime()}.xlsx`
      );
    },
    // 视频上传
    //上传前回调
    beforeUploadVideo(file) {
      const isLt1024M = file.size / 1024 / 1024 < 1024;
      this.form.videoSize = file.size / 1024 / 1024;
      //判断是不是MP4格式视频
      if (["video/mp4"].indexOf(file.type) != 0) {
        this.$message.error("请上传正确的视频格式");
        return false;
      }
      //单个视频大小限制在1024M以内
      if (!isLt1024M) {
        this.$message.error("上传视频大小不能超过1024MB哦!");
        return false;
      }
      this.isShowUploadVideo = false;
    },

    //进度条
    uploadVideoProcess(event, file, fileList) {
      this.videoFlag = true;
      this.videoUploadPercent = file.percentage.toFixed(0) * 1;
    },

    //上传成功回调
    handleVideoSuccess(res, file) {
      this.isShowUploadVideo = true;
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      if (res != "") {
        this.showVideoPath = res;
        this.form.url = res;
      } else {
        this.$message.error("视频上传失败，请重新上传！");
      }
    },
  },
};
</script>
