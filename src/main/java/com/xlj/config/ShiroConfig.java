package com.xlj.config;

import com.xlj.cache.RedisCacheManager;
import com.xlj.shiro.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author XuLeJun
 * @Date 2020/7/17 14:36
 * <p>
 * 用来整合shiro框架相关的配置类
 */

@Configuration
public class ShiroConfig {

    // 1.创建ShiroFilter————负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 配置系统受限资源 annno
        // 配置系统公共资源 authc
        Map<String, String> map = new HashMap<>();
        map.put("/register.jsp", "anon");       // anon设置为公共资源
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
//        map.put("/index", "authc");
        map.put("/**", "authc");           // /**表示所有请求页面都需求认证和授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//        shiroFilterFactoryBean.setLoginUrl("/login.jsp");     // 默认认证界面路径

        return shiroFilterFactoryBean;
    }

    // 2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    // 3.创建自定义realm
    @Bean
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();

        // 修改凭证校验匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法为md5
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        // 开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());
        customerRealm.setCachingEnabled(true);  // 开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true);    // 开启认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache");
        customerRealm.setAuthorizationCachingEnabled(true); // 开启授权缓存
        customerRealm.setAuthorizationCacheName("authorizationCache");




        return customerRealm;
    }

}
