import java.io.*;
import java.util.StringTokenizer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.lang.*;
public class D
{
 public static void main(String args[])
 {

  try{
       
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      Connection con1=DriverManager.getConnection("jdbc:odbc:ss");
      Statement st1=con1.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
      ResultSet rs=null;
       BufferedReader reader = null;
       reader = new BufferedReader(new FileReader("m2.txt"));
       BufferedWriter out = new BufferedWriter(new FileWriter("pm3.txt")); 
       BufferedWriter out1 = new BufferedWriter(new FileWriter("pn3.txt")); 
       BufferedWriter out2 = new BufferedWriter(new FileWriter("p23.txt")); 
      
              int h=0,g=0,flag1=0,flag2=0;

       /*String s1="select * from sentistrength";
       ResultSet rs=st1.executeQuery(s1);*/
      String line = reader.readLine();
int c0=0,c1=0,c2=0;

while( line != null)
{
StringTokenizer tokens = new StringTokenizer(line, "\n");

while(tokens.hasMoreTokens())
{ 

String r1 = tokens.nextToken();
String[] parts=r1.split(" ");
     int[] polar=new int[parts.length];
           for(int v=0;v<parts.length;v++)
             {     String sql="select * from sentistrength s where s.word='"+parts[v]+"'";
                     rs=st1.executeQuery(sql);
                   System.out.println(parts[v]);
            
                     if(rs.next())
                        { //System.out.println("neer");
                               System.out.println("-------");
        
                             System.out.println(rs.getString(2));
                               polar[v]=rs.getInt(3);
                               System.out.println(polar[v]);
                               System.out.println("-------");
                       }   
                       else
                       {       polar[v]=0;
                             System.out.println(polar[v]);
                      }

                 }



    int m=0;
            while(m<parts.length)
            {     

          int i=0,j=0;
                  String w;
                  int x2;
                  int n=parts.length;
            

                  System.out.println(n);


                  int x1=0;
                  int j1=parts.length;
                  int p[]= new int[parts.length];
                  int x=0;    
           
                  p[0]=polar[0];

       	          //System.out.println(parts[0]+" "+p[0]);
                 //out.write(parts[0]+" "+p[0]);
                 //g=0;h=0;flag1=0;flag2=0;
                 for(i=1;i<(polar.length);i++)
                 { 
		      x=polar[i-1]*polar[i];

                 
                      if(polar[i]==0)
		              p[i]=0;
                  
		      else
                      if(x<0)
                      {
                          if(polar[i-1]>0)
                          {
                               x1=-1*polar[i-1];
			       p[i]=(polar[i]+x1)/2;
                          }
                          else
                          {
                               x1=-1*polar[i];
			       p[i]=(x1+polar[i-1])/2;
                          }
                      }
                      else
                      {
                          if(polar[i-1]==0)
                               p[i]=polar[i];
                          else
                               p[i]=(polar[i]+polar[i-1])/2;
		      }
                      if(p[i]<0)
                      {
                          g+=p[i];
                          //System.out.println(g);
                          flag1++;
                      }
                     else if(p[i]>0)
                     {     h+=p[i];
		           flag2++;
                     }
		     //System.out.println(parts[i]+" "+p[i]);
                     //out.write(parts[i]+" "+p[i]);

                 }
                 
       m++;
       }
       
       /*if(flag1>0)
           {
                System.out.println(r1+" "+g/flag1);
                //out.write(r1+" "+g/flag1);
            }
        else
	    {    System.out.println(r1+" "+0);
	         out2.write(r1+" "+0);
                 c2++;
             }
	if(flag2>0)
                {System.out.println(r1+" "+h/flag2);
		// out.write(r1+" "+h/flag2);
                 }

	else
		{System.out.println(r1+" "+0);
                  out2.write(r1+" "+0);c2++;
                 }*/
        if (flag1==0 && flag2==0)
            {out2.write(r1+" "+0);c2++;}
           if(flag1>0)
            {
               if(flag2>0)
                  { int h1=-1*(g/flag1);
                    if(h1>h/flag2)
                      {out1.write(r1+" "+g/flag1);c1++;}
                   else
                      {out.write(r1+" "+h/flag2);c0++;}
                   }
               else if(flag2==0)
		    { out.write(r1+" "+g/flag1);c0++;}
            }

          else if(flag2>0)
               if(flag1==0)
		     {out1.write(r1+" "+h/flag2);c1++;}
            
          
            	        g=0;h=0;flag1=0;flag2=0;

       


       //int[] polar=new int[parts.length];

        
}
line= reader.readLine();
}
      out.write("/n"+ c0+"tweets");
      out1.write("/n"+c1+"tweets");
      out2.write("/n"+c2+"tweets");
       out.close();
       out1.close();
       out2.close();

      }
  catch(Exception e)
  {
e.printStackTrace();
System.out.println("ab");
   }
  }
}
