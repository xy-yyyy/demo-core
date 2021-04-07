package com.demo.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 14:07 2020/9/24
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/alipay/appTest").setViewName("AppPay");
        registry.addViewController("/alipay/wapTest").setViewName("WapPay");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //        registry.addResourceHandler("/resources/**").

        registry.addResourceHandler("/**").
                addResourceLocations("classpath:/static/").
                addResourceLocations("classpath:/static/images/").
                addResourceLocations("classpath:/public/").
                addResourceLocations("classpath:/resources/").
                addResourceLocations("classpath:/META-INF/resources/").
                addResourceLocations("/webjars/**").
                addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(10000);
        configurer.registerCallableInterceptors(timeoutInterceptor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }
}
