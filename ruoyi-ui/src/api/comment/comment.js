import request from '@/utils/request'

// 查询用户信息管理列表
export function listcomments(query) {
  return request({
    url: '/api/institution/getcomments',
    method: 'get',
    params: query
  })
}


// 新增用户信息管理
export function addcomment(data) {
  return request({
    url: '/api/institution/addcomment',
    method: 'post',
    data: data
  })
}

// 修改用户信息管理
export function updateCustomer(data) {
  return request({
    url: '/api/institution/list',
    method: 'put',
    data: data
  })
}

// 删除用户信息管理
export function delComment(id) {
  return request({
    url: '/api/institution/delcomment' + id,
    method: 'delete'
  })
}
