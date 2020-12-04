package com.djcps.djutils.imgutil.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author wyy
 * @version 1.0
 * @Classname ImgRemark
 * @date 2020/12/4 13:08
 * @description 图片增加水印文字、水印图片
 **/
public class ImgRemark {



    /**
     * 水印透明度
     */
    private static float alpha = 0.6f;

    /**
     * 水印横向位置
     */
    private static int positionWidth = 150;
    /**
     * 水印纵向位置
     */
    private static int positionHeight = 300;
    /**
     * 水印文字字体
     */
    private static Font font = new Font("宋体", Font.BOLD, 77);
    /**
     * 水印文字颜色
     */
    private static Color color = Color.LIGHT_GRAY;

    /**
     * 设置参数方法
     *
     * @param alpha          水印透明度
     * @param positionWidth  水印横向位置
     * @param positionHeight 水印纵向位置
     * @param font           水印文字字体
     * @param color          水印文字颜色
     */
    public static void setImageMarkOptions(float alpha, int positionWidth,
                                           int positionHeight, Font font, Color color) {
        if (alpha > 0) {
            ImgRemark.alpha = alpha;
        }
        if (positionWidth != 0) {
            ImgRemark.positionWidth = positionWidth;
        }
        if (positionHeight != 0) {
            ImgRemark.positionHeight = positionHeight;
        }
        if (font != null) {
            ImgRemark.font = font;
        }
        if (color != null) {
            ImgRemark.color = color;
        }
    }


    /**
     * 给图片添加水印图片、可设置水印图片旋转角度
     *
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     * @param degree     水印图片旋转角度
     */
    public static void markImageByIcon(String iconPath, InputStream srcImgPath,
                                       OutputStream targetPath, Integer degree) {
        OutputStream os = null;
        try {

            Image srcImg = ImageIO.read(srcImgPath);

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 3、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }

            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);

            // 5、得到Image对象。
            Image img = imgIcon.getImage();

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));

            // 6、水印图片的位置
            g.drawImage(img, positionWidth, positionHeight, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            // 7、释放资源
            g.dispose();

            // 8、生成图片
            ImageIO.write(buffImg, "JPG", targetPath);

            System.out.println("图片完成添加水印图片");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != targetPath) {
                    targetPath.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     *
     * @param logoText
     * @param srcImgPath
     * @param targetPath
     * @param degree
     */
    public static void markImageByText(String logoText, InputStream srcImgPath,
                                       OutputStream targetPath, Integer degree) {

        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(srcImgPath);
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText, positionWidth, positionHeight);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            ImageIO.write(buffImg, "JPG", targetPath);

            System.out.println("图片完成添加水印文字");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != srcImgPath) {
                    srcImgPath.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != targetPath) {
                    targetPath.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
