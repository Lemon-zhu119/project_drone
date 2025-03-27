import request from '@/utils/request'

// 查询用户收藏列表
export function listFavor(query) {
  return request({
    url: '/customer/favor/list',
    method: 'get',
    params: query
  })
}

// 查询用户收藏详细
export function getFavor(id) {
  return request({
    url: '/customer/favor/' + id,
    method: 'get'
  })
}

// 新增用户收藏
export function addFavor(data) {
  return request({
    url: '/customer/favor',
    method: 'post',
    data: data
  })
}

// 修改用户收藏
export function updateFavor(data) {
  return request({
    url: '/customer/favor',
    method: 'put',
    data: data
  })
}

// 删除用户收藏
export function delFavor(id) {
  return request({
    url: '/customer/favor/' + id,
    method: 'delete'
  })
}
