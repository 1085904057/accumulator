package com.spring.accumulator.common.response;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import java.util.List;

/**
 * 用于ResponseResult封装返回结果时，特殊处理
 * 参考文档：https://www.yht7.com/news/169347
 *
 * @author wangrubin
 * @date 2022-08-02
 */
@Configuration
public class ResponseResultConfig extends DelegatingWebMvcConfiguration {
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new MappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}