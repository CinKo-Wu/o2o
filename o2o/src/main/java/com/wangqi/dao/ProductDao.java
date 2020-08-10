package com.wangqi.dao;

import com.wangqi.pojo.Product;

public interface ProductDao {

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);
}
