package com.example.pro.test1;

/**
 * Created by liyanzhen on 16/7/20.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.util.Log;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Util {
    final static String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public static void go() {
        String user = "root";//SSH连接用户名
        String password = ":::MDZZgnpy";//SSH连接密码
        String host = "106.14.214.239";//SSH服务器
        int lport = 33104;//本地端口（随便取）
        String rhost = "localhost";//远程MySQL服务器
        int rport = 3306;//远程MySQL服务端口
        int port = 22;//SSH访问端口
        Log.e("==","成功7688");
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Log.e("=======>", "服务器连接成功");
            System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
            int assinged_port =session.setPortForwardingL(lport, rhost, rport);//将服务器端口和本地端口绑定，这样就能通过访问本地端口来访问服务器
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            Log.e("==","成功");
            e.printStackTrace();
        }
    }
    public static Connection openConnection(String url, String user,
                                            String password) {
        Connection conn = null;
        try {
            final String DRIVER_NAME = "com.mysql.jdbc.Driver";
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(url, user, password);
            Log.e("=====连接结果=======", "数据库连接成功");
        } catch (ClassNotFoundException e) {
            Log.e("=====连接结果=======", "报ClassNotFoundException异常");
            conn = null;
        } catch (SQLException e) {
            Log.e("=====连接结果=======", "报SQLException异常");
            conn = null;
        }

        return conn;
    }

    public static void query(Connection conn, String sql) {
        if (conn == null) {
            return;
        }
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null && result.first()) {
                int idColumnIndex = result.findColumn("id");
                int nameColumnIndex = result.findColumn("account");
                System.out.println("id\t\t" + "account");
                while (!result.isAfterLast()) {
                    System.out.print(result.getString(idColumnIndex) + "\t\t");
                    System.out.println(result.getString(nameColumnIndex));
                    result.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }} catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }
    public static String getpassword(Connection conn, String sql) {
        //if (conn == null) {
        //    return;
       // }
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null && result.first()) {
                int passwordColumnIndex = result.findColumn("password");
                System.out.print(result.getString(passwordColumnIndex) + "\n");
                return result.getString(passwordColumnIndex).toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }} catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return "not found";
    }
    public static User getuserInfo(Connection conn, String sql,String account) {
        //if (conn == null) {
        //    return;
        // }
        User user1;
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null && result.first()) {
                int idColumnIndex = result.findColumn("id");
                int nameColumnIndex = result.findColumn("Name");
                int passwordColumnIndex = result.findColumn("Password");
                int introductionColumnIndex = result.findColumn("Introduction");
                int ageColumnIndex = result.findColumn("Age");
                int placeColumnIndex = result.findColumn("Place");
                int sexColumnIndex = result.findColumn("Sex");
                int friendnameColumnIndex = result.findColumn("Friendname");
                int xinggeColumnIndex = result.findColumn("Xingge");
                int telephoneColumnIndex = result.findColumn("Telephone");
                //System.out.print(result.getString(passwordColumnIndex) + "\n");
                //System.out.print(result.getString(xinggeIndex) + "\n");
                user1 = new User(result.getString(nameColumnIndex),result.getString(passwordColumnIndex),account,result.getInt(sexColumnIndex));
                user1.introduction = result.getString(introductionColumnIndex);
                user1.age = result.getInt(ageColumnIndex);
                user1.place = result.getString(placeColumnIndex);
                user1.friendname = result.getString(friendnameColumnIndex);
                user1.character = result.getInt(xinggeColumnIndex);
                user1.telephone = result.getString(telephoneColumnIndex);
                return user1;
                //return result.getString(passwordColumnIndex).toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }} catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        User user2 = new User("2132","23","3",1);
        return user2;
    }
    public  static String getId(Connection conn, String sql) {
        //if (conn == null) {
        //    return;
        // }
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null && result.first()) {
                int passwordColumnIndex = result.findColumn("id");
                System.out.print(result.getString(passwordColumnIndex) + "\n");
                return result.getString(passwordColumnIndex).toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }} catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return "not found";
    }
    public static boolean execSQL(Connection conn, String sql) {
        boolean execResult = false;
        if (conn == null) {
            return execResult;
        }
        Statement statement = null;
        try {
            statement = conn.createStatement();
            if (statement != null) {
                execResult = statement.execute(sql);
                //execResult = statement.execute(sql1);
            }
        } catch (SQLException e) {
            execResult = false;
        }
        return execResult;
    }
    /*public static ArrayList<people> query(Connection conn, String sql) {
        //if (conn == null) {
        //    return;
        //}
        ArrayList<people> L;
        L=new ArrayList<people>();
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null && result.first()) {
                int idColumnIndex = result.findColumn("id");
                int nameColumnIndex = result.findColumn("Name");
                int passwordColumnIndex = result.findColumn("Password");
                int accountColumnIndex = result.findColumn("Account");
                int introductionColumnIndex = result.findColumn("Introduction");
                int ageColumnIndex = result.findColumn("Age");
                int placeColumnIndex = result.findColumn("Place");
                int sexColumnIndex = result.findColumn("Sex");
                int friendnameColumnIndex = result.findColumn("Friendname");
                int xinggeColumnIndex = result.findColumn("Xingge");
                int telephoneColumnIndex = result.findColumn("Telephone");

                int count=0;
                while (!result.isAfterLast()) {
                    people p;
                    p=new people();
                    p.id= Integer.parseInt(result.getString(idColumnIndex));
                    p.name= result.getString(nameColumnIndex);
                    p.account= result.getString(accountColumnIndex);
                    p.password= result.getString(passwordColumnIndex);
                    p.friendname=result.getString(friendnameColumnIndex);
                    p.introduction=result.getString(introductionColumnIndex);
                    p.age=Integer.parseInt(result.getString(ageColumnIndex));
                    p.place=result.getString(placeColumnIndex);
                    p.sex=Integer.parseInt(result.getString(sexColumnIndex));
                    p.xingge=Integer.parseInt(result.getString(xinggeColumnIndex));
                    p.telephone=result.getString(telephoneColumnIndex);
                    L.add(p);
                    count++;
                    result.next();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }} catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return  L;
    }*/
    public static ArrayList<people> queryall(Connection conn, String sql) {
        //if (conn == null) {
        //    return;
        //}
        ArrayList<people> L;
        L=new ArrayList<people>();
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery(sql);
            System.out.println(result);
            if (result != null && result.first()) {
                int idColumnIndex = result.findColumn("id");
                int nameColumnIndex = result.findColumn("Name");
                int passwordColumnIndex = result.findColumn("Password");
                int accountColumnIndex = result.findColumn("Account");
                int introductionColumnIndex = result.findColumn("Introduction");
                int ageColumnIndex = result.findColumn("Age");
                int placeColumnIndex = result.findColumn("Place");
                int sexColumnIndex = result.findColumn("Sex");
                int friendnameColumnIndex = result.findColumn("Friendname");
                int xinggeColumnIndex = result.findColumn("Xingge");
                int telephoneColumnIndex = result.findColumn("Telephone");

                int count=0;
                while (!result.isAfterLast()) {
                    people p;
                    p=new people();
                    p.id= Integer.parseInt(result.getString(idColumnIndex));
                    p.name= result.getString(nameColumnIndex);
                    p.account= result.getString(accountColumnIndex);
                    p.password= result.getString(passwordColumnIndex);
                    p.friendname=result.getString(friendnameColumnIndex);
                    p.introduction=result.getString(introductionColumnIndex);
                    p.age=Integer.parseInt(result.getString(ageColumnIndex));
                    p.place=result.getString(placeColumnIndex);
                    p.sex=Integer.parseInt(result.getString(sexColumnIndex));
                    p.xingge=Integer.parseInt(result.getString(xinggeColumnIndex));
                    p.telephone=result.getString(telephoneColumnIndex);
                    L.add(p);
                    count++;
                    result.next();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                    result = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }} catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return  L;
    }
}