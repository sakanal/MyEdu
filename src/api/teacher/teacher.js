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
  }
}
