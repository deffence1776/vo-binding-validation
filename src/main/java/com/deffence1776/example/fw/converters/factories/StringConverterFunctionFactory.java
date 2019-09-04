package com.deffence1776.example.fw.converters.factories;


import com.deffence1776.example.fw.converters.ValueObjectConverterFunctionFactory;

import java.lang.reflect.Constructor;

public class StringConverterFunctionFactory extends ValueObjectConverterFunctionFactory<String> {

    public StringConverterFunctionFactory(Class<String> parameterType) {
        super(parameterType);
    }

    @Override
    public Constructor<String> getConstructor(Class targetValueObject) throws NoSuchMethodException {
        return targetValueObject.getConstructor(String.class);
    }

    @Override
    public String toType(String s) {
        return s;
    }
}
