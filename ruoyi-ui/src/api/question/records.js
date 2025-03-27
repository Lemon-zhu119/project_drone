import request from '@/utils/request'

// 查询考试记录列表
export function listExamRecords(query) {
  return request({
    url: '/api/history/examScoreHistory',
    method: 'get',
    params: query
  })
}