package com.wangqi.service;

import com.wangqi.dto.ProductCategoryExection;
import com.wangqi.exceptions.ProductCategoryOperationException;
import com.wangqi.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 查询指定某个店铺下的所有商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     * 批量添加商品种类
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    public ProductCategoryExection batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;
}
