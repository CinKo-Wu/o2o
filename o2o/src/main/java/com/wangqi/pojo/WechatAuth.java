package com.wangqi.pojo;

import java.util.Date;

public class WechatAuth {
    //微信id
    private Long wechatAuthId;
    //openid
    private String openId;
    //创建时间
    private Date createTime;
    //关联用户
    private PersonInfo personInfo;

    public WechatAuth() {
    }

    public WechatAuth(Long wechatAuthId, String openId, Date createTime, PersonInfo personInfo) {
        this.wechatAuthId = wechatAuthId;
        this.openId = openId;
        this.createTime = createTime;
        this.personInfo = personInfo;
    }

    @Override
    public String toString() {
        return "WechatAuth{" +
                "wechatAuthId=" + wechatAuthId +
                ", openId='" + openId + '\'' +
                ", createTime=" + createTime +
                ", personInfo=" + personInfo +
                '}';
    }

    public Long getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}
