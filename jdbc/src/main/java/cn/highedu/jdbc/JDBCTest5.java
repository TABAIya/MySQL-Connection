package cn.highedu.jdbc;

import cn.highedu.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用别名查询结果列名
 * 查询刘苍松的班级人数
 * SELECT  COUNT(*)
 * FROM student s JOIN class c
 * ON s.class_id = c.id
 * JOIN teacher t
 * ON c.teacher_id = t.id
 * WHERE t.name = '刘苍松'
 */
public class JDBCTest5 {
    public static void main(String[] args) {
        try(
                Connection con = DBUtil.getConnection();
        ) {
            Statement statement = con.createStatement();
            String sql = "  SELECT  COUNT(*) count \n" +
                    "  FROM student s JOIN class c\n" +
                    "  ON s.class_id = c.id\n" +
                    "  JOIN teacher t\n" +
                    "  ON c.teacher_id = t.id\n" +
                    "  WHERE t.name = '刘苍松'";
            ResultSet rs = statement.executeQuery(sql);
            // 当结果集中只有一行数据 仅需要移动一次 可以使用if语句让游标移动
            if (rs.next()){
                int count = rs.getInt("count");
                System.out.println("刘苍松班级人数:"+count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
