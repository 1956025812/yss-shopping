package com.yss.shopping.common.vo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yss.shopping.user.entity.goods.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel("商品OutVo对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsOutVo {

    @ApiModelProperty("商品ID")
    private Long gid;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品备注")
    private String goodsRemark;

    @ApiModelProperty("商品状态：0-删除，1-上架，2-下架")
    private Integer status;

    @ApiModelProperty("创建人信息")
    private String createUser;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * Goods对象转换为GoodsOutVo对象
     *
     * @param goods
     * @return GoodsOutVo
     */
    public GoodsOutVo toGoodsOutVo(Goods goods) {
        GoodsOutVo goodsOutVo = new GoodsOutVo();
        if (null != goods) {
            goodsOutVo.setGid(goods.getId());
            goodsOutVo.setGoodsName(goods.getGoodsName());
            goodsOutVo.setGoodsRemark(goods.getGoodsRemark());
            goodsOutVo.setStatus(goods.getStatus());
            goodsOutVo.setCreateUser(goods.getCreateUser());
            goodsOutVo.setCreateTime(goods.getCreateTime());
        }
        return goodsOutVo;
    }


}
