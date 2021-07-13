package pproject;

import pproject.admin;
import pproject.operator;
import pproject.oldCustomer;
import pproject.newCustomers;
//import javax.swing.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import static pproject.DBconnect.connect;
import java.util.Scanner;
import java.util.ArrayList;
import static pproject.DBconnect.connect;
//import static pproject.Image.image;

public class BillManegement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException  {
        try
              {
        Scanner input = new Scanner(System.in);
        Connection con = connect();
        //start statement
        System.out.println("--------------------Welcome to our system--------------------");
        char y = 0;
        do{
        System.out.println("Enter your selection based on your recipe.....");
        System.out.println("1)Old Customer.");
        System.out.println("2)New Customer(log in system).");
        System.out.println("3)Operator.");
        System.out.println("4)Admin.");
        System.out.println("5)Mail (customers).");
        System.out.print("?: ");
        int choise = input.nextInt();
        switch (choise){
            //old customer choise::
            case 1:
            {
              oldCustomerInterface.interFace();
              break;
            }
            //end old customer
            //start new customer
            case 2:
            {
              newCustomers.fillInfo();
              break;
            }
            //end of new customer
            //start of operator
            case 3:
            {
              operatorInterface.opratorInterFace();
              break;
            }
            
            case 4:
            {
              adminInterface.admin_InterFace();
              break;
            }
            case 5:
            {
                mailInterface.mail_InterFace();
                break;
            }
             default :
             {
                 System.out.println("invalid choise...");
                 break;
             }
             
            } 
            
           System.out.println("Want another operation in system ? (y\n).");
           y = input.next().charAt(0);
        }while (y!='n');
  }
        catch(Exception e)
             // catch(SQLException | FileNotFoundException e)
              {
                        System.out.println(e.getMessage());
              }
}
}
    

























/*
 email 
Dear customer, your account has been temporarily suspended due to the existence of unpaid bills for three months. Please go to the company branch to pay the bills
*/


//          char choise ;
//          do{
//           System.out.println("Enter your metercode: ");
//           int metercode = input.nextInt();
//           boolean x = oldCustomer.login(metercode);
//           if (x==true)
//               System.out.println("chose a choise: ");
//           else if (x==false)
//               System.out.println("want another operation (y/n): ");
//           choise = input.next().charAt(0);
//          } while (choise!='n');
//    Mailer.sendFromGMail("tarekmostafa602@gmail.com", "1362001116", "Sohailamohsen8@gmail.com", "java", "hi");
//    operator.paidValidation(201090);











        //operator x = new operator();
//        String name = input.next();
//        int password = input.nextInt(); 
//        boolean y=operator.login(name, password);
//        if (y==true)
//            System.out.println("welcome "+name);
//        else 
//            System.out.println("sorry username and password not matched...........");
//              System.out.println("Enter meter code: ");
//              int metercode = input.nextInt();
//              System.out.println("enter current reading: ");
//              int currentReading = input.nextInt();
//              boolean x =operator.validation(metercode, currentReading);
//              if (x==true)
//                  System.out.println("correct reading");
//              else if (x==false)
//                  System.out.println("your reading is incorrect............");

/*
Connection con = connect();
        //Statement stmt =  con.createStatement();
        
        PreparedStatement pstmt = con.prepareStatement("update cusinfo set name = ? where metercode=?");
        System.out.println("Enter meter code: ");
        int mt = input.nextInt();
//        System.out.println("Enter new name: ");
//        String nm=input.next();
//        pstmt.setString(1, nm);
//        pstmt.setInt(2,mt);
//        pstmt.executeUpdate();
        
        PreparedStatement st = con.prepareStatement("select * from cusinfo where metercode =?");
        st.setInt(1, mt);
        
        ResultSet rs=st.executeQuery();
       rs.next();
        System.out.println("\nNew customer info:");
              System.out.println("meter code:   " + rs.getInt(1));
              System.out.println("region:   " + rs.getString(2));
              System.out.println("name: " + rs.getString(3));
              System.out.println("reading:  " + rs.getString(4));
              System.out.println("trafirr:  " + rs.getString(5));
              System.out.println("flag: " + rs.getString(6));
              System.out.println("cost: " + rs.getString(7));
            
            con.close();
        
*/






        
    /* while(mt.next()&&nm.next()&&rg.next()&&rd.next()) {
                System.out.println(mt.getInt(1));
                System.out.println(nm.getString(2));
                System.out.println(rg.getString(3));
                System.out.println(rd.getInt(4));
            }*/

 /*ResultSet mt =stmt.executeQuery("select metercode from cusinfo");
        ResultSet nm =stmt.executeQuery("select name from cusinfo");
        ResultSet rg =stmt.executeQuery("select region from cusinfo");
        ResultSet rd =stmt.executeQuery("select reading from cusinfo");*/





















////
////
/////**
//// *
//// * @author ta-rek,sohaila
//// */
////public class BillManegement {
////
////    /**
////     * @param args the command line arguments
////     */
////    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException  {
////        Scanner input = new Scanner(System.in);
////        Connection con = connect();
////        //start statement
////        System.out.println("--------------------Welcome to our system--------------------");
////        char y = 0;
////        do{
////        System.out.println("Enter your selection based on your recipe.....");
////        System.out.println("1)Old Customer.");
////        System.out.println("2)New Customer(log in system).");
////        System.out.println("3)Operator.");
////        System.out.println("4)Admin.");
////        System.out.println("5)Mail (customers).");
////        System.out.print("?: ");
////        int choise = input.nextInt();
////        switch (choise){
////            //old customer choise::
////            case 1:
////            {
////              oldCustomerInterface.interFace();
////              break;
////            }
////            //end old customer
////            //start new customer
////            case 2:
////            {
////              newCustomers.fillInfo();
////              break;
////            }
////            //end of new customer
////            //start of operator
////            case 3:
////            {
////              operatorInterface.opratorInterFace();
////              break;
////            }
////            
////            case 4:
////            {
////              adminInterface.admin_InterFace();
////              break;
////            }
////            case 5:
////            {
////                mailInterface.mail_InterFace();
////                break;
////            }
////             default :
////             {
////                 System.out.println("invalid choise...");
////                 break;
////             }
////             
////            } 
////            
////           System.out.println("Want another operation in system ? (y\n).");
////           y = input.next().charAt(0);
////        }while (y!='n');
////  } 
////}
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
////
////
/////*
//// email 
////Dear customer, your account has been temporarily suspended due to the existence of unpaid bills for three months. Please go to the company branch to pay the bills
////*/
////
////
//////          char choise ;
//////          do{
//////           System.out.println("Enter your metercode: ");
//////           int metercode = input.nextInt();
//////           boolean x = oldCustomer.login(metercode);
//////           if (x==true)
//////               System.out.println("chose a choise: ");
//////           else if (x==false)
//////               System.out.println("want another operation (y/n): ");
//////           choise = input.next().charAt(0);
//////          } while (choise!='n');
//////    Mailer.sendFromGMail("tarekmostafa602@gmail.com", "1362001116", "Sohailamohsen8@gmail.com", "java", "hi");
//////    operator.paidValidation(201090);
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
////        //operator x = new operator();
//////        String name = input.next();
//////        int password = input.nextInt(); 
//////        boolean y=operator.login(name, password);
//////        if (y==true)
//////            System.out.println("welcome "+name);
//////        else 
//////            System.out.println("sorry username and password not matched...........");
//////              System.out.println("Enter meter code: ");
//////              int metercode = input.nextInt();
//////              System.out.println("enter current reading: ");
//////              int currentReading = input.nextInt();
//////              boolean x =operator.validation(metercode, currentReading);
//////              if (x==true)
//////                  System.out.println("correct reading");
//////              else if (x==false)
//////                  System.out.println("your reading is incorrect............");
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
////
////
/////*
////Connection con = connect();
////        //Statement stmt =  con.createStatement();
////        
////        PreparedStatement pstmt = con.prepareStatement("update cusinfo set name = ? where metercode=?");
////        System.out.println("Enter meter code: ");
////        int mt = input.nextInt();
//////        System.out.println("Enter new name: ");
//////        String nm=input.next();
//////        pstmt.setString(1, nm);
//////        pstmt.setInt(2,mt);
//////        pstmt.executeUpdate();
////        
////        PreparedStatement st = con.prepareStatement("select * from cusinfo where metercode =?");
////        st.setInt(1, mt);
////        
////        ResultSet rs=st.executeQuery();
////       rs.next();
////        System.out.println("\nNew customer info:");
////              System.out.println("meter code:   " + rs.getInt(1));
////              System.out.println("region:   " + rs.getString(2));
////              System.out.println("name: " + rs.getString(3));
////              System.out.println("reading:  " + rs.getString(4));
////              System.out.println("trafirr:  " + rs.getString(5));
////              System.out.println("flag: " + rs.getString(6));
////              System.out.println("cost: " + rs.getString(7));
////            
////            con.close();
////        
////*/
////
////
////
////
////
////
////        
////    /* while(mt.next()&&nm.next()&&rg.next()&&rd.next()) {
////                System.out.println(mt.getInt(1));
////                System.out.println(nm.getString(2));
////                System.out.println(rg.getString(3));
////                System.out.println(rd.getInt(4));
////            }*/
////
//// /*ResultSet mt =stmt.executeQuery("select metercode from cusinfo");
////        ResultSet nm =stmt.executeQuery("select name from cusinfo");
////        ResultSet rg =stmt.executeQuery("select region from cusinfo");
////        ResultSet rd =stmt.executeQuery("select reading from cusinfo");*/
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
////
////
////
////
////
////
////
////
////
//////
///////*
//////
///////**
////// *
////// * @author tarek,sohaila
////// */
//////public class BillManegement {
//////
//////    /**
//////     * @param args the command line arguments
//////     */
//////    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException  {
//////        Scanner input = new Scanner(System.in);
//////        Connection con = connect();
//////        //start statement
//////        System.out.println("--------------------Welcome to our system--------------------");
//////        System.out.println("Enter your selection based on your recipe.....");
//////        System.out.println("1)Old Customer.");
//////        System.out.println("2)New Customer(log in system).");
//////        System.out.println("3)Operator.");
//////        System.out.println("4)Admin.");
//////        System.out.println("?: ");
//////        int choise = input.nextInt();
//////        char y = 0;
//////        do{
//////        switch (choise){
//////            //old customer choise::
//////            case 1:
//////            {
//////                System.out.println("Welcome Customer.." + "\n" +"Enter your meter code");
//////                int mc = input.nextInt();
//////                if (oldCustomer.login(mc))
//////                {
//////                    if (operator.paidValidation(mc))
//////                    {
//////                        System.out.println("1)Pay Bill.");
//////                        System.out.println("2)Enter Mounthly Reading.");
//////                        System.out.println("3)Make a complaint");
//////                        char continu = 0 ;
//////                        do {
//////                        int ocChoise = input.nextInt();
//////                        switch (ocChoise){
//////                            case 1:
//////                                oldCustomer.payBill(mc);
//////                                break;
//////                            case 2:
//////                                System.out.println("Enter Current Reading: ");
//////                                int currentReading = input.nextInt();
//////                                oldCustomer.monthlyReading(mc,currentReading );
//////                                break;
//////                            case 3:
//////                                oldCustomer.compliment(mc);
//////                                break;
//////                            default:
//////                                System.out.println("Enter valid choise");
//////                                System.out.println("want another operation(y/n): ");
//////                                continu = input.next().charAt(0); 
//////                        }
//////                        }while (continu != 'n');
//////                        
//////                        }
//////                    else
//////                        {
//////                            System.out.print("you have an important email enter your email to see: ");
//////                            String email = input.nextLine();
//////                            operator.viewEmail(email);
//////                        }    
//////                    }
//////                }
//////            //end old customer
//////            case 2:
//////            {
//////              newCustomers.fillInfo();
//////            }
//////            case 3:
//////            {
//////                System.out.println("Enter your username and password :");
//////                String un = input.next();
//////                int pass = input.nextInt();
//////                boolean res = operator.login(un, pass);
//////                if (res==true)
//////                {
//////                    System.out.println("Welcome..");
//////                    char r =0;
//////                    do 
//////                    {
//////                        System.out.println("1)Collect payments.");
//////                        System.out.println("2)Print bill by metercode.");
//////                        System.out.println("3)view bills by region.");
//////                        int oChoise = input.nextInt();
//////                        switch (oChoise)
//////                        {
//////                            case 1:
//////                                operator.collect();
//////                                break;
//////                            case 2:
//////                            {
//////                                System.out.println("Enter meter code: ");
//////                                int mc = input.nextInt();
//////                                operator.PrintBillWithMeterCode(mc);
//////                                break;
//////                            }
//////                            case 3:
//////                            {
//////                                System.out.println("Enter region: ");
//////                                String reg = input.next();
//////                                operator.viewBillsWithRegion(reg);
//////                                break;
//////                            }
//////                            default:
//////                                System.out.println("invalid choise.");
//////                      
//////                        }
//////                      
//////                        System.out.println("Want another operation ? (y\n): ");
//////                           r= input.next().charAt(0);
//////                    }while (r!='n');
//////                }
//////                else 
//////                    System.out.println("Sorry wrong username or password.");
//////                      
//////            }
//////            
//////            case 4:
//////            {
//////              System.out.println("Enter your username and password : ");
//////              String aum = input.next();
//////              int apass = input.nextInt();
//////              if (admin.cheack(aum, apass))
//////              {
//////                  System.out.println("Welcome..");
//////                  char x =0;
//////                  do
//////                  {
//////                   
//////                   System.out.println("1)Veiw all bills by region.");   
//////                   System.out.println("2)view total collected.");   
//////                   System.out.println("3)Make a consumption statistics.");
//////                   System.out.println("4)Add new customer manually.");
//////                   System.out.println("5)Uptade customer name.");
//////                   System.out.println("6)Update customer email.");
//////                   System.out.println("7)Delete customer (At the request of the client).");
//////                   int aChoise = input.nextInt();
//////                   switch (aChoise)
//////                   {
//////                       case 1:
//////                       {
//////                           System.out.println("Enter region: ");
//////                           String reg = input.nextLine();
//////                           admin.viewBillsWithRegion(reg);
//////                           break;
//////                       }
//////                       case 2:
//////                           admin.view_total_collected();
//////                           break;
//////                       case 3:
//////                       {
//////                           System.out.println("Enter region: ");
//////                           String reg = input.nextLine();
//////                           admin.consumption_statistics(reg);
//////                           break;
//////                       }
//////                       case 4:
//////                       {
//////                           //String name, String mail, String area,String address
//////                           System.out.println("Enter customer name : ");
//////                           String name = input.nextLine();
//////                           System.out.println("Enter customer mail : ");
//////                           String mail = input.nextLine();
//////                           System.out.println("Enter customer region : ");
//////                           String region = input.nextLine();
//////                           System.out.println("Enter customer address : ");
//////                           String address = input.nextLine();
//////                           System.out.println("Enter customer new password : ");
//////                           int password = input.nextInt();
//////                           int mc = admin.manualAdd(name, mail, name, address,password);
//////                           System.out.println("Customer added succesfully and his metercode is : "+mc);
//////                           break;
//////                       } 
//////                       case 5:
//////                       {
//////                           System.out.println("Enter customer new name : ");
//////                           String Nname = input.nextLine();
//////                           System.out.println("Enter customer metercode : ");
//////                           int meter = input.nextInt();
//////                           admin.update_name(aum, meter);
//////                           System.out.println("Updated successfully.");
//////                           break;
//////                       }
//////                       case 6:
//////                       {
//////                           System.out.println("Enter customer new email : ");
//////                           String Nemail = input.nextLine();
//////                           System.out.println("Enter customer new password : ");
//////                           int password = input.nextInt();
//////                           System.out.println("Enter customer metercode : ");
//////                           int meter = input.nextInt();
//////                           admin.update_email(Nemail, password ,meter);
//////                           System.out.println("Updated successfully.");
//////                           break;
//////                       }
//////                       case 7:
//////                       {
//////                           System.out.println("Enter Customer metercode : ");
//////                           int meter = input.nextInt();
//////                           admin.delete(meter);
//////                           System.out.println("Deleted successfully.");
//////                           break;
//////                       }
//////                       default :
//////                           System.out.println("invalid choise...");
//////                   }
//////                   System.out.println("Want another opeartion ? (y\n)..");
//////                   x= input.next().charAt(0);
//////                  }while (x!='n');
//////              }
//////              else 
//////                  System.out.println("sorry invalid password or username.");
//////                      
//////            }
//////             default :
//////                           System.out.println("invalid choise...");
//////                   } 
//////            
//////           System.out.println("Want another operation ? (y\n).");
//////           y = input.next().charAt(0);
//////        }while (y!='n');
//////  } 
//////}
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////*/
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////
//////////
//////////import java.sql.Connection;
//////////import java.sql.DriverManager;
//////////import java.sql.SQLException;
//////////import javax.mail.MessagingException;
//////////import static ssss.admin.add;
//////////import static ssss.admin.consumption_statistics;
//////////import static ssss.admin.view_total_collected;
//////////import static ssss.opretor.PrintBillWithMeterCode;
//////////import static ssss.opretor.ValidateReading;
//////////import static ssss.opretor.collect;
//////////import static ssss.opretor.paidValidation;
//////////import static ssss.opretor.payBill;
//////////import static ssss.opretor.searchmetercode;
//////////import static ssss.opretor.viewBillsWithRegion;
//////////
///////////**
//////////
////////// *
//////////
////////// * @author Raz
//////////
////////// */
//////////
//////////public class Ssss {
//////////
//////////    /**
//////////     * @param args the command line arguments
//////////     */
//////////    public static void main(String[] args) throws ClassNotFoundException, SQLException, MessagingException {
//////////
//////////     //  CollectPaymentFromCustomer(2000,1); 
//////////     
//////////     //collect();
//////////    if(paidValidation(1)){
//////////             viewBillsWithRegion("giza");
//////////
//////////   }
//////////     //if(ValidateReading(1,99999)){
////////////              payBill(1);
////////////view_total_collected();
////////////int n=add("sss","ddd","sds","fff");
////////////     //}
//////////     consumption_statistics("cairo");
//////////     PrintBillWithMeterCode(3);
//////////     }
//////////
//////////  
//////////    
//////////}
