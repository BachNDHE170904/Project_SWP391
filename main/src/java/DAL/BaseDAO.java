/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author AD
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class BaseDAO<T> {
    protected Connection connection;
    public BaseDAO()
    {
        try {
            String user = "sa";
            String pass = "123456";
            String url = "jdbc:sqlserver://DESKTOP-G915HEL\\TENGIDO:1433;databaseName=SWP391;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void testConnect() {
          try {
            String user = "sa";
            String pass = "123456";
            String url = "jdbc:sqlserver://DESKTOP-G915HEL\\TENGIDO:1433;databaseName=SWP391;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           Connection connection1 = DriverManager.getConnection(url, user, pass);
              System.out.println(connection1);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
       testConnect();
    }
}