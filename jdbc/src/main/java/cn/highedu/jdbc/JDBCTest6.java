package cn.highedu.jdbc;

import cn.highedu.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 模拟用户登录
 * 接收键盘的输入
 * 正确则为：登录成功，反之登录失败
 */
public class JDBCTest6 {
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
            Statement statement = con.createStatement();
            String sql = "SELECT id,username,password,nickname,age"+
                    "FROM userinfo WHERE username='"+username+"'AND "+
                    "password='"+password+"'";
            ResultSet rs = statement.executeQuery(sql);
            // 当结果集中只有一行数据 仅需要移动一次 可以使用if语句让游标移动
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

