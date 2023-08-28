package cn.highedu.jdbc;

import cn.highedu.util.DBUtil;

import java.sql.*;
import java.util.Scanner;

/**
 * 预编译
 */
public class JDBCTest7 {
    public static void main(String[] args) {
        System.out.println("欢迎登录");
        Scanner console = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = console.nextLine();
        System.out.println("请输入密码:");
        String password = console.nextLine();
        try(
                Connection con = DBUtil.getConnection();
        ) {
           //定义预编译SQL语句?表示占位符
            String sql ="SELECT id,username,password,nickname,age FROM userinfo"+
                    "WHERE username=? AND password=?";
            //加载预编译SQL语句
            PreparedStatement ps = con.prepareStatement(sql);
            //在执行SQL语句之前必须将占位符替代为真实的数据
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery(); //执行查询
            if (rs.next()){
                System.out.println("登录成功");
            }else {
                System.out.println("登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
