package com.djcps.djutils.common.util;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

/**
 * @author wyy
 * @version 1.0
 * @Classname CommonUtil
 * @date 2020/12/3 8:55
 * @description
 **/
public class CommonUtil  {

    /**************************************
     * 基础工具类 包含以下方法
     * 1、MD5加密
     * 2、根据URL地址获取文件输入流
     * 3、MultipartFile 转 File
     * 4、File 转 MultipartFile
     * ...
     * *************************************
     */

    /**
     * md5 加密算法
     * @author wyy
     * @param s
     * @return
     */
    public static String md5(String s) {

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取URL地址获取文件输入流
     * @author wyy
     * @param destUrl 输入URL地址
     * @return 返回输入流
     */
    public static InputStream saveToFile(String destUrl) {
        HttpURLConnection httpUrl;
        try {
            URL url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            return httpUrl.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * MultipartFile 转 File
     * @param file
     * @return
     * @throws IOException
     */
    public static File changeMutiFileToFile(MultipartFile file) throws IOException {
        if(null == file || file.isEmpty() ){
            throw new RuntimeException("无法解析的文件类型或空文件");
        }
        return file.getResource().getFile();
    }


    /**
     * 将file 转 MultipartFile
     * @param file
     * @return
     * @throws IOException
     */
    public static MultipartFile changeFileToMultipartFile(File file) throws IOException {
        if(null == file){
            throw new RuntimeException("无法解析的文件类型或空文件");
        }
        InputStream inputStream = new FileInputStream(file);
        return new MockMultipartFile(file.getName(), inputStream);
    }



}
