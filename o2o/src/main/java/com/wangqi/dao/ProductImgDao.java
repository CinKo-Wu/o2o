package com.wangqi.dao;

import com.wangqi.pojo.ProductImg;

import java.util.List;

public interface ProductImgDao {

    /**
     * 获得店铺所对应所有详情图
     * @param productId
     * @return
     */
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
     * @return 影响的行数
     */
    int deleteProductImgByProductId(long productId);
}
