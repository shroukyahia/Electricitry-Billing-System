/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pproject;
import static pproject.DBconnect.connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author shreen
 */
public class oldCustomer {
    
   public static boolean login (int metercode) throws ClassNotFoundException, SQLException {
       try{
            Connection con = connect();
            PreparedStatement stmt = con.prepareStatement("select name from cusinfo where metercode=?");
            stmt.setInt(1,metercode);
            ResultSet res = stmt.executeQuery();
            res.next();
            System.out.println("welcome mr:" +res.getString(1));
            return true;
            }
            catch(Exception e)
            {
                System.out.println("Your metercode not found...");
                return false;
            }
   }
   
   public static void payBill (int metercode) throws ClassNotFoundException, SQLException{
            Connection con = connect();              
            System.out.println("Enter your credit to pay bill :      ");
            Scanner input  =new Scanner(System.in);
            int bill=input.nextInt();             
            System.out.println("DONE");
            PreparedStatement stmtt = con.prepareStatement("select top (1) cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.reading,cusinfo.email, cusinfo.address,bill.consumption,bill.cost from cusinfo, bill where cusinfo.metercode=? and (cusinfo.metercode=bill.metercode) and isPaid=1");               
            stmtt.setInt(1,metercode);
            ResultSet k = stmtt.executeQuery(); 
            k.next();
            PreparedStatement sq = con.prepareStatement("update top(1) bill set isPaid=0 where metercode=? and isPaid=1");
            sq.setInt(1,metercode);
            sq.executeUpdate();
            System.out.println("\n THIS IS THE FIRST BILL FOR YOU :");
            System.out.println("metercode:         " + k.getInt(1));
            System.out.println("region : " + k.getString(2));
            System.out.println("Name:  " + k.getString(3));
            System.out.println("reading:      " + k.getInt(4));
            System.out.println("email:      " + k.getString(5));            
            System.out.println("address:      " + k.getString(6));
            System.out.println("consumption:  " + k.getInt(7));
            System.out.println("cost:      " + k.getFloat(8));
           
             con.close();
} 
   
   public static void monthlyReading (int metercode,int currentReading) throws ClassNotFoundException, SQLException {
            Connection con = connect();
            PreparedStatement stmt = con.prepareStatement("select reading from cusinfo where metercode=?");
            stmt.setInt(1,metercode);
            ResultSet l = stmt.executeQuery();
            l.next();
            int lastReading=l.getInt(1);
            try{
                
            if (operator.validation(metercode, currentReading))
            {
            int cons =currentReading-lastReading;                 
            System.out.println("your new consumption: "+cons);
            float cost=cons*operator.traiff();
            
            
                      
            PreparedStatement r = con.prepareStatement("select region from cusinfo where metercode=?");
            r.setInt(1,metercode);
            ResultSet re = r.executeQuery();
            re.next();    
            String reg=re.getString(1);

             
               PreparedStatement sq = con.prepareStatement("update cusinfo set reading=? where metercode=?");
               sq.setInt(1,currentReading);            
               sq.setInt(2,metercode);
               sq.executeUpdate();

               
               
               
               PreparedStatement sq2 = con.prepareStatement("insert into bill values (?,?,?,1,1,?)");
               sq2.setInt(1,metercode);            
               sq2.setInt(2,cons);
               sq2.setFloat(3,cost);               
               sq2.setString(4,reg);
               sq2.executeUpdate();
            
               System.out.println("Done,your reading fill succesfully:");
            }}
            catch(Exception e)
            {
                System.out.println(e);
            }
             con.close();
}
   
    public static void compliment (int metercode) throws ClassNotFoundException, SQLException {
        Scanner input = new Scanner(System.in);
        Connection con = connect();
        System.out.println("please enter your compliment : ");
        String compliment = input.nextLine(); 
        PreparedStatement stmt = con.prepareStatement("UPDATE cusinfo set compliment = ? where metercode=?");
        stmt.setNString(1, compliment);
        stmt.setInt(2, metercode);
        stmt.executeLargeUpdate();
        System.out.println("Your complaint has been submitted successfully and we will consider it as soon as possible ...");

    }
  
   public static void old_viewEmail(String email , int password) throws SQLException, ClassNotFoundException{
      try{
      Connection con = connect();
      PreparedStatement view = con.prepareStatement("SELECT emails from cusinfo where email = ? AND password = ?");
      view.setString(1, email);
      view.setInt(2, password);
      ResultSet r =view.executeQuery();
      r.next();
      System.out.println(r.getString(1));
      }
      catch (Exception e)
      {
          System.out.println("your mail not found please enter valid email.......");
      }
  }
}



























/////**
//// *
//// * @author shreen
//// */
////public class oldCustomer {
////    
////   public static boolean login (int metercode) throws ClassNotFoundException, SQLException {
////       try{
////            Connection con = connect();
////            PreparedStatement stmt = con.prepareStatement("select name from cusinfo where metercode=?");
////            stmt.setInt(1,metercode);
////            ResultSet res = stmt.executeQuery();
////            res.next();
////            System.out.println("welcome mr:" +res.getString(1));
////            return true;
////            }
////            catch(Exception e)
////            {
////                System.out.println("Your metercode not found...");
////                return false;
////            }
////   }
////   
////   public static void payBill (int metercode) throws ClassNotFoundException, SQLException{
////            Connection con = connect();              
////            System.out.println("Enter your credit to pay bill :      ");
////            Scanner input  =new Scanner(System.in);
////            int bill=input.nextInt();             
////            System.out.println("DONE");
////            PreparedStatement stmtt = con.prepareStatement("select top (1) cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.reading,cusinfo.email, cusinfo.address,bill.consumption,bill.cost from cusinfo, bill where cusinfo.metercode=? and (cusinfo.metercode=bill.metercode) and isPaid=1");               
////            stmtt.setInt(1,metercode);
////            ResultSet k = stmtt.executeQuery(); 
////            k.next();
////            PreparedStatement sq = con.prepareStatement("update top(1) bill set isPaid=0 where metercode=? and isPaid=1");
////            sq.setInt(1,metercode);
////            sq.executeUpdate();
////            System.out.println("\n THIS IS THE FIRST BILL FOR YOU :");
////            System.out.println("metercode:         " + k.getInt(1));
////            System.out.println("region : " + k.getString(2));
////            System.out.println("Name:  " + k.getString(3));
////            System.out.println("reading:      " + k.getInt(4));
////            System.out.println("email:      " + k.getString(5));            
////            System.out.println("address:      " + k.getString(6));
////            System.out.println("consumption:  " + k.getInt(7));
////            System.out.println("cost:      " + k.getFloat(8));
////           
////             con.close();
////} 
////   
////   public static void monthlyReading (int metercode,int currentReading) throws ClassNotFoundException, SQLException {
////            Connection con = connect();
////            PreparedStatement stmt = con.prepareStatement("select reading from cusinfo where metercode=?");
////            stmt.setInt(1,metercode);
////            ResultSet l = stmt.executeQuery();
////            l.next();
////            int lastReading=l.getInt(1);
////            try{
////                
////            if (operator.validation(metercode, currentReading))
////            {
////            int cons =currentReading-lastReading;                 
////            System.out.println("your new consumption: "+cons);
////            float cost=cons*operator.traiff();
////            
////            
////                      
////            PreparedStatement r = con.prepareStatement("select region from cusinfo where metercode=?");
////            r.setInt(1,metercode);
////            ResultSet re = r.executeQuery();
////            re.next();    
////            String reg=re.getString(1);
////
////             
////               PreparedStatement sq = con.prepareStatement("update cusinfo set reading=? where metercode=?");
////               sq.setInt(1,currentReading);            
////               sq.setInt(2,metercode);
////               sq.executeUpdate();
////
////               
////               
////               
////               PreparedStatement sq2 = con.prepareStatement("insert into bill values (?,?,?,1,1,?)");
////               sq2.setInt(1,metercode);            
////               sq2.setInt(2,cons);
////               sq2.setFloat(3,cost);               
////               sq2.setString(4,reg);
////               sq2.executeUpdate();
////            
////               System.out.println("Done,your reading fill succesfully:");
////            }}
////            catch(Exception e)
////            {
////                System.out.println(e);
////            }
////             con.close();
////}
////   
////    public static void compliment (int metercode) throws ClassNotFoundException, SQLException {
////        Scanner input = new Scanner(System.in);
////        Connection con = connect();
////        System.out.println("please enter your compliment : ");
////        String compliment = input.nextLine(); 
////        PreparedStatement stmt = con.prepareStatement("UPDATE cusinfo set compliment = ? where metercode=?");
////        stmt.setNString(1, compliment);
////        stmt.setInt(2, metercode);
////        stmt.executeLargeUpdate();
////        System.out.println("Your complaint has been submitted successfully and we will consider it as soon as possible ...");
////
////    }
////   }
////
////
////
////
////
////
////
////
////
////
////
////
/////**
////
//// *
////
//// * @author Raz
////
//// */
////////
////////public class NewClass {
////////
////////    public static Connection connect() throws ClassNotFoundException{ 
////////
//////////    Connection con =null; 
////////
////////    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////////
//////////    String connectionURL="jdbc:sqlserver://localhost:1433;databaseName=admindb;user=admin;password=1234"; 
////////
////////    try{
////////
////////    Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=admindb;user=admin;password=1234");
////////   // System.out.println("Connection is successfull");
////////            Statement stmt = con.createStatement();
////////     //   PreparedStatement pstmt = con.prepareStatement("UPDATE monthes SET month1 = 7897987 WHERE [meter code] = 1000");
////////       //                 pstmt.executeUpdate();
////////
////////           // ResultSet r = stmt.executeQuery("update monthes set month1=5555 where [meter code]=1000 ");
////////            ResultSet rs = stmt.executeQuery("select region from customer");
////////
////////
////////                         while(rs.next()) {
////////                System.out.println(rs.getString(1));
////////            }
////////
////////    }
////////
////////    catch(SQLException e){
////////
////////     System.out.println(e);
////////
////////    }
//////////        return con;
////////
////////
////////
////////
////////
////////
////////
////////
////////        return null;
////////    
////////
////////}
////////
////////}
////
////
////
