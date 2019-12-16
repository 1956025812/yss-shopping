package com.yss.shopping.controller.goods;

import com.yss.shopping.entity.goods.Goods;
import com.yss.shopping.service.goods.GoodsService;
import com.yss.shopping.vo.ResultVO;
import com.yss.shopping.vo.goods.GoodsOutVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author yss
 * @since 2019-12-16
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;


    @ApiOperation("根据商品ID查询商品信息")
    @GetMapping("/select/by/id")
    public ResultVO<GoodsOutVo> selectGoodsById(@ApiParam(value = "商品ID", required = true, example = "1") @RequestParam Long gid) {
        logger.info("根据商品ID查询商品信息, 请求参数为：[gid:{}]", gid);
        Goods goods = this.goodsService.selectGoodsById(gid);
        GoodsOutVo goodsOutVo = new GoodsOutVo().toGoodsOutVo(goods);
        return ResultVO.getSuccess("查询商品信息成功", goodsOutVo);
    }


}
