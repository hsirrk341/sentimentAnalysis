import java.io.*;
import java.lang.*;
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
import java.awt.*;
import java.util.List;

public class Algorithm extends JFrame implements ItemListener, ActionListener {
	String abpath = "";
	int z = 0, num = 0, num1 = 0, num2 = 0, num3 = 0, flag = 0;
	public String str;
	JComboBox alg = null;
	JButton analyse, go, back;
	public String ok = "";
	JScrollPane scroll1 = null;
	JScrollPane scroll2 = null;
	JScrollPane scroll3 = null;
	JTextArea posArea = null;
	JTextArea negArea = null;
	JTextArea neuArea = null;
	String absolutePath1 = "";
	String xyz;
	String apath = "";
	JFrame parent;
	List<String> list = null;
	boolean firsttime = true;
	List<String> list1;

	StringBuilder positiveReviews = new StringBuilder();
	StringBuilder negativeReviews = new StringBuilder();
	StringBuilder nvnu = new StringBuilder();

	public Algorithm(String path, JFrame parent, List<String> list1) {
		this.list1 = list1;
		// UIManager.put("Panel.background", Color.BLUE);
		this.parent = parent;

		Container con = this.getContentPane();
		// System.out.println(path+"sairammmmmmmmmmmmmm7777777777777777");
		con.setLayout(new BorderLayout());
		abpath = path;

		JLabel title = new JLabel("ALGORITHM", JLabel.CENTER);

		title.setFont(new Font("Arial", Font.BOLD, 25));

		con.add(title, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 3));

		alg = new JComboBox();
		alg.addItem("select one alg");
		alg.addItem("naviebayes");
		// alg.addItem("semantic");
		// alg.addItem("dti");
		alg.setBounds(100, 200, 200, 40);
		// con.add( alg,BorderLayout.SOUTH );
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(alg);
		buttonPanel.add(analyse = new JButton("analyse"));
		buttonPanel.add(back = new JButton("Back"));
		con.add(buttonPanel, BorderLayout.SOUTH);
		analyse.addActionListener(this);
		back.addActionListener(this);

		posArea = new JTextArea();
		centerPanel.add(scroll1 = new JScrollPane(posArea));
		// con.add(scroll1,BorderLayout.EAST);
		negArea = new JTextArea();
		centerPanel.add(scroll2 = new JScrollPane(negArea));
		// con.add(scroll2,BorderLayout.CENTER);

		neuArea = new JTextArea();
		centerPanel.add(scroll3 = new JScrollPane(neuArea));
		con.add(centerPanel, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();

		alg.addItemListener(this);

		// con.setBackground(Color.blue);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void itemStateChanged(ItemEvent ie) {
		if (ie.getStateChange() == 1) {

			String nvp1 = "", nvn1 = "", nvnu1 = "", filter = "", stop = "", wt = "";
			// StringBuilder nvp =new StringBuilder();
			// StringBuilder nvp =new StringBuilder();
			JComboBox combotype = (JComboBox) ie.getSource();
			String type = (String) combotype.getSelectedItem();

			if ("naviebayes".equals(type)) {
				// System.out.println("*****1122222***");
				double y = 0, totalpol = 0, pol;
				double pospol, negpol;
				try {
					double totalpolarity = 0, polarity1 = 0, count, overallpolarity = 0, totpol = 0;
					String word;

					int z = 0;
					num = 0;
					num1 = 0;
					num2 = 0;
					num3 = 0;
					List<String> list3 = new ArrayList<String>();
					// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					//
					// ` con = DriverManager
					// .getConnection("jdbc:odbc:sai");
					ConnectionHanlder connectionHanlder = new ConnectionHanlder();
					Connection con = connectionHanlder.getConnecton();
					Statement st = con.createStatement(
							java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
							java.sql.ResultSet.CONCUR_READ_ONLY);
					Statement st1 = con.createStatement(
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					BufferedReader reader = null;
					reader = new BufferedReader(new FileReader("p1.txt"));
					BufferedWriter out1 = new BufferedWriter(new FileWriter(
							"nv1pos.txt"));
					BufferedWriter out2 = new BufferedWriter(new FileWriter(
							"nv1neg.txt"));
					BufferedWriter out3 = new BufferedWriter(new FileWriter(
							"nv1neu.txt"));
					String line = reader.readLine();
					// posArea.setText(apath);
					int size = -1, count7 = 0;
					while (line != null) {

						StringTokenizer tokens = new StringTokenizer(line, "\n");
						while (tokens.hasMoreTokens()) {
							totpol = 0;
							num = num + 1;
							String r1 = tokens.nextToken();
							StringTokenizer tokens4 = new StringTokenizer(r1,
									".");
							while (tokens4.hasMoreTokens()) {
								// count7++;
								String r9 = tokens4.nextToken();

								StringTokenizer tokens1 = new StringTokenizer(
										r9, " ");
								totpol = 0;
								count = 0;
								totalpolarity = 0;
								while (tokens1.hasMoreTokens()) {
									String r2 = tokens1.nextToken();
									size++;
									// System.out.print("sai78908 ");
									String sql = "select * from navie s1 where s1.name1='"
											+ r2 + "' ";

									ResultSet rs = st.executeQuery(sql);

									if (rs.next()) {

										z = 0;
										try {
											String s7 = rs.getString(3);

											int p = f1(s7, size);
											if ((p == -10)) {
												break;
											}
											pol = 0;
											pospol = Double.parseDouble(rs
													.getString(1));
											negpol = (Double.parseDouble(rs
													.getString(2)));
											if (p == -1) {
												double tem = 0;
												tem = pospol;
												pospol = negpol;
												negpol = tem;
											} else {
												pospol = (pospol);
												negpol = (negpol);
											}

											y++;
											pospol = (pospol + 1);
											negpol = (negpol + 1);
											pol = ((Math.log(pospol)) - (Math
													.log(negpol)));
											totalpol = totalpol + pol;

										} catch (Exception e) {

										}
									}

								}

								count7++;
								System.out.println("totalpolarity" + totalpol);
								totpol = totpol + totalpol;

							}
							overallpolarity = overallpolarity + totpol;
							System.out.println("overallpolarity"
									+ (overallpolarity));
							System.out.println();
							totalpol = 0;
							if ((overallpolarity) >= 0.1) {
								positiveReviews.append(line + "\n");
								num1++;
								posArea.setText(positiveReviews.toString());
							} else if ((overallpolarity) < -0.1) {
								negativeReviews.append(line + "\n");
								num2++;
								negArea.setText(negativeReviews.toString());
							} else {
								nvnu.append(line + "\n");
								num3++;
								neuArea.setText(nvnu.toString());
							}

						}
						overallpolarity = 0;
						totpol = 0;
						count7 = 0;
						line = reader.readLine();

					}

					System.out.println("no of reviews=" + num);
					System.out.println("no of positive reviews=" + num1);
					System.out.println("no of negative reviews=" + num2);
					System.out.println("no of neutral reviews=" + num3);
					out1.write(positiveReviews.toString());
					out2.write(negativeReviews.toString());
					out3.write(nvnu.toString());
					con.close();
					out1.close();
					out3.close();
					out2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}// if

		}

	}// method

	public int f1(String arr, int count1) throws Exception {
		BufferedReader reader = null;
		int z = 0;
		try {
			List<String> list2 = new ArrayList<String>();
 			if (firsttime || list == null)
				list = getNegatorlist();
			String temp = arr;

			for (int b = 0; b < list.size(); b++)

				if (temp.equals(list.get(b))) {

					return -10;
				}

			int x = (count1) - 3;
			if (list1 == null) {
				reader = new BufferedReader(new FileReader("p1.txt"));
				str = reader.readLine();
				while (str != null) {

					String[] words = str.split(" ");
					for (int y = 0; y < words.length; y++)
						list1.add(words[y]);
					str = reader.readLine();
				}
			}
			for (int k = count1 - 1; k >= x; k--) {
				for (int b1 = 0; b1 < list.size(); b1++) {
					if (list1.size() < b1) {
						if ((list1.get(k)).equals(list.get(b1))) {
							z++;
						}// if
					} else {
						break;
					}
				}// for
			}
		} catch (Exception e) {
		}
		if ((z == 1) || (z == 3))
			return -1;
		else
			return 1;

	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public List<String> getNegatorlist() throws SQLException {
		ConnectionHanlder connectionHanlder = new ConnectionHanlder();
		Connection con = connectionHanlder.getConnecton();
		List<String> list = new ArrayList<String>();
		Statement st = con.createStatement(
				java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
				java.sql.ResultSet.CONCUR_READ_ONLY);
		String sql1 = "select * from negetorslist";
		ResultSet rs1 = st.executeQuery(sql1);

		while (rs1.next()) {
			String s10 = rs1.getString(2);
			list.add(s10);
		}
		firsttime = false;
		return list;
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == analyse) {
			int a, b, c, d;
			a = num;
			b = num1;
			c = num2;
			d = num3;

			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

			int x = (screen.width * 10 / 100);

			int y = (screen.height * 10 / 100);

			int w = (screen.width * 80 / 100);

			int h = (screen.height * 80 / 100);

			Analyse ana = new Analyse(a, b, c, d, this,
					negativeReviews.toString(), positiveReviews.toString());

			ana.setBounds(x, y, w, h);

			// choosefile.setText( "" );

			// password.setText( "" );

			this.setVisible(false);

			ana.setVisible(true);
		} else if (ae.getSource() == back) {
			parent.setVisible(true);
			this.setVisible(false);
		}

	}

}// class
