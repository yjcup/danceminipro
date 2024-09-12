<template>
    <div class="app-container">
        <el-form :model="form" ref="form">

 <el-form-item label="上传视频" prop="courseUrl" >
   <el-upload class="avatar-uploader el-upload--text" :headers="videoUpload.headers" :action="videoUpload.url" :show-file-list="false"
 accept=".mp4"  :on-success="handleVideoSuccess"  :before-upload="beforeUploadVideo" :on-progress="uploadVideoProcess">
 
   <!--视频区域，:src里面存放视频上传成功后的存储地址-->
   <video v-if="this.showVideoPath !='' && !videoFlag"  :src="videoUpload.url2 + this.showVideoPath"  class="avatar video-avatar" controls="controls">
      您的浏览器不支持视频播放
   </video> 
 
   <!-- 变量showVideoPath如果不存在，就不显示，存在就显示视频 -->
   <i v-else-if="this.showVideoPath =='' && !videoFlag" class="el-icon-plus avatar-uploader-icon">
   </i>  
   <!--上传进度条区域-->
   <el-progress :stroke-width="10" class="progressType" v-if="videoFlag == true"
     type="circle" :percentage="videoUploadPercent" style="margin-top:30px;">
   </el-progress>
 
   <!--此为视频点击上传按钮 isShowUploadVideo代表按钮显示与否-->
   <el-button class="video-btn" slot="trigger" size="small" v-if="isShowUploadVideo" type="primary">点击上传视频</el-button> 
   </el-upload>
</el-form-item>
<!--form.courseUrl 代表成功上传返回的视频路径赋值给表单的这个属性，随后通过方法提交保存在对应的表中-->        
<el-form-item  style="position: relative ;">
   <el-input type="hidden" v-model="form.courseUrl" readonly class="noAlert" placeholder="" />
</el-form-item>
        </el-form>
    </div>
  </template>
  
  <script>
//   import { listDetail, getDetail, delDetail, addDetail, updateDetail } from "@/api/dance/detail";
  import { getToken } from "@/utils/auth";

  export default {
    name: "Detail",
   
data() {
  return {
    //拿到当前环境的请求前缀
    form:{
        courseUrl:null
    },

    videoUpload:{
      // 设置上传的请求头部
      headers: { Authorization: "Bearer " + getToken() },
      // 上传的地址
      url: process.env.VUE_APP_BASE_API + "/common/uploadVideo",
      url2: process.env.VUE_APP_BASE_API,
    },
    showVideoPath: "",
    uploadUrl:"",//你要上传视频到你后台的地址
    videoFlag:false , //是否显示进度条
    videoUploadPercent:"", //进度条的进度，
    isShowUploadVideo:false //显示上传按钮
    
  };},
    created() {
 
    },
    methods: {
  //上传前回调
  beforeUploadVideo (file) {
    const isLt1024M = (file.size / 1024 / 1024) < 1024;
    this.form.videoSize = file.size / 1024 / 1024;
    //判断是不是MP4格式视频
    if (['video/mp4'].indexOf(file.type) != 0) { 
        this.$message.error('请上传正确的视频格式');
        return false;
    }
    //单个视频大小限制在1024M以内
    if (!isLt1024M ) {
        this.$message.error('上传视频大小不能超过1024MB哦!');
        return false;
    }
    this.isShowUploadVideo = false;
  },
 
  //进度条
  uploadVideoProcess (event, file, fileList) {
      this.videoFlag = true;
      this.videoUploadPercent = file.percentage.toFixed(0) * 1;
  },
 
  //上传成功回调
  handleVideoSuccess (res, file) {
      this.isShowUploadVideo = true;
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      if(res !=""){
          this.showVideoPath = res;
          this.form.courseUrl = res;
      } else {
          this.$message.error('视频上传失败，请重新上传！');
      }
   }
}
  };
  </script>
  