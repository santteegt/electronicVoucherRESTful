package com.buzz.persistence.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

public class BeanUtils {
	
	public static final Logger log = Logger.getLogger(BeanUtils.class);
	
	public static void copyFields(Object source, Object target)throws Exception {
		for(Field field:source.getClass().getDeclaredFields()) {
			Method getterMethod;
			String getter = "get" + WordUtils.
					capitalize(field.getName(), new char[]{'_'})
					.replaceAll("_", "");
			Method setterMethod;
			String setter = "set" + WordUtils.
					capitalize(field.getName(), new char[]{'_'})
					.replaceAll("_", "");
			try{
				getterMethod = source.getClass().getMethod(getter);
			}catch(NoSuchMethodException e) {
				log.info("Getter Not Found (" + getter + ") of " + source.getClass().getCanonicalName());
				continue;
			}
			Object value = getterMethod.invoke(source);
			try{
				setterMethod = target.getClass().getMethod(setter, 
					getterMethod.getReturnType());
			}catch(NoSuchMethodException e) {
				log.info("Setter Not Found (" + setter + ") of " + target.getClass().getCanonicalName());
				continue;
			}
			setterMethod.invoke(target, value);
		}
	}

}
