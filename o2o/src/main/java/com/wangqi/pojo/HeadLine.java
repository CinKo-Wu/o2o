package com.wangqi.pojo;

import java.util.Date;

public class HeadLine {
    //头条id
    private Long lineId;
    //头条名
    private String lineName;
    //头条链接
    private String lineLink;
    //头条图片
    private String lineImg;
    //头条权重，优先级
    private String priority;
    //0：不可用，1：可用
    private Integer enableStatus;
    //创建时间
    private Date createTime;
    //修改时间
    private Date lastEditTime;

    public HeadLine() {
    }

    public HeadLine(Long lineId, String lineName, String lineLink, String lineImg, String priority, Integer enableStatus, Date createTime, Date lastEditTime) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.lineLink = lineLink;
        this.lineImg = lineImg;
        this.priority = priority;
        this.enableStatus = enableStatus;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
    }

    @Override
    public String toString() {
        return "HeadLine{" +
                "lineId=" + lineId +
                ", lineName='" + lineName + '\'' +
                ", lineLink='" + lineLink + '\'' +
                ", lineImg='" + lineImg + '\'' +
                ", priority='" + priority + '\'' +
                ", enableStatus=" + enableStatus +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineLink() {
        return lineLink;
    }

    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }

    public String getLineImg() {
        return lineImg;
    }

    public void setLineImg(String lineImg) {
        this.lineImg = lineImg;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
