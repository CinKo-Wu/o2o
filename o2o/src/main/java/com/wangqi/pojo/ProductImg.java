package com.wangqi.pojo;

import java.util.Date;

public class ProductImg {
    //图片id
    private Long productImgId;
    //图片地址
    private String imgAddr;
    //图片描述
    private String imgDesc;
    //图片显示权重
    private Integer priority;
    //图片创建时间
    private Date createTime;
    //所对应的产品id
    private Long productId;

    public ProductImg() {
    }

    public ProductImg(Long productImgId, String imgAddr, String imgDesc, Integer priority, Date createTime, Long productId) {
        this.productImgId = productImgId;
        this.imgAddr = imgAddr;
        this.imgDesc = imgDesc;
        this.priority = priority;
        this.createTime = createTime;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductImg{" +
                "productImgId=" + productImgId +
                ", imgAddr='" + imgAddr + '\'' +
                ", imgDesc='" + imgDesc + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", productId=" + productId +
                '}';
    }

    public Long getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
