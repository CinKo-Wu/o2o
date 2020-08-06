CREATE SCHEMA `o2o` DEFAULT CHARACTER SET utf8;

use o2o;

CREATE TABLE `o2o`.`tb_area` (
  `area_id` INT NOT NULL AUTO_INCREMENT,
  `area_name` VARCHAR(200) NOT NULL,
  `priority` INT ZEROFILL NOT NULL DEFAULT 0,
  `create_time` DATETIME NULL DEFAULT NULL,
  `last_edit_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`area_id`, `area_name`),
  UNIQUE INDEX `area_name_UNIQUE` (`area_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `o2o`.`tb_person_info` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL DEFAULT NULL,
  `profile_img` VARCHAR(1024) NULL DEFAULT NULL,
  `email` VARCHAR(1024) NULL DEFAULT NULL,
  `gender` VARCHAR(2) NULL DEFAULT NULL,
  `enable_status` INT NOT NULL DEFAULT 0 COMMENT '0：禁止使用商城，1：允许使用商城',
  `user_type` INT NOT NULL DEFAULT 1 COMMENT '1：顾客，2：店家，3：超级管理员',
  `create_time` DATETIME NULL DEFAULT NULL,
  `last_edit_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `o2o`.`tb_wechat_auth` (
  `wechat_auth_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `open_id` VARCHAR(1024) NOT NULL,
  `create_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  INDEX `fk_wechatauth_profile_idx` (`user_id` ASC),
  CONSTRAINT `fk_wechatauth_profile`
    FOREIGN KEY (`user_id`)
    REFERENCES `o2o`.`tb_person_info` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `o2o`.`tb_local_auth` (
  `local_auth_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `username` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `create_time` DATETIME NULL DEFAULT NULL,
  `last_edit_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_localauth_profile_idx` (`user_id` ASC),
  CONSTRAINT `fk_localauth_profile`
    FOREIGN KEY (`user_id`)
    REFERENCES `o2o`.`tb_person_info` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `o2o`.`tb_head_line` (
  `line_id` INT NOT NULL AUTO_INCREMENT,
  `line_name` VARCHAR(1000) NULL DEFAULT NULL,
  `line_link` VARCHAR(2000) NOT NULL,
  `line_img` VARCHAR(2000) NOT NULL,
  `priority` INT NULL DEFAULT NULL,
  `enable_status` INT NULL DEFAULT 0,
  `create_time` DATETIME NULL DEFAULT NULL,
  `last_edit_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`line_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `o2o`.`tb_shop_category` (
  `shop_category_id` INT NOT NULL AUTO_INCREMENT,
  `shop_category_name` VARCHAR(100) NOT NULL DEFAULT '',
  `shop_category_desc` VARCHAR(1000) NULL DEFAULT '',
  `shop_category_img` VARCHAR(2000) NULL DEFAULT NULL,
  `priority` INT NULL DEFAULT 0,
  `create_time` DATETIME NULL DEFAULT NULL,
  `last_edit_time` DATETIME NULL DEFAULT NULL,
  `parent_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `o2o`.`tb_shop` (
  `shop_id` INT NOT NULL AUTO_INCREMENT,
  `owner_id` INT NOT NULL COMMENT '店铺创建人',
  `area_id` INT NULL DEFAULT NULL,
  `shop_category_id` INT NULL DEFAULT NULL,
  `shop_name` VARCHAR(256) NOT NULL,
  `shop_desc` VARCHAR(1024) NULL DEFAULT NULL,
  `shop_addr` VARCHAR(200) NULL DEFAULT NULL,
  `phone` VARCHAR(128) NULL DEFAULT NULL,
  `shop_img` VARCHAR(1024) NULL DEFAULT NULL,
  `priority` INT NULL DEFAULT 0,
  `create_time` DATETIME NULL DEFAULT NULL,
  `last_edit_time` DATETIME NULL DEFAULT NULL,
  `enable_status` INT NOT NULL DEFAULT 0,
  `advice` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  INDEX `fk_shop_area_idx` (`area_id` ASC),
  INDEX `fk_shop_profile_idx` (`owner_id` ASC),
  INDEX `fk_shop_shopcate_idx` (`shop_category_id` ASC),
  CONSTRAINT `fk_shop_area`
    FOREIGN KEY (`area_id`)
    REFERENCES `o2o`.`tb_area` (`area_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shop_profile`
    FOREIGN KEY (`owner_id`)
    REFERENCES `o2o`.`tb_person_info` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shop_shopcate`
    FOREIGN KEY (`shop_category_id`)
    REFERENCES `o2o`.`tb_shop_category` (`shop_category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `o2o`.`tb_product_category` (
  `product_category_id` INT NOT NULL AUTO_INCREMENT,
  `product_category_name` VARCHAR(100) NOT NULL,
  `priority` INT NULL DEFAULT 0,
  `create_time` DATETIME NULL DEFAULT NULL,
  `shop_id` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_category_id`),
  INDEX `fk_procate_shop_idx` (`shop_id` ASC),
  CONSTRAINT `fk_procate_shop`
    FOREIGN KEY (`shop_id`)
    REFERENCES `o2o`.`tb_shop` (`shop_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE `o2o`.`tb_product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(100) NOT NULL,
  `product_desc` VARCHAR(2000) NULL DEFAULT NULL,
  `img_addr` VARCHAR(2000) NULL DEFAULT '',
  `normal_price` VARCHAR(100) NULL DEFAULT NULL,
  `promotion_price` VARCHAR(100) NULL DEFAULT NULL,
  `priority` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NULL DEFAULT NULL,
  `last_edit_time` DATETIME NULL DEFAULT NULL,
  `enable_status` INT NULL DEFAULT 0,
  `product_category_id` INT NULL DEFAULT NULL,
  `shop_id` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`),
  INDEX `fk_product_procate_idx` (`product_category_id` ASC),
  INDEX `fk_product_shop_idx` (`shop_id` ASC),
  CONSTRAINT `fk_product_procate`
    FOREIGN KEY (`product_category_id`)
    REFERENCES `o2o`.`tb_product_category` (`product_category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_shop`
    FOREIGN KEY (`shop_id`)
    REFERENCES `o2o`.`tb_shop` (`shop_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `o2o`.`tb_product_img` (
  `product_img_id` INT NOT NULL AUTO_INCREMENT,
  `img_addr` VARCHAR(2000) NOT NULL,
  `img_desc` VARCHAR(2000) NULL DEFAULT NULL,
  `priority` INT NULL DEFAULT 0,
  `create_time` DATETIME NULL DEFAULT NULL,
  `product_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  INDEX `fk_proimg_product_idx` (`product_id` ASC),
  CONSTRAINT `fk_proimg_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `o2o`.`tb_product` (`product_id`)
    ON DELETE NO ACTION
ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
