package com.wangqi.util;

import com.wangqi.exceptions.ShopOperationException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    //获得水印图片
    public static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    //时间格式，用于生成随机文件名
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    //随机数对象，用于生成随机文件名
    private static final Random r = new Random();
    //记录日志
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);


    /**
     * 众所周知,文件上传spring使用CommonsMultipartFile来接收上传过来的文件，
     * 而generateThumbnails方法中的第一个入参我们已经调整为了File
     * （主要是为了方便service层进行单元测试，
     * 在service层无法初始化CommonsMultipartFile，只能在前端传入的时候初始化,
     * 我们从底往上搭建项目，还不具备页面,因此做了适当的改造）
     * @param cFile
     * @return
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
        File file = null;
        try {
            // 获取前端传递过来的文件名
            file = new File(cFile.getOriginalFilename());
            // 将cfile转换为file
            cFile.transferTo(file);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 处理用户传递的图，并返回新生成图片的相对路径
     * @param thumbnail 图片文件
     * @param targetAddr 图片存储路径
     * @return
     */
    public static String generateThumbnail(File thumbnail, String targetAddr) {
        //获取不重复的文件名
        String realFileName = getRandomFileName();
        //获取文件的扩展名
        String extension = getFileExtension(thumbnail);
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(路径/文件名.拓展名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        // 获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
        // 调用Thumbnails生成带有水印的图片
        try {
            Thumbnails.of(thumbnail).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            //此处图片记载失败则相对路径返回null，不进行修改。或者在这里也抛一个runtime异常出去？
            relativeAddr = null;
        }
        return relativeAddr;
    }

    /**
     * 处理用户传递的商品详情图，并返回新生成图片的相对路径
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateNormalThumbnail(File thumbnail, String targetAddr) {
        //获取不重复的文件名
        String realFileName = getRandomFileName();
        //获取文件的扩展名
        String extension = getFileExtension(thumbnail);
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(路径/文件名.拓展名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        // 获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
        // 调用Thumbnails生成带有水印的图片
        try {
            Thumbnails.of(thumbnail).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            //此处图片记载失败则相对路径返回null，不进行修改。或者在这里也抛一个runtime异常出去？
            relativeAddr = null;
        }
        return relativeAddr;
    }


    /**
     * 创建目标文件所在目录
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {    //如果不存在，则创建目录
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的拓展名
     * @param thumbnail
     * @return
     */
    private static String getFileExtension(File thumbnail) {
        String originalFilename= thumbnail.getName();
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    /**
     * 生成随机文件名,当前年月日时分秒+五位随机数
     * @return
     */
    private static String getRandomFileName() {
        //获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = simpleDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * storePath判断是文件还是目录路径，
     * 如果storePath是文件路径则删除该文件，
     * 如果是目录路径则删除该目录下的所有文件。
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File file[] = fileOrPath.listFiles();
                for (int i = 0; i < file.length; i++)
                    file[i].delete();
            }
            fileOrPath.delete();
        }

    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("E:/javaProjects/image/xiaohuangya.jpg")).size(200,200)
                .watermark(Positions.BOTTOM_RIGHT,
                        ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f).outputQuality(0.8f)
                .toFile("E:/javaProjects/image/xiaohuangyanew.jpg");
    }
}
