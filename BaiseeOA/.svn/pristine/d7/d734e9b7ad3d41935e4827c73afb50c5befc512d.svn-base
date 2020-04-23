package cn.baisee.oa.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Role {
	String[] value() default {}; //数组 当中 放入 我们菜单 menu_code ZDGL 一个 code 对应一个 url  对应一个功能
	boolean ignore() default false;//这个功能是否要进行权限检查  登录这个  检查字典是否存在 ajax ignore=true
	boolean writeLog() default false;//写日志
}
