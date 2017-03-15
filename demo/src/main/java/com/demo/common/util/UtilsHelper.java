package com.demo.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Objects;

import com.demo.exception.IllegalFieldException;

/**
 * 工具类
 */
public abstract class UtilsHelper {

	/**
	 * 字符串首字母转大写
	 * @param str
	 * @return
	 */
	public static String toUpperCaseFirstOne(String str) {
		if (Character.isUpperCase(str.charAt(0))) {
			return str;
		}
		return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
	}
	
	/**
	 * 获取属性的get方法名
	 * @param field
	 * @return
	 */
	public static String getGetMethodName(String field) {
		return "get" + toUpperCaseFirstOne(field);
	}
	
	/**
	 * 反射通过属性名获取属性值, 改属性必须有get方法
	 * @param o
	 * @param field
	 * @return
	 */
	public static Object getValueByFieldName(Object o, String field) {
		Objects.requireNonNull(o);
		try {
			Method method = o.getClass().getMethod(getGetMethodName(field));
			return method.invoke(o);
		} catch (Exception e) {
			throw new IllegalFieldException(o.getClass() + " 找不到 [" + field + "]属性 get方法");
		}
	}
	
	/**
	 * 四舍五入保留两位小数
	 * @param value
	 * @return
	 */
	public static double getRound(double value){
		BigDecimal decimal = new BigDecimal(value);
		return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
	}

}
