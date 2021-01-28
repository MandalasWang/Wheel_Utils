package ink.boyuan.wheels.common.util;

import ink.boyuan.wheels.common.enums.ThreadPoolEnum;
import ink.boyuan.wheels.common.enums.TimeStampType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.concurrent.*;

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
     * 建议：如何区分类型 看程序计算量
     * 创建线程池—分CPU密集型（主要计算） IO密集型（主IO）
     *
     * @param poolEnum 线程池枚举类型
     */
    public static ExecutorService getThreadPool(ThreadPoolEnum poolEnum) {
        int coreCount = Runtime.getRuntime().availableProcessors();
        switch (poolEnum) {
            case POOL_FOR_CPU_TYPE:
                coreCount += 1;
                break;
            case POOL_FOR_IO_TYPE:
                coreCount = (int) (coreCount / (1 - 0.9));
                break;
            default:
                break;
        }
        return new ThreadPoolExecutor(coreCount,
                coreCount,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
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


    /**
     * 生成当前时间的时间戳
     * @return
     */
    public static String getCurrentTimeStamp(TimeStampType stampType){
        long  timeNew ;
        switch (stampType){
            case MILLIS_TIME_STAMP:
                // 13位数的时间戳
               timeNew =  System.currentTimeMillis();
                break;
            case SECOND_TIME_STAMP:
                // 10位数的时间戳
               timeNew = System.currentTimeMillis()/ 1000;
                break;
                default:
                    return "";
        }
        return String.valueOf(timeNew);
    }



}
