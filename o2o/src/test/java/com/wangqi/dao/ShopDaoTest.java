package com.wangqi.dao;

import com.wangqi.BaseTest;
import com.wangqi.pojo.Area;
import com.wangqi.pojo.PersonInfo;
import com.wangqi.pojo.Shop;
import com.wangqi.pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopListAndCount() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        ShopCategory shopCategory = new ShopCategory();
        ShopCategory parent = new ShopCategory();
        parent.setShopCategoryId(1L);
        shopCategory.setParent(parent);
        shopCondition.setOwner(owner);
        shopCondition.setShopCategory(shopCategory);
        List<Shop> shops = shopDao.queryShopList(shopCondition, 0, 5);
        for (Shop shop : shops) {
            System.out.println(shop);
        }
        int i = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺总数" + i);
    }

    @Test
    public void testQueryByShopId() {
        Long shopId = 20L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop);
    }

    @Test
    public void testInsertShop() {
        Area area = new Area();
        area.setAreaId(1);

        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);

        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);

        Shop shop = new Shop();
        shop.setArea(area);
        shop.setOwner(personInfo);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺2号");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setEnableStatus(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        System.out.println("effectedNum : " + effectedNum);
        //返回的主键key根据xml中的设置直接修改反应在对象shop中
        System.out.println(shop.getShopId());
    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        System.out.println("effectedNum : " + effectedNum);
    }
}
