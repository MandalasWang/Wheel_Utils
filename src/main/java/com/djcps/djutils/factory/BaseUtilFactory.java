package com.djcps.djutils.factory;

import com.djcps.djutils.base.BaseUtil;
import com.djcps.djutils.base.UtilEnums;
import com.djcps.djutils.common.util.CommonUtil;
import com.djcps.djutils.easyexcel.util.ImportExcelUtil;
import com.djcps.djutils.easyexcel.util.ReportExcelUtil;
import com.djcps.djutils.enumutil.EnumUtil;
import com.djcps.djutils.httputil.util.HttpClientUtil;
import com.djcps.djutils.imgutil.util.ImageRemarkUtil;
import com.djcps.djutils.imgutil.util.QrCodeUtil;

/**
 * @program: Myutils
 * @description: //工厂类获取各种工具类的主要入口
 * @author: Mr.Wang
 * @create: 2020-12-06 19:32
 **/
public class BaseUtilFactory {


    /**
     * 工厂方法获取各种工具类实例
     * @param enums 传入方法工具类枚举
     * @return 返回各个方法工具类的具体实现
     * @throws ClassNotFoundException
     */
    public static BaseUtil getRealUtil(UtilEnums enums) throws ClassNotFoundException {


        switch (enums){
            //获取common工具类
            case COMMON_UTIL:
                return new CommonUtil();
                //获取图片工具类
            case IMG_UTIL:
                return new ImageRemarkUtil();
                //获取请求工具类
            case HTTP_UTIL:
                return new HttpClientUtil();
                //获取二维码画图工具类
            case QR_CODE_UTIL:
                return new QrCodeUtil();
                //获取枚举工具类
            case ENUMS_CODE_UTIL:
                return new EnumUtil();
                //获取报表导出工具类
            case EXCEL_WRITE_UTIL:
                return ReportExcelUtil.getInstance();
                //获取报表读取工具类
            case EXCEL_READER_UTIL:
                return ImportExcelUtil.getInstance();
            default:
                throw new ClassNotFoundException();
        }
    }
}
