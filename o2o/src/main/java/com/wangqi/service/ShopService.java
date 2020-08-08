package com.wangqi.service;

import com.wangqi.dto.ShopExecution;
import com.wangqi.pojo.Shop;

import java.io.File;

public interface ShopService {

    /**
     * 通过店铺id获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括对图片的处理
     * @param shop
     * @param shopImg
     * @return
     */
    ShopExecution modifyShop(Shop shop, File shopImg);

    /**
     * 注册店铺信息，包括对图片的处理
     * @param shop
     * @param shopImg
     * @return
     */
    ShopExecution addShop(Shop shop, File shopImg);
}
