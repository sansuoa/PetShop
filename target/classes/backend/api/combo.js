// 查询列表数据
const getSetmealPage = (params) => {
  return $axios({
    url: '/petLeave/page',
    method: 'get',
    params
  })
}

// 删除数据接口
const deleteSetmeal = (ids) => {
  return $axios({
    url: '/petLeave',
    method: 'delete',
    params: { ids }
  })
}

// 修改数据接口
const editSetmeal = (params) => {
  return $axios({
    url: '/petLeave',
    method: 'put',
    data: { ...params }
  })
}

// 新增数据接口
const addPetLeave = (params) => {
  return $axios({
    url: '/petLeave',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情接口
const querySetmealById = (id) => {
  return $axios({
    url: `/petLeave/${id}`,
    method: 'get'
  })
}

// 批量起售禁售
const setmealStatusByStatus = (params) => {
  return $axios({
    url: `/petLeave/status/${params.status}`,
    method: 'post',
    params: { ids: params.ids }
  })
}
