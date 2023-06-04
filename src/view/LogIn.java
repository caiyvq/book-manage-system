package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import dao.UserDao;
import model.User;
import util.JDBC;
import util.Str;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordText;
	private JTextField usernameText;
	private JDBC jdbc=new JDBC();
	private UserDao userDao=new UserDao();
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
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
	public LogIn() {
		setAutoRequestFocus(false);
		setTitle("图书管理系统-用户登录");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 588);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("Button.border"));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel(" 登录");
		lblNewLabel.setBounds(193, 42, 340, 68);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(LogIn.class.getResource("/image/9165684_login_arrow_icon.png")));
		
		JLabel lblNewLabel_1 = new JLabel("用户名:");
		lblNewLabel_1.setIcon(new ImageIcon(LogIn.class.getResource("/image/309035_user_account_human_person_icon.png")));
		lblNewLabel_1.setBounds(123, 165, 166, 35);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		
		JLabel lblNewLabel_2 = new JLabel("密  码:");
		lblNewLabel_2.setIcon(new ImageIcon(LogIn.class.getResource("/image/2538749_key_open_password_security_icon.png")));
		lblNewLabel_2.setBounds(123, 267, 167, 51);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 30));
		
		passwordText = new JPasswordField();
		passwordText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='\n')
				{
					btnNewButton.doClick();
				}
				if(e.getKeyCode()==38)
				{
					usernameText.requestFocus();
				}
			}
		});
		passwordText.setFont(new Font("宋体", Font.PLAIN, 30));
		passwordText.setBounds(299, 267, 220, 51);
		
		JLabel lblNewLabel_3 = new JLabel("20个字符以内的字母、数字");
		lblNewLabel_3.setBounds(536, 170, 240, 24);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_4 = new JLabel("12个字符以内的字母、数字");
		lblNewLabel_4.setBounds(536, 283, 240, 24);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		
		usernameText = new JTextField();
		usernameText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='\n')
				{
					btnNewButton.doClick();
				}
				if(e.getKeyCode()==40)
				{
					passwordText.requestFocus();
				}
			}
		});
		usernameText.setBounds(299, 158, 220, 49);
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_1);
		contentPane.add(passwordText);
		contentPane.add(usernameText);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblNewLabel_4);
		contentPane.add(lblNewLabel);
		//TODO:回车键登录
		btnNewButton = new JButton("登录");
		btnNewButton.setIcon(new ImageIcon(LogIn.class.getResource("/image/8666609_user_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn(e);
			}
		});
		btnNewButton.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton.setBounds(228, 393, 176, 57);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("注册");
		btnNewButton_1.setIcon(new ImageIcon(LogIn.class.getResource("/image/8666546_user_plus_icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToRegister(e);
			}
		});
		btnNewButton_1.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton_1.setBounds(443, 393, 186, 57);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("返回");
		btnNewButton_2.setBackground(new Color(230, 230, 230));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Initial().setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(374, 483, 99, 40);
		contentPane.add(btnNewButton_2);
		
		this.setLocationRelativeTo(null);

	}

	protected void goToRegister(ActionEvent e) {
		new Register().setVisible(true);
	}

	protected void gotoInitial(ActionEvent e) {
		Initial frame=new Initial();
		frame.setVisible(true);
	}

	private void logIn(ActionEvent evt) {
		String username=this.usernameText.getText();
		String password=new String(this.passwordText.getPassword());
		if(Str.isEmpty(username))
		{
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(Str.isEmpty(password))
		{
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		User user=new User(username,password);
		Connection con=null;
		try {
			con=jdbc.getCon();
			User resultUser=userDao.login(con, user);
			if(resultUser==null)
			{
				JOptionPane.showMessageDialog(null, "用户名不存在或密码错误！");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "登录成功！");
				dispose();
				MainFrame mainFrame=new MainFrame();
				mainFrame.setVisible(true);
				mainFrame.usernameText.setText(username);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbc.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return;
	}
	
//	private void addAction()
//	{
//		KeyListener keyListener = new KeyListener() {
//
//			@Override
//			public void keyTyped(KeyEvent e) {
//				
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if(e.getKeyChar()=='\n')
//				{
//					btnNewButton.doClick();
//				}
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//				
//			}
//		};
//		
//		usernameText.addKeyListener(keyListener);
//		passwordText.addKeyListener(keyListener);
//	}
}
