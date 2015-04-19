import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.lang.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;




public class Fs3 extends JFrame implements ActionListener
{
JLabel title=null,title2=null,title3=null,head=null;
JButton analyse,back;
JTextField text=null,text2=null,text3=null;

JFrame parent;
     public Fs3(JFrame parent)
	 {
	   this.parent=parent;
	 //UIManager.put("Panel.background", Color.BLUE);
	 Container con = this.getContentPane(  );
		con.setLayout( new BorderLayout( 10,30 ) );

		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1));
		JPanel rowpanel=new JPanel();
		JLabel title=new JLabel("enter the feature :",JLabel.LEFT);
		rowpanel.add(title);
		rowpanel.add(text=new JTextField(20));
		rowpanel.add(analyse=new JButton("Analyse"));
		buttonPanel.add(rowpanel);
		JPanel rowpanel2=new JPanel();
		rowpanel2.add(title2=new JLabel("positive:",JLabel.LEFT));
		//title2.setBackground(Color.WHITE);
		 //title2.setForeground(Color.WHITE);
		rowpanel2.add(text2=new JTextField(20));
		rowpanel2.add(title3=new JLabel("negative :",JLabel.CENTER));
		rowpanel2.add(text3=new JTextField(20));
		//title3.setForeground(Color.WHITE);
		//title.setForeground(Color.WHITE);
		buttonPanel.add(rowpanel2);
		text2.setText("0");
		text3.setText("0");
		con.add(buttonPanel,BorderLayout.CENTER);
		con.add(head=new JLabel("FEATURE SELECTION",JLabel.CENTER ),BorderLayout.NORTH);
		//head.setForeground(Color.WHITE);
		head.setFont(new Font("Courier New", Font.BOLD, 30));
		con.add(back=new JButton("Back"),BorderLayout.SOUTH);
		con.add(new JPanel(),BorderLayout.EAST);
		con.add(new JPanel(),BorderLayout.WEST);
		
 //con.setBackground(Color.blue);
		analyse.addActionListener(this);
		back.addActionListener(this);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		}






public void actionPerformed(ActionEvent ae)
		{
		if( ae.getSource() == analyse )
		{

		try
{
String strp="0",strn="0";

BufferedReader reader1 = null;
reader1 = new BufferedReader(new FileReader("nv1pos.txt"));
BufferedReader reader2 = null;
reader2 = new BufferedReader(new FileReader("nv1neg.txt"));

String line = reader1.readLine();
 int count[]=new int[1000];
 String lists1[]=new String[1000];
 int i=0,x=0,y=0;

while( line != null)
{

StringTokenizer tokens = new StringTokenizer(line, " ");
while(tokens.hasMoreTokens())
{

String r1 = tokens.nextToken();

if(y==0)
{
lists1[0]=r1;
count[0]=1;

//System.out.println(lists1[0]);
y=100;
continue;
}
int k=0;
for(i=0;i<=x;i++)
{
 String po=lists1[i];
 //System.out.println(po);
if(po.equals(r1))
{
k++;
//System.out.println("krishna");
break;
}
}
if(k!=0)
{
count[i]++;
//System.out.println("hai");
}
else
{
x++;
lists1[x]=r1;
count[x]=1;
System.out.println(r1);
}

}

line = reader1.readLine();
}
//System.out.println("krishna out");
for(i=0;i<=x;i++)
{
System.out.println(lists1[i]+"  "+count[i]);
}





System.out.println("*******************************");



 String line2 = reader2.readLine();
 int count1[]=new int[1000];
 String lists2[]=new String[1000];
 int i2=0,x2=0,y1=0;

while( line2 != null)
{

StringTokenizer tokens2 = new StringTokenizer(line2, " ");
while(tokens2.hasMoreTokens())
{

String r2 = tokens2.nextToken();
//System.out.println("krishna ddd");
if(y1==0)
{
lists2[0]=r2;
count1[0]=1;
//System.out.println(lists1[0]);
y1=100;
continue;
}
int k1=0;
for(i2=0;i2<=x2;i2++)
{
 String po2=lists2[i2];
 //System.out.println(po);
if(po2.equals(r2))
{
k1++;
//System.out.println(k);
break;
}
}
if(k1!=0)
{
count1[i2]++;
//System.out.println("hai");
}
else
{
x2++;
lists2[x2]=r2;
count1[x2]=1;
//System.out.println("bye");
//
}

}

line2 = reader2.readLine();
}

for(i2=0;i2<=x2;i2++)
{
System.out.println(lists2[i2]+"  "+count1[i2]);
}

String s=text.getText();
strp="0";
 for(int d=0;d<=x2;d++)
 {
 //System.out.println("krishna krrish  kkkkkk");
 if(s.equals(lists2[d]))
 {
 strp=Integer.toString(count1[d]);
 
 //System.out.println("krishna krrish");
 break;
 }
}


 strn="0";
 for(int d=0;d<=x;d++)
 {
 if(s.equals(lists1[d]))
 {
 strn=Integer.toString(count[d]);
 
// System.out.println("krishna krrish krrrrish");

 break;
 }
 }
System.out.println(strn+" +ve");
System.out.println(strp+" -ve");
text3.setText(strp);
text2.setText(strn);
}
catch(Exception e)
{
e.printStackTrace();
}
}
else if(ae.getSource()==back)
{
parent.setVisible(true);
this.setVisible(false);
}


		}

		}




