import request from '@/utils/request'

// 查询题目管理列表
export function listQuestion(query) {
  return request({
    url: '/question/list',
    method: 'get',
    params: query
  })
}

// 查询题目管理详细
export function getQuestion(id) {
  return request({
    url: '/question/' + id,
    method: 'get'
  })
}

// 新增题目管理
export function addQuestion(data) {
  return request({
    url: '/question/add',
    method: 'post',
    data: data
  })
}

// 修改题目管理
export function updateQuestion(data) {
  return request({
    url: '/question/edit',
    method: 'put',
    data: data
  })
}
// 获取全部题目类型
export function getType() {
  return request({
    url: '/api/question/typeList',
    method: 'get'
  })
}

// 获取全部题目类型和数量
export function getTypeAndCount() {
  return request({
    url: '/api/question/typeAndCountList',
    method: 'get'
  })
}

// 删除题目管理
export function delQuestion(id) {
  return request({
    url: '/question/' + id,
    method: 'delete'
  })
}
