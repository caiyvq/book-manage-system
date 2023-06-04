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
import javax.swing.border.EmptyBorder;

import dao.CardDao;
import model.User;
import util.JDBC;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CardApply extends JFrame {

	private JPanel contentPane;
	public JTextField usernameText;
	private JPasswordField passwordText;
	private JButton btnNewButton;
	
	JDBC jdbc=new JDBC();
	CardDao cardDao=new CardDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardApply frame = new CardApply();
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
	public CardApply() {
		setTitle("借书证办理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("用户名:");
		lblNewLabel_1.setBounds(36, 217, 105, 35);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("借书证办理");
		lblNewLabel.setIcon(new ImageIcon(CardApply.class.getResource("/image/8664836_id_card_icon.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel.setBounds(36, 78, 363, 68);
		contentPane.add(lblNewLabel);
		
		usernameText = new JTextField();
		usernameText.setEditable(false);
		usernameText.setFont(new Font("黑体", Font.PLAIN, 20));
		usernameText.setColumns(10);
		usernameText.setBounds(151, 213, 220, 49);
		contentPane.add(usernameText);
		
		JLabel lblNewLabel_2 = new JLabel("密  码:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(35, 323, 106, 35);
		contentPane.add(lblNewLabel_2);
		
		passwordText = new JPasswordField();
		passwordText.setFont(new Font("宋体", Font.PLAIN, 30));
		passwordText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='\n')
				{
					btnNewButton.doClick();
				}
			}
		});
		passwordText.setBounds(151, 308, 220, 51);
		contentPane.add(passwordText);
		
		btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCard(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(134, 436, 140, 57);
		contentPane.add(btnNewButton);
		
		this.setLocationRelativeTo(null);
	}

	private void addCard(ActionEvent e) {
		String username=usernameText.getText();
		String password=new String(passwordText.getPassword());
		User user = new User(username,password);
		
		Connection con=null;
		try {
			con=jdbc.getCon();
			int num=cardDao.cardNumber(con, username);
			if(num>=5)
			{
				JOptionPane.showMessageDialog(null, "可持卡数达到限制（5张）！");
				dispose();
			}
			else
			{
				int result=cardDao.add(con, user);
				if(result==0)
				{
					JOptionPane.showMessageDialog(null, "密码错误！");
				}
				else
				{
					String msg="办理成功！卡号:"+Integer.toString(result);
					JOptionPane.showMessageDialog(null, msg);
					dispose();
				}
			}
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
