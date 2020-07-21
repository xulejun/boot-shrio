package com.xlj.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author XuLeJun
 * @Date 2020/7/17 19:23
 *
 * 自定义realm中与数据库交接的工具类，通过工厂拿到bean
 */

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // 根据bean名字获取工厂中指定的对象
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}
