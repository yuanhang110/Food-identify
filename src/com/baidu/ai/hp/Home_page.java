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
		home_page = new JFrame("��ҳ");
		home_page.setLayout(null);// �ղ���
		// ����ҳ����λ��
		home_page.setSize(600, 430); // ����ҳ���ô�С
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
		JMenuBar menuBar;// �˵�����
		JMenu systemMenu;// ���˵�
		JMenuItem exitItem, choosephotoItem, showItem, findItem;

		menuBar = new JMenuBar();
		/*
		 * ��ʼ���˵���
		 */
		home_page.setLayout(null);// �ղ���
		systemMenu = new JMenu("ͼ��ʶ��");
		choosephotoItem = new JMenuItem("ѡ��ͼƬ");
		showItem = new JMenuItem("ʶ����ʷ��¼");
		findItem = new JMenuItem("���ݿ��Ʒ��Ϣ����");
		exitItem = new JMenuItem("�˳�");
		systemMenu.add(choosephotoItem);
		systemMenu.addSeparator();
		systemMenu.add(showItem);
		systemMenu.addSeparator();
		systemMenu.add(findItem);
		systemMenu.addSeparator();
		systemMenu.add(exitItem);
		/*
		 * ���˵��봰�ڽ��
		 */
		menuBar.add(systemMenu);
		home_page.setJMenuBar(menuBar);
		JLabel label1 = new JLabel("");
		label1.setBounds(60, 40, 500, 300);
		home_page.add(label1);
		/*
		 * �˳�����
		 */
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				home_page.setVisible(false);// ��ҳ������
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
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// �����죬
		} catch (Exception e) {
			e.printStackTrace();
		}
		Home_page m = new Home_page();
	}
}
