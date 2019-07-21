package com.demo.SpringBoot.web.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * 多个数据源时，配置多个这样的类，放到dao/mapper/下，扫描不同的文件夹
**/
@Configuration
@MapperScan(basePackages = "com.demo.SpringBoot.dao.mapper", sqlSessionFactoryRef = "sqlSessionFactoryDB")
public class MybatisConfig {

    @Bean(name = "sqlSessionFactoryDB")
    public SqlSessionFactory sqlSessionFactoryDB(
            @Qualifier("dataSourceDB") DataSource dataSource,
            @Value("classpath:com/demo/SpringBoot/dao/mapper/**/*.xml") Resource[] resources
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplateDB")
    public SqlSessionTemplate sqlSessionTemplateDB(@Qualifier("sqlSessionFactoryDB") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
