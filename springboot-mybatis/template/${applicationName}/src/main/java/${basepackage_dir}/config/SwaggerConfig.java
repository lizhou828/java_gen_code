package ${basepackage}.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        List<Parameter> pars = new ArrayList<Parameter>();

//        //自定义请求头
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        //name表示名称，description表示描述
//        //required表示是否必填，defaultvalue表示默认值
//        ticketPar.name(Constant.JWT_REQUEST_HEADER).description("接口校验")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).defaultValue("").build();
//        //添加完此处一定要把下边的也加上否则不生效
//        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("${basepackage}.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("${applicationName} RESTful API")
                .description("support by swagger-bootstrap-ui")
                .version("1.0")
                .build();
    }
}
