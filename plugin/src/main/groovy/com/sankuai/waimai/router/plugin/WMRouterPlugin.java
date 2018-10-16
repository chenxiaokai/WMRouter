package com.sankuai.waimai.router.plugin;

import com.android.build.gradle.BaseExtension;
import com.sankuai.waimai.router.interfaces.Const;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * 插件所做工作：将注解生成器生成的初始化类汇总到ServiceLoaderInit，运行时直接调用ServiceLoaderInit
 *
 * ServiceLoaderInit 是在 plugin 插件中生成的
 */
public class WMRouterPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        //获取自定义插件的参数
        WMRouterExtension extension = project.getExtensions()
                .create(Const.NAME, WMRouterExtension.class);

        WMRouterLogger.info("register transform");
        project.getExtensions().findByType(BaseExtension.class)
                .registerTransform(new WMRouterTransform());

        project.afterEvaluate(p -> WMRouterLogger.setConfig(extension));
    }
}
