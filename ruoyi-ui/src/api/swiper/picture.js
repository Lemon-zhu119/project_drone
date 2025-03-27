import request from '@/utils/request'

// 查询轮播图管理列表
export function listPicture(query) {
  return request({
    url: '/swiper/picture/list',
    method: 'get',
    params: query
  })
}

// 查询轮播图管理详细
export function getPicture(id) {
  return request({
    url: '/swiper/picture/' + id,
    method: 'get'
  })
}

// 新增轮播图管理
export function addPicture(data) {
  return request({
    url: '/swiper/picture/add',
    method: 'post',
    data: data
  })
}

// 修改轮播图管理
export function updatePicture(data) {
  return request({
    url: '/swiper/picture/edit',
    method: 'put',
    data: data
  })
}

// 删除轮播图管理
export function delPicture(id) {
  return request({
    url: '/swiper/picture/' + id,
    method: 'delete'
  })
}

export function upload(data) {
  return request({
    responseType:'blob',
    url: '/swiper/picture/upload',
    method: 'post',
    data: {
      file:data
    }
  })
}
