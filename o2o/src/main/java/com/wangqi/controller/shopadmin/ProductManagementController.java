package com.wangqi.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangqi.dto.ProductExecution;
import com.wangqi.enums.ProductStateEnum;
import com.wangqi.exceptions.ProductOperationException;
import com.wangqi.pojo.Product;
import com.wangqi.pojo.Shop;
import com.wangqi.service.ProductService;
import com.wangqi.util.CodeUtil;
import com.wangqi.util.HttpServletRequestUtil;
import com.wangqi.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
    @Autowired
    private ProductService productService;

    //支持上传商品详情图的最大数量
    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String,Object> modelMap = new HashMap<String, Object>();
        //验证码校验
        if(!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误");
            return modelMap;
        }
        //接收前端参数的变量的初始化，包括商品、缩略图、详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        MultipartHttpServletRequest multipartHttpServletRequest = null;
        //缩略图和详情图列表
        File thumbnail = null;
        List<File> productImgList = new ArrayList<File>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            //若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
            if(multipartResolver.isMultipart(request)) {
                multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                thumbnail = ImageUtil.transferCommonsMultipartFileToFile(
                        (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail"));
                for (int i = 0; i < IMAGEMAXCOUNT; i++) {
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartHttpServletRequest
                            .getFile("productImg" + i);
                    if (productImgFile != null) {
                        productImgList.add(ImageUtil.transferCommonsMultipartFileToFile(productImgFile));
                    } else {
                        break;
                    }
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "上传图片不能为空");
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        try {
            String productStr = HttpServletRequestUtil.getString(request, "productStr");
            //尝试获取前端传过来的表单string流并将其转换成Product实体类
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //若Product信息、缩略图、以及详情图列表为非空，则开始进行商品添加操作
        if(product != null && thumbnail != null && productImgList.size() > 0) {
            try {
                // 从session中获取当前店铺的Id并赋值给product，减少对前端的依赖
                Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                //执行添加操作
                ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
                if(pe.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            } catch (ProductOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg",e.getMessage());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg","请输入商品信息");
        }
        return modelMap;
    }
}
