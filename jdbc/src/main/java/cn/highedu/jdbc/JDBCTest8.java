package cn.highedu.jdbc;

import cn.highedu.entity.Userinfo;
import cn.highedu.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest8 {
   //保存用户信息
    public void save(Userinfo userinfo){
        try(
                Connection con = DBUtil.getConnection()
                ) {
            String sql = "INSERT INTO userinfo (username, password, nickname, age)"+
                    "VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            // 替换占位符
            ps.setString(1, userinfo.getUsername());
            ps.setString(2, userinfo.getPassword());
            ps.setString(3, userinfo.getNickname());
            ps.setInt(4,userinfo.getAge());
            int num = ps.executeUpdate(); //执行DML语句，返回受影响的行数
            if (num > 0){
                System.out.println("保存数据成功");
            }else {
                System.out.println("保存失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //删除数据
    public void delete(Integer id){

    }
    //修改数据
    public void update(Userinfo userinfo){
        try (Connection con = DBUtil.getConnection()){
            String sql = "UPDATE userinfo SET password=?,nickname=?,age=?"+
                    "WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,userinfo.getPassword());
            ps.setString(2,userinfo.getNickname());
            ps.setInt(3,userinfo.getAge());
            ps.setInt(4,userinfo.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //查询数据
    public Userinfo getById(Integer id){
        Userinfo userinfo = null;
        try (Connection con = DBUtil.getConnection()){
            String sql = "SELECT id,username,password,nickname.age FROM"+
                    "userinfo WHERE id=?";
            //加载预编译SQL语句
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            //执行SQL语句
            ResultSet rs = ps.executeQuery();
            //遍历结果集
            while (rs.next()){
                //创建用户对象
                userinfo = new Userinfo();
                userinfo.setId(rs.getInt("id"));
                userinfo.setUsername(rs.getString("username"));
                userinfo.setPassword(rs.getString("password"));
                userinfo.setNickname(rs.getString("nickname"));
                userinfo.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userinfo;
    }


    //查询所有的数据
    public List<Userinfo> list(){
        List<Userinfo> list = new ArrayList<>();
        Userinfo userinfo = null;
        try (Connection con = DBUtil.getConnection()){
            String sql = "SELECT id,username,password,nickname,age FROM userinfo";
            //加载预编译SQL语句
            PreparedStatement ps = con.prepareStatement(sql);
            //执行SQL语句
            ResultSet rs = ps.executeQuery();
            //遍历结果集
            while (rs.next()){
                //创建用户对象
                userinfo = new Userinfo();
                userinfo.setId(rs.getInt("id"));
                userinfo.setUsername(rs.getString("username"));
                userinfo.setPassword(rs.getString("password"));
                userinfo.setNickname(rs.getString("nickname"));
                userinfo.setAge(rs.getInt("age"));
                //对象存入
                list.add(userinfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {
//        //创建Userinfo对象
//        Userinfo userinfo = new Userinfo(null,"王五","12345","阿五",22);
//        //保存数据
//        new JDBCTest8().save(userinfo);
        JDBCTest8 jt = new JDBCTest8();
//        Userinfo userinfo = jt.getById(1);
//        System.out.println(userinfo);
//        userinfo.setPassword("1232324");
//        userinfo.setAge(21);
//        jt.update(userinfo);
        List<Userinfo> list = jt.list();
        for (Userinfo userinfo : list){
            System.out.println(userinfo);
        }
    }
}
