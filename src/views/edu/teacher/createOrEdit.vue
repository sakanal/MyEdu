<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="教师名称">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="教师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" :min="0" />
      </el-form-item>
      <el-form-item label="教师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级教师" />
          <el-option :value="2" label="首席教师" />
        </el-select>
      </el-form-item>
      <el-form-item label="教师资历">
        <el-input v-model="teacher.career" />
      </el-form-item>
      <el-form-item label="教师简介">
        <el-input v-model="teacher.intro" type="textarea" :rows="10" />
      </el-form-item>

      <el-form-item label="教师头像">
        <pan-thumb :image="String(teacher.avatar)" />
        <el-button type="primary" icon="el-icon-upload" @click="openImageUpload">更换头像</el-button>
        <image-cropper
          v-show="imageCropperShow"
          :key="imageCropperKey"
          :width="300"
          :height="300"
          :url="'http://localhost:8002/oss/file/avatar/'+teacher.name"
          field="multipartFile"
          @close="closeImageUpload"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
import teacher from '@/api/teacher/teacher'
import PanThumb from '@/components/PanThumb'
import ImageCropper from '@/components/ImageCropper'

export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      saveBtnDisabled: false,
      isSave: true,
      teacher: {
        sort: 0,
        level: 1
      },
      imageCropperShow: false, // 上传弹框组件是否显示
      imageCropperKey: 0, // 上传组件key值
      BASE_API: process.env.VUE_APP_BASE_API // 获取dev.env.js里面地址
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      this.getTeacherById(this.$route.params.id)
      this.isSave = false
    }
  },
  methods: {
    saveOrUpdate() {
      if (this.isSave) {
        this.createTeacher()
      } else {
        this.updateTeacher()
      }
    },
    createTeacher() {
      teacher.createTeacher(this.teacher)
        .then(response => {
          this.$message.success('添加成功')
          this.$router.push({ path: '/teacher/table' })
          // console.log(response)
        })
        .catch(error => {
          console.log(error)
        })
    },
    getTeacherById(id) {
      teacher.getTeacherById(id)
        .then(response => {
          this.teacher = response.data
        })
        .catch(response => {
          console.log(response)
        })
    },
    updateTeacher() {
      teacher.updateTeacher(this.teacher)
        .then(response => {
          this.$message.success('修改成功')
          this.$router.push({ path: '/teacher/table' })
        })
        .catch(error => {
          console.log(error)
        })
    },
    openImageUpload() {
      if (this.teacher.name === '' || this.teacher.name === null || this.teacher.name === undefined) {
        this.$message.info('请先完成教师基本信息的输入')
      } else {
        this.imageCropperShow = true
      }
    },
    closeImageUpload() {
      this.imageCropperShow = false
      this.imageCropperKey = this.imageCropperKey + 1
    },
    cropSuccess(data) {
      console.log(data)
      this.teacher.avatar = data
    }
  }
}
</script>

<style scoped>

</style>
