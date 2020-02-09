package com.yss.shopping.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author duxuebo
 * @date 2020/02/09
 * @description 通用代码名称VO对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeAndNameVO {

    private String id;
    private String code;
    private String name;
    private String parentCode;

}
