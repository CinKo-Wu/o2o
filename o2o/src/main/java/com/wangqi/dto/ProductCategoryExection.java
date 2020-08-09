package com.wangqi.dto;

import com.wangqi.enums.ProductCategoryStateEnum;
import com.wangqi.pojo.ProductCategory;
import com.wangqi.pojo.Shop;

import java.util.List;

public class ProductCategoryExection {
    // 结果状态
    private int state;
    // 状态标识（对结果状态的描述）
    private String stateInfo;
    // 店铺数量
    private int count;
    // 操作的店铺(增删改店铺的时候用到)
    private ProductCategory productCategory;
    // shop列表（查询店铺列表的时候使用）
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExection() {
    }

    //操作失败时候使用的构造器
    public ProductCategoryExection(ProductCategoryStateEnum productCategoryStateEnum) {
        this.state = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
    }

    //操作成功时使用的构造器
    public ProductCategoryExection(ProductCategoryStateEnum productCategoryStateEnum, ProductCategory productCategory) {
        this.state = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
        this.productCategory = productCategory;
    }

    //操作成功时使用的构造器
    public ProductCategoryExection(ProductCategoryStateEnum productCategoryStateEnum, List<ProductCategory> productCategoryList) {
        this.state = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
