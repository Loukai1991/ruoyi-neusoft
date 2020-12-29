import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store/'

axios.defaults.baseURL = ''
axios.defaults.timeout = 15000
// 拦截请求
axios.interceptors.request.use((config) => {

  // 数据格式
  config.headers.Accept = 'application/json'
  return config
})

// 拦截响应
axios.interceptors.response.use((response) => {
    return Promise.resolve(response.data)
  // if (response.status === 200) {
  //   const data = response.data
  //   const status = data.code || data.success
  //   const errCode = parseInt(data.code)
  //   if (!status) {
  //     if (errCode === 1001 || errCode === 1002 || errCode === 1003 || errCode === 1004) {
  //       // token错误时取消所有正在进行的请求
  //       store.dispatch('http/clearRequests')
  //       // token错误跳转到登录页面
  //       MessageBox.confirm('token异常！请重新登录', '系统提示', {
  //         confirmButtonText: '确定',
  //         cancelButtonText: '取消',
  //         type: 'error'
  //       }).then(() => {
  //         store.dispatch('user/logout').then(res => {
  //           location.reload()
  //         })
  //       })
  //     } else {
  //       Message.error({
  //         message: data.info || '服务出错'
  //       })
  //       return Promise.reject(data)
  //     }
  //   } else {
  //     return Promise.resolve(data)
  //   }
  // } else {
  //   Message.error({
  //     message: '异常错误'
  //   })
  //   return Promise.reject(response)
  // }
},
(error) => {
  if (axios.isCancel(error)) {
    // 取消请求的情况下，终止Promise调用链
    return new Promise(() => {})
  } else {
    Message.error({
      message: '网络异常'
    })
    return Promise.reject(error)
  }
})

export default class Http {
  static get (url, params, config = {}) {
    return new Promise((resolve, reject) => {
      axios.get(url, {
        params: params
      }, config).then(res => {
        resolve(res.data)
      }).catch(err => {
        reject(err)
      })
    })
  }

  static post (url, params, config = {}) {
    return new Promise((resolve, reject) => {
      axios.post(url, {
        ...params
      }, config).then(res => {
        resolve(res || {})
      }).catch((err) => {
        reject(err)
      })
    })
  }
}
