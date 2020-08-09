package com.wangqi.dao;

import com.wangqi.BaseTest;
import com.wangqi.pojo.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
