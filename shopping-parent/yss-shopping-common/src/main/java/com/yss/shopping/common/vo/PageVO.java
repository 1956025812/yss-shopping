package com.yss.shopping.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页VO对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageVO<T> {

    @ApiModelProperty("当前页码")
    private Long currentPage = 1L;

    @ApiModelProperty("每页记录数")
    private Long pageSize = 10L;

    @ApiModelProperty("总记录数")
    private Long totalCount;

    @ApiModelProperty("总页数")
    private Long totalPage;

    @ApiModelProperty("列表数据")
    private List<T> items;

    public PageVO(Long currentPage, Long pageSize, Long totalCount, Long totalPage, List<T> items) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.items = items;
    }

}