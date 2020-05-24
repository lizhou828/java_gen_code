package ${basepackage}.enums;

/**
 *
    模块枚举类('wechat'公众号、'mini'小程序、'app'App端、'pc'PC端、'h5'H5端、'allPlatform'全平台)
 * Created by lizhou on 2020/4/20.
 */
public enum ModuleEnums {
    WECHAT_OFFICIAL_ACCOUNT("wechat","微信公众号"),
    MINI_PROGRAM("mini","小程序"),
    APP("app","APP端"),
    PC("pc","PC端"),
    H5("h5","H5端"),
    ALL_PLATFORM("allPlatform","全平台");

    /* 编码 */
    private String code;

    /* 名称 */
    private String name;

    ModuleEnums(String code , String name) {
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

    public static String getName(Integer code) {
        if(null == code || code < 0) {
            return null;
        }
        for(ModuleEnums moduleEnums : ModuleEnums.values()){
            if(null != moduleEnums && code.equals(moduleEnums.getCode())){
                return moduleEnums.getName();
            }
        }
        return null;
    }
}
