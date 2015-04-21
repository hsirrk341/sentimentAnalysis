// Browsing.

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
import javax.swing.JColorChooser;

public class Preproc extends JFrame implements ActionListener {

	JButton open, url, filtering, spsy, what, go, back, p1;
	public String ok = "",ok1="", send1 = "", path2 = "";
	JScrollPane scroll1 = null;
	JScrollPane scroll2 = null;
	JScrollPane scroll3 = null;
	JScrollPane scroll4 = null;

	JTextArea urlArea = null;
	JTextArea filterArea = null;
	JTextArea whArea = null;
	JTextArea splArea = null;
	String absolutePath1 = "";
	// Homepg ob=new Homepg();
	// String abc=ob.f1();;
	String xyz;// =ob.absolutePath;;
	String absolutepath = "";
	// public File f1=new File(abc);
	JFrame parent;

	public Preproc(String path, JFrame parent) {

		this.parent = parent;

		Container con = this.getContentPane();

		con.setLayout(new GridLayout(5, 2));
		absolutepath = path;
		// System.out.println(path);
		p1 = new JButton("PREPROCESSING");

		con.add(p1);
		JPanel buttonPanel9 = new JPanel();
		buttonPanel9.setLayout(new GridLayout(1, 2));
		buttonPanel9.add(back = new JButton("BACK"));
		buttonPanel9.add(go = new JButton("Go To Algorithm---->"));
		con.add(buttonPanel9);
		// back.setVisible(false);
		go.setVisible(false);
		// JPanel buttonPanel = new JPanel();

		con.add(url = new JButton("Remove URL"));
		urlArea = new JTextArea();
		scroll1 = new JScrollPane(urlArea);
		con.add(scroll1);

		con.add(filtering = new JButton("Filter Names and Tags (@ ,# )"));
		filterArea = new JTextArea();
		scroll2 = new JScrollPane(filterArea);
		con.add(scroll2);
		con.add(what = new JButton("Remove 'WH' Questions"));
		whArea = new JTextArea();
		scroll3 = new JScrollPane(whArea);
		con.add(scroll3);
		con.add(spsy = new JButton("Special Symbols (unknown charcters) "));
		splArea = new JTextArea();
		scroll4 = new JScrollPane(splArea);
		con.add(scroll4);
		// con.add( buttonPanel );

		JPanel inputPanel = new JPanel();

		url.addActionListener(this);
		filtering.addActionListener(this);
		spsy.addActionListener(this);
		what.addActionListener(this);
		go.addActionListener(this);
		back.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {

		String url19 = "", stop = "", wt = "";
		StringBuilder url191 = new StringBuilder();
		StringBuilder filter = new StringBuilder();
		// *************** URL ****************

		
 
		if (ae.getSource() == url) {
			try {
				
				String line = HomeScreen.dataBuilder.toString();
				
				
				ok1=line.replaceAll("\\{.*?text\": \"", " ");
				line = ok1.replaceAll("\", \"type.*?\\}", " ");
				ok = line.replaceAll("http.*?\\s", " ");
				
				// if (line != null) {
				//
				// StringTokenizer tokens = new StringTokenizer(line, "\n");
				//
				// while (tokens.hasMoreTokens()) {
				//
				// String word = tokens.nextToken();
				// String[] words = word.split("\\s");
				//
				// for (String item : words)
				// try {
				// URL url = new URL(item);
				//
				// // out.write" " );
				// // url19 = url19 + " ";
				// url191.append("");
				//
				// } catch (MalformedURLException e) {
				//
				// // out.write item + " " );
				// // url19 = url19 + item + " ";
				// url191.append(item + " ");
				// }
				// // out.write"\n");
				// // url19 = url19 + "\n";
				// url191.append("\n");
				//
				// }
				// urlArea.setText(url191.toString());
				// // System.out.println("********");
				// // line = reader.readLine();
				// }
				// out.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			urlArea.setText(ok);
			// System.out.println(url19);

			// ok = url191.toString();
		}

		// ************** FILTER ***************

		else if (ae.getSource() == filtering) {

			String line = ok;
			try {
				int ip = 0;

				StringBuilder fin = new StringBuilder();

				StringTokenizer tokens1 = new StringTokenizer(line, "\n");
				while (tokens1.hasMoreTokens()) {
					ip++;

					String s = tokens1.nextToken();

					StringBuilder finals = new StringBuilder();
					StringTokenizer tokens = new StringTokenizer(s, " ");
					while (tokens.hasMoreTokens()) {

						String word = tokens.nextToken();

						int len = word.length();
						if (len == 1) {
							finals.append(" ");
							finals.append(word + " ");
						} else {
							if (word.charAt(0) == '@' || word.charAt(0) == '#')// ||
																				// word.charAt(1)=='@'
																				// ||
																				// word.charAt(0)=='#'
																				// ||
																				// word.charAt(1)=='#'
																				// )
							{
								continue;
							} else {

								for (int i = 0; i < len - 2; i++) {

									if (word.charAt(i) == word.charAt(i + 1)
											&& word.charAt(i) == word
													.charAt(i + 2)) {
										continue;
									} else {
										finals.append(word.charAt(i));

									}

								}
								if (word.charAt(len - 2) == word
										.charAt(len - 1)) {

									finals.append(word.charAt(len - 1));

								} else {

									finals.append(word.charAt(len - 2));
									finals.append(word.charAt(len - 1));
								}
								finals.append(" ");

							}
						}

					}
					finals.append("\n");
					fin.append(finals); //
				}
				// System.out.println(fin);
				filter.append(fin);
				ok = fin.toString();
				filterArea.setText(ok);

			} catch (Exception e) {
			}

		}// else if

		// ************ what *****************

		else if (ae.getSource() == what) {
			String line = ok;
			ok= line.replaceAll("(?:Who|What|When|Where|Why|Which|Whom|Whose).*?[^\\?\\.\\!]+\\?", " ");
			whArea.setText(ok);
			/*try {
				int i = 0;
				String line = ok;
				String fin = "";
				String whList[] = { "what", "where", "when", "how", "why",
						"who", "whome", "which" };
				StringBuilder finalBuilder = new StringBuilder();
				for (int g = 0; g < 8; g++) {
					StringTokenizer tokens = new StringTokenizer(line, "\n");
					while (tokens.hasMoreTokens()) {
						i++;
						String s = tokens.nextToken();
						String finals = "";
						int p0, p1, p2;
						p0 = 0;
						p1 = s.indexOf(whList[g], p0);
						int dum = p1;
						p2 = s.indexOf("?", p1);
						if (p1 != -1 && p2 != -1) {
							dum--;
							if (p1 == 0) {
							} else {
								String sub = s.substring(p0, dum);
								finals = finals + sub;
							}
							while (p1 >= 0) {
								p0 = p2 + 1;
								p1 = s.indexOf(whList[g], p0);
								p2 = s.indexOf("?", p1);
								if (p1 == -1) {
									break;
								} else {
									finals = finals + s.substring(p0, p1 - 1);
								}
							}
							if (p0 < s.length()) {
								finals = finals + s.substring(p0);
							}

						} else {
							finals = s;
						}
						//finals = finals + "\n";
						finalBuilder.append(finals+"\n");
						//fin = fin + finals;

					}
//					line = fin;
//					fin = "";
				}
//				fin = line;
//				System.out.println(fin);
				//line = fin;
				ok = finalBuilder.toString();
				// System.out.println("99jjjjj9    "+ok);
				// out.writeline);
				// out.close();
				whArea.setText(ok);
			} catch (Exception e) {
			}

*/		}// if else

		else if (ae.getSource() == spsy) {
			try {

				path2 = ok.replaceAll("[$%#@(&,''{}!?)]", " ");
				// BufferedWriter out7 = new BufferedWriter(new FileWriter(
				// "p1.txt"));
				//
				// String line = ok;
				// String word1 = "";
				// StringBuilder fulltext= new StringBuilder();
				// StringTokenizer tokens = new StringTokenizer(line, "\n");
				//
				// while (tokens.hasMoreTokens()) {
				//
				// String word = tokens.nextToken();
				// word1 = word.replaceAll("[$%#@(&,''{}!?)]", " ");
				// fulltext.append(word.replaceAll("[$%#@(&,''{}!?)]",
				// " ")+"\n");
				// ok = stop;
				// out7.write(word);
				// }
				//
				// splArea.setText(stop);
				 go.setVisible(true);
				splArea.setText(path2);
			} catch (Exception e) {
			}
		//	path2 = ok;
			// send1=stop;

		}// else if

		else if (ae.getSource() == go) {

			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screen.width * 10 / 100);
			int y = (screen.height * 10 / 100);
			int w = (screen.width * 80 / 100);

			int h = (screen.height * 80 / 100);

			Alg al = new Alg(path2, this);

			al.setBounds(x, y, w, h);

			// choosefile.setText( "" );

			// password.setText( "" );
			this.setVisible(false);

			al.setVisible(true);
		}

		else if (ae.getSource() == back) {
			parent.setVisible(true);
			this.setVisible(false);
		}

	}// method

}// class
