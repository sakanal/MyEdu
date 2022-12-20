import request from "@/utils/request";
export default {
  //前台多条件分页查询
  getConditionPage(page,size,searchObj){
    return request({
      url: `/edu/front/course/getCourseList/${page}/${size}`,
      method: 'post',
      data: searchObj
    })
  },
  //查询所有分类（一级分类、二级分类）的方法
  getAllSubject(){
    return request({
      url: `/edu/subject/findAll`,
      method: 'get'
    })
  },
  getCourseInfo(courseId){
    return request({
      url: `/edu/front/course/getCourseInfoAndChapterInfoByCourseId/${courseId}`,
      method: 'get'
    })
  }
}
