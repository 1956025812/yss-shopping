package com.yss.shopping.user.service.impl.goods;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.user.api.user.SysUserApi;
import com.yss.shopping.user.api.vo.SysUserOutVo;
import com.yss.shopping.user.entity.goods.Goods;
import com.yss.shopping.user.mapper.goods.GoodsMapper;
import com.yss.shopping.user.service.goods.GoodsService;
import com.yss.shopping.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author yss
 * @since 2019-12-16
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SysUserApi sysUserApi;


    @Override
    public Goods selectGoodsById(Long gid) {
        logger.info("根据商品ID查询商品信息, 请求参数为：[gid:{}]", gid);
        Goods goods = this.getById(gid);
        logger.info("查询到的商品信息为：{}", JSONUtil.toJsonStr(goods));
        return goods;
    }


    @Override
    public SysUserOutVo testFeignSysUserApi(Long uid) {
        logger.info("测试通过FEIGN调用用户服务: 根据用户ID查询商品信息, 请求参数为：[uid", uid);

        SysUserOutVo sysUserOutVo;
        ResultVO<SysUserOutVo> resultVO = this.sysUserApi.selectSysUserById(uid);
        logger.info("调用USER服务的根据ID查询用户信息接口，响应结果为：{}", JSONUtil.toJsonStr(resultVO));
        Assert.isTrue(null != resultVO || resultVO.getCode().equals(0), "调用USER服务的根据ID查询用户信息接口失败");
        sysUserOutVo = resultVO.getData();

        return sysUserOutVo;
    }
}
