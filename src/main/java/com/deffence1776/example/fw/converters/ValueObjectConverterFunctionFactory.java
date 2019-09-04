package com.deffence1776.example.fw.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public abstract class ValueObjectConverterFunctionFactory<T> {

    private Class<T> parameterType;

    public ValueObjectConverterFunctionFactory(Class<T> parameterType) {
        this.parameterType = parameterType;
    }

    Function<String, Object> getValueObjectConverterFunction(Class targetValueObject) {
        try {
            var constructor = getConstructor(targetValueObject);
            var fun = new Function<String, Object>() {
                @Override
                public Object apply(String s) {
                    try {
                        return constructor.newInstance(toType(s));
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalStateException(e);
                    }
                }

            };
            return fun;
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    Class<T> getType() {
        return parameterType;
    }

    protected abstract Constructor<T> getConstructor(Class targetValueObject) throws NoSuchMethodException;

    protected abstract T toType(String s);
}
