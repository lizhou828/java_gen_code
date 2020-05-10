package ${basepackage}.enums;

/**
 * 所有表的通用逻辑删除字段枚举值
 * Created by lizhou on 2020/4/22.
 */
public enum  DropStateEnum {
    NOT_DELETEED(1,"未删除"),
    DELETEED(2,"已删除");

    /* 编码 */
    private Integer code;

    /* 名称 */
    private String name;

    DropStateEnum(Integer code , String name) {
        this.code = code;
        this.name = name;
    }


    public Integer getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

    public static String getName(Integer code) {
        if(null == code || code < 0) {
            return null;
        }
        for(DropStateEnum dropStateEnum : DropStateEnum.values()){
            if(null != dropStateEnum && code.equals(dropStateEnum.getCode())){
                return dropStateEnum.getName();
            }
        }
        return null;
    }
}
