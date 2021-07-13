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
public class oldCustomerInterface {
    public static void interFace() throws ClassNotFoundException, SQLException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Customer.." + "\n" +"Enter your meter code");
        int mc = input.nextInt();
        //check if valid
        if (oldCustomer.login(mc)) // if true
        {
            System.out.println("Welcome..");
            char continu = 0 ;
            do 
            {
                        System.out.println("1)Pay Bill.");
                        System.out.println("2)Enter Mounthly Reading.");
                        System.out.println("3)Make a complaint");
                        int ocChoise = input.nextInt();
                        //start of switch case
                        switch (ocChoise){
                            case 1:
                            {
                                oldCustomer.payBill(mc);
                                break;
                            }
                            case 2:
                            {
                                //check paid status
                                if (!(operator.paidValidation(mc))){
                                System.out.println("Enter Current Reading: ");
                                int currentReading = input.nextInt();
                                //reading check
                                if (operator.validation(mc, currentReading))
                                {
                                oldCustomer.monthlyReading(mc,currentReading );
                                }
                                else 
                                    System.out.println("Your reading isn'n ture please enter correct reading...");
                                //end of reading check
                                }//end of paid check if
                                else
                                {
                                    System.out.println("Sorry you can't enter reading now. check your mail");
                                }
                                //end of paid check
                                
                                break;
                            }
                            case 3:
                                oldCustomer.compliment(mc);
                                break;
                            default:
                                System.out.println("Enter valid choise");
                        }//end of switch case
                        System.out.println("want another operation as an old customer?(y/n) ");
                        continu = input.next().charAt(0); 
            }while (continu!='n');
            
        }//end of customer check if
        
    }//end of interFace function
}