// Browsing.

import java.io.*;
import java.lang.*;
import java.awt.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;
import java.awt.Font;

public class Analyse extends JFrame implements ActionListener {
	int z = 0, num = 0, num1 = 0, num2 = 0, num3 = 0, flag = 0;
	public String str;
	JComboBox alg = null;
	JButton count, percent, back, fe;
	public String ok = "";
	JLabel krish, nucval, noval, pcval, ncval, hisrk, poval, neval, x1, x2, x3,
			x4, x5, x6, x7;
	JScrollPane scroll1 = null;
	JScrollPane scroll2 = null;
	JScrollPane scroll3 = null;
	JTextArea pArea = null;
	JTextArea cArea = null;
	JTextArea neuArea = null;
	String absolutePath1 = "";
	String xyz;
	String apath = "";
	JFrame parent;
	String negativeReviews;
	String positiveReviews;
	public Analyse(int n1, int n2, int n3, int n4, JFrame parent, String negativeReviews, String positiveReviews) {
		// UIManager.put("Panel.background", Co);
		this.parent = parent;
		num = n1;
		num1 = n2;
		num2 = n3;
		num3 = n4;
		this.negativeReviews=negativeReviews;
		this.positiveReviews=positiveReviews;
		Container con = this.getContentPane();

		con.setLayout(new BorderLayout());

		JLabel title = new JLabel("ANALYSIS", JLabel.CENTER);
		JLabel counts = new JLabel("COUNT", JLabel.CENTER);
		JLabel per = new JLabel("PERCENTAGE", JLabel.CENTER);
		JLabel no = new JLabel("No of comments:", JLabel.CENTER);
		JLabel pc = new JLabel("NO of Positive comments:", JLabel.CENTER);
		JLabel nc = new JLabel("NO of Negative comments:", JLabel.CENTER);
		JLabel nuc = new JLabel("NO of neutral comments:", JLabel.CENTER);
		JLabel po = new JLabel("NO of Postive percentage:", JLabel.CENTER);
		JLabel ne = new JLabel("NO of Negative percentage:", JLabel.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 25));
		// JLabel
		// krish=null,nucval=null,noval=null,pcval=null,ncval=null,hisrk=null,poval=null,neval=null,x1=null,x2=null,x3=null,x4=null,x5=null,x6=null,x7=null;
		con.add(title, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 1));
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(5, 2));
		left.add(counts);
		// counts.setForeground(Color.WHITE);
		// left.add(per);
		left.add(krish = new JLabel(""));
		// left.add(perval=new JLabel("",JLabel.CENTER));
		left.add(no);
		// no.setForeground(Color.WHITE);
		left.add(noval = new JLabel(""));
		left.add(pc);
		// pc.setForeground(Color.WHITE);
		left.add(pcval = new JLabel(""));
		left.add(nc);
		// nc.setForeground(Color.WHITE);
		left.add(ncval = new JLabel(""));
		left.add(nuc);
		// nuc.setForeground(Color.WHITE);
		left.add(nucval = new JLabel(""));

		centerPanel.add(left);
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(3, 2));
		right.add(per);
		// per.setForeground(Color.WHITE);
		right.add(hisrk = new JLabel(""));
		right.add(po);
		// po.setForeground(Color.WHITE);
		right.add(poval = new JLabel(""));
		right.add(ne);
		// ne.setForeground(Color.WHITE);
		right.add(neval = new JLabel(""));
		/*
		 * left.add(x3=new JLabel("")); left.add(x4=new JLabel(""));
		 * left.add(x5=new JLabel("")); left.add(x6=new JLabel(""));
		 */
		centerPanel.add(right);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(count = new JButton("count"));
		buttonPanel.add(percent = new JButton("percentage"));
		buttonPanel.add(fe = new JButton("Feature selection"));
		buttonPanel.add(back = new JButton("Back"));
		con.add(buttonPanel, BorderLayout.SOUTH);
		count.addActionListener(this);
		fe.addActionListener(this);
		back.addActionListener(this);
		percent.addActionListener(this);

		// con.add(scroll1,BorderLayout.EAST);
		/*
		 * cArea=new JTextArea(); centerPanel.add(scroll2=new
		 * JScrollPane(cArea)); pArea=new JTextArea();
		 * centerPanel.add(scroll1=new JScrollPane(pArea));
		 * //con.add(scroll2,BorderLayout.CENTER);
		 */
		con.add(centerPanel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();

		// alg.addItemListener(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			int a, b, c, d;
			a = num;
			b = num1;
			c = num2;
			d = num3;
			String s = "";
			String s1 = "No of comments:";
			String s2 = "No of positive comments:";
			String s3 = "No of negative comments:";
			String s4 = "No of neutral comments:";
			if (ae.getSource() == count) {

				s1 = Integer.toString(a);
				s2 = Integer.toString(b);
				s3 = Integer.toString(c);
				s4 = Integer.toString(d);
				noval.setText("" + s1);
				pcval.setText("" + s2);
				ncval.setText("" + s3);
				nucval.setText("" + s4);

			} else if (ae.getSource() == percent) {
				float posperc = ((float) b / (b + c)) * 100;
				float negperc = ((float) c / (b + c)) * 100;
				String s5 = Float.toString(posperc);
				String s6 = Float.toString(negperc);

				poval.setText(s5);
				neval.setText(s6);
			} else if (ae.getSource() == fe) {
				System.out.println("feature selction");

				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

				int x = (screen.width * 10 / 100);

				int y = (screen.height * 10 / 100);

				int w = (screen.width * 80 / 100);

				int h = (screen.height * 80 / 100);

				FeatureSelection obj = new FeatureSelection(this,negativeReviews,positiveReviews);

				obj.setBounds(x, y, w, h);

				obj.setVisible(true);
				// this.setVisible(false);

			} else if (ae.getSource() == back) {
				parent.setVisible(true);
				this.setVisible(false);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}// class
