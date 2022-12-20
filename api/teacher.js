import request from "@/utils/request";
export default{
  getPageList(page,size) {
    return request({
      url: `/edu/front/teacher/pageFind/${page}/${size}`,
      method: 'get'
    })
  },
  getTeacherAndCourseListByTeacherId(teacherId) {
    return request({
      url: `/edu/front/teacher/getTeacherInfoAndCourseListByTeacherId/${teacherId}`,
      method: 'get'
    })
  }
}
