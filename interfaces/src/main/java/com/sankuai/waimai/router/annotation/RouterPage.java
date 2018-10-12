package com.sankuai.waimai.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定一个内部页面跳转，此注解可以用在Activity和UriHandler上
 *
 * Created by jzj on 2018/3/19.
 */

//RetentionPolicy.SOURCE. 注解保留在源代码中，但是编译的时候会被编译器所丢弃。比如@Override, @SuppressWarnings
//RetentionPolicy.CLASS. 这是默认的policy。注解会被保留在class文件中，但是在运行时期间就不会识别这个注解。
//RetentionPolicy.RUNTIME. 注解会被保留在class文件中，同时运行时期间也会被识别。所以可以使用反射机制获取注解信息。比如@Deprecated
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface RouterPage {

    /**
     * path
     */
    String[] path();

    /**
     * 要添加的interceptors
     */
    Class[] interceptors() default {};
}
