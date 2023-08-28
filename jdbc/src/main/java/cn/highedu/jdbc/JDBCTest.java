package cn.highedu.jdbc;

import java.sql.*;
import java.util.Scanner;

/**
 * java连接数据库
 * 1、加载数据库驱动
 * 2、建立数据库连接
 * 3、创建Statement对象 用于加载SQL语句
 * 4、执行SQL语句
 * 5、如果执行查询操作 处理查询结果
 * 6、关闭连接
 */

public class JDBCTest {
    private static Connection con;
    //在构造方法中进行初始化con对象
    static {
        // 加载数据库驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3309/hedu?useSSL=false"+
                "&serverTimeZone=Asia/Shanghai&characterEncoding=utf-8";
        String username="root";
        String password="root";
        try {
            con = DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
//        test1();
        test2();
//        test3();
//        test4();
//        test5();
//        test6();
    }
    public static void test1(){
        //1、加载数据库驱动
        //Mysql5的驱动
//        Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //Mysql8
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        String url="jdbc:mysql://localhost:3306/hedu"; //数据库连接字符串
        String url="jdbc:mysql://localhost:3309/hedu?useSSL=false" +
                 "&serverTimeZone=Asia/Shanghai&characterEncoding=utf-8"; //数据库连接字符串 MYSQL5.8以上
        String username ="root";   //数据库账号
        String password ="root";   //数据库密码
        //2、创建数据库连接
        Connection connection = null;
        //3、创建Statement对象
        Statement statement = null;
        //4、定义SQL语句
        String sql = "SELECT name FROM teacher WHERE id=1";
        //5、创建接收查询操作返回值的对象
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
            //5、指向SQL语句
            rs = statement.executeQuery(sql);  //执行查询语句
            //处理查询结果(只针对查询操作)
            while (rs.next()){
                String s = rs.getString(1);  //1代表取出第一个位置 取出结果中的内容
                System.out.println(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    //执行DDL语句 创建一张表[仅作了解]
    public static void test2() throws SQLException {
        Statement statement = con.createStatement();
        String sql = "CREATE TABLE userinfo("+
                " id INT PRIMARY KEY AUTO_INCREMENT,"+
                " username VARCHAR(30),"+
                " password VARCHAR(30),"+
                " nickname VARCHAR(30),"+
                " age INT(3)"+
                ")";
        //执行DDL语句 返回false(表示没有结果集)
        boolean b = statement.execute(sql);
        System.out.println(b);
        con.close();  //关闭数据库连接
    }


    //执行DML语句 向userinfo表中插入一条数据
    public static void test3() throws SQLException {
        Statement statement = con.createStatement();
        String sql = "INSERT INTO userinfo (username,password,nickname,age)"+
                "VALUES('陈俊宁','190048','健康',20)";
        //执行DMLL语句 返回受影响的行数，数据库改变的行数
        int num = statement.executeUpdate(sql);
        if(num>0){
            System.out.println("保存成功，数据库改变"+num+"行");
        }else {
            System.out.println("保存失败");
        }
    }


    /**
     * 程序运行 显示'欢迎注册'
     * 输入用户名等信息【账号密码等】
     * 接受相关信息
     * 拼接sql语句
     * 保存数据
     */
    public static void test4() throws SQLException {
        Statement statement = con.createStatement();
        System.out.println("欢迎注册");
        Scanner console = new Scanner(System.in);
        System.out.println("用户名:");
        String username = console.nextLine();
        System.out.println("密码:");
        String password = console.nextLine();
        System.out.println("昵称:");
        String nickname = console.nextLine();
        System.out.println("年龄:");
        int age = console.nextInt();
        String sql = "INSERT INTO userinfo (username,password,nickname,age)"+
                "VALUES('"+username+"','"+password+"','"+
                nickname+"',"+age+")";
        int num = statement.executeUpdate(sql);
        if (num>0){
            System.out.println("保存成功");
        }
        else {
            System.out.println("保存失败");
        }
    }
    //执行DML修改操作
    public static void test5() throws SQLException{
        Statement statement = con.createStatement();
        String sql = "UPDATE userinfo SET password='654321' WHERE username='陈俊宁'";
        int num = statement.executeUpdate(sql);
        if (num > 0){
            System.out.println("修改密码成功");
        }else {
            System.out.println("修改密码成功");
        }
        con.close();
    }

    //执行DML删除语句
    public static void test6() throws SQLException {
        Statement statement = con.createStatement();
        String sql = "DELETE FROM userinfo WHERE username='陈俊宁'";
        int num = statement.executeUpdate(sql);
        if (num > 0){
            System.out.println("删除数据成功");
        }else {
            System.out.println("删除数据失败");
        }
        con.close();
    }
}
