package com.djcps.djutils.httputil.constant;

/**
 * @author wyy
 * @version 1.0
 * @Classname HttpClientConstant
 * @date 2020/12/3 8:49
 * @description
 **/
public class HttpClientConstant {


    /**
     * 请求通过
     */
    public static final int STATUS_CODE_ACCESS = 200;
    /**
     * 请求禁止失败
     */
    public static final int STATUS_CODE_DENY = 400;
    /**
     * 请求未找到 404
     */
    public static final int STATUS_CODE_NOT_FOUND = 404;
    /**
     * 请求失败
     */
    public static final int STATUS_CODE_INTERNAL_ERROR = 500;

}
