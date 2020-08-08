package com.wangqi.dao;


import com.wangqi.BaseTest;
import com.wangqi.pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        System.out.println(shopCategoryList.size());
        ShopCategory parent= new ShopCategory();
        parent.setShopCategoryId(1L);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setParent(parent);
        System.out.println(shopCategoryDao.queryShopCategory(shopCategory).get(0).getShopCategoryName());
    }
}
