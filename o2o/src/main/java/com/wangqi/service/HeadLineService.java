package com.wangqi.service;

import com.wangqi.pojo.HeadLine;

import java.util.List;

public interface HeadLineService {
    /**
     * 根据传入的条件返回指定的头条列表
     * @param headLineCondition
     * @return
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
