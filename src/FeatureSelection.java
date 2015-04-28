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
import java.util.*;

public class FeatureSelection extends JFrame implements ActionListener {
	JLabel title = null, title2 = null, title3 = null, head = null;
	JButton analyse, back;
	JTextField text = null, text2 = null, text3 = null;
	String negativeReviews;
	String positiveReviews;
	JFrame parent;

	public FeatureSelection(JFrame parent, String negativeReviews,
			String positiveReviews) {
		this.parent = parent;
		this.negativeReviews = negativeReviews;
		this.positiveReviews = positiveReviews;
		// UIManager.put("Panel.background", Color.BLUE);
		Container con = this.getContentPane();
		con.setLayout(new BorderLayout(10, 30));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));
		JPanel rowpanel = new JPanel();
		JLabel title = new JLabel("enter the feature :", JLabel.LEFT);
		rowpanel.add(title);
		rowpanel.add(text = new JTextField(20));
		rowpanel.add(analyse = new JButton("Analyse"));
		buttonPanel.add(rowpanel);
		JPanel rowpanel2 = new JPanel();
		rowpanel2.add(title2 = new JLabel("positive:", JLabel.LEFT));
		// title2.setBackground(Color.WHITE);
		// title2.setForeground(Color.WHITE);
		rowpanel2.add(text2 = new JTextField(20));
		rowpanel2.add(title3 = new JLabel("negative :", JLabel.CENTER));
		rowpanel2.add(text3 = new JTextField(20));
		// title3.setForeground(Color.WHITE);
		// title.setForeground(Color.WHITE);
		buttonPanel.add(rowpanel2);
		text2.setText("");
		text3.setText("");
		con.add(buttonPanel, BorderLayout.CENTER);
		con.add(head = new JLabel("FEATURE SELECTION", JLabel.CENTER),
				BorderLayout.NORTH);
		// head.setForeground(Color.WHITE);
		head.setFont(new Font("Courier New", Font.BOLD, 30));
		con.add(back = new JButton("Back"), BorderLayout.SOUTH);
		con.add(new JPanel(), BorderLayout.EAST);
		con.add(new JPanel(), BorderLayout.WEST);

		// con.setBackground(Color.blue);
		analyse.addActionListener(this);
		back.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == analyse) {

			String s = text.getText();
			String strp = "0", strn = "0";
			int countP = 0, countN = 0;
			int statusP = positiveReviews.indexOf(s);
		//	System.out.println("started...");
			if (statusP > 0) {
				while (statusP > 0) {
					countP++;
					int nextlineP = -1;
					nextlineP = positiveReviews.indexOf("\n", statusP);
					//System.out.println(nextlineP + " nextlineP");
					//System.out.println(countP + " countP");
					if (nextlineP > 0)
						statusP = positiveReviews.indexOf(s, nextlineP + 1);
					else
						statusP = -1;
				}

			}
			int statusN = negativeReviews.indexOf(s);
			if (statusN > 0) {
				while (statusN > 0) {
					countN++;
					int nextlineN = -1;
					nextlineN = negativeReviews.indexOf("\n", statusN);
					//System.out.println(countN + " countN");
					if (nextlineN > 0)
						statusN = negativeReviews.indexOf(s, nextlineN + 1);
					else
						statusN = -1;
				}

			}

			text3.setText(countN + "");
			text2.setText(countP + "");
		} else if (ae.getSource() == back) {
			parent.setVisible(true);
			this.setVisible(false);
		}

	}

}
