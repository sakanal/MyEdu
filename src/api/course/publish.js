import request from '@/utils/request'

export default {
  getPublishCourseInfo(courseId) {
    return request({
      url: `/edu/course/getPublishCourseInfo/${courseId}`,
      method: 'get'
    })
  },
  publishCourse(courseId) {
    return request({
      url: `/edu/course/publishCourse/${courseId}`,
      method: 'put'
    })
  }
}
