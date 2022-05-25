import request from '@/utils/request'

const api_name = '/admin/hosp/hospitalSet'

export default {

  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/findPageHospSet/${page}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },

  deleteHospSet(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'delete'
    })
  },

  removeRows(idList) {
    return request({
      url: `${api_name}/batchRemove`,
      method: 'delete',
      data: idList
    })
  },

  lockHospSet(id, status) {
    return request({
      url: `${api_name}/lockHospitalSet/${id}/${status}`,
      method: 'put'
    })
  },

  saveHospSet(hospitalSet) {
    return request({
      url: `${api_name}/saveHospitalSet`,
      method: 'post',
      data: hospitalSet
    })
  },

  getHospSet(id) {
    return request({
      url: `${api_name}/getHospSet/${id}`,
      method: 'get'
    })
  },

  updateHospSet(hospitalSet) {
    return request({
      url: `${api_name}/updateHospitalSet`,
      method: 'post',
      data: hospitalSet
    })
  }
}
