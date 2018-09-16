package com.baizhi.shiro;

import com.baizhi.dao.EmpDao;
import com.baizhi.entity.Emp;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2018/9/13 0013.
 */

public class MyRealm extends AuthorizingRealm{
    @Autowired
    private EmpDao empDao;
    /*授权部分*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal =(String) principalCollection.getPrimaryPrincipal();//同样拿到用户的主身份信息(同样是传进来的用户名)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("normal");
        simpleAuthorizationInfo.addStringPermission("delete");
        return simpleAuthorizationInfo;
    }
    /*认证部分*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String )authenticationToken.getPrincipal();//拿到姓名信息
        Emp query = empDao.query(principal);
        System.out.println(query);
        if(query!=null){
            return new SimpleAuthenticationInfo(query.getName(),query.getPassword(), ByteSource.Util.bytes(query.getSalt()),this.getName());//还应该在此处一并返回私盐
        }
        return null;
    }
}
