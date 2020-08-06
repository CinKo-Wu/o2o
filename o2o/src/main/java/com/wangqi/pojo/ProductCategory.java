package com.wangqi.pojo;

import java.util.Date;

public class ProductCategory {
    private Long productCategoryId;
    private Long shopId;
    private String productCategoryName;
    private Integer priority;
    private Date CreateTIme;

    public ProductCategory() {
    }

    public ProductCategory(Long productCategoryId, Long shopId, String productCategoryName, Integer priority, Date createTIme) {
        this.productCategoryId = productCategoryId;
        this.shopId = shopId;
        this.productCategoryName = productCategoryName;
        this.priority = priority;
        CreateTIme = createTIme;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "productCategoryId=" + productCategoryId +
                ", shopId=" + shopId +
                ", productCategoryName='" + productCategoryName + '\'' +
                ", priority=" + priority +
                ", CreateTIme=" + CreateTIme +
                '}';
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTIme() {
        return CreateTIme;
    }

    public void setCreateTIme(Date createTIme) {
        CreateTIme = createTIme;
    }
}

