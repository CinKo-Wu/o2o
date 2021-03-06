package com.wangqi.service.Impl;

import com.wangqi.dao.AreaDao;
import com.wangqi.pojo.Area;
import com.wangqi.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
