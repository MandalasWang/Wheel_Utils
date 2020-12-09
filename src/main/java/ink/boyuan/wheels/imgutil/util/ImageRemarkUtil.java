package ink.boyuan.wheels.imgutil.util;

import ink.boyuan.wheels.base.BaseUtil;

import java.awt.Color;
import java.awt.Font;
import java.io.*;


/**
 * Description: 图片水印工具类
 * @author wyy
 * @version 1.0
 */
public class ImageRemarkUtil implements BaseUtil {


    /**
     * 设置水印参数
     * @author wyy
     * @param alpha  透明度
     * @param positionWidth  长度
     * @param positionHeight  高度
     * @param font  大小
     * @param color 颜色
     */
    public static void setRemarkData(float alpha, int positionWidth,
                                     int positionHeight, Font font, Color color){
        ImgWaterRemark.setImageMarkOptions(alpha,positionWidth,positionHeight,font,color);
    }


    /**
     * 给图片添加水印文字 不旋转
     * @author wyy
     * @param logoText   水印文字
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     */
    public static void markImageByText(String logoText, InputStream srcImgPath,
                                       OutputStream targetPath) {
        ImgWaterRemark.markImageByText(logoText, srcImgPath, targetPath, null);
    }

    /**
     * 给图片添加水印文字 旋转
     * @author wyy
     * @param logoText   水印文字
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     * @param degree 目标图片路径
     * @description
     */
    public static void markImageByText(String logoText, InputStream srcImgPath,
                                       OutputStream targetPath,Integer degree) {
        ImgWaterRemark.markImageByText(logoText, srcImgPath, targetPath, degree);
    }


    /**
     * 给图片添加水印文字 不旋转水平水印
     * @author wyy
     * @param logoText   水印文字
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     */
    public static void markImageByText(String logoText, InputStream srcImgPath,
                                       String targetPath) {
        if ("".equals(targetPath)) {
            throw new RuntimeException("目标路径不能为空");
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(targetPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImgWaterRemark.markImageByText(logoText, srcImgPath, outputStream, null);
    }


    /**
     * 给图片添加水印文字 不旋转水平水印
     * @author wyy
     * @param logoText   水印文字
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     * @param degree 旋转角度
     */
    public static void markImageByText(String logoText, InputStream srcImgPath,
                                       String targetPath,Integer degree) {
        if ("".equals(targetPath)) {
            throw new RuntimeException("目标路径不能为空");
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(targetPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImgWaterRemark.markImageByText(logoText, srcImgPath, outputStream, degree);
    }


    /**
     * 给图片添加水印图片 不旋转水平水印
     * @author wyy
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     */
    public static void markImageByIcon(String iconPath, InputStream srcImgPath,
                                       String targetPath) {
        if ("".equals(targetPath)) {
            throw new RuntimeException("目标路径不能为空");
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(targetPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImgWaterRemark.markImageByIcon(iconPath, srcImgPath, outputStream, null);
    }


    /**
     * 给图片添加水印图片 不旋转水平水印
     * @author wyy
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     * @param degree 旋转角度
     */
    public static void markImageByIcon(String iconPath, InputStream srcImgPath,
                                       String targetPath,Integer degree) {
        if ("".equals(targetPath)) {
            throw new RuntimeException("目标路径不能为空");
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(targetPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImgWaterRemark.markImageByIcon(iconPath, srcImgPath, outputStream, degree);
    }

    /**
     * 给图片添加水印图片 水平水印
     * @author wyy
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     */
    public static void markImageByIcon(String iconPath, InputStream srcImgPath,
                                       OutputStream targetPath) {
        ImgWaterRemark.markImageByIcon(iconPath, srcImgPath, targetPath, null);
    }



    /**
     * 给图片添加水印图片 水平水印
     * @author wyy
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     */
    public static void markImageByIcon(String iconPath, InputStream srcImgPath,
                                       OutputStream targetPath,Integer degree) {
        ImgWaterRemark.markImageByIcon(iconPath, srcImgPath, targetPath, degree);
    }

}
