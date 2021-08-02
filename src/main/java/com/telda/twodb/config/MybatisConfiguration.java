package com.telda.twodb.config;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import com.telda.twodb.mappers.OneMapper;
import com.telda.twodb.mappers.TwoMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class MybatisConfiguration {
    public static final String FIRST_SESSION_FACTORY = "oneSessionFactory";
    public static final String SECOND_SESSION_FACTORY = "twoSessionFactory";

    @Bean(name = FIRST_SESSION_FACTORY, destroyMethod = "")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory(@Named(DatabaseConfiguration.First_datasource) final DataSource oneDataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(oneDataSource);
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().addMapper(OneMapper.class);

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperFactoryBean<OneMapper> etrMapper(@Named(FIRST_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        MapperFactoryBean<OneMapper> factoryBean = new MapperFactoryBean<>(OneMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean(name = SECOND_SESSION_FACTORY, destroyMethod = "")
    public SqlSessionFactoryBean censoSqlSessionFactory(@Named(DatabaseConfiguration.Second_datasource) final DataSource anotherDataSource)
            throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(anotherDataSource);
        final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().addMapper(TwoMapper.class);
        // Various other SqlSessionFactory settings
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperFactoryBean<TwoMapper> dbMapper(@Named(SECOND_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean)
            throws Exception {
        MapperFactoryBean<TwoMapper> factoryBean = new MapperFactoryBean<>(TwoMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;

    }
}