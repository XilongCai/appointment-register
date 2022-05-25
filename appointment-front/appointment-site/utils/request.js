import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
// 创建axios实例
const service = axios.create({
    baseURL: 'http://localhost:9001',
    timeout: 15000 // 请求超时时间
})
// http request 拦截器
service.interceptors.request.use(
    config => {
    // token 先不处理，后续使用时在完善
    return config
},
  err => {
    return Promise.reject(err)
})
// http response 拦截器
service.interceptors.response.use(
    response => {
        if (response.data.code !== 200) {
            console.log("ha")
            Message({
                message: response.data.data.message,
                type: 'error',
                duration: 5 * 1000
            })
            return Promise.reject(response.data.data)
        } else {
            return response.data
        }
    },
    error => {
        console.log("hahaha")
        return Promise.reject(error.response)
})
export default service
