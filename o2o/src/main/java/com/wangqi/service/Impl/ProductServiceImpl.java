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
import com.wangqi.util.PageCalculator;
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


    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

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
     * 1.若缩略图参数有值，则处理缩略图（此时若原先有缩略图，则删除），获取缩略图相对路径并赋值给product
     * 2.往tb_product写入商品信息，获取productId
     * 3.结合productId批量处理商品详情图，如果参数有详情图，则删除原有的所有详情图
     * 4.将商品详情图列表批量插入tb_shop_img中
     * @param product
     * @param thumbnail
     * @param productList
     * @return
     * @throws ProductOperationException
     */
    @Transactional
    public ProductExecution modifyProduct(Product product, File thumbnail, List<File> productList) throws ProductOperationException {
        //空值判断
        if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //设置商品默认属性
            product.setLastEditTime(new Date());
            // 若商品缩略图不为空 且原有缩略图不为空，则删除原有缩略图并添加
            if(thumbnail != null) {
                //先获取一遍原有信息
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if(tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            //如果有新存入的商品详情图，则将原先的删除，并添加新的图片
            if(productList != null && productList.size() > 0) {
                // 如果详情图表有值，则删除原来的所有详情图
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productList);
            }
            try {
                //更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    public ProductExecution getProductList(Product productContion, int pageIndex, int pageSize) {
        //页码转换成数据库的行码
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productContion, rowIndex, pageSize);
        //基于同样的查询条件查询条件下的商品总数
        int count = productDao.queryProductCount(productContion);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    /**
     * 删除productId商品的所有详情图
     * @param productId
     */
    private void deleteProductImgList(Long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        //删除原来的图片
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
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
