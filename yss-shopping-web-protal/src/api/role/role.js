import axios from '@/libs/api.request'

/**
 * @description 查询角色分页列表
 */
export const selectRolePageAPI = (params) => {
    return axios.request({
        url: '/role/page',
        method: 'get',
        params: params
    });
}


/**
 * @description 查询角色列表 
 */
export const selectRoleListAPI = (params) => {
    return axios.request({
        url: '/role/list',
        method: 'get',
        params: params
    });
}


/**
* @description 查询角色详情
*/
export const selectRoleDetailAPI = (params) => {
    return axios.request({
        url: '/role/detail',
        method: 'get',
        params: params
    });
}


/**
 * @description 新增角色
 */
export const saveRoleAPI = (params) => {
    return axios.request({
        url: '/role/save',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}



/**
 * @description 修改角色
 */
export const editRoleAPI = (params) => {
    return axios.request({
        url: '/role/update',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}



/**
 * @description 删除角色
 */
export const delRoleAPI = (params) => {
    return axios.request({
        url: '/role/del',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}
