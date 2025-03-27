import request from '@/utils/request'

// 查询机构信息详情管理列表
export function listInstitutionmore(query) {
  return request({
    url: '/institution/list',
    method: 'get',
    params: query
  })
}

// 查询机构信息详情管理详细
export function getInstitutionmore(id) {
  return request({
    url: '/institution/' + id,
    method: 'get'
  })
}

// 新增机构信息详情管理
export function addInstitutionmore(data) {
  return request({
    url: '/institution',
    method: 'post',
    data: data
  })
}

// 修改机构信息详情管理
export function updateInstitutionmore(data) {
  return request({
    url: '/institution/edit',
    method: 'put',
    data: data
  })
}

// 删除机构信息详情管理
export function delInstitutionmore(id) {
  return request({
    url: '/institution/' + id,
    method: 'delete'
  })
}
