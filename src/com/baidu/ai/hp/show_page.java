package com.baidu.ai.hp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.ByteOrder;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.baidu.ai.aip.Dish;
import com.baidu.ai.dishinform.Dishinformation;

class show_page extends JFrame implements ActionListener {
	JTextField t1, t2, t3, t4;
	JTextArea t;
	private JButton b1, b2;
	JPanel p2, p4, p5;
	JLabel l1;
	static String path;
	final JTextArea area;
	JLabel pic;
	GridBagLayout layout;
	GridBagConstraints c;

	public show_page() {
		Container c1 = this.getContentPane();
		c1.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JLabel label1 = new JLabel("图像信息", SwingConstants.CENTER);
		p1.add(label1);
		p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 2));
		JPanel p3 = new JPanel(new GridLayout(4, 2));
		t1 = new JTextField(30);
		t2 = new JTextField(30);
		t3 = new JTextField(30);
		t4 = new JTextField(30);
		p3.add(new JLabel("菜品名称", SwingConstants.CENTER));
		p3.add(t1);
		p3.add(new JLabel("每100克卡路里值", SwingConstants.CENTER));
		p3.add(t2);
		p3.add(new JLabel("识别正确的可能性", SwingConstants.CENTER));
		p3.add(t3);
		p3.add(new JLabel("还可能是", SwingConstants.CENTER));
		p3.add(t4);
		new Dish();
		t = new JTextArea();
		p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(t);// 添加滚动文本显示框
		p4.add(scroll, BorderLayout.NORTH);
		p2.add(p3);
		p2.add(p4);
		JPanel p5 = new JPanel();
		p5.setLayout(new GridLayout(1, 4));
		b1 = new JButton("显示");
		b2 = new JButton("退出");
		l1 = new JLabel("拖拽图片->", SwingConstants.RIGHT);
		area = new JTextArea();
		area.setLineWrap(true);
		add(new JScrollPane(area));
		p5.add(b1);
		p5.add(b2);
		p5.add(l1);
		p5.add(area);
		c1.add(p1, BorderLayout.NORTH);
		c1.add(p2, BorderLayout.CENTER);
		c1.add(p5, BorderLayout.SOUTH);
		this.setBounds(250, 50, 800, 600);
		this.setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
		area.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根
				new DropTarget(area, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter() {
					@Override
					public void drop(DropTargetDropEvent dtde) {
						try {
							// 如果拖入的文件格式受支持
							if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
								// 接收拖拽来的数据
								dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
								@SuppressWarnings("unchecked")
								List<File> list = (List<File>) (dtde.getTransferable()
										.getTransferData(DataFlavor.javaFileListFlavor));
								area.setText("");
								for (File file : list) {
									area.append(file.getAbsolutePath());
									if (file.getAbsolutePath() != null) {
										path = file.getAbsolutePath();
										Dish.setPath(path);
										Dish.dish();
										System.out.println(path);
										Icon myImage = new ImageIcon(path);
										pic = new JLabel(myImage);
										p4.add(pic, BorderLayout.CENTER);
										Dishinformation d1 = new Dishinformation();
										t1.setText(d1.getName1());
										t2.setText(String.valueOf(d1.getCalorie()));
										t3.setText(String.valueOf(d1.getProbability()));
										t4.setText(d1.getName2());
										char[] d2 = new char[100];
										d2 = d1.getDescribtion().toCharArray();// 利用toCharArray方法转换
										for (int i = 0; i < d1.getDescribtion().length(); i++) {
											t.append(String.valueOf(d2[i]));
											if (i % 20 == 0 && i > 0) {
												t.append("\n");
											}
										}
									}
									area.append("\r\n");
								}
								// 指示拖拽操作已完成
								dtde.dropComplete(true);
							} else {
								// 拒绝拖拽来的数据
								dtde.rejectDrop();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				area.setText(null);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			Dish.filechoose();
			Dish.dish();
			System.out.println(Dish.getFilePath());
			Icon myImage = new ImageIcon(Dish.getFilePath());
			pic = new JLabel(myImage);
			p4.add(pic, BorderLayout.CENTER);
			Dishinformation d1 = new Dishinformation();
			t1.setText(d1.getName1());
			t2.setText(String.valueOf(d1.getCalorie()));
			t3.setText(String.valueOf(d1.getProbability()));
			t4.setText(d1.getName2());
			t.append(d1.getDescribtion());
		}
		if (e.getSource() == b2) {
			this.dispose();
		}
	}
}
