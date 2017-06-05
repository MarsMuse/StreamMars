package com.beta.prop.util;

import java.lang.reflect.Field;

/**
 * 
 * @ClassName:  ReflectHandler   
 * @Description:TODO(反射处理器)   
 * @author: FireMonkey
 * @date:   2017年6月5日 下午4:26:00   
 *     
 * @Copyright: 2017 
 *
 */
public class ReflectHandler {
    
    
    /**
     * 
     * @Title: getFieldByFieldName   
     * @Description: TODO(获取obj对象fieldName的Field.)   
     * @param: @param obj
     * @param: @param fieldName
     * @param: @return      
     * @return: Field      
     * @throws
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class;
             superClass = superClass.getSuperclass())
        {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                //忽略这个异常
            }
        }
        return null;
    }
    
    /**
     * 
     * @Title: getValueByFieldName   
     * @Description: TODO(根据对象与变量名获取到成员变量)   
     * @param: @param obj
     * @param: @param fieldName
     * @param: @return
     * @param: @throws NoSuchFieldException
     * @param: @throws IllegalAccessException      
     * @return: Object      
     * @throws
     */
    public static Object getValueByFieldName(Object obj, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if(field!=null){
            if (field.isAccessible()) {
                value = field.get(obj);
            }else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }
    
    /**
     * 
     * @Title: setValueByFieldName   
     * @Description: TODO(设置该对象对应成员变量的值)   
     * @param: @param obj
     * @param: @param fieldName
     * @param: @param value
     * @param: @throws NoSuchFieldException
     * @param: @throws IllegalAccessException      
     * @return: void      
     * @throws
     */
    public static void setValueByFieldName(Object obj, String fieldName,
            Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }
}
