package cn.highedu.jdbc;

import cn.highedu.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest2 {
    public static void main(String[] args) {
        try(
                Connection con = DBUtil.getConnection();
                ){
            String sql ="SELECT 'Hello World'";
            Statement statement = con.createStatement();
            ResultSet rs =  statement.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
