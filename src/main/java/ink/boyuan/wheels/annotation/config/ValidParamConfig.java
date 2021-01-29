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
     * 金额正则
     */
    private String moneyFormat = "^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$";
}
