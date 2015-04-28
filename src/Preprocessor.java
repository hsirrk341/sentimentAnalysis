// Browsing.

import java.io.*;
import java.lang.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.JColorChooser;

public class Preprocessor extends JFrame implements ActionListener {

	JButton open, url, filtering, spsy, what, go, back, p1;
	public String ok = "", ok1 = "", send1 = "", path2 = "";
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
	List<String> list1 = new ArrayList<String>();

	public Preprocessor(String path, JFrame parent) {

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

				ok1 = line.replaceAll("\\{.*?text\": \"", " ");
				line = ok1.replaceAll("\", \"type.*?\\}", " ");
				ok = line.replaceAll("http.*?\\s", " ");
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			urlArea.setText(ok);
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
						list1.add(word);
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
					finals.append(" \n ");
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
			ok = line
					.replaceAll(
							"(?:Who|What|When|Where|Why|Which|Whom|Whose|How|who|what|when|where|why|which|whom|whose|how).*?(?:\\?|\\!|\\.)",
							" ");
			whArea.setText(ok);
		}// if else

		else if (ae.getSource() == spsy) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						"p1.txt"));
				path2 = ok.replaceAll("[$%#@(&,''{}!?)]", " ");
				out.write(path2);
				go.setVisible(true);
				splArea.setText(path2);
				out.close();
			} catch (Exception e) {
			}
		}// if else
		/*
		else if (ae.getSource() == spsy) {
			try {
				

				String line = ok;
				String word1 = "";

				word1 = line.replaceAll("[$%#@(&,''{}!?)]", " ");
				stop = word1;
				ok = stop;
				out7.write(word1);

				splArea.setText(stop);
				go.setVisible(true);
				out7.write("\n");
				out7.write("\n");
				out7.write("\n");
				out7.write("\n");
				out7.close();
			} catch (Exception e) {
			}
			path2 = ok;
			// send1=stop;

		}// else if */
		else if (ae.getSource() == go) {

			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screen.width * 10 / 100);
			int y = (screen.height * 10 / 100);
			int w = (screen.width * 80 / 100);

			int h = (screen.height * 80 / 100);

			Algorithm al = new Algorithm(path2, this, list1);

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
