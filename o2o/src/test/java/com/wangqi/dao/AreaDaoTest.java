package com.wangqi.dao;

import com.wangqi.BaseTest;
import com.wangqi.pojo.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AreaDaoTest extends BaseTest {
    //方法一：自动装配
    //方法二：new ClassPathXmlApplicationContext("").getBean("areaDao")
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> areaList = areaDao.queryArea();
        for (Area area : areaList) {
            System.out.println(area);
        }
    }
}
