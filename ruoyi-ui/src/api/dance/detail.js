import request from '@/utils/request'

// 查询课程详情表列表
export function listDetail(query) {
  return request({
    url: '/dance/detail/list',
    method: 'get',
    params: query
  })
}

// 查询课程详情表详细
export function getDetail(id) {
  return request({
    url: '/dance/detail/' + id,
    method: 'get'
  })
}

// 新增课程详情表
export function addDetail(data) {
  return request({
    url: '/dance/detail',
    method: 'post',
    data: data
  })
}

// 修改课程详情表
export function updateDetail(data) {
  return request({
    url: '/dance/detail',
    method: 'put',
    data: data
  })
}

// 删除课程详情表
export function delDetail(id) {
  return request({
    url: '/dance/detail/' + id,
    method: 'delete'
  })
}
