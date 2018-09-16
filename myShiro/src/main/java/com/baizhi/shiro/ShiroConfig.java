package com.baizhi.shiro;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/12 0012.
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        Map<String,String> map=new HashMap<>();
        map.put("/index.jsp","anon");//匿名资源 任何人都可以访问
        map.put("/user/shiro","anon");
        map.put("/**","authc");//认证资源 需要认证才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/user/login.jsp");//设置shiro可以识别的登录界面
        shiroFilterFactoryBean.setSecurityManager(securityManager);//把安全工具类交给 过滤工厂
        return shiroFilterFactoryBean;
    }
    @Bean
    public SecurityManager getSecurityManager(Realm realm,RedisCacheManager redisCacheManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);//让shiro 默认按我们的复写的AuthorizingRealm类来运行
        securityManager.setCacheManager(redisCacheManager);//让shiro 使用redis来做缓存实现
        return securityManager;
    }
    @Bean
    public Realm getRealm(CredentialsMatcher credentialsMatcher){//以后注入或者形参注入的时候 用到的Realm Spring会从这里拿到 Realm对象,因为Realm是外来户不是Spring本地的
        MyRealm myRealm=new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher);
        return myRealm;
    }
    @Bean
    public CredentialsMatcher getCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//为HashedCredentialsMatcher指定加密方式
        hashedCredentialsMatcher.setHashIterations(1024);//指定散列次数
        return  hashedCredentialsMatcher;
    }
    //配置redisManager
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        //redisManager.setHost(host);//设置需要连接的redisIP
       // redisManager.setPort(port);//设置redis 端口号
        redisManager.setExpire(1800);// 配置过期时间
        // redisManager.setTimeout(timeout);
        // redisManager.setPassword(password);
        return redisManager;
    }
    //RedisCachManager 实现了CacheManager
    @Bean
    public RedisCacheManager cacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }
}
