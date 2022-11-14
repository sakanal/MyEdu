import request from "~/utils/request";

export default {
  getHotTeacher() {
    return request({
      url: '/edu/index/teacher',
      method: 'get'
    })
  },
  getHotCourse() {
    return request({
      url: '/edu/index/course',
      method: 'get'
    })
  }
}
