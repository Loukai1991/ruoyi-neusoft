// import request from '@/utils/request'
import Http from '@/utils/http'

const apiUrl = 'http://ruoyi-admin-api/index.php'

// 获取任务列表
export const fetchTodoList = () => {
  return new Promise(resolve => {
    resolve({
      code:'200',
      msg:'查询成功',
      total:1,
      rows:[
        {
          todoId: '1',
          assignee:'1',
          assigneer:'1',
          deadline:"2019-10-10",
          todoDesc:"任务描述",
          todoTitle:'任务标题',
          createTime:"2020-10-1",
          todoStatus:'0',
          createBy:'lisi',
          todoType:'0',
          pjtId:'1',
          priority:'1',
          productId:'1'
        }
      ]
    })
  })
  // return Http.post(apiUrl + '/user/login', data)
  // return request({
  //   url: '/todo/list',
  //   method: 'post'
  // })
}

// 查询任务详细
export function getTodo(todoId) {
  return new Promise(resolve => {
    resolve({
      code:'200',
      msg:'查询成功',
      data:
        {
          todoId: '1',
          assignee:'1',
          assigneer:'1',
          deadline:"2019-10-10",
          todoDesc:"任务描述",
          todoTitle:'任务标题',
          createTime:"2020-10-1",
          todoStatus:'0',
          createBy:'lisi',
          todoType:'0',
          pjtId:'1',
          priority:'1',
          productId:'1'
        }

    })
  })
  // return request({
  //   url: '/todo/one' + todoId,
  //   method: 'get'
  // })
}

// 新增任务
export function addTodo(data) {
  // return request({
  //   url: '/system/notice',
  //   method: 'post',
  //   data: data
  // })
}

// 修改公告
export function updateTodo(data) {
  // return request({
  //   url: '/system/notice',
  //   method: 'put',
  //   data: data
  // })
}

// 删除公告
export function delTodo(noticeId) {
  // return request({
  //   url: '/system/notice/' + noticeId,
  //   method: 'delete'
  // })
}
