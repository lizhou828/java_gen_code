package ${basepackage}.config;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;


@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {


    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        //1.需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 不忽略对象属性中的null值
        fastJsonConfig.setSerializerFeatures(
//                WriteMapNullValue,  //是否输出值为null的字段,默认为false
                WriteNullListAsEmpty,  //List字段如果为null,输出为[],而非null
                WriteNullStringAsEmpty, //字符类型字段如果为null,输出为"",而非null
                WriteNullBooleanAsFalse //Boolean字段如果为null,输出为false,而非null
        );
        //3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters当中.

        ValueFilter valueFilter = new ValueFilter() {
            @Override
            public Object process(Object obj, String s, Object v) {
                if(!StringUtils.isEmpty(s) && s.toLowerCase().contains("time") && v == null){
                    return "";
                }
                return v;
            }
        };
        fastJsonConfig.setSerializeFilters(valueFilter);

        converters.add(fastConverter);
        converters.add(responseBodyConverter());
    }

}
