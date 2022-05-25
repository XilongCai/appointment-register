import request from '@/utils/request'

const api_name = '/admin/cmn/dict'

export default {

  dictList(id) {
    return request({
      url: `${api_name}/findChildData/${id}`,
      method: 'get'
    })
  }

}