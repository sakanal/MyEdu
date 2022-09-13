<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="1"
      process-status="wait"
      align-center
      style="margin-bottom: 40px;"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="最终发布" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>

      <!--课程分类-->
      <el-form-item label="课程分类">
        <el-select v-model="courseInfo.subjectParentId" placeholder="一级分类" @change="subjectLevelOneChanged">
          <el-option
            v-for="subject in subjectOneLists"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option
            v-for="subject in subjectTwoLists"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>

      <!--课程讲师-->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number
          v-model="courseInfo.lessonNum"
          :min="0"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>

      <!-- 课程简介 TODO -->
      <el-form-item label="课程简介">
        <!--        <el-input-->
        <!--          v-model="courseInfo.description"-->
        <!--          placeholder=""-->
        <!--        />-->
        <tinymce v-model="courseInfo.description" :height="300" />
      </el-form-item>

      <!-- 课程封面 TODO -->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API + '/oss/file/course'"
          name="multipartFile"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover">
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number
          v-model="courseInfo.price"
          :min="0"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />元</el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import course from '@/api/course/course.js'
import subject from '@/api/subject/subject.js'
import tinymce from '@/components/Tinymce'
export default {
  components: { tinymce },
  data() {
    return {
      saveBtnDisabled: false,
      courseInfo:
        {
          teacherId: '',
          subjectId: '',
          subjectParentId: '',
          title: '',
          price: 0,
          lessonNum: 0,
          cover: 'https://guli-edu-sakanal.oss-cn-hangzhou.aliyuncs.com/avatar/string/2022/09/13/ef45e4e3e2764a77a3925aa4ce1f5e31multipartFile.png',
          description: ''
        },
      teacherList: [],
      subjectOneLists: [], // 封装所有一级分类数据
      subjectTwoLists: [], // 封装二级分类数据
      BASE_API: process.env.VUE_APP_OSS_BASE_API // 接口API地址
    }
  },
  created() {
    // 初始化所有讲师
    this.getAllTeacher()
    // 初始化一级分类
    this.getSubjectList()
  },
  methods: {
    saveOrUpdate() {
      course.addCourseInfo(this.courseInfo)
        .then(response => {
          this.$message.success('添加课程信息成功')
          // 跳转到第二步，并带着这个课程生成的id
          this.$router.push({ path: '/course/chapter/' + response.data })
        })
        .catch(error => {
          console.log(error)
        })
    },
    getAllTeacher() {
      course.getAllTeacher()
        .then(response => {
          this.teacherList = response.data
        })
        .catch(error => {
          console.log(error)
        })
    },
    getSubjectList() {
      subject.getSubjectList()
        .then(response => {
          this.subjectOneLists = response.data
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 点击某个一级分类，触发change事件，显示对应的二级分类
    subjectLevelOneChanged(value) {
      // value就是一级分类的id值
      for (let i = 0; i < this.subjectOneLists.length; i++) {
        if (this.subjectOneLists[i].id === value) {
          this.subjectTwoLists = this.subjectOneLists[i].children
          this.courseInfo.subjectId = ''
        }
      }
    },
    // 上传封面成功调用的方法
    handleAvatarSuccess(resp, file) {
      this.courseInfo.cover = resp.data
    },
    // 上传之前要调用的方法
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 10
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 10MB!')
      }
      return isLt2M
    }

  }
}
</script>

<style scoped>

</style>
