/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pproject;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class adminInterface {
   public static void admin_InterFace() throws ClassNotFoundException, SQLException
   {
              Scanner input = new Scanner(System.in);
              System.out.println("Enter your username and password : ");
              String aum = input.next();
              int apass = input.nextInt();
              //admin validation check
              if (admin.cheack(aum, apass))
              {
                  System.out.println("Welcome..");
                  char x =0;
                  do
                  {
                   System.out.println("1)Veiw all bills by region.");   
                   System.out.println("2)view total collected.");   
                   System.out.println("3)Make a consumption statistics.");
                   System.out.println("4)Add new customer manually.");
                   System.out.println("5)Uptade customer name.");
                   System.out.println("6)Update customer email.");
                   System.out.println("7)Delete customer (At the request of the client).");
                   int aChoise = input.nextInt();
                   //admin functions 
                   switch (aChoise)
                   {
                       case 1:
                       {
                           System.out.println("Enter region: ");
                           String reg = input.next();
                           admin.viewBillsWithRegion(reg);
                           break;
                       }
                       case 2:
                           admin.view_total_collected();
                           break;
                       case 3:
                       {
                           System.out.println("Enter region: ");
                           String reg = input.nextLine();
                           admin.consumption_statistics(reg);
                           break;
                       }
                       case 4:
                       {
                           //String name, String mail, String area,String address
                           System.out.println("Enter customer name : ");
                           String name = input.nextLine();
                           System.out.println("Enter customer mail : ");
                           String mail = input.nextLine();
                           System.out.println("Enter customer region : ");
                           String region = input.nextLine();
                           System.out.println("Enter customer address : ");
                           String address = input.nextLine();
                           System.out.println("Enter The Path Of Photo :");
                           String contract =input.next();
                           int mc = admin.manualAdd(name, mail, name, address,contract);
                           System.out.println("Customer added succesfully and his metercode is : "+mc);
                           break;
                       } 
                       case 5:
                       {
                           System.out.println("Enter customer new name : ");
                           String Nname = input.nextLine();
                           System.out.println("Enter customer metercode : ");
                           int meter = input.nextInt();
                           admin.update_name(aum, meter);
                           System.out.println("Updated successfully.");
                           break;
                       }
                       case 6:
                       {
                           System.out.println("Enter customer new email : ");
                           String Nemail = input.nextLine();
                           System.out.println("Enter customer metercode : ");
                           int meter = input.nextInt();
                           admin.update_email(Nemail, meter);
                           System.out.println("Updated successfully.");
                           break;
                       }
                       case 7:
                       {
                           System.out.println("Enter Customer metercode : ");
                           int meter = input.nextInt();
                           admin.delete(meter);
                           System.out.println("Deleted successfully.");
                           break;
                       }
                       default :
                           System.out.println("invalid choise...");
                           break;
                   }
                   //end admin fuctions
                   System.out.println("Want another opeartion as an admin? (y\"\n\")..");
                   x= input.next().charAt(0);
                  }while (x!='n');
                  return ;
              }
              //end of admin validation check if
              else 
              {
                  System.out.println("sorry invalid password or username.");
                  return;
              }    
        
   }
}
 



/*
System.out.println("Enter your username and password : ");
              String aum = input.next();
              int apass = input.nextInt();
              if (admin.cheack(aum, apass))
              {
                  System.out.println("Welcome..");
                  char x =0;
                  do
                  {
                   
                   System.out.println("1)Veiw all bills by region.");   
                   System.out.println("2)view total collected.");   
                   System.out.println("3)Make a consumption statistics.");
                   System.out.println("4)Add new customer manually.");
                   System.out.println("5)Uptade customer name.");
                   System.out.println("6)Update customer email.");
                   System.out.println("7)Delete customer (At the request of the client).");
                   int aChoise = input.nextInt();
                   switch (aChoise)
                   {
                       case 1:
                       {
                           System.out.println("Enter region: ");
                           String reg = input.nextLine();
                           admin.viewBillsWithRegion(reg);
                           break;
                       }
                       case 2:
                           admin.view_total_collected();
                           break;
                       case 3:
                       {
                           System.out.println("Enter region: ");
                           String reg = input.nextLine();
                           admin.consumption_statistics(reg);
                           break;
                       }
                       case 4:
                       {
                           //String name, String mail, String area,String address
                           System.out.println("Enter customer name : ");
                           String name = input.nextLine();
                           System.out.println("Enter customer mail : ");
                           String mail = input.nextLine();
                           System.out.println("Enter customer region : ");
                           String region = input.nextLine();
                           System.out.println("Enter customer address : ");
                           String address = input.nextLine();
                           int mc = admin.manualAdd(name, mail, name, address);
                           System.out.println("Customer added succesfully and his metercode is : "+mc);
                           break;
                       } 
                       case 5:
                       {
                           System.out.println("Enter customer new name : ");
                           String Nname = input.nextLine();
                           System.out.println("Enter customer metercode : ");
                           int meter = input.nextInt();
                           admin.update_name(aum, meter);
                           System.out.println("Updated successfully.");
                           break;
                       }
                       case 6:
                       {
                           System.out.println("Enter customer new email : ");
                           String Nemail = input.nextLine();
                           System.out.println("Enter customer metercode : ");
                           int meter = input.nextInt();
                           admin.update_email(Nemail, meter);
                           System.out.println("Updated successfully.");
                           break;
                       }
                       case 7:
                       {
                           System.out.println("Enter Customer metercode : ");
                           int meter = input.nextInt();
                           admin.delete(meter);
                           System.out.println("Deleted successfully.");
                           break;
                       }
                       default :
                           System.out.println("invalid choise...");
                   }
                   System.out.println("Want another opeartion ? (y\n)..");
                   x= input.next().charAt(0);
                  }while (x!='n');
              }
              else 
                  System.out.println("sorry invalid password or username.");
                   
*/
