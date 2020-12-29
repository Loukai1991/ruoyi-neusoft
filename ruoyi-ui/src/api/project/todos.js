import request from '@/utils/request'
import Http from '@/utils/http'
// const apiUrl = 'http://ruoyi-admin-api/index.php'
const apiUrl = 'http://121.4.97.177:8081/index.php'
// 查询任务列表
export function listTodos(data) {
  return Http.post(apiUrl + '/home/index/todoList', data)
  // return request({
  //   url: '/project/todos/list',
  //   method: 'get',
  //   params: query
  // })
}

// 查询任务详细
export function getTodos(todoId) {
  return Http.post(apiUrl + '/home/index/getTodos', {todoId:todoId})
  // return request({
  //   url: '/project/todos/' + todoId,
  //   method: 'get'
  // })
}

// 新增任务
export function addTodos(data) {
  return Http.post(apiUrl + '/home/index/addTodo', data)
  // return request({
  //   url: '/project/todos',
  //   method: 'post',
  //   data: data
  // })
}

// 修改任务
export function updateTodos(data) {
  return Http.post(apiUrl + '/home/index/updateTodos', data)
  // return request({
  //   url: '/project/todos',
  //   method: 'put',
  //   data: data
  // })
}

// 删除任务
export function delTodos(para) {
  return Http.post(apiUrl + '/home/index/delTodos', para)
  // return request({
  //   url: '/project/todos/' + todoId,
  //   method: 'delete'
  // })
}

// 导出任务
export function exportTodos(query) {
  return request({
    url: '/project/todos/export',
    method: 'get',
    params: query
  })
}
