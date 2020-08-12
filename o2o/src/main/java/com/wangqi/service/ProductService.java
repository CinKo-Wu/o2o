package com.wangqi.service;

import com.wangqi.dto.ProductExecution;
import com.wangqi.exceptions.ProductCategoryOperationException;
import com.wangqi.exceptions.ProductOperationException;
import com.wangqi.pojo.Product;

import java.io.File;
import java.util.List;

public interface ProductService {

    /**
     * 添加商品信息以及图片处理
     * @param product
     * @param thumbnail 缩略图
     * @param productImgList 详情图
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, File thumbnail, List<File> productImgList) throws ProductOperationException;

    /**
     * 通过商品id查询唯一的商品信息
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 修改商品信息以及图片处理
     * @param product
     * @param thumbnail
     * @param productList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, File thumbnail, List<File> productList)
            throws ProductOperationException;

    /**
     * 查询商品列表并分页，可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
     * @param productContion
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productContion, int pageIndex, int pageSize);
}
