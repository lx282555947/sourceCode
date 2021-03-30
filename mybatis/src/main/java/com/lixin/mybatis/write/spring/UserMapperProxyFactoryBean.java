package com.lixin.mybatis.write.spring;

import com.lixin.mybatis.write.bean.User;
import com.lixin.mybatis.write.mapper.UserMapper;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserMapperProxyFactoryBean implements FactoryBean {

    private Class<?> mapperInterface;

    @Override
    public Object getObject() throws Exception {
//        User user = new User();
//        user.setUsername("lixin");
//        return user;
        return Proxy.newProxyInstance(UserMapperProxyFactoryBean.class.getClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return "hello, userMapper I'm proxy!";
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }
}
