package com.baidu.ai.hp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.baidu.ai.dbconnect.DBHelper;

public class finddish extends JFrame implements ActionListener {
	private JTextField nameText, caluliText, kenengxingText;
	private JTextArea t;
	private String name;
	private JButton b1, b2;
	public finddish() {
	Container c1 = this.getContentPane();
	JPanel p3=new JPanel();
	p3.setLayout(new GridLayout(4,1));
	JLabel label1 = new JLabel("菜品信息检索", SwingConstants.CENTER);
	JLabel label0 = new JLabel("请输菜名", SwingConstants.CENTER);
	JPanel pp = new JPanel(new GridLayout(2, 1));
	pp.add(label1);
	pp.add(label0);
	p3.add(pp);
	JPanel p1 = new JPanel();
	nameText = new JTextField(20);
	p1.add(nameText);
	p3.add(p1);
	//c1.add(p1,BorderLayout.CENTER);
	JPanel p2 = new JPanel();
	b1 = new JButton("查询");
	b2 = new JButton("退出");
	// 两个按钮的监听注册
	b1.addActionListener(this);
	b2.addActionListener(this);
	p2.add(b1);
	p2.add(b2);
	p3.add(p2);
	JPanel center = new JPanel(new GridLayout(2, 2));
	caluliText = new JTextField(30);
	kenengxingText = new JTextField(30);
	center.add(new JLabel("卡路里值", SwingConstants.CENTER));
	center.add(caluliText);
	center.add(new JLabel("正确率", SwingConstants.CENTER));
	center.add(kenengxingText);
	p3.add(center);
	t = new JTextArea();
	JScrollPane scroll = new JScrollPane(t);// 添加滚动文本显示框
	c1.add(scroll,BorderLayout.CENTER);
	c1.add(p3,BorderLayout.NORTH);
	
	this.setVisible(true);
	this.setBounds(200, 200, 350, 380);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			Connection con = DBHelper.getConnection();
			String sql ="select * FROM dish";
			Statement st = null;
			name = nameText.getText();
			int k = 0,flag=0;
			try {
				st = con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next()) {
					if(rs.getString("name").equals(name)) {
						caluliText.setText(String.valueOf(rs.getInt("calorie"))); 
						kenengxingText.setText(String.valueOf(rs.getFloat("probability")));
					    char[] d2 = new char[100];
						d2 = rs.getString("description").toCharArray();// 利用toCharArray方法转换
						if(flag==0) {
							for (int i = 0; i < rs.getString("description").length(); i++) {
								flag=1;
								t.append(String.valueOf(d2[i]));
								if (i % 24 == 0 && i > 0) {
									t.append("\n");
								}
							}	
						}
						k=1;
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (k == 0) {
				JOptionPane.showMessageDialog(this, "数据库未收录此菜");
			}
		}
		if (e.getSource() == b2) {
			this.dispose();// 结束监听
		}
	}
}
