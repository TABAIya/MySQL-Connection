package cn.highedu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Druid数据库连接池
 */
public class JDBCTest9 {
    private static DruidDataSource pool;

    public static void main(String[] args) throws SQLException {
        // 初始化数据库连接池
        pool = new DruidDataSource();
        //设置数据库连接池
        pool.setUrl("jdbc:mysql://localhost:3309/hedu?useSSL=false&"+
                "serverTimeZone=Asia/Shanghai");  //设置数据库连接字符串
        pool.setUsername("root");
        pool.setPassword("root");
        pool.setInitialSize(5);  //连接池的初始连接数
        pool.setMaxActive(20);  //数据库连接池最大连接数

        //从连接池中获取数据库连接
        Connection con = pool.getConnection();
        System.out.println(con);
    }
}
