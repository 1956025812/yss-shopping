package com.yss.shopping.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.common.constant.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 返回结果VO对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;


    public ResultVO() {
    }


    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 请求成功
     *
     * @param msg 返回信息
     * @return ResultVO
     */
    public static ResultVO getSuccess(String msg) {
        return new ResultVO(ResultCodeEnum.SUCCESS.getCode(), msg);
    }


    /**
     * 请求成功
     *
     * @param msg  返回信息
     * @param data 返回对象
     * @param <T>  类型
     * @return ResultVO
     */
    @SuppressWarnings("unchecked")
    public static <T> ResultVO getSuccess(String msg, T data) {
        return new ResultVO(ResultCodeEnum.SUCCESS.getCode(), msg, data);
    }


    /**
     * 请求失败
     *
     * @param msg 返回信息
     * @return ResultVO
     */
    public static ResultVO getFailed(String msg) {
        return new ResultVO(ResultCodeEnum.FAILED.getCode(), msg);
    }


    /**
     * 请求失败
     *
     * @param msg  返回信息
     * @param data 返回数据
     * @param <T>  类型
     * @return ResultVO
     */
    @SuppressWarnings("unchecked")
    public static <T> ResultVO getFailed(String msg, T data) {
        return new ResultVO(ResultCodeEnum.FAILED.getCode(), msg, data);
    }


    /**
     * 用户未登录
     *
     * @return ResultVO
     */
    public static ResultVO getNoLogin() {
        return new ResultVO(ResultCodeEnum.UNAUTHORIZED.getCode(), ResultCodeEnum.UNAUTHORIZED.getMsg());
    }


    /**
     * 用户没有操作权限
     *
     * @return ResultVO
     */
    public static ResultVO getNoAuthorization() {
        return new ResultVO(ResultCodeEnum.FORBIDDEN.getCode(), ResultCodeEnum.FORBIDDEN.getMsg());
    }

}