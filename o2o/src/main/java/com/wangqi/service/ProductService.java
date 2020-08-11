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
}
