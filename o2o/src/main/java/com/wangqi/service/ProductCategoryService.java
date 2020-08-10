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

    /**
     * 将此类别下的商品里的类别id置为空，再删掉该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    public ProductCategoryExection deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
