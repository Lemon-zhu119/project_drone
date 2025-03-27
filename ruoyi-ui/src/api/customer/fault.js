import request from '@/utils/request'

// 查询用户错题表列表
export function listFault(query) {
  return request({
    url: '/customer/fault/list',
    method: 'get',
    params: query
  })
}

// 查询用户错题表详细
export function getFault(id) {
  return request({
    url: '/customer/fault/' + id,
    method: 'get'
  })
}

// 新增用户错题表
export function addFault(data) {
  return request({
    url: '/customer/fault',
    method: 'post',
    data: data
  })
}

// 修改用户错题表
export function updateFault(data) {
  return request({
    url: '/customer/fault',
    method: 'put',
    data: data
  })
}

// 删除用户错题表
export function delFault(id) {
  return request({
    url: '/customer/fault/' + id,
    method: 'delete'
  })
}
