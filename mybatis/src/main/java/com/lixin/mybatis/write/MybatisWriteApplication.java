package com.lixin.mybatis.write;
import com.lixin.mybatis.write.bean.User;
import com.lixin.mybatis.write.mapper.TestMapperProxyFactoryBeanMapper;
import com.lixin.mybatis.write.service.UserService;
import com.lixin.mybatis.write.spring.*;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

//@SpringBootApplication
@ComponentScan("com.lixin.mybatis.write")
//@Import(MybatisImportBeanDefinitionRegistar.class)
public class MybatisWriteApplication {
    public static void main(String[] args) {
//        SpringApplication.run(MybatisWriteApplication.class, args);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MybatisWriteApplication.class);
        //声明spring bean，并注册到容器中
        /**
         * 第一步，尝试使用FactoryBean来代理对应的Mapper
         */
        //测试通过factoryBean为UserMapper生成代理类的注入情况
//        AbstractBeanDefinition userMapperBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        userMapperBeanDefinition.setBeanClass(UserMapperProxyFactoryBean.class);
//        applicationContext.registerBeanDefinition("userMapper", userMapperBeanDefinition);
//        //测试通过factoryBean为MemberMapper生成代理类的注入情况
//        AbstractBeanDefinition memberMapperBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        memberMapperBeanDefinition.setBeanClass(MemberMapperProxyFactoryBean.class);
//        applicationContext.registerBeanDefinition("memberMapper", memberMapperBeanDefinition);
//        /**
//         * 第二步，尝试使用统一的FactoryBean来代理Mapper
//         */
//        AbstractBeanDefinition testProxyFactoryBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        testProxyFactoryBeanDefinition.setBeanClass(MapperProxyFactoryBean.class);
//        ConstructorArgumentValues constructorArgumentValues = testProxyFactoryBeanDefinition.getConstructorArgumentValues();
//        constructorArgumentValues.addGenericArgumentValue(TestMapperProxyFactoryBeanMapper.class);
//        applicationContext.registerBeanDefinition("testMapperProxyFactoryBeanMapper", testProxyFactoryBeanDefinition);
        /**
         * 第二步，尝试将以上情况统一化，无需再指定具体的Mapper类型
         */
        //首先要扫描指定目录下的Mapper
        String path = "com.lixin.mybatis.write.mapper";
        MybatisScanner mybatisScanner = new MybatisScanner(applicationContext);
//        mybatisScanner.addIncludeFilter(new TypeFilter() {
//            @Override
//            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
//                return true;
//            }
//        });
        mybatisScanner.doCustomScan(path);
        //容器初开始始化
        applicationContext.refresh();
        //获取userService，查看userMapper是否注入
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.findUser());
        System.out.println(userService.findMember());
        System.out.println(userService.testMapperProxyFactoryBeanMapper());
    }

}
