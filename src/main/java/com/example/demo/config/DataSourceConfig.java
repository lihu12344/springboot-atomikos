package com.example.demo.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.example.demo.dao.user"},sqlSessionFactoryRef = "sqlSessionFactory_1")
public class DataSourceConfig {

    @Bean("datasource_1")
    @ConfigurationProperties("spring.datasource.druid.one")
    public DruidXADataSource initDruidXADataSource(){
        return new DruidXADataSource();
    }

    @Bean("atomikosDatasource_1")
    public AtomikosDataSourceBean initAtomikosDataSourceBean(@Qualifier("datasource_1") DruidXADataSource dataSource){
        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(dataSource);
        atomikosDataSourceBean.setBeanName("datasource_1");

        return atomikosDataSourceBean;
    }

    @Bean("sqlSessionFactory_1")
    public SqlSessionFactory initSqlSessionFactory(@Qualifier("atomikosDatasource_1") AtomikosDataSourceBean dataSource) throws Exception{
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/user/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
}
