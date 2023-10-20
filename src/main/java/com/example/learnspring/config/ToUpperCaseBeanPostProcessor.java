package com.example.learnspring.config;

import com.example.learnspring.annotation.ToUpperCase;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class ToUpperCaseBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(final Object bean,
                                                  final String beanName) throws BeansException {


        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ToUpperCase.class)) {

                field.setAccessible(true);
                try {
                    ReflectionUtils.setField(field, bean, ((String) field.get(bean)).toUpperCase());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bean;
    }
}
