import request from '@/utils/request'

export default {
  // 分页查询课程信息
  getCourseListPage(current, courseQuery) {
    return request({
      url: `/edu/course/pageFind/${current}`,
      method: 'post',
      data: courseQuery
    })
  },
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
  },

  // 根据课程id查询课程基本信息
  getCourseInfoByCourseId(courseId) {
    return request({
      url: `/edu/course/getById/${courseId}`,
      method: 'get'
    })
  },
  // 更新课程基本信息
  updateCourseInfo(courseInfoForm) {
    return request({
      url: `/edu/course/update`,
      method: 'put',
      data: courseInfoForm
    })
  },
  // 根据课程id删除课程
  removeCourseById(courseId) {
    return request({
      url: `/edu/course/remove/${courseId}`,
      method: 'delete'
    })
  }
}
