/**
 * FileName: DruidDataSourceConfig
 * Author:   13235
 * Date:     2018/11/26 23:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.xkf.rabbitmqha.ha.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2018/11/26
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {

    @Bean
    public DataSource dataSource(DruidDataSourceSettings druidSettings) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidSettings.getDriverClassName());
        dataSource.setUrl(druidSettings.getUrl());
        dataSource.setUsername(druidSettings.getUsername());
        dataSource.setPassword(druidSettings.getPassword());
        dataSource.setInitialSize(druidSettings.getInitialSize());
        dataSource.setMinIdle(druidSettings.getMinIdle());
        dataSource.setMaxActive(druidSettings.getMaxActive());
        dataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidSettings.getValidationQuery());
        dataSource.setTestWhileIdle(druidSettings.getTestWhileIdle());
        dataSource.setTestOnBorrow(druidSettings.getTestOnBorrow());
        dataSource.setTestOnReturn(druidSettings.getTestOnReturn());
        dataSource.setPoolPreparedStatements(druidSettings.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidSettings.getMaxOpenPreparedStatements());
        dataSource.setFilters(druidSettings.getFilters());
        dataSource.setConnectProperties(druidSettings.getConnectionProperties());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DruidDataSourceSettings druidSettings) throws Exception {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(this.dataSource(druidSettings));
        return manager;
    }

}
