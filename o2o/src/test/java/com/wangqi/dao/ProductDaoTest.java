package com.wangqi.dao;

import com.wangqi.BaseTest;
import com.wangqi.pojo.Product;
import com.wangqi.pojo.ProductCategory;
import com.wangqi.pojo.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testInsertProduct() throws Exception {
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory pc1 = new ProductCategory();
        pc1.setProductCategoryId(1L);
        //初始化3个商品添加到shopId为1的店铺里
        Product product1 = new Product();
        product1.setProductName("测试商品1");
        product1.setProductDesc("测试商品1Desc");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc1);
        Product product2 = new Product();
        product2.setProductName("测试商品2");
        product2.setProductDesc("测试商品2Desc");
        product2.setImgAddr("test2");
        product2.setPriority(2);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(pc1);
        Product product3 = new Product();
        product3.setProductName("测试商品3");
        product3.setProductDesc("测试商品3Desc");
        product3.setImgAddr("test3");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(pc1);
        int effectedNum = productDao.insertProduct(product1);
        System.out.println(effectedNum);
        effectedNum = productDao.insertProduct(product1);
        System.out.println(effectedNum);
        effectedNum = productDao.insertProduct(product1);
        System.out.println(effectedNum);
    }

}
