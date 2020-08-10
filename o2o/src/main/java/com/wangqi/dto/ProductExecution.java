package com.wangqi.dto;

import com.wangqi.enums.ProductStateEnum;
import com.wangqi.pojo.Product;
import com.wangqi.pojo.Shop;

import java.util.List;

public class ProductExecution {
    // 结果状态
    private int state;
    // 状态标识（对结果状态的描述）
    private String stateInfo;
    // 商品数量
    private int count;
    // 操作的product（增删改商品的时候用）
    private Product product;
    // 获取的product列表
    private List<Product> productList;

    public ProductExecution() {
    }

    //操作失败时，使用的构造器
    public ProductExecution(ProductStateEnum productStateEnum) {
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
    }

    //操作成功时，使用的构造器
    public ProductExecution(ProductStateEnum productStateEnum, Product product) {
        this.product = product;
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
    }

    //操作成功时，使用的构造器
    public ProductExecution(ProductStateEnum productStateEnum, List<Product> productList) {
        this.productList = productList;
        this.state = productStateEnum.getState();
        this.stateInfo = productStateEnum.getStateInfo();
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
