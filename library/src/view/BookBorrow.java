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
import dao.RecordDao;
import model.Card;
import util.JDBC;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookBorrow extends JFrame {

	private JPanel contentPane;
	private JTextField cardNoText;
	private JPasswordField passwordText;
	public JTextField nameText;
	public JTextField noText;
	public JLabel usernameText;
	
	JDBC jdbc=new JDBC();
	CardDao cardDao = new CardDao();
	RecordDao recordDao = new RecordDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookBorrow frame = new BookBorrow();
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
	public BookBorrow() {
		setTitle("图书借阅");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("图书借阅");
		lblNewLabel.setIcon(new ImageIcon(BookBorrow.class.getResource("/image/8664993_book_medical_icon.png")));
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(80, 46, 276, 83);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("卡号:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(29, 307, 92, 49);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密码:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(29, 384, 92, 49);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrow(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(143, 459, 142, 62);
		contentPane.add(btnNewButton);
		
		cardNoText = new JTextField();
		cardNoText.addKeyListener(new KeyAdapter() {
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
		cardNoText.setFont(new Font("宋体", Font.PLAIN, 30));
		cardNoText.setBounds(129, 307, 239, 49);
		contentPane.add(cardNoText);
		cardNoText.setColumns(10);
		
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
					cardNoText.requestFocus();
				}
			}
		});
		passwordText.setFont(new Font("宋体", Font.PLAIN, 30));
		passwordText.setBounds(129, 384, 239, 49);
		contentPane.add(passwordText);
		
		JLabel lblNewLabel_1_2 = new JLabel("书名:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_2.setBounds(29, 228, 92, 49);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("书号:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_3.setBounds(29, 155, 92, 49);
		contentPane.add(lblNewLabel_1_3);
		
		nameText = new JTextField();
		nameText.setFont(new Font("宋体", Font.PLAIN, 30));
		nameText.setEditable(false);
		nameText.setColumns(10);
		nameText.setBounds(129, 228, 239, 49);
		contentPane.add(nameText);
		
		noText = new JTextField();
		noText.setFont(new Font("宋体", Font.PLAIN, 30));
		noText.setEditable(false);
		noText.setColumns(10);
		noText.setBounds(129, 155, 239, 49);
		contentPane.add(noText);
		
		JLabel lblNewLabel_8 = new JLabel("用户名:");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_8.setBounds(10, 537, 116, 40);
		contentPane.add(lblNewLabel_8);
		
		usernameText = new JLabel("username");
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setBounds(106, 534, 142, 43);
		contentPane.add(usernameText);
		
		this.setLocationRelativeTo(null);
	}

	private void borrow(ActionEvent e) {
		String cardNoStr=cardNoText.getText();
		int cardNo=0;
		if(!cardNoStr.isEmpty())
		{
			cardNo=Integer.parseInt(cardNoStr);
		}
		
		String username=usernameText.getText();
		String no=noText.getText();
		String password=new String(passwordText.getPassword());
		
		
		Card card=new Card(cardNo,username);
		
		Connection con;
		try {
			con = jdbc.getCon();

			if(!recordDao.checkUserCard(con, username))
			{
				JOptionPane.showMessageDialog(null, "您当前没有借书证，请先进行办理！");
				return;
			}
			
			int check = recordDao.checkBlankBorrow(cardNo, password);
			if(check==0)
			{
				JOptionPane.showMessageDialog(null, "卡号不能为空！");
				return;
			}
			if(check==1)
			{
				JOptionPane.showMessageDialog(null, "密码不能为空！");
				return;
			}
			
			int result = recordDao.checkCard(con, card);
			
			if(result==0)
			{
				JOptionPane.showMessageDialog(null, "您没有该卡号的借书证！");
			}
			else if(result==2)
			{
				JOptionPane.showMessageDialog(null, "该卡号的借书证可借书量达到上限（10本）！");
			}
			else
			{
				if(recordDao.checkPassword(con, username, password))
				{
					recordDao.add(con, cardNo, no);
					JOptionPane.showMessageDialog(null, "借阅成功！");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "密码错误！");
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
