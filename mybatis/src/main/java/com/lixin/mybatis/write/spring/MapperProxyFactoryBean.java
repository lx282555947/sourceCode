package com.lixin.mybatis.write.spring;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MapperProxyFactoryBean implements FactoryBean {

    private Class<?> mapperInterface;

    public MapperProxyFactoryBean(Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(MapperProxyFactoryBean.class.getClassLoader(), new Class[]{mapperInterface}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return "hello," + mapperInterface.getName() + " I'm proxy!";
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}
