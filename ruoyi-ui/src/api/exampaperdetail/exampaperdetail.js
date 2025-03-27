import request from '@/utils/request'

// 查询考卷绑定关系的列表
export function listPaperDetail(query) {
  return request({
    url: '/ExamPaperDetail/paper/list',
    method: 'get',
    params: query
  })
}

// 查询绑定关系管理详细
export function getPaperDetail(id) {
  return request({
    url: '/ExamPaperDetail/paper/' + id,
    method: 'get'
  })
}

// 新增绑定关系管理
export function addPaperDetail(data) {
  return request({
    url: '/ExamPaperDetail/paper',
    method: 'post',
    data: data
  })
}

// 修改绑定关系管理
export function updatePaperDetail(data) {
  return request({
    url: '/ExamPaperDetail/paper',
    method: 'put',
    data: data
  })
}

// 删除绑定关系管理
export function delPaperDetail(id) {
  return request({
    url: '/ExamPaperDetail/paper/' + id,
    method: 'delete'
  })
}

// // 随机生成题目
// export function randomGenerate(data) {
//   return request({
//     url: '/ExamPaperDetail/paper/randomGenerate',
//     method: 'post',
//     data: data
//   })
// }
// // 获取对应试卷的题目列表
// export function getDetailList(data) {
//   return request({
//     url: '/ExamPaperDetail/paper/detailList',
//     method: 'get',
//     params: data
//   })
// }
// // 获取题库里剩下的题目列表
// export function getRemainQuestionList(data) {
//   return request({
//     url: '/ExamPaperDetail/paper/remainQuestionList',
//     method: 'get',
//     params: data
//   })
// }
    // 删除绑定关系管理
export function delPaperDetailByDetail(data) {
  return request({
    url: '/ExamPaperDetail/paper/delPaperDetailByDetail',
    method: 'post',
    data: data
  })
}   
