package com.baidu.ai.hp;
import com.baidu.ai.dbconnect.DBHelper;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

class ShowPhoto extends JFrame implements ActionListener {
	private JButton b1, b2;
	JTextArea t;

	public ShowPhoto() {
		Container c1 = this.getContentPane();
		c1.setLayout(new GridLayout(3, 1));
		JPanel p1 = new JPanel();
		JLabel label1 = new JLabel("历史纪录", SwingConstants.CENTER);
		p1.add(label1);
		c1.add(p1);
		t = new JTextArea();
		JScrollPane scroll = new JScrollPane(t);// 添加滚动文本显示框
		c1.add(scroll);
		JPanel p3 = new JPanel();
		b1 = new JButton("显示");
		b2 = new JButton("退出");
		p3.add(b1);
		p3.add(b2);
		c1.add(p3);
		this.setBounds(200, 200, 400, 300);
		this.setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			Connection con = DBHelper.getConnection();
			String sql = "select *from dish";
			Statement st = null;
			String s1 = " 菜名                  每100克卡路里值          识别正确可能性";
			try {
				st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				int i = 0;
				String[] s = new String[100];
				while (rs.next()) {
					String name = rs.getString("name");
					String kaluli = rs.getString("calorie");
					Float kenengxing = rs.getFloat("probability");
					s[i] = name + "                  " + kaluli + "                        "+kenengxing;
					i++;
				}
				for (int j = 0; j < i; j++) {
					s1 = s1 + "\n" + s[j];
				}
				t.append(s1);// 追加到文本末尾
			} catch (Exception e2) {
			}
		}
		if (e.getSource() == b2)
		{
			this.dispose();
		}
	}
}

