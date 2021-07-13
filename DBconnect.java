package pproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
public class DBconnect {
      public static Connection connect() throws ClassNotFoundException{ 
             {
        Connection con=null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=admindb;user=admin;password=232001";
        //jdbc:sqlserver://localhost:1433;databaseName=admindb
        try
        {
            con=DriverManager.getConnection(connectionURL);
            //System.out.println("Connection is Successfull");
        }
        catch(SQLException e)
        {
                System.out.println(e);
        }
        return con;
    }
      }}
      /*
    Connection con =null;
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String connectionURL="jdbc:sqlserver://localhost:1433;databaseName=admindb;user=admin;password=232001"; 
    try{
    con=DriverManager.getConnection(connectionURL);
    System.out.println("Connection is successfull");
    }
    catch(SQLException e){
     System.out.println(e);

    }
        return con;
    }
}*/