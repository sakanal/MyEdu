import request from '@/utils/request'

export default {
  // 添加课程信息功能
  addCourseInfo(courseInfo) {
    return request({
      url: '/edu/course/add',
      method: 'post',
      data: courseInfo
    })
  },
  // 查询所有讲师
  getAllTeacher() {
    return request({
      url: '/edu/teacher/findAll',
      method: 'get'
    })
  }
}
