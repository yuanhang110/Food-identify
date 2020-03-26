package com.baidu.ai.hp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Home_page {
	JFrame home_page;

	public Home_page() {
		home_page = new JFrame("主页");
		home_page.setLayout(null);// 空布局
		// 给主页设置位置
		home_page.setSize(600, 430); // 给主页设置大小
		home_page.setLocation(350, 120);
		creatMenu();

		JPanel cp = (JPanel) home_page.getContentPane();
		cp.setLayout(new BorderLayout());
		ImageIcon background = new ImageIcon("src/images/33.png");
		JLabel label=new JLabel(background);
		cp.add("Center", label);
		home_page.pack();
		home_page.setVisible(true);
		home_page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void creatMenu() {
		JMenuBar menuBar;// 菜单变量
		JMenu systemMenu;// 主菜单
		JMenuItem exitItem, choosephotoItem, showItem, findItem;

		menuBar = new JMenuBar();
		/*
		 * 初始化菜单栏
		 */
		home_page.setLayout(null);// 空布局
		systemMenu = new JMenu("图像识别");
		choosephotoItem = new JMenuItem("选择图片");
		showItem = new JMenuItem("识别历史纪录");
		findItem = new JMenuItem("数据库菜品信息检索");
		exitItem = new JMenuItem("退出");
		systemMenu.add(choosephotoItem);
		systemMenu.addSeparator();
		systemMenu.add(showItem);
		systemMenu.addSeparator();
		systemMenu.add(findItem);
		systemMenu.addSeparator();
		systemMenu.add(exitItem);
		/*
		 * 将菜单与窗口结合
		 */
		menuBar.add(systemMenu);
		home_page.setJMenuBar(menuBar);
		JLabel label1 = new JLabel("");
		label1.setBounds(60, 40, 500, 300);
		home_page.add(label1);
		/*
		 * 退出监听
		 */
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				home_page.setVisible(false);// 主页面隐藏
			}
		});

		choosephotoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new show_page();
			}
		});
		showItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new ShowPhoto();
			}
		});
		findItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new finddish();
			}
		});

	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// 待考察，
		} catch (Exception e) {
			e.printStackTrace();
		}
		Home_page m = new Home_page();
	}
}
