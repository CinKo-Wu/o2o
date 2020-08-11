package com.wangqi.service.Impl;

import com.wangqi.dao.ProductDao;
import com.wangqi.dao.ProductImgDao;
import com.wangqi.dto.ProductExecution;
import com.wangqi.enums.ProductStateEnum;
import com.wangqi.exceptions.ProductCategoryOperationException;
import com.wangqi.exceptions.ProductOperationException;
import com.wangqi.pojo.Product;
import com.wangqi.pojo.ProductImg;
import com.wangqi.pojo.Shop;
import com.wangqi.service.ProductService;
import com.wangqi.util.ImageUtil;
import com.wangqi.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Autowired
    ProductImgDao productImgDao;

    /**
     * 1.处理缩略图，获取缩略图相对路径并赋值给product
     * 2.往tb_product写入商品信息，获取productId
     * 3.结合productId批量处理商品详情图
     * 4.将商品详情图列表批量插入tb_shop_img中
     *
     * @param product
     * @param thumbnail      缩略图
     * @param productImgList 详情图
     * @return
     * @throws ProductOperationException
     */
    @Transactional
    public ProductExecution addProduct(Product product, File thumbnail, List<File> productImgList) throws ProductOperationException {
        // 空值判断
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            // 给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            // 默认为上架状态
            product.setEnableStatus(1);
            // 若商品缩略图不为空则添加
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                //创建商品信息
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败" + e.toString());
            }
            if (productImgList != null && productImgList.size() > 0) {
                addProductImgList(product, productImgList);
            }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            //传参为空返回空值错误信息
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    /**
     * 添加商品缩略图
     *
     * @param product
     * @param thumbnail
     */
    private void addThumbnail(Product product, File thumbnail) {
        //获取product所属于shop的相对路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(thumbnailAddr);
    }

    /**
     * 添加商品详情图
     *
     * @param product
     * @param thumbnailList
     */
    private void addProductImgList(Product product, List<File> thumbnailList) {
        //获取shop图片的相对路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        //遍历所有图片文件保存，并且添加进productImg实体类中
        for (File thumbnail : thumbnailList) {
            String imgAddr = ImageUtil.generateNormalThumbnail(thumbnail, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        //批量添加图片
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败" + e.toString());
            }
        }
    }
}
