package ink.boyuan.wheels.factory;

import ink.boyuan.wheels.base.BaseUtil;
import ink.boyuan.wheels.base.UtilEnums;
import ink.boyuan.wheels.common.util.CommonUtil;
import ink.boyuan.wheels.easyexcel.util.ImportExcelUtil;
import ink.boyuan.wheels.easyexcel.util.ReportExcelUtil;
import ink.boyuan.wheels.enumutil.EnumUtil;
import ink.boyuan.wheels.httputil.util.HttpClientUtil;
import ink.boyuan.wheels.imgutil.util.ImageRemarkUtil;
import ink.boyuan.wheels.imgutil.util.QrCodeUtil;

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
