package com.wangqi.dao;

import com.wangqi.pojo.ProductImg;

import java.util.List;

public interface ProductImgDao {

    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 批量删除商品详情图片
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);
}
