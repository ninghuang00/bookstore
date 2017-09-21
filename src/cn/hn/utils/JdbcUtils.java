package cn.hn.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JdbcUtils {

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    private static DataSource ds;

    static {
        ds = new ComboPooledDataSource();
    }

    public static DataSource getDataSource() {
        return ds;
    }

   /* public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }*/

    public static Connection getConnection() {
        //检查当前线程上有没有绑定connection
        try {
            Connection conn = tl.get();
            if (conn == null) {
                conn = getDataSource().getConnection();
                tl.set(conn);
            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void startTransaction(){
        try {
            Connection conn = getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit(){
        try {
            Connection conn = tl.get();
            if(conn != null){
                conn.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback(){
        try {
            Connection conn = tl.get();
            if(conn != null){
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(){
        try {
            Connection conn = tl.get();
            if(conn != null){
                conn.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            tl.remove();//移除线程当前绑定的连接
        }
    }



}
