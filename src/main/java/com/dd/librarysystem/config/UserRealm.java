package com.dd.librarysystem.config;

import com.dd.librarysystem.model.User;
import com.dd.librarysystem.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("authorizer")
class UserRealm extends AuthorizingRealm {

    UserRepository userRepository;

    @Autowired
    public UserRealm(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 10. 前面被roles拦截后，需要授权才能登录，SecurityManager需要调用这里进行权限查询
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        User user = (User)principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //暂时把全部用户都设置成admin
        Set<String> role = new HashSet<>();
        role.add("admin");
        info.setRoles(role);
        Set<String> perm = new HashSet<>();
        perm.add("admin:all");
        info.setStringPermissions(perm);
        //info.setRoles(user.getRoleNames());
        //默认数据库中user和role是多对一的关系,不存在user有多个role的情况(但是没有约束...)
        //暂不设置permission
        //info.setStringPermissions(rmsAdminLoginService.getPermissionByRoleId(user.getRoleId()));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行登录逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userRepository.findUsersByName(token.getUsername());

        if (token.getUsername() == null || !token.getUsername().equals(user.getName())){
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), "UserRealm");
    }


}