import request from '@/utils/request'

// 查询用户顺序练习记录列表
export function listHistory(query) {
  return request({
    url: '/question/history/list',
    method: 'get',
    params: query
  })
}

// 查询用户顺序练习记录详细
export function getHistory(id) {
  return request({
    url: '/question/history/' + id,
    method: 'get'
  })
}

// 新增用户顺序练习记录
export function addHistory(data) {
  return request({
    url: '/question/history',
    method: 'post',
    data: data
  })
}

// 修改用户顺序练习记录
export function updateHistory(data) {
  return request({
    url: '/question/history',
    method: 'put',
    data: data
  })
}

// 删除用户顺序练习记录
export function delHistory(id) {
  return request({
    url: '/question/history/' + id,
    method: 'delete'
  })
}
