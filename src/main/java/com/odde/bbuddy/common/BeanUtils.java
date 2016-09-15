package com.odde.bbuddy.common;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;

import static com.odde.bbuddy.common.Formats.DAY;
import static org.apache.commons.beanutils.BeanUtilsBean.getInstance;

public class BeanUtils {
    public static void copyProperties(Object target, Object source) {
        BeanUtilsBean beanUtilsBean = getInstance();
        beanUtilsBean.getConvertUtils().register(dateConverter(), String.class);
        try {
            beanUtilsBean.copyProperties(target, source);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }

    private static DateConverter dateConverter() {
        DateConverter converter = new DateConverter();
        converter.setPattern(DAY);
        return converter;
    }
}
