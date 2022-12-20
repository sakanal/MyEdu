import request from '@/utils/request'

export default{
  //根据视频id，获取视频凭证
  getPlayAuthById(videoSourceId){
    return request({
      url:`/vod/file/getPlayAuth/${videoSourceId}`,
      method: 'get'
    })
  }
}
