package com.wzxlq.utils;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


/**
 * Created by lenovo on 2018/2/27.
 */
public class DriverMangerListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
 
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
        //com.mysql.jdbc.AbandonedConnectionCleanupThread.uncheckedShutdown();
        Enumeration<Driver> enumeration = DriverManager.getDrivers();
        while (enumeration.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(enumeration.nextElement());
                com.mysql.jdbc.AbandonedConnectionCleanupThread.uncheckedShutdown();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      
    }
}

