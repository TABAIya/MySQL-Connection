package cn.highedu.jdbc;

import cn.highedu.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC关联查询
 * 查询刘苍松带的班级里所有的学生
 * SELECT s.name,s.gender,c.name,t.name
 * FROM student s JOIN class c
 * ON s.class_id = c.id
 * JOIN teacher t
 * ON c.teacher_id = t.id
 * WHERE t.name = '刘苍松';
 */
public class JDBCTest4 {
    public static void main(String[] args) {
        try(
                Connection con = DBUtil.getConnection();
                ) {
            Statement statement = con.createStatement();
            String sql = " SELECT s.name sname,s.gender sgneder,c.name cname,t.name tname\n" +
                    "  FROM student s JOIN class c\n" +
                    "  ON s.class_id = c.id\n" +
                    "  JOIN teacher t\n" +
                    "  ON c.teacher_id = t.id\n" +
                    "  WHERE t.name = '刘苍松'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String sname = rs.getString("s.name");
                String sgender = rs.getString("s.gender");
                String cname = rs.getString("c.name");
                String tname = rs.getString("t.name");
                System.out.println(sname+","+sgender+","+cname+","+tname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
