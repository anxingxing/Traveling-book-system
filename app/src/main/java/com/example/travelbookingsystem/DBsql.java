package com.example.travelbookingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import static com.example.travelbookingsystem.DBUtils.getConn;

/**
 * Created by 安行行 on 2020/11/23.
 */

public class DBsql {

    //查询数据
    public static HashMap<String,Object> getInfoByAccount1(String s1,String s2) {
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = getConn();
        try {
            Statement st = connection.createStatement();
            String sql = "select * from flights where fromcity ='" + s1
                    + "' and arivcity='" + s2 + "'";
            //String sql = "select * from signin";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                DBUtils.closeAll();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static HashMap<String,Object> getInfoByAccount(String s1,String s2) {
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = getConn();
        try {
            Statement st = connection.createStatement();
            // mysql简单的查询语句。这里是根据sigin表的sname字段来查询某条记录
            String sql = "select * from signin where sname ='" + s1
                    + "' and spassword='" + s2 + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                DBUtils.closeAll();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static HashMap<String,Object> getInfoByAccount(String s1,int j) {
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = getConn();
        try {
            Statement st = connection.createStatement();
            // mysql简单的查询语句。
            String sql = "select * from hotels where location ='" + s1
                    + "' and numvail>'" + j + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                DBUtils.closeAll();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static HashMap<String,Object> getInfoByAccount2(String s1,int j) {
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = getConn();
        try {
            Statement st = connection.createStatement();
            // mysql简单的查询语句。
            String sql = "select * from bus where location ='" + s1
                    + "' and numvail>'" + j + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                DBUtils.closeAll();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<String,Object> getInfoByAccount3(String s1,int j) {
        HashMap<String, Object> map = new HashMap<>();
        // 根据数据库名称，建立连接
        Connection connection = getConn();
        try {
            Statement st = connection.createStatement();
            // mysql简单的查询语句。
            String sql = "";
            if(j==2)
            {
                sql+="select * from bus where location ='" + s1 +"'";
            }
            if(j==3)
            {
                sql+="select * from hotels where location ='" + s1 +"'";
            }
            if(j==4)
            {
                sql+="select * from reservation where custname ='" + s1 +"'";
            }

            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                res.next();
                for (int i = 1; i <= cnt; ++i) {
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }
                DBUtils.closeAll();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 插入数据（插入一条姓名为 name，年龄为 age 的数据）
     */
    public static boolean insert(String name, String password) {
        // 插入数据的 sql 语句
        String sql = "insert into signin (sname,spassword) values (?, ?)";
        Connection connection = DBUtils.getConn();
        PreparedStatement ps = null;
        if (connection == null) {
            return false;
        }
        try {
            ps = connection.prepareStatement(sql);
            // 为两个 ? 设置具体的值
            ps.setString(1, name);
            ps.setString(2, password);
            // 执行语句
            ps.executeUpdate();
            DBUtils.closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
    }

    /**
     * 插入数据（插入一条姓名为 name，年龄为 age 的数据）
     */
    public static boolean insert(String custname, int type ,String hint) {
        // 插入数据的 sql 语句
        String sql = "insert into reservation (custname,resvtype,hint) values (?, ? ,?)";
        Connection connection = DBUtils.getConn();
        PreparedStatement ps = null;
        if (connection == null) {
            return false;
        }
        try {
            ps = connection.prepareStatement(sql);
            // 为两个 ? 设置具体的值
            ps.setString(1, custname);
            ps.setInt(2, type);
            ps.setString(3, hint);
            // 执行语句
            ps.executeUpdate();
            DBUtils.closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
    }

    public static boolean insertflight(String s1, int a,int b,int c,String s2, String s3) {
        // 插入数据的 sql 语句
        String sql = "insert into flights (flightnum,price,numseats,numavail,fromcity,arivcity) " +
                "values ( ? , ? , ? , ? , ? , ?  )";
        Connection connection = DBUtils.getConn();
        PreparedStatement ps = null;
        if (connection == null)
        {
            return false;
        }
        try {
            ps = connection.prepareStatement(sql);
            // 为两个 ? 设置具体的值
            ps.setString(1, s1);
            ps.setInt(2, a);
            ps.setInt(3, b);
            ps.setInt(4, c);
            ps.setString(5, s2);
            ps.setString(6, s3);
            // 执行语句
            ps.executeUpdate();
            DBUtils.closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
    }
    /**
     * 更新数据
     */
    public static void update(int i,int numavail, String fname) {
        // 更新数据的 sql 语句
        Connection connection = DBUtils.getConn();
        String sql="";
        if(i==1) {
            sql += "update flights set numavail = numavail - ? where flightnum= ? ";
        }
        else if(i==2)
        {
            sql += "update bus set numvail = numvail - ? where location= ? ";
        }
        else if(i==3)
        {
            sql += "update hotels set numvail = numvail - ? where location= ? ";
        }

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 为两个 ? 设置具体的值
            ps.setInt(1, numavail);
            ps.setString(2, fname);
            // 执行语句
            ps.executeUpdate();
            DBUtils.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除数据（删除姓名为 sname 的数据）
     */
    public static void delete(String name,int i) {
        // 删除数据的 sql 语句
        String sql="";
        if(i==1) {
             sql += "delete from flights where flightnum = ?";
        }
        else if (i==2)
        {
            sql += "delete from bus where location = ?";
        }
        else if(i==3)
        {
            sql += "delete from hotels where location = ?";
        }

        Connection connection = DBUtils.getConn();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 为 ? 设置具体的值
            ps.setString(1, name);
            // 执行语句
            ps.executeUpdate();
            DBUtils.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查询 表的所有数据
     */
    public static String query(String sql) {
        // 查询的 sql 语句
        Connection connection = DBUtils.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder builder = new StringBuilder();
        try {
            ps = connection.prepareStatement(sql);
            // 执行语句（执行查询语句用的是 executeQuery 方法）
            rs = ps.executeQuery();
            // 得到查询结果
            if (rs != null) {
                if(sql == "select * from bus") {
                    while (rs.next()) {
                        builder.append("[location = ");
                        builder.append(rs.getString("location"));
                        builder.append(", price = ");
                        builder.append(rs.getInt("price"));
                        builder.append(", numvail = ");
                        builder.append(rs.getInt("numvail"));
                        builder.append("] ");
                        builder.append("\n");
                        builder.append("\n");
                    }
                }

                else if(sql == "select * from flights") {
                    while (rs.next()) {
                        builder.append("[flightnum = ");
                        builder.append(rs.getString("flightnum"));
                        builder.append(", price = ");
                        builder.append(rs.getInt("price"));
                        builder.append(", numavail = ");
                        builder.append(rs.getInt("numavail"));
                        builder.append(", fromcity = ");
                        builder.append(rs.getString("fromcity"));
                        builder.append(", arivcity = ");
                        builder.append(rs.getString("arivcity"));
                        builder.append("] ");
                        builder.append("\n");
                        builder.append("\n");
                    }
                }

                else if(sql == "select * from hotels") {
                    while (rs.next()) {
                        builder.append("[location = ");
                        builder.append(rs.getString("location"));
                        builder.append(", price = ");
                        builder.append(rs.getInt("price"));
                        builder.append(", numvail = ");
                        builder.append(rs.getString("numvail"));
                        builder.append("] ");
                        builder.append("\n");
                        builder.append("\n");
                    }
                }

                else if(sql == "select * from reservation") {
                    while (rs.next()) {
                        builder.append("[resvkey = ");
                        builder.append(rs.getString("resvkey"));
                        builder.append(", custname = ");
                        builder.append(rs.getString("custname"));
                        builder.append(", resvtype = ");
                        builder.append(rs.getInt("resvtype"));
                        builder.append(", hint = ");
                        builder.append(rs.getString("hint"));
                        builder.append("] ");
                        builder.append("\n");
                        builder.append("\n");
                    }
                }
                else if(sql == "select * from customers") {
                    while (rs.next()) {
                        builder.append("[custname = ");
                        builder.append(rs.getString("custname"));
                        builder.append(", custid = ");
                        builder.append(rs.getString("custid"));
                        builder.append("] ");
                        builder.append("\n");
                        builder.append("\n");
                    }
                }
                else
                {
                    while (rs.next()) {
                        builder.append("[custname = ");
                        builder.append(rs.getString("custname"));
                        builder.append(", hint = ");
                        builder.append(rs.getString("hint"));
                        builder.append("] ");
                        builder.append("\n");
                        builder.append("\n");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return builder.toString();
    }


    public static String queryreservation(String sql) {
        // 查询的 sql 语句
        Connection connection = DBUtils.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder builder = new StringBuilder();
        try {
            ps = connection.prepareStatement(sql);
            // 执行语句（执行查询语句用的是 executeQuery 方法）
            rs = ps.executeQuery();
            // 得到查询结果
            if (rs != null) {
                    while (rs.next()) {
                        builder.append("[custname = ");
                        builder.append(rs.getString("custname"));
                        builder.append(", hint = ");
                        builder.append(rs.getInt("hint"));
                        builder.append("] ");
                        builder.append("\n");
                        builder.append("\n");
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return builder.toString();
    }
}

