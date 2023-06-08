// 查询列表页接口
const getOrderDetailPage = (params) => {
  return $axios({
    url: '/orders/page',
    method: 'get',
    params
  })
}

// 查看接口
const queryOrderDetailById = (id) => {
  return $axios({
    url: `/orderDetail/${id}`,
    method: 'get'
  })
}

// 取消，派送，完成接口
const editOrderDetail = (params) => {
  return $axios({
    url: '/orders',
    method: 'put',
    data: { ...params }
  })
}

const getOrderGoods = (params) => {
  return $axios({
    url: '/orders/goods',
    method: 'get',
    params
  })
}
const deleteOrderImpl = (id) => {
  return axios({
    url: '/orders/delete',
    method: 'delete',
    params: {
      id: id
    }
  })
}
