/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pproject;

import customers.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
 * @author shorouk
 */
public class newCustomers {
    
    public static void fillInfo () throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to our System if you want to subscripe in our system,"+"\n"+"please fill this inforamtion below.....");
        System.out.println("Enter your name : ");
        String name = input.nextLine();
        System.out.println("Enter your region : ");
        String region = input.nextLine();
        System.out.println("Enter your address : ");
        String address = input.nextLine();
        System.out.println("Enter your email : ");
        String email = input.nextLine();
        System.out.println("Enter your password : ");
        int password = input.nextInt(); 
        Contractt c1=new Contractt();
        System.out.println("Enter The Path Of Photo :");
        //String contract =input.next();
       // System.out.println("Enter The Path Of Photo :");
        c1.setContract(input.next());
        String contract=c1.getContract();
        InputStream in = new FileInputStream(contract); //To Check the pic in the file or not
        Image1 i=new Image1();             //For showing The Photo to the Customer
        i.pic=c1.getContract();
        i.Image1();
        admin.add(name,email,region,address,password,contract);
             
    }
    
}












///**
// *
// * @author shorouk
// */
//public class newCustomers {
//    
//    public static void fillInfo () throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Welcome to our System if you want to subscripe in our system,"+"\n"+"please fill this inforamtion below.....");
//        System.out.println("Enter your name : ");
//        String name = input.nextLine();
//        System.out.println("Enter your region : ");
//        String region = input.nextLine();
//        System.out.println("Enter your address : ");
//        String address = input.nextLine();
//        System.out.println("Enter your email : ");
//        String email = input.nextLine();
//        System.out.println("Enter your password : ");
//        int password = input.nextInt();
//        //System.out.println("Do you want to atta"); 
//        System.out.println("Enter The Path Of Photo :");
//        String contract =input.next();
//                    
//        
//        admin.add(name,email,region,address,password,contract);
//             
//    }
//    
//}
