import axios from '@/libs/api.request'


/**
 * @description 查询菜单分页列表
 */
export const selectSysMenuPageAPI = (params) => {
    return axios.request({
        url: '/menu/page',
        method: 'get',
        params: params
    });
}


/**
 * @description 查询菜单列表
 */
export const selectSysMenuListAPI = (params) => {
    return axios.request({
        url: '/menu/list',
        method: 'get',
        params: params
    });
}


/**
 * @description 查询菜单详情
 */
export const selectSysMenuDetailAPI = (params) => {
    return axios.request({
        url: '/menu/detail',
        method: 'get',
        params: params
    });
}


/**
 * @description 新增菜单
 */
export const saveMenuAPI = (params) => {
    return axios.request({
        url: '/menu/save',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}


/**
 * @description 修改菜单
 */
export const editMenuAPI = (params) => {
    return axios.request({
        url: '/menu/update',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}


/**
 * @description 删除菜单
 */
export const delMenuAPI = (params) => {
    return axios.request({
        url: '/menu/del',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}