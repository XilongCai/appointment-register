import request from '@/utils/request'

export default {
  getPageList(current, limit, searchObj) {
    return request({
      url: `admin/hosp/hospital/${current}/${limit}`,
      method: 'get',
      data: searchObj
    })
  },

  findByDictCode(dictCode) {
    return request({
      url: `/admin/cmn/dict/findByDictCode/${dictCode}`,
      method: 'get'
    })
  },

  findByParentId(dictCode) {
    return request({
      url: `/admin/cmn/dict/findChildData/${dictCode}`,
      method: 'get'
    })
  },

  updateStatus(id, status) {
    return request({
      url: `/admin/hosp/hospital/updateStatus/${id}/${status}`,
      method: 'get'
    })
  },

  getHospById(id) {
    return request({
      url: `/admin/hosp/hospital/show/${id}`,
      method: 'get'
    })
  },

  getDeptByHoscode(hoscode) {
    return request({
      url: `/admin/hosp/department/getDeptList/${hoscode}`,
      method: 'get'
    })
  }
}