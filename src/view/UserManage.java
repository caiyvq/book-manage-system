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

import dao.RecordDao;
import util.JDBC;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserManage extends JFrame {

	private JPanel contentPane;
	private JTextField usernameText;
	private JButton btnNewButton;
	
	private JDBC jdbc=new JDBC();
	private RecordDao recordDao=new RecordDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManage frame = new UserManage();
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
	public UserManage() {
		setTitle("用户管理");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("用户管理");
		lblNewLabel_5.setIcon(new ImageIcon(UserManage.class.getResource("/image/7351045_edit_user_profile_avatar_icon.png")));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel_5.setBounds(63, 31, 293, 81);
		contentPane.add(lblNewLabel_5);
		
		usernameText = new JTextField();
		usernameText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='\n')
				{
					btnNewButton.doClick();
				}
			}
		});
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setColumns(10);
		usernameText.setBounds(140, 155, 210, 49);
		contentPane.add(usernameText);
		
		JLabel lblNewLabel_1 = new JLabel("用户名:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(25, 155, 105, 49);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton = new JButton("注销");
		btnNewButton.setIcon(new ImageIcon(UserManage.class.getResource("/image/103763_close_user_remove_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm=JOptionPane.showConfirmDialog(null, "确认修改？");
				if(confirm==0)
				{
					deleteUser(e);
				}
				
			}
		});
		btnNewButton.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton.setBounds(120, 270, 189, 62);
		contentPane.add(btnNewButton);
		
		this.setLocationRelativeTo(null);
	}

	private void deleteUser(ActionEvent e) {
		String username=usernameText.getText();
		if(username.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "用户名不能为为空！");
			return;
		}
		try {
			Connection con=jdbc.getCon();
			int result=recordDao.deleteUser(con, username);
			if(result==0)
			{
				JOptionPane.showMessageDialog(null, "注销成功！");
			}
			else if(result==-1)
			{
				JOptionPane.showMessageDialog(null, "该用户不存在！");
			}
			usernameText.setText("");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
