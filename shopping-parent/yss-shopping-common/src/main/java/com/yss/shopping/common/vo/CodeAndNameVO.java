package com.yss.shopping.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * <p>
 * 通用代码名称VO对象
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeAndNameVO {

    private String id;
    private String code;
    private String name;
    private String parentCode;

}
