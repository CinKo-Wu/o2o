package com.wangqi.service.Impl;

import com.wangqi.dao.ProductCategoryDao;
import com.wangqi.dto.ProductCategoryExection;
import com.wangqi.enums.ProductCategoryStateEnum;
import com.wangqi.exceptions.ProductCategoryOperationException;
import com.wangqi.pojo.ProductCategory;
import com.wangqi.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Transactional
    public ProductCategoryExection batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try{
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("商品种类创建失败");
                } else {
                    return new ProductCategoryExection(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error" + e.getMessage());
            }
        } else {
            return new ProductCategoryExection(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }


}
