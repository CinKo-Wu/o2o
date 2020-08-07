package com.wangqi.service;

import com.wangqi.BaseTest;
import com.wangqi.dto.ShopExecution;
import com.wangqi.enums.ShopStateEnum;
import com.wangqi.pojo.Area;
import com.wangqi.pojo.PersonInfo;
import com.wangqi.pojo.Shop;
import com.wangqi.pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() {
        Shop shop = new Shop();
        File fileImg = new File("E:/javaProjects/image/xiaohuangya.jpg");


        Area area = new Area();
        area.setAreaId(1);
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shop.setArea(area);
        shop.setOwner(personInfo);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺2号");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setAdvice("审核中");

        ShopExecution shopExecution = shopService.addShop(shop, fileImg);
        System.out.println(shopExecution.getState());
        System.out.println(shopExecution.getStateInfo());
    }
}
