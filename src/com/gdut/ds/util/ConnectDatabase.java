package com.gdut.ds.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectDatabase implements ServletContextListener{
    private static Connection conn = null;

    private static String url = null;
    
    private static String configPath = null;
   
//    static Logger log = Logger.getLogger(AccessTest.class);  
    public static Connection getConn(){

//        Properties pro = new Properties();
//        System.out.println(new File(".").getAbsolutePath());

//        try {
//            pro.load(new FileInputStream(new File(servletContextEvent.getServletContext().getRealPath(".")+"/config/configuration.preperties")));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String path = (String)pro.get("databasePath");
//        System.out.println(path);
//        String url = "jdbc:Access:///"+path;
//        url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+path;
    	
    	Properties pro = new Properties();
        try {
            pro.load(new FileInputStream(new File(configPath)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = (String)pro.get("databasePath");
//        url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+path;
//        url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=G:/Project/test.mdb";
        if(path==null){
        	path = "winterWheat";
        }
        if(path.indexOf(':')==-1)
          url="jdbc:odbc:"+path;
        else{
        	url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+path;
        }
    	
        try {
//            Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
//    		Class.forName("com.hxtt.sql.access.AccessDriver");
        	System.out.println("url:"+url);
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn = DriverManager.getConnection(url);
//            log.info("Access数据库连接成功");
            System.out.println("Access数据库连接成功");
        } catch (Exception e) {
//            log.info("Access数据库连接失败");
            e.printStackTrace();
        }

        /*
//      String url = "jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};DBQ=C:/Users/cjf/Desktop/学习/竞赛/冬小麦生长状况指标数据库.mdb"; //myDS 数据源名称  
    	
//    	String url = "jdbc:Access:C:/Users/cjf/Desktop/学习/竞赛/冬小麦生长状况指标数据库.mdb"; //myDS 数据源名称  
//    	String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=C:/Users/cjf/Desktop/winterWheat.mdb";
        Properties pro = new Properties();
//        System.out.println(new File(".").getAbsolutePath());

        try {
            pro.load(new FileInputStream(new File("WebRoot/config/configuration.preperties")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = (String)pro.get("databasePath");
        System.out.println(path);
        String url = "jdbc:Access:///"+path;
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
//    		Class.forName("com.hxtt.sql.access.AccessDriver");
            conn = DriverManager.getConnection(url);  
//            log.info("Access数据库连接成功");  
            System.out.println("Access数据库连接成功");
        } catch (Exception e) {  
//            log.info("Access数据库连接失败");  
        	e.printStackTrace();
        }
        */
        return conn;  
    }  
  
    public static void close(ResultSet rs, Statement stmt, Connection conn) {  
        try {  
            if (rs != null)  
                rs.close();  
        } catch (Exception e) {  
//            log.error("access rs 关闭异常",e);  
        	e.printStackTrace();
        } finally {  
            try {  
                if (stmt != null)  
                    stmt.close();  
            } catch (Exception e2) {  
//                log.error("access st 关闭异常",e2);  
            	e2.printStackTrace();
            } finally {  
                try {  
                    if (conn != null)  
                        conn.close();  
                } catch (Exception e3) {  
//                    log.error("access conn 关闭异常",e3); 
                	e3.printStackTrace();
                }  
            }  
        }  
  
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {

//        Properties pro = new Properties();
//        try {
//            pro.load(new FileInputStream(new File(servletContextEvent.getServletContext().getRealPath(".")+"/config/configuration.preperties")));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String path = (String)pro.get("databasePath");
////        url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+path;
////        url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=G:/Project/test.mdb";
//        if(path==null){
//        	path = "winterWheat";
//        }
//        if(path.indexOf(':')==-1)
//          url="jdbc:odbc:"+path;
//        else{
//        	url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+path;
//        }
    	
    	configPath = servletContextEvent.getServletContext().getRealPath(".")+"/config/configuration.preperties";

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
