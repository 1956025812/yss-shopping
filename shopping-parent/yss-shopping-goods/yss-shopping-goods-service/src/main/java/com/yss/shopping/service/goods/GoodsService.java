package com.yss.shopping.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yss.shopping.api.vo.SysUserOutVo;
import com.yss.shopping.entity.goods.Goods;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author yss
 * @since 2019-12-16
 */
public interface GoodsService extends IService<Goods> {


    /**
     * 根据商品ID查询商品信息
     *
     * @param gid 商品ID
     * @return Goods
     */
    Goods selectGoodsById(Long gid);


    /**
     * @param uid
     * @return
     */
    SysUserOutVo testFeignSysUserApi(Long uid);
}
