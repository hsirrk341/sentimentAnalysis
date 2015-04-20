import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import java.util.ArrayList;
import java.sql.*;
import java.awt.*;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

public class Alg extends JFrame implements ItemListener, ActionListener {
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
  
	public Alg(String path, JFrame parent) {
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
		alg.addItem("semantic");
		alg.addItem("dti");
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

			StringBuilder nvp = new StringBuilder();
			StringBuilder nvn = new StringBuilder(); 
			StringBuilder nvnu = new StringBuilder(); 
			StringBuilder filter = new StringBuilder(); 
			StringBuilder stop = new StringBuilder();
			StringBuilder wt = new StringBuilder(); 
			

			JComboBox combotype = (JComboBox) ie.getSource();
			String type = (String) combotype.getSelectedItem();

			if ("naviebayes".equals(type)) {
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
									System.out.print(r2);
									System.out.print(" ");
									String sql = "select * from navie s1 where s1.name1='"
											+ r2 + "' ";

									ResultSet rs = st.executeQuery(sql);

									if (rs.next()) {

										z = 0;
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
										System.out.println(pol);
										// System.out.println("sairam");
									}

								}

								count7++;
								System.out.println("totalpolarity" + totalpol);
								totpol = totpol + totalpol;
								System.out.println(count7);

							}
							overallpolarity = overallpolarity + totpol;
							System.out.println("overallpolarity"
									+ (overallpolarity));
							System.out.println();
							// totpol=0;
							totalpol = 0;
							// continue;
							if ((overallpolarity) >= 0.1) {
								out1.write(line);
								nvp.append(line);
								nvp.append("\n");
								out1.write("\n");
								num1++;
								posArea.setText(nvp.toString());
							} else if ((overallpolarity) < -0.1) {
								out2.write(line);
								nvn.append(line);
								nvn .append("\n");
								out2.write("\n");
								num2++;
								negArea.setText(nvn.toString());
							} else {
								out3.write(line);
								nvnu.append( line);
								nvnu.append( "\n");
								out3.write("\n");
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
					con.close();
					out1.close();
					out3.close();
					out2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}// if
			else if ("semantic".equals(type)) {
				// BufferedReader reader = null;
				// String str;
				try {
					float totalpolarity = 0, polarity1 = 0, count, overallpolarity = 0, totpol = 0;
					String word;
					int z = 0;
					num = 0;
					num1 = 0;
					num2 = 0;
					num3 = 0;
					flag = 0;
					List<String> list3 = new ArrayList<String>();
					// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

					// Connection
					// con=DriverManager.getConnection("jdbc:odbc:sai");
					ConnectionHanlder connectionHanlder = new ConnectionHanlder();
					Connection con = connectionHanlder.getConnecton(); // DriverManager.getConnection("jdbc:odbc:sai");

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
							"nv2neg.txt"));
					BufferedWriter out3 = new BufferedWriter(new FileWriter(
							"nv3neu.txt"));
					String line = reader.readLine();
					// count=0;
					int size = -1, count7 = 0;
					while (line != null) {
						// num=num+1;
						// count7=0;
						StringTokenizer tokens = new StringTokenizer(line, "\n");
						while (tokens.hasMoreTokens()) {
							totpol = 0;
							num = num + 1;
							String r1 = tokens.nextToken();
							flag = 0;
							StringTokenizer tokens4 = new StringTokenizer(r1,
									".");
							while (tokens4.hasMoreTokens()) {
								// count7++;
								String r9 = tokens4.nextToken();

								StringTokenizer tokens1 = new StringTokenizer(
										r9, " ");

								count = 0;
								totalpolarity = 0;

								while (tokens1.hasMoreTokens()) {
									String r2 = tokens1.nextToken();
									size++;
									// System.out.println("sairam7");
									System.out.print(r2);
									System.out.print(" ");
									String sql = "select * from sentistrength s where s.word='"
											+ r2 + "'";
									ResultSet rs = st.executeQuery(sql);
									String sql1 = "select * from boosterslist";
									ResultSet rs1 = st1.executeQuery(sql1);
									while (rs1.next()) {
										String s10 = rs1.getString(1);
										list3.add(s10);
									}
									// list3.add("sairam");

									while (rs.next()) {
										flag++;
										z = 0;
										String s7 = rs.getString(1);

										int p = f1(s7, size);

										if ((p == -10)) {
											break;
										}
										polarity1 = rs.getInt(2);
										if (p == -1)
											polarity1 = -(polarity1);

										String temp1 = s7;
										for (int b = 0; b < list3.size(); b++) {
											String ss4 = list3.get(b);
											if (s7.equals(ss4)) {

												System.out.println("sairam10");
												break;
											}
										}
										int x = (size);
										List<String> list1 = new ArrayList<String>(
												10000);
										BufferedReader reader1 = new BufferedReader(
												new FileReader("p1.txt"));
										str = reader1.readLine();
										while (str != null) {
											String[] words = str.split(" ");
											for (int y = 0; y < words.length; y++) {
												list1.add(words[y]);
											}
											str = reader1.readLine();
										}
										for (int k = x - 1; k <= x + 1; k++) {
											for (int b1 = 0; b1 < list3.size(); b1++) {
												if (list1.size() < b1) {
													String ss1 = list1.get(k);
													String ss2 = list3.get(b1);
													if (ss1.equals(ss2)) {
														String sql2 = "select * from boosterslist where Field1='"
																+ ss2 + "'";
														ResultSet rs2 = st1
																.executeQuery(sql2);
														while (rs2.next()) {
															z = rs2.getInt(2);
														}
														// System.out.println("sairam111011");
													}// if
												} else {
													break;
												}
											}// for
										}
										// System.out.println(z);
										if (polarity1 > 0)
											polarity1 = polarity1 + z;
										else
											polarity1 = polarity1 - z;

										totalpolarity = (totalpolarity + ((polarity1)));
										System.out.print("[" + polarity1 + "]");

									}

								}

								count7++;
								System.out.println();
								System.out.println("totalpolarity="
										+ totalpolarity);
								totpol = totpol + totalpolarity;
								System.out.println(count7);
							}
							overallpolarity = overallpolarity + totpol;
							System.out.println("overallpolarity="
									+ (overallpolarity / count7));
							System.out.println();
							if ((overallpolarity) > 0) {
								out1.write(line);
								nvp.append(line);
								nvp.append("\n");
								out1.write("\n");
								num1++;
								posArea.setText(nvp.toString());
							} else if ((overallpolarity) < 0) {
								out2.write(line);
								nvn.append(line);
								nvn.append("\n");
								out2.write("\n");
								num2++;
								negArea.setText(nvn.toString());
							} else {
								out3.write(line);
								nvnu.append(line);
								nvnu.append( "\n");
								out3.write("\n");
								num3++;
								neuArea.setText(nvnu.toString());
							}
							// continue;

						}
						overallpolarity = 0;
						count7 = 0;
						line = reader.readLine();

					}

					System.out.println("no of reviews=" + num);
					System.out.println("no of positive reviews=" + num1);
					System.out.println("no of negative reviews=" + num2);
					System.out.println("no of neutral reviews=" + num3);
					con.close();
					out1.close();
					out2.close();
					out3.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			else if ("dti".equals(type)) {
				int c0 = 0, c1 = 0, c2 = 0;

				try {

					// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					// Connection
					// con1=DriverManager.getConnection("jdbc:odbc:sai");
					ConnectionHanlder connectionHanlder = new ConnectionHanlder();
					Connection con1 = connectionHanlder.getConnecton(); // DriverManager.getConnection("jdbc:odbc:sai");

					Statement st1 = con1.createStatement(
							java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
							java.sql.ResultSet.CONCUR_READ_ONLY);
					ResultSet rs = null;
					BufferedReader reader = null;
					reader = new BufferedReader(new FileReader("p1.txt"));
					BufferedWriter out = new BufferedWriter(new FileWriter(
							"nv1pos.txt"));
					BufferedWriter out1 = new BufferedWriter(new FileWriter(
							"nv1neg.txt"));
					BufferedWriter out2 = new BufferedWriter(new FileWriter(
							"nv1neu.txt"));

					int h = 0, g = 0, flag1 = 0, flag2 = 0;

					String line = reader.readLine();

					while (line != null) {
						StringTokenizer tokens = new StringTokenizer(line, "\n");

						while (tokens.hasMoreTokens()) {

							String r1 = tokens.nextToken();
							String[] parts = r1.split(" ");
							int[] polar = new int[parts.length];
							for (int v = 0; v < parts.length; v++) {
								String sql = "select * from sentistrength s where s.word='"
										+ parts[v] + "'";
								rs = st1.executeQuery(sql);
								System.out.println(parts[v]);

								if (rs.next()) {
									// System.out.println("-------");

									System.out.println(rs.getString(1));
									polar[v] = rs.getInt(2);
									System.out.println(polar[v]);
									// System.out.println("-------");
								} else {
									polar[v] = 0;
									System.out.println(polar[v]);
								}

							}

							int m = 0;
							while (m < parts.length) {

								int i = 0, j = 0;
								String w;
								int x2;
								int n = parts.length;

								System.out.println(n);

								int x1 = 0;
								int j1 = parts.length;
								int p[] = new int[parts.length];
								int x = 0;

								p[0] = polar[0];

								// System.out.println(parts[0]+" "+p[0]);
								// out.write(parts[0]+" "+p[0]);
								// g=0;h=0;flag1=0;flag2=0;
								for (i = 1; i < (polar.length); i++) {
									x = polar[i - 1] * polar[i];

									if (polar[i] == 0)
										p[i] = 0;

									else if (x < 0) {
										if (polar[i - 1] > 0) {
											x1 = -1 * polar[i - 1];
											p[i] = (polar[i] + x1) / 2;
										} else {
											x1 = -1 * polar[i];
											p[i] = (x1 + polar[i - 1]) / 2;
										}
									} else {
										if (polar[i - 1] == 0)
											p[i] = polar[i];
										else
											p[i] = (polar[i] + polar[i - 1]) / 2;
									}
									if (p[i] < 0) {
										g += p[i];
										// System.out.println(g);
										flag1++;
									} else if (p[i] > 0) {
										h += p[i];
										flag2++;
									}
									// System.out.println(parts[i]+" "+p[i]);
									// out.write(parts[i]+" "+p[i]);

								}

								m++;
							}

							if (flag1 == 0 && flag2 == 0) {
								out2.write(r1 + " " + 0);
								nvnu.append(r1);
								nvnu.append("\n");
								out2.write("\n");
								c2++;
								neuArea.setText(nvnu.toString());
							}
							if (flag1 > 0) {
								if (flag2 > 0) {
									int h1 = -1 * (g / flag1);
									if (h1 > h / flag2) {
										out1.write(r1 + " " + g / flag1);
										out1.write("\n");
										nvn.append( r1);
										nvn.append("\n");
										c1++;
										posArea.setText(nvn.toString());
									} else {
										out.write(r1 + " " + h / flag2);
										out.write("\n");
										nvp.append(r1);
										nvp.append("\n");
										c0++;
										negArea.setText(nvp.toString());
									}
								} else if (flag2 == 0) {
									out.write(r1 + " " + g / flag1);
									out.write("\n");
									nvp.append(r1);
									nvp.append("\n");
									c0++;
									negArea.setText(nvp.toString());
								}
							}

							else if (flag2 > 0)
								if (flag1 == 0) {
									out1.write(r1 + " " + h / flag2);
									out1.write("\n");
									nvn.append(r1);
									nvn.append("\n");
									c1++;
									posArea.setText(nvn.toString());
								}

							g = 0;
							h = 0;
							flag1 = 0;
							flag2 = 0;

							// int[] polar=new int[parts.length];

						}
						line = reader.readLine();
					}
					num = (c0 + c1 + c2);
					num1 = c1;
					num2 = c0;
					num3 = c2;
					// out.write("/n"+ c0+"tweets");
					// out1.write("/n"+c1+"tweets");
					// out2.write("/n"+c2+"tweets");
					System.out.println("no of reviews=" + num);
					System.out.println("no of positive reviews=" + num1);
					System.out.println("no of negative reviews=" + num2);
					System.out.println("no of neutral reviews=" + num3);
					out.close();
					out1.close();
					out2.close();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ab");
				}

			}

		}

	}// method

	public int f1(String arr, int count1) throws Exception {
		BufferedReader reader = null;
		int z = 0;
		try {
			List<String> list = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();

			// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// Connection con=DriverManager.getConnection("jdbc:odbc:sai");
			ConnectionHanlder connectionHanlder = new ConnectionHanlder();
			Connection con = connectionHanlder.getConnecton(); // DriverManager.getConnection("jdbc:odbc:sai");

			Statement st = con.createStatement(
					java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
					java.sql.ResultSet.CONCUR_READ_ONLY);
			String sql1 = "select * from negetorslist";
			ResultSet rs1 = st.executeQuery(sql1);

			while (rs1.next()) {
				String s10 = rs1.getString(2);
				list.add(s10);
				// System.out.println(list.get(1));
			}
			String temp = arr;

			for (int b = 0; b < list.size(); b++)

				if (temp.equals(list.get(b))) {

					return -10;
				}

			int x = (count1) - 3;
			List<String> list1 = new ArrayList<String>();
			reader = new BufferedReader(new FileReader("p1.txt"));
			str = reader.readLine();
			while (str != null) {

				String[] words = str.split(" ");
				for (int y = 0; y < words.length; y++)
					list1.add(words[y]);
				str = reader.readLine();
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

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == analyse) {
			int a, b, c, d;
			a = num;
			b = num1;
			c = num2;
			d = num3;
			System.out.println(a + b + c + d);

			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

			int x = (screen.width * 10 / 100);

			int y = (screen.height * 10 / 100);

			int w = (screen.width * 80 / 100);

			int h = (screen.height * 80 / 100);

			Analyse ana = new Analyse(a, b, c, d, this);

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
