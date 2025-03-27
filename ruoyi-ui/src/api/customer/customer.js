import request from '@/utils/request'

// 查询用户信息管理列表
export function listCustomer(query) {
  return request({
    url: '/customer/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息管理详细
export function getCustomer(id) {
  return request({
    url: '/customer/' + id,
    method: 'get'
  })
}

// 新增用户信息管理
export function addCustomer(data) {
  return request({
    url: '/customer/add',
    method: 'post',
    data: data
  })
}

// 修改用户信息管理
export function updateCustomer(data) {
  return request({
    url: '/customer/edit',
    method: 'put',
    data: data
  })
}

// 删除用户信息管理
export function delCustomer(id) {
  return request({
    url: '/customer/' + id,
    method: 'delete'
  })
}
