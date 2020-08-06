package com.wangqi.pojo;

import java.util.Date;

public class Shop {
    //店铺id
    private Long shopId;
    //店铺名
    private String shopName;
    //店铺描述
    private String shopDesc;
    //店铺地址
    private String shopAddr;
    //电话
    private String phone;
    //店铺图片地址
    private String shopImg;
    //店铺优先级权重
    private Integer priority;
    //店铺创建时间
    private Date createTime;
    //店铺修改时间
    private Date lastEditTime;
    //店铺状态：-1：不可用，0：审核中，1：可用
    private Integer enableStatus;
    //超级管理员给店铺的提醒
    private String advice;
    //所在区域
    private Area area;
    //所属用户
    private PersonInfo owner;
    //属于哪个店铺类别
    private ShopCategory shopCategory;
}
