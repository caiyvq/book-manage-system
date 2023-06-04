package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class LogInA extends JFrame {

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
					LogInA frame = new LogInA();
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
	public LogInA() {
		setAutoRequestFocus(false);
		setTitle("图书管理系统-管理员登录");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 588);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("Button.border"));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel(" 管理员登录");
		lblNewLabel.setBounds(182, 42, 393, 68);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(LogInA.class.getResource("/image/9165684_login_arrow_icon.png")));
		
		JLabel lblNewLabel_1 = new JLabel("用户名:");
		lblNewLabel_1.setIcon(new ImageIcon(LogInA.class.getResource("/image/309035_user_account_human_person_icon.png")));
		lblNewLabel_1.setBounds(97, 162, 157, 35);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		
		JLabel lblNewLabel_2 = new JLabel("密  码:");
		lblNewLabel_2.setIcon(new ImageIcon(LogInA.class.getResource("/image/2538749_key_open_password_security_icon.png")));
		lblNewLabel_2.setBounds(86, 264, 186, 57);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 30));
		
		passwordText = new JPasswordField();
		passwordText.setFont(new Font("宋体", Font.PLAIN, 30));
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
		passwordText.setBounds(299, 267, 220, 51);
		
		JLabel lblNewLabel_3 = new JLabel("20个字符以内的字母、数字");
		lblNewLabel_3.setBounds(536, 170, 262, 24);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_4 = new JLabel("12个字符以内的字母、数字");
		lblNewLabel_4.setBounds(536, 283, 249, 24);
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
		usernameText.setFont(new Font("黑体", Font.PLAIN, 30));
		usernameText.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_2);
		contentPane.add(lblNewLabel_1);
		contentPane.add(passwordText);
		contentPane.add(usernameText);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblNewLabel_4);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logInA(e);
			}
		});
		btnNewButton.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton.setBounds(240, 391, 140, 57);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Initial().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton_1.setBounds(446, 391, 140, 57);
		contentPane.add(btnNewButton_1);
		
		this.setLocationRelativeTo(null);

	}

	private void logInA(ActionEvent evt) {
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
			User resultUser=userDao.loginA(con, user);
			if(resultUser==null)
			{
				JOptionPane.showMessageDialog(null, "用户名不存在或密码错误！");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "登录成功！");
				dispose();
				new MainFrameA().setVisible(true);
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
}
