<template>
  <div class="app-container">
    <el-form :inline="true" :model="courseQuery" size="small " class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程标题" />
      </el-form-item>
      <el-form-item>
        <el-select v-model="courseQuery.price" clearable placeholder="课程价格">
          <el-option :value="10" label="10元以下" />
          <el-option :value="50" label="50元以下" />
          <el-option :value="100" label="100元以下" />
          <el-option :value="1000" label="1000元以下" />
          <el-option :value="10000" label="10000元以下" />
        </el-select>
      </el-form-item>
      <el-form-item style="width: 120px">
        <el-select v-model="courseQuery.status" clearable placeholder="课程状态">
          <el-option value="Normal" label="已发布" />
          <el-option value="Draft" label="未发布" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="courseQuery.startTime" type="datetime" placeholder="选择开始时间" value-format="yyyy-MM-dd HH:mm:ss" default-time="00:00:00"/>
      </el-form-item>
      <el-form-item>
        <el-date-picker v-model="courseQuery.endTime" type="datetime" placeholder="选择结束时间" value-format="yyyy-MM-dd HH:mm:ss" default-time="00:00:00"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getCourseListPage()">查询</el-button>
        <el-button @click="resetData()">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="isLoading" :data="courseList" style="width: 100%">
      <el-table-column label="序号" width="60">
        <template slot-scope="scope">
          {{ (current-1)*10 + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="标题" prop="title" width="240"/>
      <el-table-column label="价格" prop="price" />
      <el-table-column label="状态" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status==='Normal' ? '已发布':'未发布' }}
        </template>
      </el-table-column>
      <el-table-column label="购买人数" prop="buyCount" />
      <el-table-column label="观看人数" prop="viewCount" />
      <el-table-column label="创建时间" prop="gmtCreate" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="updateCourse(scope.row.id)">编辑</el-button>
          <el-button size="mini" type="danger" @click="removeCourse(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      style="padding: 30px 0;text-align: center"
      layout="total, prev, pager, next, jumper"
      :current-page="current"
      :page-size="10"
      :total="total"
      @current-change="getCourseListPage"
    />
  </div>
</template>

<script>
import course from '@/api/course/course'

export default {
  data() {
    return {
      isLoading: true,
      courseList: null,
      // courseList: {
      //   title: '', // 标题
      //   price: 0, // 价格
      //   status: '', // 状态 Normal 已发布   Draft 未发布
      //   buyCount: 0, // 购买人数
      //   viewCount: 0, // 观看人数
      //   gmtCreate: '' // 创建时间
      // },
      current: 1, // 页码（当前页）
      courseQuery: {}, // 查询条件
      total: -1, // 数据总数
      pages: 0 // 总页数
    }
  },
  created() {
    this.getCourseListPage()
  },
  methods: {
    getCourseListPage(current = 1) {
      this.isLoading = true
      this.current = current
      course.getCourseListPage(this.current, this.courseQuery)
        .then(response => {
          this.courseList = response.data.records
          this.total = response.data.total
          this.pages = response.data.pages
        })
        .catch(error => {
          console.log(error)
        })
      this.isLoading = false
    },
    resetData() {
      this.courseQuery = {}
      this.getCourseListPage()
    },
    updateCourse(courseId) {
      this.$router.push({ path: `/course/edit/${courseId}` })
    },
    removeCourse(courseId) {
      this.$confirm('此操作将永久删除该课程以及其所有内容, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        course.removeCourseById(courseId)
          .then(response => {
            this.$message.success('删除成功')
            this.getCourseListPage()
          })
          .catch(error => {
            console.log(error)
          })
      })
    }
  }
}
</script>

<style scoped>

</style>
