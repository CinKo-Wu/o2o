package com.wangqi.dao;

import com.wangqi.pojo.Shop;

public interface ShopDao {

    /**
     * 通过shopId查询店铺
     * @param shopId
     * @return
     */
    Shop queryByShopId(Long shopId);

    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
