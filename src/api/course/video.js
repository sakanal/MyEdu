import request from '@/utils/request'

export default {
  // 添加小节
  addVideo(video) {
    return request({
      url: '/edu/video/add',
      method: 'post',
      data: video
    })
  },
  // 根据小节id，获取小节信息
  getVideoById(videoId) {
    return request({
      url: `/edu/video/getById/${videoId}`,
      method: 'get'
    })
  },
  // 修改小节
  updateVideo(video) {
    return request({
      url: '/edu/video/update',
      method: 'put',
      data: video
    })
  },
  // 删除小节
  removeVideo(videoId) {
    return request({
      url: `/edu/video/remove/${videoId}`,
      method: 'delete'
    })
  },
  // 删除视频
  removeAliyunVideo(videoSourceId) {
    return request({
      url: `/vod/file/remove/${videoSourceId}`,
      method: 'delete'
    })
  },
  testCors() {
    return request({
      url: '/vod/file/testCors',
      method: 'post'
    })
  }
}
