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
public class operatorInterface {
    public static void opratorInterFace ()throws ClassNotFoundException, SQLException
    {
          Scanner input = new Scanner(System.in);
          System.out.println("Enter your username and password :");
          String un = input.next();
          int pass = input.nextInt();
                //check if operator valid or not              
                if (operator.login(un, pass))
                {
                    System.out.println("Welcome..");
                    char r =0;
                    do 
                    {
                        System.out.println("1)Collect payments.");
                        System.out.println("2)Print bill by metercode.");
                        System.out.println("3)view bills by region.");
                        int oChoise = input.nextInt();
                        //start funtions
                        switch (oChoise)
                        {
                            case 1:
                                operator.collect();
                                break;
                            case 2:
                            {
                                System.out.println("Enter meter code: ");
                                int mc = input.nextInt();
                                operator.PrintBillWithMeterCode(mc);
                                break;
                            }
                            case 3:
                            {
                                System.out.println("Enter region: ");
                                String reg = input.next();
                                operator.viewBillsWithRegion(reg);
                                break;
                            }
                            default:
                                System.out.println("invalid choise.");
                      
                        }
                        //end of funtions
                        System.out.println("Want another operation as an operator? (y\n): ");
                           r= input.next().charAt(0);
                    }while (r!='n');
                    return ;
                }
                //end operator validation check
                else {
                    return;
                }
    }


}