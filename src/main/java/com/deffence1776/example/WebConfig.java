package com.deffence1776.example;

import com.deffence1776.example.fw.converters.GenericValueObjectConverter;
import com.deffence1776.example.fw.converters.factories.IntConverterFunctionFactory;
import com.deffence1776.example.fw.converters.factories.StringConverterFunctionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new GenericValueObjectConverter(
                new StringConverterFunctionFactory(String.class),
                new IntConverterFunctionFactory(Integer.class)));
    }
}
