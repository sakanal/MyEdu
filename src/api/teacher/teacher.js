import request from '@/utils/request'

export default {
  /**
   * 教师条件分页+条件查询
   * @param current 页码
   * @param teacherQuery 查询条件
   * @returns {*}
   */
  getTeacherListPage(current, teacherQuery) {
    return request({
      url: `/edu/teacher/pageFind/${current}`,
      method: 'post',
      // teacherQuery条件对象，后端使用requestBody获取数据
      // 将对象转为json传递
      data: teacherQuery
    })
  },
  /**
   * 根据教师id删除教师，逻辑删除
   * @param id 教师id
   * @returns {*}
   */
  removeTeacherById(id) {
    return request({
      url: `/edu/teacher/delete/${id}`,
      method: 'delete'
    })
  },
  /**
   * 添加教师
   * @param teacher 教师信息
   * @returns {*}
   */
  createTeacher(teacher) {
    return request({
      url: '/edu/teacher/addTeacher',
      method: 'post',
      data: teacher
    })
  },
  /**
   * 根据id获取教师信息
   * @param id 教师id
   * @returns {*}
   */
  getTeacherById(id) {
    return request({
      url: `/edu/teacher/getById/${id}`,
      method: 'get'
    })
  },
  /**
   * 修改教师信息
   * @param teacher 教师修改后的信息
   * @returns {*}
   */
  updateTeacher(teacher) {
    return request({
      url: '/edu/teacher/updateById',
      method: 'put',
      data: teacher
    })
  }

  // testCors(userName) {
  //   return request({
  //     url: `/oss/file/avatar/${userName}`,
  //     method: 'post'
  //   })
  // }
}
