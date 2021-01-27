package ink.boyuan.wheels.enumutil.enums;

/**
 * 时间格式枚举
 *
 * @author yhc
 * @date 2019-10-17 19:44
 */
public enum FormatEnum {
    /**
     * format
     */
    DATE_TIME("yyyy-MM-dd HH:mm:ss"),
    DATE("yyyy-MM-dd"),
    TIME("HH:mm:ss"),
    YEAR_MONTH("yyyy-MM"),
    ;

    private String code;

    FormatEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
