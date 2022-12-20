<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="3"
      process-status="wait"
      align-center
      style="margin-bottom: 40px;"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="最终发布" />
    </el-steps>

    <div class="ccInfo">
      <img :src="coursePublishInfo.cover">
      <div class="main">
        <h2>{{ coursePublishInfo.title }}</h2>
        <p class="gray">
          <span>共{{ coursePublishInfo.lessonNum }}课时</span>
        </p>
        <p>
          <span>所属分类：{{ coursePublishInfo.subjectLevelOne }} —
            {{ coursePublishInfo.subjectLevelTwo }}</span>
        </p>
        <p>课程讲师：{{ coursePublishInfo.teacherName }}</p>
        <h3 class="red">￥{{ coursePublishInfo.price }}</h3>
      </div>
    </div>

    <el-form label-width="120px">
      <el-form-item>
        <el-button @click="previous">返回修改</el-button>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="publish">发布课程</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import publish from '@/api/course/publish'
export default {
  data() {
    return {
      courseId: '',
      coursePublishInfo: {
        id: '',
        teacherId: '',
        subjectId: '',
        subjectParentId: '',
        title: '',
        price: 0,
        lessonNum: 0,
        cover: '',
        description: ''
      },
      saveBtnDisabled: false
    }
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id
      this.getPublishCourseInfo()
    }
  },
  methods: {
    getPublishCourseInfo() {
      publish.getPublishCourseInfo(this.courseId)
        .then(response => {
          console.log(response)
          this.coursePublishInfo = response.data
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 跳转到上一步
    previous() {
      this.$router.push({ path: '/course/chapter/' + this.courseId })
    },
    // 最终发布
    publish() {
      publish.publishCourse(this.courseId)
        .then(response => {
          this.$message.success('课程最终发布成功')
          this.$router.push({ path: '/course/list' })
        })
        .catch(error => {
          console.log(error)
        })
    }

  }
}
</script>

<style scoped>
.ccInfo {
  background: #f5f5f5;
  padding: 20px;
  overflow: hidden;
  border: 1px dashed #ddd;
  margin-bottom: 40px;
  position: relative;
}
.ccInfo img {
  background: #d6d6d6;
  width: 500px;
  height: 278px;
  display: block;
  float: left;
  border: none;
}
.ccInfo .main {
  margin-left: 520px;
}
.ccInfo .main h2 {
  font-size: 28px;
  margin-bottom: 30px;
  line-height: 1;
  font-weight: normal;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main p {
  margin-bottom: 10px;
  word-wrap: break-word;
  line-height: 24px;
  max-height: 48px;
  overflow: hidden;
}
.ccInfo .main h3 {
  left: 540px;
  bottom: 20px;
  line-height: 1;
  font-size: 28px;
  color: #d32f24;
  font-weight: normal;
  position: absolute;
}
</style>
