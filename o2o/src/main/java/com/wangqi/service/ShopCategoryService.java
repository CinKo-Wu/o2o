package com.wangqi.service;

import com.wangqi.pojo.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    /**
     * 根据查询条件获得ShopCategory列表
     * @param shopCategoryCondition
     * @return
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
