package com.yss.shopping.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * <p>
 * 基础VO对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO {

    @ApiModelProperty("当前页码")
    @NotNull(message = "当前页码字段currentPage不能为空")
    private Long currentPage;

    @ApiModelProperty("每页条数")
    @NotNull(message = "每页条数字段pageSize不能为空")
    private Long pageSize;

    @ApiModelProperty("开始时间 格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间 格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty("开始日期 格式：yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @ApiModelProperty("结束日期 格式：yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ApiModelProperty("开始月份 格式：yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth startMonth;

    @ApiModelProperty("结束月份 格式：yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth endMonth;

}