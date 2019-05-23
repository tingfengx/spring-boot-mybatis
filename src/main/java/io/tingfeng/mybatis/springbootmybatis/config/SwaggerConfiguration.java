package io.tingfeng.mybatis.springbootmybatis.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder().supportedSubmitMethods(new String[]{})
                .displayOperationId(true)
                .build();
    }


    @Bean(value = "defaultApi")
    @Order(value = 4)
    public Docket defaultApi() {
        List<Parameter> parameters = Lists.newArrayList();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("defaultApiGroup")
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.tingfeng.mybatis.springbootmybatis.resource"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(parameters);
    }

    /**
     * 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("UI using swagger bootstrap (trial version)")
                .description("This is description")
                .termsOfServiceUrl("https://github.com/ELO1SA")
                .contact("tingfeng.xia@mail.utoronto.ca")
                .version("0.1")
                .build();
    }

}
