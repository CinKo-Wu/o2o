package com.wangqi.dao;

import com.wangqi.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    /**
     * 通过店铺shopId查询商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量添加商品类别
     * @param productCategoryList
     * @return 影响的行数
     */
    int batchInsertProductCategory(List <ProductCategory> productCategoryList);
}
