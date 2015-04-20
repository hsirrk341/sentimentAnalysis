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

public class HomeScreen extends JFrame implements ActionListener {
	public String absolutePath = "";
	String path = "";
	String path1 = "";
	String fullpath = "";
	String data = "";
	public static StringBuilder dataBuilder= new StringBuilder();
	JButton open, next;

	JScrollPane scroll = null;

	JTextArea choosefile = null;

	JFileChooser fileChooser = null;

	public HomeScreen() {
		Container con = this.getContentPane();

		con.setLayout(new BorderLayout());
		// UIManager.put("Panel.background", Color.BLUE);

		JLabel title = new JLabel("Home page", JLabel.CENTER);

		title.setFont(new Font("Arial", Font.BOLD, 25));

		con.add(title, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();

		buttonPanel.add(open = new JButton("open"));
		buttonPanel.add(next = new JButton("next"));

		con.add(buttonPanel, BorderLayout.SOUTH);
		JPanel inputPanel = new JPanel();

		inputPanel.setLayout(new GridLayout(2, 2));

		inputPanel.add(new JLabel(" choose file ", JLabel.CENTER));

		choosefile = new JTextArea();
		scroll = new JScrollPane(choosefile);
		inputPanel.add(scroll);
		con.add(inputPanel, BorderLayout.CENTER);
		next.addActionListener(this);

		open.addActionListener(this);
		// UIManager.put("Panel.background", Color.BLUE);
		// con.setBackground(Color.blue);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {
		absolutePath = "";
		if (ae.getSource() != next) {
			fileChooser = new JFileChooser();

			int result = fileChooser.showOpenDialog(this);

			if (result == JFileChooser.CANCEL_OPTION) {
			}

			else {
				String filename = fileChooser.getSelectedFile().getName();
				if (filename.endsWith(".txt") || filename.endsWith(".java")
						|| filename.endsWith(".TXT")
						|| filename.endsWith(".JAVA")) {
					absolutePath = (fileChooser.getSelectedFile())
							.getAbsolutePath();
					path1 = absolutePath;
					choosefile.setText(absolutePath);
					choosefile.setText("");

					try {
						FileReader fileReader = new FileReader(absolutePath);
						int ch;
						ch = fileReader.read();
						while (ch != -1) {
							choosefile.append("" + (char) ch);
							ch = fileReader.read();
						//	data = data + (char) ch;
							dataBuilder.append((char)ch);
						}
						fileReader.close();
					}// try
					catch (Exception e) {
						JOptionPane.showMessageDialog(this, "message" + e,
								"error", JOptionPane.INFORMATION_MESSAGE);
					}
					//System.out.println(data1);

				} else {

					JOptionPane
							.showMessageDialog(this, "File should be .txt",
									"Unknow file type",
									JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

		else if (ae.getSource() == next) {

			// pathName1(absolutePath);
		//	System.out.println(path1);
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

			int x = (screen.width * 10 / 100);

			int y = (screen.height * 10 / 100);

			int w = (screen.width * 80 / 100);

			int h = (screen.height * 80 / 100);

			Preproc p1 = new Preproc(path1, this);

			p1.setBounds(x, y, w, h);

			choosefile.setText("");

			// password.setText( "" );

			this.setVisible(false);

			p1.setVisible(true);
		}

	}

}