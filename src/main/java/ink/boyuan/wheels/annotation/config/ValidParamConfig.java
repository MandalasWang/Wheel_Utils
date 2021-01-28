package ink.boyuan.wheels.annotation.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
}
