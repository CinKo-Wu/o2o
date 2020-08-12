package com.wangqi.service;

import com.wangqi.BaseTest;
import com.wangqi.dto.ProductCategoryExection;
import com.wangqi.dto.ProductExecution;
import com.wangqi.enums.ProductStateEnum;
import com.wangqi.pojo.Product;
import com.wangqi.pojo.ProductCategory;
import com.wangqi.pojo.Shop;
import com.wangqi.util.ImageUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void testAddProduct() {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品2");
        product.setProductDesc("测试商品2");
        product.setPriority(20);
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        //添加缩略图
        File thumbnail = new File("E:/javaProjects/image/suoluetu.PNG");
        //创建两个商品详情图片添加到详情图列表中
        File thumbnail1 = new File("E:/javaProjects/image/jienigui.PNG");
        File thumbnail2 = new File("E:/javaProjects/image/kedaya.PNG");
        List<File> thumbnailList = new ArrayList<File>();
        thumbnailList.add(thumbnail1);
        thumbnailList.add(thumbnail2);
        //添加商品并验证
        ProductExecution pe = productService.addProduct(product, thumbnail, thumbnailList);
        System.out.println("状态信息 : " + pe.getStateInfo());
    }

    @Test
    public void testModifyProduct() {
        //创建shopId为1的productCategoryId为1的商品实例并给其变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(1L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("正式的商品");
        product.setProductDesc("正式的商品描述");
        //添加缩略图
        File thumbnail = new File("E:/javaProjects/image/shenqibaobei.PNG");
        //创建两个商品详情图片添加到详情图列表中
        File thumbnail1 = new File("E:/javaProjects/image/jienigui.PNG");
        File thumbnail2 = new File("E:/javaProjects/image/jienigui.PNG");
        List<File> thumbnailList = new ArrayList<File>();
        thumbnailList.add(thumbnail1);
        thumbnailList.add(thumbnail2);
        //添加商品并验证
        ProductExecution pe = productService.modifyProduct(product, thumbnail, thumbnailList);
        System.out.println("修改商品的结果状态信息 : " + pe.getStateInfo());
    }

    @Test
    public void testDeleteProductCategory() {
        long shopId = 1L;
        long productCategoryId = 3L;
        ProductCategoryExection pce = productCategoryService.deleteProductCategory(productCategoryId, shopId);
        System.out.println(pce.getStateInfo());
    }

}
