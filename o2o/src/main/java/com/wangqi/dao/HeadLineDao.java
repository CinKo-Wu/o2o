package com.wangqi.dao;

import com.wangqi.pojo.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {

    /**
     * 根据传入的查询条件（可用状态）查询头条
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition")HeadLine headLineCondition);
}
