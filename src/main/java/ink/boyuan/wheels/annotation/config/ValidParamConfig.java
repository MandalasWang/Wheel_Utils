package ink.boyuan.wheels.annotation.config;

import lombok.Data;

/**
 * @author wyy
 * @version 1.0
 * @Classname ValidParamConfig
 * @date 2021/1/28 13:46
 * @description
 **/
@Data
public class ValidParamConfig {


    /**
     * 邮件校验格式
     */
     private String emailFormat = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

    /**
     * 手机号校验格式
     */
    private String phoneFormat ="(\\+\\d+)?1[34578]\\d{9}$";

    /**
     * 金额正则 最大支持 999999.99 最多保留两位小数 如果小数位都为0 可不传
     * 正确写法: 1.00  1
     * 错误写法：1.000 9999999
     */
    private String moneyFormat = "^(([1-9][0-9]{0,6})|(0{1}))(\\.\\d{1,2})?$";
}
