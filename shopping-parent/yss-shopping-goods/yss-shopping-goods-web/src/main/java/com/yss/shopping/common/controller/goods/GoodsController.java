package com.yss.shopping.common.controller.goods;

import com.yss.shopping.user.api.vo.SysUserOutVo;
import com.yss.shopping.user.entity.goods.Goods;
import com.yss.shopping.user.service.goods.GoodsService;
import com.yss.shopping.common.vo.ResultVO;
import com.yss.shopping.common.vo.goods.GoodsOutVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @ApiOperation("根据商品ID查询商品信息")
    @GetMapping("/select/by/id")
    public ResultVO<GoodsOutVo> selectGoodsById(@ApiParam(value = "商品ID", required = true, example = "1") @RequestParam Long gid) {
        log.info("根据商品ID查询商品信息, 请求参数为：[gid:{}]", gid);
        Goods goods = this.goodsService.selectGoodsById(gid);
        GoodsOutVo goodsOutVo = new GoodsOutVo().toGoodsOutVo(goods);
        return ResultVO.getSuccess("查询商品信息成功", goodsOutVo);
    }


    @ApiOperation("测试通过FEIGN调用用户服务: 根据用户ID查询用户信息")
    @GetMapping("/select/by/id/feign")
    public ResultVO<SysUserOutVo> testFeignSysUserApi(@ApiParam(value = "用户ID", required = true, example = "1") @RequestParam Long uid) {
        log.info("测试通过FEIGN调用用户服务: 根据用户ID查询商品信息, 请求参数为：[uid: {}]", uid);
        SysUserOutVo sysUserOutVo = this.goodsService.testFeignSysUserApi(uid);
        return ResultVO.getSuccess("调用用户信息", sysUserOutVo);
    }

}
