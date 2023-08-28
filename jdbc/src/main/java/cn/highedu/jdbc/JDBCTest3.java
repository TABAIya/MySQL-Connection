package cn.highedu.jdbc;

import cn.highedu.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *  JDBC执行DQL语句
 */
public class JDBCTest3 {
    public static void main(String[] args) {
        try (Connection con = DBUtil.getConnection()) {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM teacher";
            ResultSet rs = statement.executeQuery(sql);
            //rs.next()查看取出姓名的
            while (rs.next()) {
                /* int id = rs.getInt(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);
                int salary = rs.getInt(4);*/
                int id = rs.getInt("id");  //取出结果籍中的第二列数据
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int salary = rs.getInt("salary");
                System.out.println(id + "," + name + "," + gender + "," + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
