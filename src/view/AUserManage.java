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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import util.JDBC;
import util.Str;
import javax.swing.ImageIcon;

public class AUserManage extends JFrame {

	private JPanel contentPane;
	private JTextField usernameText;
	private JTextField passwordText;

	private JDBC jdbc = new JDBC();
	private UserDao userDao = new UserDao();
	private JTextField passwordConfirmText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AUserManage frame = new AUserManage();
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
	public AUserManage() {
		setTitle("管理员管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 769, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("管理员管理");
		lblNewLabel_5.setIcon(new ImageIcon(AUserManage.class.getResource("/image/7351045_edit_user_profile_avatar_icon.png")));
		lblNewLabel_5.setBounds(209, 45, 331, 58);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 50));
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1 = new JLabel("用户名:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(41, 145, 105, 49);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密  码：");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(29, 231, 134, 49);
		contentPane.add(lblNewLabel_1_1);
		
		usernameText = new JTextField();
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setColumns(10);
		usernameText.setBounds(173, 145, 271, 49);
		contentPane.add(usernameText);
		
		passwordText = new JTextField();
		passwordText.setFont(new Font("宋体", Font.PLAIN, 30));
		passwordText.setColumns(10);
		passwordText.setBounds(173, 231, 271, 49);
		contentPane.add(passwordText);
		
		JButton btnNewButton_1 = new JButton("添加");
		btnNewButton_1.setIcon(new ImageIcon(AUserManage.class.getResource("/image/103747_close_add_user_icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAdmin(e);
			}
		});
		btnNewButton_1.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton_1.setBounds(173, 442, 172, 62);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("删除");
		btnNewButton_1_1.setIcon(new ImageIcon(AUserManage.class.getResource("/image/103763_close_user_remove_icon.png")));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAdmin(e);
			}
		});
		btnNewButton_1_1.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton_1_1.setBounds(449, 442, 172, 62);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("确认密码:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_1_1.setBounds(14, 328, 149, 49);
		contentPane.add(lblNewLabel_1_1_1);
		
		passwordConfirmText = new JTextField();
		passwordConfirmText.setFont(new Font("宋体", Font.PLAIN, 30));
		passwordConfirmText.setColumns(10);
		passwordConfirmText.setBounds(173, 328, 271, 49);
		contentPane.add(passwordConfirmText);
		
		JLabel lblNewLabel_2 = new JLabel("20个字符以内的数字、字母");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(454, 159, 249, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("20个字符以内的数字、字母");
		lblNewLabel_2_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(454, 241, 249, 27);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("20个字符以内的数字、字母");
		lblNewLabel_2_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(454, 336, 249, 27);
		contentPane.add(lblNewLabel_2_2);
		
		this.setLocationRelativeTo(null);
	}

	protected void deleteAdmin(ActionEvent e) {
		String username=usernameText.getText();
		String password=passwordText.getText();
		String passwordConfirm=passwordConfirmText.getText();
		if(Str.isEmpty(username))
		{
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(username.length()>20)
		{
			JOptionPane.showMessageDialog(null, "用户名长度超过限制！");
			return;
		}
		if(Str.isEmpty(password))
		{
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		if(password.length()>12)
		{
			JOptionPane.showMessageDialog(null, "密码长度超过限制！");
			return;
		}
		if(!password.equals(passwordConfirm))
		{
			JOptionPane.showMessageDialog(null, "两次密码输入不一致！");
			return;
		}
		
		User user=new User(username,password);
		Connection con=null;
		try {
			con=jdbc.getCon();
			int result=userDao.delete(con, user);
			if(result==1)
			{
				JOptionPane.showMessageDialog(null, "删除成功！");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "用户名不存在！");
			}
			usernameText.setText("");
			passwordText.setText("");
			passwordConfirmText.setText("");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				jdbc.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	protected void addAdmin(ActionEvent e) {
		String username=usernameText.getText();
		String password=passwordText.getText();
		String passwordConfirm=passwordConfirmText.getText();
		if(Str.isEmpty(username))
		{
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(username.length()>20)
		{
			JOptionPane.showMessageDialog(null, "用户名长度超过限制！");
			return;
		}
		if(Str.isEmpty(password))
		{
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		if(password.length()>12)
		{
			JOptionPane.showMessageDialog(null, "密码长度超过限制！");
			return;
		}
		if(!password.equals(passwordConfirm))
		{
			JOptionPane.showMessageDialog(null, "两次密码输入不一致！");
			return;
		}
		
		User user=new User(username,password);
		Connection con=null;
		try {
			con=jdbc.getCon();
			int result=userDao.add(con, user);
			if(result==1)
			{
				JOptionPane.showMessageDialog(null, "添加成功！");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "用户名已存在！");
			}
			usernameText.setText("");
			passwordText.setText("");
			passwordConfirmText.setText("");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				jdbc.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
