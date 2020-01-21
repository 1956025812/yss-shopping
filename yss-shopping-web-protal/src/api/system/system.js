import axios from '@/libs/api.request'

/**
 * @description 查询系统分页列表
 */
export const selectSystemPageAPI = (params) => {
    return axios.request({
        url: '/system/page',
        method: 'get',
        params: params
    });
}

/**
 * @description 新增系统
 */
export const saveSystemAPI = (params) => {
    return axios.request({
        url: '/system/save',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}


/**
 * @description 查询系统详情   
 */
export const selectSystemDetailAPI = (params) => {
    return axios.request({
        url: '/system/detail',
        method: 'get',
        params: params
    });
}



/**
 * @description 修改系统
 */
export const editSystemAPI = (params) => {
    return axios.request({
        url: '/system/update',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}




/**
 * @description 删除系统
 */
export const delSystemAPI = (params) => {
    return axios.request({
        url: '/system/del',
        method: 'post',
        data: JSON.stringify(params),
        headers: { 'Content-Type': 'application/json' }
    });
}



/**
 * @description 查询系统列表
 */
export const selectSystemListAPI = (params) => {
    return axios.request({
        url: '/system/list',
        method: 'get',
        params: params
    });
}



