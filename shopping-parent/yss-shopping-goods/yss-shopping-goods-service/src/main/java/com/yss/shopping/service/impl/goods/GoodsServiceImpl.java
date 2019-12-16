package com.yss.shopping.service.impl.goods;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yss.shopping.entity.goods.Goods;
import com.yss.shopping.mapper.goods.GoodsMapper;
import com.yss.shopping.service.goods.GoodsService;
import com.yss.shopping.util.FastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public Goods selectGoodsById(Long gid) {
        logger.info("根据商品ID查询商品信息, 请求参数为：[gid:{}]", gid);
        Goods goods = this.getById(gid);
        logger.info("查询到的商品信息为：{}", FastJsonUtil.bean2Json(goods));
        return goods;
    }
}
