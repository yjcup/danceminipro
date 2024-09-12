import request from '@/utils/request'

// 查询课程信息管理列表
export function listCourse(query) {
  return request({
    url: '/dance/course/list',
    method: 'get',
    params: query
  })
}

// 查询课程信息管理详细
export function getCourse(id) {
  return request({
    url: '/dance/course/' + id,
    method: 'get'
  })
}

// 新增课程信息管理
export function addCourse(data) {
  return request({
    url: '/dance/course',
    method: 'post',
    data: data
  })
}

// 修改课程信息管理
export function updateCourse(data) {
  return request({
    url: '/dance/course',
    method: 'put',
    data: data
  })
}

// 删除课程信息管理
export function delCourse(id) {
  return request({
    url: '/dance/course/' + id,
    method: 'delete'
  })
}
