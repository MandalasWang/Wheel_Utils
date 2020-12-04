package com.djcps.djutils.imgutil.util;


import com.google.zxing.WriterException;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author wyy
 * @version 1.0
 * @Classname QrCodeUtil
 * @date 2020/12/4 13:33
 * @description
 **/
public class QrCodeUtil {



    /**
     * 生成二维码的方法
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @param outputStream 输出流
     */
    public static void generateQRCodeImge(String content, int width, int height, OutputStream outputStream) throws IOException {
        try {
            QrCode.generateQRCodeImage(content,width,height,outputStream);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码的方法
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @param outputStream 输出流
     */
    public static void generateQRCodeImge(String content, int width, int height, String outputStream) throws IOException {
        try {
            QrCode.generateQRCodeImage(content,width,height,outputStream);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
