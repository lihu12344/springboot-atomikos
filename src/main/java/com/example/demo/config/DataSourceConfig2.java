package com.example.demo.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.example.demo.dao.person"},sqlSessionFactoryRef = "sqlSessionFactory_2")
public class DataSourceConfig2 {

    @Bean("datasource_2")
    @ConfigurationProperties("spring.datasource.druid.two")
    public DruidXADataSource initDruidXADataSource2(){
        return new DruidXADataSource();
    }

    @Bean("atomikosDataSource2")
    public AtomikosDataSourceBean initAtomikosDataSourceBean(@Qualifier("datasource_2") DruidXADataSource dataSource){
        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(dataSource);
        atomikosDataSourceBean.setUniqueResourceName("datasource2");

        return atomikosDataSourceBean;
    }

    @Bean("sqlSessionFactory_2")
    public MybatisSqlSessionFactoryBean initMybatisSqlSessionFactoryBean2(@Qualifier("atomikosDataSource2") AtomikosDataSourceBean dataSource) throws Exception{
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/person/*.xml"));

        return sqlSessionFactoryBean;
    }
}