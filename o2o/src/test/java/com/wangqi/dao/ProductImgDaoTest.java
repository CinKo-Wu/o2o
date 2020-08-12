package com.wangqi.dao;

import com.wangqi.BaseTest;
import com.wangqi.pojo.Product;
import com.wangqi.pojo.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductImgDaoTest extends BaseTest {
    @Autowired
    ProductImgDao productImgDao;

    @Test
    public void testBatchInsertProductImg() throws Exception {
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1的地址");
        productImg1.setImgDesc("测试图片1");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2的地址");
        productImg2.setImgDesc("测试图片2");
        productImg2.setPriority(2);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        System.out.println(effectedNum);
    }

    @Test
    public void testDeleteProductImgByProductId() {
        int effectedNum = productImgDao.deleteProductImgByProductId(6L);
        System.out.println("删除的行数：" + effectedNum);
    }

    @Test
    public void testQueryProductImgList() {
        long productId = 1L;
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            System.out.println(productImg);
        }
    }
}
