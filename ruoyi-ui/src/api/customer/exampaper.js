import request from '@/utils/request'

// 查询用户考卷记录列表
export function listExampaper(query) {
  return request({
    url: '/customer/exampaper/list',
    method: 'get',
    params: query
  })
}

// 查询用户考卷记录详细
export function getExampaper(id) {
  return request({
    url: '/customer/exampaper/' + id,
    method: 'get'
  })
}

// 新增用户考卷记录
export function addExampaper(data) {
  return request({
    url: '/customer/exampaper',
    method: 'post',
    data: data
  })
}

// 修改用户考卷记录
export function updateExampaper(data) {
  return request({
    url: '/customer/exampaper',
    method: 'put',
    data: data
  })
}

// 删除用户考卷记录
export function delExampaper(id) {
  return request({
    url: '/customer/exampaper/' + id,
    method: 'delete'
  })
}
