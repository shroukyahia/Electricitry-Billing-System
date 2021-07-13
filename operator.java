
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
 * @author tarek&sohaila
 */
public  class operator {
    Scanner input = new Scanner(System.in);
 // to enable operator to log in system          
public static  boolean login (String username , int password) throws ClassNotFoundException, SQLException{
        Connection con = connect();
        try{
        Statement stmt =  con.createStatement();
        PreparedStatement pstmt = con.prepareStatement("select password from operator where username=? ;");
        pstmt.setNString(1, username);
        ResultSet rs=pstmt.executeQuery();
        rs.next();
        System.out.println("Welcome . mr"+username);
        return true;
        }
        catch(Exception e)
        {
          System.out.println("Invalid user name or password.");
          return false;
        }
    }
 // to define tariff   
public static float traiff (){
        float traiff = (float) .5;
        return traiff; 
    } 
  //to collect payments  
public static void collect () throws ClassNotFoundException, SQLException {
       

              Connection con = connect();
              Statement stmt = con.createStatement();                 
              ResultSet cost = stmt.executeQuery("select metercode, cost from bill where isPaid =0 and isCollected=1");
              //ResultSet meter = stmt.executeQuery("select metercode from bill where isPaid =0 and isCollected=0");
              ArrayList<Integer> meterc = new ArrayList<Integer>();   
              ArrayList<Integer> costs = new ArrayList<Integer>();
              System.out.println("meter code    cost");
              while(cost.next()) {
              System.out.println(cost.getInt(1)+ "        "+cost.getInt(2));
              meterc.add(cost.getInt(1));
              costs.add(cost.getInt(2));
              }
             int i=0;
              for (i=0;i<meterc.size();i++) { 
               PreparedStatement sq = con.prepareStatement("INSERT INTO collected VALUES (?,?);");
               sq.setInt(1,meterc.get(i));
               sq.setInt(2,costs.get(i));
               sq.executeUpdate();
              }
         String sql=" update bill set isCollected=0 where isPaid=0 and isCollected=1;";
         PreparedStatement p=con.prepareStatement(sql);
         p.executeUpdate();
            con.close();
          }
  //to validate reading  
public static boolean validation (int metercode , int currentreading) throws ClassNotFoundException, SQLException {
       Connection con = connect();
            PreparedStatement stmt = con.prepareStatement("select reading from cusinfo where metercode=?");
            stmt.setInt(1,metercode);
            ResultSet res = stmt.executeQuery();
            res.next();
            //l.getInt(1);
            if(res.getInt(1)<=currentreading)
                return true;
            else
                return false;
   }
 //to make sure about customer's payments
public static boolean paidValidation (int metercode) throws ClassNotFoundException, SQLException{
            Connection con = connect();
            PreparedStatement stmt = con.prepareStatement("select sum(isPaid) from bill where metercode=?");
            stmt.setInt(1,metercode);
            ResultSet res = stmt.executeQuery();
            res.next();
            if (res.getInt(1)>=3)
            {
                String msg = "Dear customer,"+"\n"+"your account has been temporarily suspended due to the existence of unpaid bills for three months."+"\n"+"Please go to the company branch to pay the bills";
                PreparedStatement email = con.prepareStatement("UPDATE cusinfo set emails = ? where metercode = ?");
                email.setString(1, msg);
                email.setInt(2, metercode);
                email.executeUpdate();
                return true;
            }
            else if (res.getInt(1)==0)
            {
                System.out.println("You paid all bill dear customer.");
                return false;
            }
            else
                return false;
        }
  //to print bill with customer meter code
public static void PrintBillWithMeterCode (int metercode) throws SQLException, ClassNotFoundException{
              
              Connection con = connect();
              PreparedStatement stmt = con.prepareStatement("select top(1) cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.email,cusinfo.address,bill.consumption,bill.cost from cusinfo join bill on (bill.metercode=cusinfo.metercode and bill.metercode = ?) and bill.isPaid=1");       
              stmt.setInt(1,metercode);             
              ResultSet l = stmt.executeQuery(); 
              l.next();
              
              System.out.println("\nBILL:");
            System.out.println("metercode:         " + l.getInt(1));
            System.out.println("region : " + l.getString(2));
            System.out.println("Name:  " + l.getString(3));
            System.out.println("email:      " + l.getString(4));            
            System.out.println("address:      " + l.getString(5));
            System.out.println("consumption:  " + l.getInt(6));
            System.out.println("cost:      " + l.getInt(7));

              
             
              con.close();
   }
//to view all bills with reading
public static void viewBillsWithRegion(String region) throws SQLException, ClassNotFoundException{
              
              Connection con = connect();
              PreparedStatement stmt = con.prepareStatement("select cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.reading,cusinfo.email, cusinfo.address,bill.consumption,bill.cost,bill.isPaid,bill.isCollected from cusInfo,bill where cusInfo.metercode=bill.metercode AND cusinfo.region=?;");       
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
            System.out.println("Is paid?:      " + l.getInt(9));
            System.out.println("Is collected?:      " + l.getInt(10));

              }
             
              con.close();
   }

}





































/////**
//// *
//// * @author sohaila & tarek
//// */
////public  class operator {
////    Scanner input = new Scanner(System.in);
//// // to enable operator to log in system          
////public static  boolean login (String username , int password) throws ClassNotFoundException, SQLException{
////        Connection con = connect();
////        Statement stmt =  con.createStatement();
////        PreparedStatement pstmt = con.prepareStatement("select password from operator where username=? ;");
////        pstmt.setNString(1, username);
////        ResultSet rs=pstmt.executeQuery();
////        rs.next();
////        if (rs.getInt(1)==password)
////            return true;
////        else
////            return false;
////    }
//// // to define tariff   
////public static float traiff (){
////        float traiff = (float) .5;
////        return traiff; 
////    } 
////  //to collect payments  
////public static void collect () throws ClassNotFoundException, SQLException {
////       
////
////              Connection con = connect();
////              Statement stmt = con.createStatement();                 
////              ResultSet cost = stmt.executeQuery("select metercode,cost from bill where isPaid =0 and isCollected=0");
////              //ResultSet meter = stmt.executeQuery("select metercode from bill where isPaid =0 and isCollected=0");
////              ArrayList<Integer> meterc = new ArrayList<Integer>();   
////              ArrayList<Integer> costs = new ArrayList<Integer>();
////              System.out.println("meter code    cost");
////              while(cost.next()) {
////              System.out.println(cost.getInt(1)+ "        "+cost.getInt(2));
////              meterc.add(cost.getInt(1));
////              costs.add(cost.getInt(2));
////              }
////             int i=0;
////              for (i=0;i<meterc.size();i++) { 
////               PreparedStatement sq = con.prepareStatement("INSERT INTO collected VALUES (?,?);");
////               sq.setInt(1,meterc.get(i));
////               sq.setInt(2,costs.get(i));
////               sq.executeUpdate();
////              }
////            con.close();
////          }
////  //to validate reading  
////public static boolean validation (int metercode , int currentreading) throws ClassNotFoundException, SQLException {
////       Connection con = connect();
////            PreparedStatement stmt = con.prepareStatement("select reading from cusinfo where metercode=?");
////            stmt.setInt(1,metercode);
////            ResultSet res = stmt.executeQuery();
////            res.next();
////            //l.getInt(1);
////            if(res.getInt(1)<=currentreading)
////                return true;
////            else
////                return false;
////   }
//// //to make sure about customer's payments
////public static boolean paidValidation (int metercode) throws ClassNotFoundException, SQLException{
////            Connection con = connect();
////            PreparedStatement stmt = con.prepareStatement("select sum(isPaid) from bill where metercode=?");
////            stmt.setInt(1,metercode);
////            ResultSet res = stmt.executeQuery();
////            res.next();
////            if (res.getInt(1)>=3)
////            {
////                String msg = "Dear customer,"+"\n"+"your account has been temporarily suspended due to the existence of unpaid bills for three months."+"\n"+"Please go to the company branch to pay the bills";
////                PreparedStatement email = con.prepareStatement("UPDATE cusinfo set emails = ? where metercode = ?");
////                email.setString(1, msg);
////                email.setInt(2, metercode);
////                email.executeUpdate();
////                return true;
////            }
////            else
////                return false;
////        }
////  //to print bill with customer meter code
////public static void PrintBillWithMeterCode (int metercode) throws SQLException, ClassNotFoundException{
////              
////              Connection con = connect();
////              PreparedStatement stmt = con.prepareStatement("select cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.reading,cusinfo.email, cusinfo.address,bill.consumption,bill.cost from cusinfo join bill on bill.metercode=? and cusinfo.metercode=?");       
////              stmt.setInt(1,metercode);
////              stmt.setInt(2,metercode);
////              
////              ResultSet l = stmt.executeQuery(); 
////              l.next();
////              
////              System.out.println("\nBILL:");
////            System.out.println("metercode:         " + l.getInt(1));
////            System.out.println("region : " + l.getString(2));
////            System.out.println("Name:  " + l.getString(3));
////            System.out.println("reading:      " + l.getInt(4));
////            System.out.println("email:      " + l.getString(5));            
////            System.out.println("address:      " + l.getString(6));
////            System.out.println("consumption:  " + l.getInt(7));
////            System.out.println("cost:      " + l.getInt(8));
////
////              
////             
////              con.close();
////   }
//////to view all bills with reading
////public static void viewBillsWithRegion(String region) throws SQLException, ClassNotFoundException{
////              
////              Connection con = connect();
////              PreparedStatement stmt = con.prepareStatement("select cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.reading,cusinfo.email, cusinfo.address,bill.consumption,bill.cost from cusInfo,bill where cusInfo.metercode=bill.metercode AND cusinfo.region=?");       
////              stmt.setString(1,region);       
////              ResultSet l = stmt.executeQuery(); 
////              while(l.next())
////              {              
////            System.out.println("\nBILL:");
////            System.out.println("metercode:         " + l.getInt(1));
////            System.out.println("region : " + l.getString(2));
////            System.out.println("Name:  " + l.getString(3));
////            System.out.println("reading:      " + l.getInt(4));
////            System.out.println("email:      " + l.getString(5));            
////            System.out.println("address:      " + l.getString(6));
////            System.out.println("consumption:  " + l.getInt(7));
////            System.out.println("cost:      " + l.getInt(8));
////
////              }
////             
////              con.close();
////   }
//////veiw emails
////public static void viewEmail(String email) throws SQLException, ClassNotFoundException{
////      try{
////      Connection con = connect();
////      PreparedStatement view = con.prepareStatement("SELECT emails from cusinfo where email = ?");
////      view.setString(1, email);
////      ResultSet r =view.executeQuery();
////      r.next();
////      System.out.println(r.getString(1));
////      }
////      catch (Exception e)
////      {
////          System.out.println("your mail not found please enter valid email.......");
////      }
////  }
////
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









////////
////////import java.sql.Connection;
////////
////////import java.sql.DriverManager;
////////import java.sql.ResultSet;
////////import java.sql.ResultGet;
////////import java.sql.Statement;
////////import java.sql.PreparedStatement;
////////import java.sql.SQLException;
////////import java.io.FileInputStream;
////////import java.util.ArrayList;
////////import java.util.Scanner;
////////import static ssss.DBconnect.connect;
////////
////////public class opretor {
////////
//////// 
////////    //ATTRIBUTES-LOGIN OPRATOR
////////    private String username;
////////    private int password;
////////
////////    public void setUsername(String username) {
////////        this.username = username;
////////    }
////////
////////    public void setPassword(int password) {
////////        this.password = password;
////////    }
////////    
////////    
////////    public static void collect () throws ClassNotFoundException, SQLException 
////////            {       
////////              Connection con = connect();
////////              Statement stmt = con.createStatement();                 
////////              ResultSet cost = stmt.executeQuery("select metercode,cost from bill where isPaid =0 and isCollected=0");
////////              //ResultSet meter = stmt.executeQuery("select metercode from bill where isPaid =0 and isCollected=0");
////////              ArrayList<Integer> meterc = new ArrayList<Integer>();   
////////              ArrayList<Integer> costs = new ArrayList<Integer>();
////////              System.out.println("meter code       cost");
////////              while(cost.next()) {
////////              System.out.println(cost.getInt(1)+ "                 "+cost.getInt(2));
////////              meterc.add(cost.getInt(1));
////////              costs.add(cost.getInt(2));
////////              }
////////             int i=0;
////////              for (i=0;i<meterc.size();i++) { 
////////               PreparedStatement sq = con.prepareStatement("INSERT INTO Collected VALUES (?,?);");
////////               sq.setInt(1,meterc.get(i));
////////               sq.setInt(2,costs.get(i));
////////               sq.executeUpdate();
////////              }
////////              meterc.clear();
////////              costs.clear();
////////              
////////            con.close();
////////          }
////////    //VALIDATION
////////       public static boolean ValidateReading(int metercode,int currentReading ) throws SQLException, ClassNotFoundException{
////////              
////////              Connection con = connect();
////////              PreparedStatement stmt = con.prepareStatement("select reading from customer where metercode=?");       
////////              stmt.setInt(1,metercode);
////////              ResultSet l = stmt.executeQuery();
////////              l.next();
////////              
////////              int lastReading=l.getInt(1);
////////              if(lastReading>currentReading){
////////                  System.out.println("your reading isn't correct");
////////                  return false;
////////                
////////              }
////////              else{
////////                  System.out.println("your reading is correct");
////////                  return true;
////////              }
////////              
////////   }
////////    //PrintBillWithMeterCode
////////        public static void PrintBillWithMeterCode(int metercode) throws SQLException, ClassNotFoundException{
////////              
////////              Connection con = connect();
////////              PreparedStatement stmt = con.prepareStatement("select customer.metercode,customer.region,customer.name,customer.reading,customer.email, customer.address,bill.consumption,bill.cost from customer join bill on bill.metercode=? and customer.metercode=?");       
////////              stmt.setInt(1,metercode);
////////              stmt.setInt(2,metercode);
////////              
////////              ResultSet l = stmt.executeQuery(); 
////////              l.next();
////////              
////////              System.out.println("\nBILL:");
////////            System.out.println("metercode:         " + l.getInt(1));
////////            System.out.println("region : " + l.getString(2));
////////            System.out.println("Name:  " + l.getString(3));
////////            System.out.println("reading:      " + l.getInt(4));
////////            System.out.println("email:      " + l.getString(5));            
////////            System.out.println("address:      " + l.getString(6));
////////            System.out.println("consumption:  " + l.getInt(7));
////////            System.out.println("cost:      " + l.getInt(8));
////////
////////              
////////             
////////              con.close();
////////   }
////////    
////////    
////////    //viewBillsWithRegion
////////            public static void viewBillsWithRegion(String region) throws SQLException, ClassNotFoundException{
////////              
////////              Connection con = connect();
////////              PreparedStatement stmt = con.prepareStatement("select customer.metercode,customer.region,customer.name,customer.reading,customer.email, customer.address,bill.consumption,bill.cost from customer,bill where bill.metercode=customer.metercode and customer.region=?");       
////////              stmt.setString(1,region);
////////              
////////              ResultSet l = stmt.executeQuery(); 
////////              while(l.next())
////////              {              
////////              System.out.println("\nBILL:");
////////            System.out.println("metercode:         " + l.getInt(1));
////////            System.out.println("region : " + l.getString(2));
////////            System.out.println("Name:  " + l.getString(3));
////////            System.out.println("reading:      " + l.getInt(4));
////////            System.out.println("email:      " + l.getString(5));            
////////            System.out.println("address:      " + l.getString(6));
////////            System.out.println("consumption:  " + l.getInt(7));
////////            System.out.println("cost:      " + l.getInt(8));
////////
////////              }
////////             
////////              con.close();
////////   }
////////    
////////    //paidValidation
////////    public static boolean paidValidation (int metercode) throws ClassNotFoundException, SQLException
////////{
////////            Connection con = connect();
////////            PreparedStatement stmt = con.prepareStatement("select sum(isPaid) from bill where metercode=?");
////////            stmt.setInt(1,metercode);
////////            ResultSet res = stmt.executeQuery();
////////            res.next();
////////            if (res.getInt(1)>=3)
////////                return true;
////////            else
////////                return false;
////////        }
////////    
////////    
////////    //monthlyReading
////////    
////////    public static void monthlyReading (int metercode,int currentReading,int tariff) throws ClassNotFoundException, SQLException
////////{
////////            Connection con = connect();
////////            PreparedStatement stmt = con.prepareStatement("select reading from cusinfo where metercode=?");
////////            stmt.setInt(1,metercode);
////////            ResultSet l = stmt.executeQuery();
////////            l.next();
////////            int lastReading=l.getInt(1);
////////            
////////            int cons =currentReading-lastReading;                 
////////            System.out.print(cons);
////////            float cost=cons*tariff;
////////            
////////            
////////            
////////            
////////            
////////            PreparedStatement r = con.prepareStatement("select region from cusinfo where metercode=?");
////////            r.setInt(1,metercode);
////////            ResultSet re = r.executeQuery();
////////            re.next();    
////////            String reg=re.getString(1);
////////
////////             
////////               PreparedStatement sq = con.prepareStatement("update cusinfo set reading=? where metercode=?");
////////               sq.setInt(1,currentReading);            
////////               sq.setInt(2,metercode);
////////               sq.executeUpdate();
////////
////////               
////////               
////////               
////////               PreparedStatement sq2 = con.prepareStatement("insert into bill values (?,?,?,1,0,?)");
////////               sq2.setInt(1,metercode);            
////////               sq2.setInt(2,cons);
////////               sq2.setFloat(3,cost);               
////////               sq2.setString(4,reg);
////////               sq2.executeUpdate();
////////     
////////            
////////             con.close();
////////}
////////    
//////////PAYBILL
////////               
////////    public static void payBill (int metercode) throws ClassNotFoundException, SQLException
////////{
////////            Connection con = connect();              
////////
////////              PreparedStatement stmtt = con.prepareStatement("select cusinfo.metercode,cusinfo.region,cusinfo.name,cusinfo.reading,cusinfo.email, cusinfo.address,bill.consumption,bill.cost from cusinfo join bill on bill.metercode=? and cusinfo.metercode=? where isPaid=1");               
////////              stmtt.setInt(1,metercode);
////////              stmtt.setInt(2,metercode);
////////              ResultSet k = stmtt.executeQuery(); 
////////              k.next();
////////              System.out.println("Enter your credit to pay bill :      ");
////////               Scanner input  =new Scanner(System.in);
////////                int bill=input.nextInt();             
////////                System.out.println("DONE");
////////               PreparedStatement sq = con.prepareStatement("update cusinfo set isPaid=0 where metercode=?");
////////               sq.setInt(1,metercode);               
////////            System.out.println("\n THIS IS THE FIRST BILL FOR YOU :");
////////            System.out.println("metercode:         " + k.getInt(1));
////////            System.out.println("region : " + k.getString(2));
////////            System.out.println("Name:  " + k.getString(3));
////////            System.out.println("reading:      " + k.getInt(4));
////////            System.out.println("email:      " + k.getString(5));            
////////            System.out.println("address:      " + k.getString(6));
////////            System.out.println("consumption:  " + k.getInt(7));
////////            System.out.println("cost:      " + k.getInt(8));
////////             
////////               
////////            
////////             con.close();
////////}
    
//    
//    public static void payBill (int metercode) throws ClassNotFoundException, SQLException
//{
//            Connection con = connect();              
//            System.out.println("Enter your credit to pay bill :      ");
//            Scanner input  =new Scanner(System.in);
//            int bill=input.nextInt();             
//            System.out.println("DONE");
//            PreparedStatement stmtt = con.prepareStatement("select top(1) customer.metercode,customer.region,customer.name,customer.reading,customer.email, customer.address,bill.consumption,bill.cost from customer, bill where customer.metercode=bill.metercode and isPaid=1");               
//            stmtt.setInt(1,metercode);
//            ResultSet k = stmtt.executeQuery(); 
//            k.next();
//            PreparedStatement sq = con.prepareStatement("update bill set isPaid=0 where metercode=?");
//            sq.setInt(1,metercode);
//            sq.executeUpdate();
//            System.out.println("\n THIS IS THE FIRST BILL FOR YOU :");
//            System.out.println("metercode:         " + k.getInt(1));
//            System.out.println("region : " + k.getString(2));
//            System.out.println("Name:  " + k.getString(3));
//            System.out.println("reading:      " + k.getInt(4));
//            System.out.println("email:      " + k.getString(5));            
//            System.out.println("address:      " + k.getString(6));
//            System.out.println("consumption:  " + k.getInt(7));
//            System.out.println("cost:      " + k.getFloat(8));
//           
//             con.close();
//}
    
//    
//     //SEARCH
//    public static boolean searchmetercode (int metercode) throws ClassNotFoundException, SQLException
//{
//            Connection con = connect();
//            PreparedStatement stmt = con.prepareStatement("select metercode from customer where metercode=?");
//            stmt.setInt(1,metercode);
//            ResultSet result = stmt.executeQuery(); 
//
//            int value=0;
//            while(result.next())
//            {
//                value = result.getInt(1);
//
//            }
//            if(value!=0)
//            {
//            System.out.println("welcome!");     
//            return true;
//
//
//            }
//            else{
//            System.out.println("you are not an old customer please go back and choose new customer");        
//            return false;
//            }
//        
//            
//            
//            
//            
//            
//        }
//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    //connect with database
//      public static Connection connectopretor(int currentReading,int metercode) throws ClassNotFoundException{ 
//
//          Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//          try{
//              Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=admindb;user=admin;password=1234");
//    
//              System.out.println("Connection is successfull");
//////////   public static void CollectPaymentFromCustomer(int currentReading,int metercode) throws SQLException, ClassNotFoundException{
//////////              
//////////              Connection con = connect();
//////////              Statement stmt = con.createStatement();       
//////////              Statement metter = con.createStatement();                 
//////////             
//////////              ResultSet l = stmt.executeQuery("select reading from customer where metercode="+metercode);
//////////              int lastReading=l.getInt(1);
//////////              if(lastReading>currentReading){
//////////                  System.out.println("your reading isn't correct");
//////////              }
//////////              else
//////////                  System.out.println("your reading is correct");
//////////              
//////////              con.close();
//////////   }
//            //  ResultSet meter = metter.executeQuery("select metercode from bill");
//              ArrayList<String> list=new ArrayList<String> ();
//         //     ArrayList<String> listm=new ArrayList<String> ();
//
//            //  ResultGet cost2 = (ResultGet) stmt.executeQuery("select cost from collected");                       
//              
//              while(cost.next()) {
//              
//             // listm.add(meter.getString(1));
//              list.get(1)=cost.getString(1);
//             ////   String m=meter.getString(1);
//             // PreparedStatement pstmt = con.prepareStatement("UPDATE collected SET cost2 = d");
//             //stmt.addBatch("update collected set metercode= "+meter.getString(1)+",cost="+cost.getString(1));
//             // meter.next();
//              }
//                stmt.executeBatch();

//          }
//
//    catch(SQLException e){
//
//     System.out.println(e);
//
//    }
//
//        return null;
//    
////
////    public void setUsername(String username) {
////        this.username = username;
////    }
////
////    public void setPassword(int password) {
////        this.password = password;
////    }
////    
////    
////    public void CollectPaymentFromCustomer(){
////        
////    }
    
    
    
    //  } 

