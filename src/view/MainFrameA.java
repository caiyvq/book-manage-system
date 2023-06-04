package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;

public class MainFrameA extends JFrame implements KeyListener{

	private JPanel contentPane;
	private int keyTypedCount = 0 ;

	private JButton btnNewButton_1_2_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameA frame = new MainFrameA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrameA() {
		setVisible(true);
		setResizable(false);
		setTitle("图书管理系统-主界面-管理员模式");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1425, 959);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==32)
				{
					if(keyTypedCount>=10)
					{
						btnNewButton_1_2_1.setEnabled(true);
						btnNewButton_1_2_1.setVisible(true);
					}
					else
					{
						keyTypedCount++;
					}
				}
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("图书管理系统");
		lblNewLabel.setIcon(new ImageIcon(MainFrameA.class.getResource("/image/7755372_hipster_lifestyle_book_reading_library_icon.png")));
		lblNewLabel.setBounds(424, 112, 465, 69);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 60));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("查询图书");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookQueryA().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton.setBounds(524, 278, 288, 98);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("管理图书");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookManage().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton_1.setBounds(524, 407, 288, 98);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("登出");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Initial().setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton_2.setBackground(new Color(230, 230, 230));
		btnNewButton_2.setBounds(584, 840, 143, 51);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("管理员模式");
		lblNewLabel_1.setFont(new Font("宋体", Font.ITALIC, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(835, 191, 174, 38);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1_1 = new JButton("管理借书证");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CardManage().setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton_1_1.setBounds(524, 546, 288, 98);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("管理用户");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserManage().setVisible(true);
			}
		});
		btnNewButton_1_2.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton_1_2.setBounds(524, 694, 288, 98);
		contentPane.add(btnNewButton_1_2);
		
		btnNewButton_1_2_1 = new JButton("管理管理员");
		btnNewButton_1_2_1.setEnabled(false);
		btnNewButton_1_2_1.setVisible(false);
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AUserManage().setVisible(true);
			}
		});
		btnNewButton_1_2_1.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton_1_2_1.setBounds(880, 694, 288, 98);
		contentPane.add(btnNewButton_1_2_1);

		contentPane.requestFocus();
		
		this.setLocationRelativeTo(null);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
