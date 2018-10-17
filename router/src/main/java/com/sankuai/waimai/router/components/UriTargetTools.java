package com.sankuai.waimai.router.components;

import android.app.Activity;

import com.sankuai.waimai.router.activity.ActivityClassNameHandler;
import com.sankuai.waimai.router.activity.ActivityHandler;
import com.sankuai.waimai.router.common.NotExportedInterceptor;
import com.sankuai.waimai.router.core.UriHandler;
import com.sankuai.waimai.router.core.UriInterceptor;

import java.lang.reflect.Modifier;

/**
 * 跳转目标，支持ActivityClass, ActivityClassName, UriHandler。
 *
 * Created by jzj on 2018/3/26.
 */

public class UriTargetTools {

    public static UriHandler parse(Object target, boolean exported, UriInterceptor... interceptors) {
        UriHandler handler = toHandler(target);
        if (handler != null) {
            if (!exported) {
                handler.addInterceptor(NotExportedInterceptor.INSTANCE);
            }
            handler.addInterceptors(interceptors);
        }
        return handler;
    }

    private static UriHandler toHandler(Object target) {
        if (target instanceof UriHandler) {
            return (UriHandler) target;
        } else if (target instanceof String) {
            return new ActivityClassNameHandler((String) target);
        } else if (target instanceof Class && isValidActivityClass((Class) target)) {
            //noinspection unchecked
            return new ActivityHandler((Class<? extends Activity>) target);
        } else {
            return null;
        }
    }

    private static boolean isValidActivityClass(Class clazz) {
        /*
         *  a.isAssignableFrom(b) 返回true的条件
         *      1. a对象所对应类信息是b对象所对应的类信息的父类或者是父接口，简单理解即a是b的父类或接口
         *      2. a对象所对应类信息与b对象所对应的类信息相同，简单理解即a和b为同一个类或同一个接口
         */
        return clazz != null && Activity.class.isAssignableFrom(clazz)
                && !Modifier.isAbstract(clazz.getModifiers());
    }
}
