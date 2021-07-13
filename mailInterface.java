/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pproject;


import static pproject.DBconnect.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


/**
 *
 * @author hp
 */
public class mailInterface {
   public static void mail_InterFace () throws ClassNotFoundException, SQLException
   {
     Scanner input = new Scanner(System.in);  
     System.out.print("Enter your mail: ");
     String mail = input.next();
     System.out.print("Enter your password: ");
     int password = input.nextInt();
     try{
         Connection con = connect();
         PreparedStatement view = con.prepareStatement("SELECT emails from cusinfo where email = ? AND password = ?");
         view.setString(1, mail);
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