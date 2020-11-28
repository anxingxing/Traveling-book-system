package com.example.travelbookingsystem;

/**
 * Created by 安行行 on 2020/11/18.
 */

import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库工具类：连接数据库用、获取数据库数据用
 * 相关操作数据库的方法均可写在该类
 */
public class DBUtils {

    private static String driver = "com.mysql.jdbc.Driver";// MySql驱动

    private static String url = "jdbc:mysql://localhost:3306/travelbooking";

    private static String user = "root";// 用户名

    private static String password = "251421";// 密码
    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;


    public static Connection getConn() {
        String dbName="travelbooking";
        try {
            Class.forName(driver);// 动态加载类
            String ip = "192.168.56.1";// 写成本机地址，不能写成localhost，同时手机和电脑连接的网络必须是同一个
            // 尝试建立到给定数据库URL的连接
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + dbName,
                    user, password);
            Log.e("DBUtils", "连接成功");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 关闭数据库
     * */
    public static void closeAll()
    {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

