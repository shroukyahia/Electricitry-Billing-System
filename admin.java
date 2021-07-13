
package pproject;

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
import java.util.Random;



/**
 *
 * @author shaima
 */
public class admin {
    private static String user_name="admin";
    private static int password=12345;
    boolean cheack;

   
    //login Admin
    public static boolean cheack(String name, int pass) {
        return admin.password == pass && name.equals(admin.user_name);
    }
   //total collected
      public static void view_total_collected() throws SQLException, ClassNotFoundException {
        Connection con = connect();          
        PreparedStatement stmt = con.prepareStatement("select sum(cost) from collected ");
        ResultSet res = stmt.executeQuery();
        res.next();        
        System.out.println("the total collected " + res.getInt(1) + "\n");
        
        con.close();
    }
   //add
    public static void add(String name, String mail, String area,String address,int password,String contract) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        Connection con = connect(); 
        String Characters ="0123456789";
        char[] temp = new char[8];
        String metercode="";  
        Random rand = new Random();
        for (int i=0;i<8;i++) {
            temp[i] = Characters.charAt(rand.nextInt(Characters.length()));
        }
        for(int i=0;i<temp.length;i++) {
            metercode+=temp[i];
        }
        int code = Integer.parseInt(metercode);
        
                
        String sql = "insert into cusinfo(name, metercode ,email ,region,reading,address,password,contract) values(?,?,?,?,0,?,?,?)";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, name);
        p.setInt(2,code);
        p.setString(3, mail);
        p.setString(4, area);
        p.setString(5,address);
        p.setInt(6,password);
        FileInputStream fis = new FileInputStream(contract);
        p.setBinaryStream(7, fis, fis.available());
        p.executeUpdate();
        String msg= "your informations is fill succesfully and your meter code is "+metercode;
        PreparedStatement email = con.prepareStatement("use admindb; UPDATE cusinfo set emails = ? where metercode = ? ;");
        email.setNString(1,msg);
        email.setInt(2, code);
        email.executeUpdate();
        System.out.println("Done,check your mail...");
    } 
    /*public static int manualAdd(String name, String mail, String area,String address,int password,String bath) throws SQLException, ClassNotFoundException {
        Connection con = connect(); 
        String Characters ="0123456789";
        char[] temp = new char[8];
        String metercode="";  
        Random rand = new Random();
        for (int i=0;i<8;i++) {
            temp[i] = Characters.charAt(rand.nextInt(Characters.length()));
        }
        for(int i=0;i<temp.length;i++) {
            metercode+=temp[i];
        }
        int code = Integer.parseInt(metercode);
        String sql = "insert into cusinfo(name, metercode ,email ,region,reading,address,password,contract) values(?,?,?,?,0,?,?,?)";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, name);
        p.setInt(2,code);
        p.setString(3, mail);
        p.setString(4, area);
        p.setString(5,address);
        p.setInt(6,password);
        p.setString(7,bath);
        p.executeUpdate();
        return code;
    }*/
    public static int manualAdd(String name, String mail, String area,String address,String bath) throws SQLException, ClassNotFoundException {
        Connection con = connect(); 
        String Characters ="0123456789";
        char[] temp = new char[8];
        String metercode="";  
        Random rand = new Random();
        for (int i=0;i<8;i++) {
            temp[i] = Characters.charAt(rand.nextInt(Characters.length()));
        }
        for(int i=0;i<temp.length;i++) {
            metercode+=temp[i];
        }
        int code = Integer.parseInt(metercode);
        String sql = "insert into cusinfo(name, metercode ,email ,region,reading,address,contract) values(?,?,?,?,0,?,?)";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, name);
        p.setInt(2,code);
        p.setString(3, mail);
        p.setString(4, area);
        p.setString(5,address);
        p.setString(6,bath);
        p.executeUpdate();
        return code;
    }
     //update name
    public static void update_name(String s,int code) throws SQLException, ClassNotFoundException {
        Connection con = connect();          

        String sql=" update cusinfo set name =? where metercode =?";
        PreparedStatement p=con.prepareStatement(sql);
        p.setString(1,s);
        p.setInt(2, code);
        p.executeUpdate();
        System.out.println("done");
        con.close();
    }
    //update email
    public static void update_email(String e,int mc) throws SQLException, ClassNotFoundException {
         Connection con = connect();          

         String sql=" update cusinfo set email=? where metercode =?";
         PreparedStatement p=con.prepareStatement(sql);
         p.setString(1,e);
         p.setInt(2, mc);
         p.executeUpdate();
         System.out.println("done");
                con.close();
            } 
    //dalete
     public static void delete(int mc) throws SQLException, ClassNotFoundException
     {
     Connection con = connect();          
     String sql="delete from cusinfo where metercode=?";
     PreparedStatement p=con.prepareStatement(sql);
     p.setInt(1, mc);
       int a = p.executeUpdate();
        if (a > 0) {
            System.out.println("done");
        }
        con.close();
 }
     //consumption statistics
      public static void consumption_statistics(String area) throws SQLException, ClassNotFoundException
       {
        Connection con = connect();          
        PreparedStatement stmt = con.prepareStatement("select sum(consumption) ,avg(consumption) from bill where region=? ");
        stmt.setString(1,area);
       
        ResultSet res = stmt.executeQuery();
        res.next(); 
        
        System.out.printf("The total of consumption for this region : "+res.getInt(1)+"\nAnd the average of consumption for this region : "+res.getInt(2)+"\n");
        con.close();

}
      //viewBillsWithRegion
      public static void viewBillsWithRegion(String region) throws SQLException, ClassNotFoundException{
              
              Connection con = connect();
              PreparedStatement stmt = con.prepareStatement("select cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.reading,cusinfo.email, cusinfo.address,bill.consumption,bill.cost from cusInfo,bill where cusInfo.metercode=bill.metercode AND cusinfo.region=?;");       
              stmt.setString(1,region);       
              ResultSet l = stmt.executeQuery(); 
              while(l.next())
              {              
            System.out.println("\nBILL:");
            System.out.println("metercode:         " + l.getInt(1));
            System.out.println("region : " + l.getString(2));
            System.out.println("Name:  " + l.getString(3));
            System.out.println("reading:      " + l.getInt(4));
            System.out.println("email:      " + l.getString(5));            
            System.out.println("address:      " + l.getString(6));
            System.out.println("consumption:  " + l.getInt(7));
            System.out.println("cost:      " + l.getInt(8));

              }
             
              con.close();
   }
       
}
































///**
// *
// * @author shaima
// */
//public class admin {
//    private static String user_name="admin";
//    private static int password=12345;
//    boolean cheack;
//
//   
//    //login Admin
//    public static boolean cheack(String name, int pass) {
//        return admin.password == pass && name.equals(admin.user_name);
//    }
//   //total collected
//      public static void view_total_collected() throws SQLException, ClassNotFoundException {
//        Connection con = connect();          
//        PreparedStatement stmt = con.prepareStatement("select sum(cost) from collected ");
//        ResultSet res = stmt.executeQuery();
//        res.next();        
//        System.out.println("the total collected " + res.getInt(1) + "\n");
//        
//        con.close();
//    }
//   //add
//     public static void add(String name, String mail, String area,String address,int password,String contract) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
//        Connection con = connect(); 
//        String Characters ="0123456789";
//        char[] temp = new char[8];
//        String metercode="";  
//        Random rand = new Random();
//        for (int i=0;i<8;i++) {
//            temp[i] = Characters.charAt(rand.nextInt(Characters.length()));
//        }
//        for(int i=0;i<temp.length;i++) {
//            metercode+=temp[i];
//        }
//        int code = Integer.parseInt(metercode);
//        
//                
//        String sql = "insert into cusinfo(name, metercode ,email ,region,reading,address,password,contract) values(?,?,?,?,0,?,?,?)";
//        PreparedStatement p = con.prepareStatement(sql);
//        p.setString(1, name);
//        p.setInt(2,code);
//        p.setString(3, mail);
//        p.setString(4, area);
//        p.setString(5,address);
//        p.setInt(6,password);
//        FileInputStream fis = new FileInputStream(contract);
//   
//        p.setBinaryStream(7, fis, fis.available());
//        p.executeUpdate();
//        String msg= "your informations is fill succesfully and your meter code is "+metercode;
//        PreparedStatement email = con.prepareStatement("use admindb; UPDATE cusinfo set emails = ? where metercode = ? ;");
//        email.setNString(1,msg);
//        email.setInt(2, code);
//        email.executeUpdate();
//        System.out.println("Done,check your mail...");
//    } 
//    public static int manualAdd(String name, String mail, String area,String address,int password) throws SQLException, ClassNotFoundException {
//        Connection con = connect(); 
//        String Characters ="0123456789";
//        char[] temp = new char[8];
//        String metercode="";  
//        Random rand = new Random();
//        for (int i=0;i<8;i++) {
//            temp[i] = Characters.charAt(rand.nextInt(Characters.length()));
//        }
//        for(int i=0;i<temp.length;i++) {
//            metercode+=temp[i];
//        }
//        int code = Integer.parseInt(metercode);
//        String sql = "insert into cusinfo(name, metercode ,email ,region,reading,address,password) values(?,?,?,?,0,?,?)";
//        PreparedStatement p = con.prepareStatement(sql);
//        p.setString(1, name);
//        p.setInt(2,code);
//        p.setString(3, mail);
//        p.setString(4, area);
//        p.setString(5,address);
//        p.setInt(6,password);               
//        p.executeUpdate();
//        return code;
//    }
//     //update name
//    public static void update_name(String s,int code) throws SQLException, ClassNotFoundException {
//        Connection con = connect();          
//
//        String sql=" update cusinfo set name =? where metercode =?";
//        PreparedStatement p=con.prepareStatement(sql);
//        p.setString(1,s);
//        p.setInt(2, code);
//        p.executeUpdate();
//        System.out.println("done");
//        con.close();
//    }
//    //update email
//    public static void update_email(String e,int pass,int mc) throws SQLException, ClassNotFoundException {
//         Connection con = connect();          
//
//         String sql=" update cusinfo set email=? and password=? where metercode =?";
//         PreparedStatement p=con.prepareStatement(sql);
//         p.setString(1,e);
//         p.setInt(2,pass);        
//         p.setInt(3, mc);
//         p.executeUpdate();
//         System.out.println("done");
//                con.close();
//            } 
//    //dalete
//     public static void delete(int mc) throws SQLException, ClassNotFoundException
//     {
//     Connection con = connect();          
//     String sql="delete from cusinfo where metercode=?";
//     PreparedStatement p=con.prepareStatement(sql);
//     p.setInt(1, mc);
//       int a = p.executeUpdate();
//        if (a > 0) {
//            System.out.println("done");
//        }
//        con.close();
// }
//     //consumption statistics
//      public static void consumption_statistics(String area) throws SQLException, ClassNotFoundException
//       {
//        Connection con = connect();          
//        PreparedStatement stmt = con.prepareStatement("select sum(consumption) ,avg(consumption) from bill where region=? ");
//        stmt.setString(1,area);
//       
//        ResultSet res = stmt.executeQuery();
//        res.next(); 
//        
//        System.out.printf("The total of consumption for this region : "+res.getInt(1)+"\nAnd the average of consumption for this region : "+res.getInt(2)+"\n");
//        con.close();
//
//}
//      //viewBillsWithRegion
//      public static void viewBillsWithRegion(String region) throws SQLException, ClassNotFoundException{
//              
//              Connection con = connect();
//              PreparedStatement stmt = con.prepareStatement("select cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.reading,cusinfo.email, cusinfo.address,bill.consumption,bill.cost from cusInfo,bill where cusInfo.metercode=bill.metercode AND cusinfo.region=?;");       
//              stmt.setString(1,region);       
//              ResultSet l = stmt.executeQuery(); 
//              while(l.next())
//              {              
//            System.out.println("\nBILL:");
//            System.out.println("metercode:         " + l.getInt(1));
//            System.out.println("region : " + l.getString(2));
//            System.out.println("Name:  " + l.getString(3));
//            System.out.println("reading:      " + l.getInt(4));
//            System.out.println("email:      " + l.getString(5));            
//            System.out.println("address:      " + l.getString(6));
//            System.out.println("consumption:  " + l.getInt(7));
//            System.out.println("cost:      " + l.getInt(8));
//
//              }
//             
//              con.close();
//   }
//      
//
//    
//    
//    
//}