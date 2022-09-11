<template>
  <div class="app-container">
    <el-form :inline="true" :model="teacherQuery" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="头衔">
          <el-option :value="1" label="高级教师"/>
          <el-option :value="2" label="首席教师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.startTime"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.endTime"
          type="datetime"
          placeholder="选择结束时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getTeacherListPage()">查询</el-button>
        <el-button @click="resetData()">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="teacherList"
      style="width: 100%">

      <el-table-column
        label="序号"
        width="60">
        <template slot-scope="scope">
          {{ (current-1)*10 + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
      </el-table-column>

      <el-table-column
        prop="level"
        label="头衔"
        width="120">
        <template slot-scope="scope">
          {{ scope.row.level===1 ? '高级教师':'首席教师' }}
        </template>
      </el-table-column>

      <el-table-column
        prop="career"
        label="资历">
      </el-table-column>

      <el-table-column
        prop="gmtCreate"
        label="添加时间"
        width="180">
      </el-table-column>

      <el-table-column
        prop="sort"
        label="排序"
        width="60">
      </el-table-column>

      <el-table-column
        label="操作"
        width="200"
        align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      style="padding: 30px 0;text-align: center"
      layout="total, prev, pager, next, jumper"
      :current-page="current"
      :page-size="10"
      :total="total"
      @current-change="getTeacherListPage">
    </el-pagination>
  </div>
</template>

<script>
import teacher from '@/api/teacher/teacher'

export default {
  data() {
    return {
      current: 1, // 页码（当前页）
      teacherQuery: {}, // 查询条件
      total: 0, // 数据总数
      pages: 0, // 总页数
      teacherList: null // 查询之后返回结果
    }
  },

  created() {
    this.getTeacherListPage()
  },

  methods: {
    getTeacherListPage(current = 1) {
      this.current = current
      teacher.getTeacherListPage(this.current, this.teacherQuery)
        .then(response => {
          console.log(response)
          this.teacherList = response.data.records
          this.total = response.data.total
          this.pages = response.data.pages
          console.log(this.teacherList)
        })
        .catch(error => {
          console.log(error)
        })
    },
    resetData() {
      this.teacherQuery = {}
      this.getTeacherListPage()
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    }
  }
}
</script>

<style scoped>

</style>
