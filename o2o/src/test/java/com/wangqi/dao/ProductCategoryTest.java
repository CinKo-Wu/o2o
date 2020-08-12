package com.wangqi.dao;

import com.wangqi.BaseTest;
import com.wangqi.pojo.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCategoryTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryProductCategoryList() {
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(1L);
        for (ProductCategory productCategory : productCategoryList) {
            System.out.println(productCategory);
        }
    }

    @Test
    public void testBatchInsertProductCategory() {
        List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
        productCategoryList.add(new ProductCategory(null, 1L, "商品类别1", null, new Date()));
        productCategoryList.add(new ProductCategory(null, 1L, "商品类别2", null, new Date()));
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        System.out.println("插入的行数为：" + effectedNum);
    }

    @Test
    public void testDeleteProductCategory() {
        long shopId = 1L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory productCategory : productCategoryList) {
            if("商品类别1".equals(productCategory.getProductCategoryName()) || "商品类别2".equals(productCategory.getProductCategoryName())) {
                int effectedNum = productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(), shopId);
                System.out.println("删除操作影响的行数量：" + effectedNum);
            }
        }
    }

}
