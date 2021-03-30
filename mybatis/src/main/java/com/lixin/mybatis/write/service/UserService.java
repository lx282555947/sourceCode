package com.lixin.mybatis.write.service;
import com.lixin.mybatis.write.mapper.MemberMapper;
import com.lixin.mybatis.write.mapper.TestMapperProxyFactoryBeanMapper;
import com.lixin.mybatis.write.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private TestMapperProxyFactoryBeanMapper testMapperProxyFactoryBeanMapper;

    public String findUser() {
        return userMapper.selectById();
    }

    public String findMember() {
        return memberMapper.selectById();
    }

    public String testMapperProxyFactoryBeanMapper() {
        return testMapperProxyFactoryBeanMapper.selectById();
    }
}
