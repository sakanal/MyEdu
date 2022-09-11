import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/edu/teacher/findAll',
    method: 'get',
    params
  })
}
