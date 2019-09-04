package com.deffence1776.example.fw.converters.factories;


import com.deffence1776.example.fw.converters.ValueObjectConverterFunctionFactory;

import java.lang.reflect.Constructor;

public class IntConverterFunctionFactory extends ValueObjectConverterFunctionFactory<Integer> {

    public IntConverterFunctionFactory(Class<Integer> parameterType) {
        super(parameterType);
    }

    @Override
    public Constructor<Integer> getConstructor(Class targetValueObject) throws NoSuchMethodException {
        try {
            return targetValueObject.getConstructor(int.class);
        } catch (NoSuchMethodException e) {
            return targetValueObject.getConstructor(Integer.class);
        }
    }
    @Override
    public Integer toType(String s) {
        return Integer.parseInt(s);
    }
}
