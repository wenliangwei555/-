package cn.baisee.oa.core.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 温莨 权限的注解
 * @Date 2019/1/20 16:50
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Datasource {
    /**
     * 在dao相应方法添加注解(如：@Datasource(name=DatasourceType)
     *
     * @return
     */
    DatasourceTypes value() default DatasourceTypes.OA;
}
