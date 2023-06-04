package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import util.JDBC;
import util.Str;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField usernameText;
	private JTextField passwordText;
	private JTextField passwordConfirmText;
	private JButton btnNewButton;
	private JDBC jdbc=new JDBC();
	private UserDao userDao=new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setResizable(false);
		setTitle("图书管理系统-注册");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 758, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("注册");
		lblNewLabel.setIcon(new ImageIcon(Register.class.getResource("/image/9035610_person_add_outline_icon.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel.setBounds(220, 33, 249, 75);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("用户名：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(89, 171, 164, 44);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密 码：");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(93, 259, 151, 44);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("确认密码：");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_2.setBounds(93, 346, 157, 44);
		contentPane.add(lblNewLabel_1_2);
		
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
				if(e.getKeyCode()==38)
				{
					passwordConfirmText.requestFocus();
				}
			}
		});
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setBounds(247, 171, 217, 42);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		passwordText = new JTextField();
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
				if(e.getKeyCode()==40)
				{
					passwordConfirmText.requestFocus();
				}
			}
		});
		passwordText.setFont(new Font("宋体", Font.PLAIN, 30));
		passwordText.setColumns(10);
		passwordText.setBounds(247, 256, 217, 42);
		contentPane.add(passwordText);
		
		passwordConfirmText = new JTextField();
		passwordConfirmText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='\n')
				{
					btnNewButton.doClick();
				}
				if(e.getKeyCode()==38)
				{
					passwordText.requestFocus();
				}
				if(e.getKeyCode()==40)
				{
					usernameText.requestFocus();
				}
			}
		});
		passwordConfirmText.setFont(new Font("宋体", Font.PLAIN, 30));
		passwordConfirmText.setColumns(10);
		passwordConfirmText.setBounds(249, 341, 217, 42);
		contentPane.add(passwordConfirmText);
		
		JLabel lblNewLabel_2 = new JLabel("20个字符以内的数字、字母");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(474, 179, 249, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("12个字符以内的数字、字母");
		lblNewLabel_2_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(475, 271, 249, 27);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("12个字符以内的数字、字母");
		lblNewLabel_2_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(476, 346, 249, 27);
		contentPane.add(lblNewLabel_2_2);
		
		btnNewButton = new JButton("注册");
		btnNewButton.setIcon(new ImageIcon(Register.class.getResource("/image/7853762_check mark_kashifarif_check_accept_tick_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(register(e))
				{
					dispose();
				}
			}
		});
		btnNewButton.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton.setBounds(173, 426, 182, 61);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.setIcon(new ImageIcon(Register.class.getResource("/image/2849810_cross_multimedia_error_delite_icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton_1.setBounds(401, 426, 196, 61);
		contentPane.add(btnNewButton_1);
		
		this.setLocationRelativeTo(null);

	}

	protected boolean register(ActionEvent evt) {
		String username=this.usernameText.getText();
		String password=this.passwordText.getText();
		String passwordConfirm=this.passwordConfirmText.getText();
		if(Str.isEmpty(username))
		{
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return false;
		}
		if(username.length()>20)
		{
			JOptionPane.showMessageDialog(null, "用户名长度超过限制！");
			return false;
		}
		if(Str.isEmpty(password))
		{
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return false;
		}
		if(password.length()>12)
		{
			JOptionPane.showMessageDialog(null, "密码长度超过限制！");
			return false;
		}
		if(!password.equals(passwordConfirm))
		{
			JOptionPane.showMessageDialog(null, "两次密码输入不一致！");
			return false;
		}
		
		User user=new User(username,password);
		Connection con=null;
		try {
			con=jdbc.getCon();
			int result=userDao.register(con, user);
			if(result==1)
			{
				JOptionPane.showMessageDialog(null, "注册成功！");
				return true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "用户名已存在！");
				return false;
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
		return false;
	}

	protected void gotoInitial(ActionEvent e) {
		Initial frame=new Initial();
		frame.setVisible(true);
	}
}
