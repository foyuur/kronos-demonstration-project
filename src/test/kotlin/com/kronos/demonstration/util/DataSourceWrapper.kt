package com.kronos.demonstration.util

import com.alibaba.druid.pool.DruidDataSource
import javax.sql.DataSource

object DataSourceWrapper {

    val dataSource: DataSource by lazy {
        DruidDataSource().apply {
            url =
                "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&rewriteBatchedStatements=true"
            username = "root"
            password = "rootroot"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            initialSize = 5
            maxActive = 10
        }
    }

}