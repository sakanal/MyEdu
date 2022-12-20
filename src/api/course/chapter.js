import request from '@/utils/request'

export default {
  // 根据课程id获取章节和小节信息
  getAllChapter(courseId) {
    return request({
      url: '/edu/chapter/getAllById/' + courseId,
      method: 'get'
    })
  },
  // 添加章节
  addChapter(chapter) {
    return request({
      url: '/edu/chapter/add',
      method: 'post',
      data: chapter
    })
  },
  // 根据章节id，获取章节信息
  getChapterById(courseId) {
    return request({
      url: `/edu/chapter/getById/${courseId}`,
      method: 'get'
    })
  },
  // 根据章节id，修改章节
  updateChapter(chapter) {
    return request({
      url: '/edu/chapter/update',
      method: 'put',
      data: chapter
    })
  },
  // 根据章节id，删除章节，需要该章节下没有小节
  removeChapter(chapterId) {
    return request({
      url: `/edu/chapter/remove/${chapterId}`,
      method: 'delete'
    })
  }
}
