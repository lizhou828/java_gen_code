package ${basepackage}.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * springboot系列文章之启动时初始化数据
 * https://blog.csdn.net/weixin_34162695/article/details/88013115
 * Created by lizhou on 2020/5/10.
 */

@Component
public class InitialDataConfig implements ApplicationRunner {
    @Value("${r'${'}profile.env${r'}'}")
    private String env;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner is running ! 当前项目环境env=" +env);
    }

}
