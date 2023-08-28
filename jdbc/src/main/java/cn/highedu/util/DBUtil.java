package cn.highedu.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 专门做数据库连接
 */
public class DBUtil {
    private static DruidDataSource pool;
    private DBUtil(){

    }
    // 初始化数据库连接池
    static {
        pool = new DruidDataSource();
        pool.setUrl("jdbc:mysql://localhost:3309/hedu?useSSL=false&"+
                "serverTimeZone=Asia/Shanghai&rewriteBatchedStatements=true");
        pool.setUsername("root");
        pool.setPassword("root");
        pool.setInitialSize(5);  //连接池的初始连接数
        pool.setMaxActive(20);  //数据库连接池最大连接数
        pool.setMinIdle(5);  //空闲时最小连接数
        pool.setMaxWait(5000); //进程最大等待时间 单位毫秒
    }
    //返回数据库连接
    public static Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
}
