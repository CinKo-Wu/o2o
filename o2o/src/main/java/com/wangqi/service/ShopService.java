package com.wangqi.service;

import com.wangqi.dto.ShopExecution;
import com.wangqi.pojo.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File ShopImg);
}
