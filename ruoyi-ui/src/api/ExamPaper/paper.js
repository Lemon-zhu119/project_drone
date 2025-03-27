import request from '@/utils/request'

// 查询考卷管理列表
export function listPaper(query) {
  return request({
    url: '/ExamPaper/paper/list',
    method: 'get',
    params: query
  })
}

// 查询考卷管理详细
export function getPaper(id) {
  return request({
    url: '/ExamPaper/paper/' + id,
    method: 'get'
  })
}

// 新增考卷管理
export function addPaper(data) {
  return request({
    url: '/ExamPaper/paper',
    method: 'post',
    data: data
  })
}

// 修改考卷管理
export function updatePaper(data) {
  return request({
    url: '/ExamPaper/paper',
    method: 'put',
    data: data
  })
}

// 删除考卷管理
export function delPaper(id) {
  return request({
    url: '/ExamPaper/paper/' + id,
    method: 'delete'
  })
}

// 随机生成题目
export function randomGenerate(data) {
  return request({
    url: '/ExamPaper/paper/randomGenerate',
    method: 'post',
    data: data
  })
}
// 获取对应试卷的题目列表
export function getDetailList(data) {
  return request({
    url: '/ExamPaper/paper/detailList',
    method: 'get',
    params: data
  })
}
// 获取题库里剩下的题目列表
export function getRemainQuestionList(data) {
  return request({
    url: '/ExamPaper/paper/remainQuestionList',
    method: 'get',
    params: data
  })
}