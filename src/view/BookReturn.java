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
import model.Card;
import util.JDBC;
import javax.swing.ImageIcon;

public class BookReturn extends JFrame {

	private JPanel contentPane;
	private JTextField noText;
	private JTextField cardNoText;
	public JLabel usernameText;
	
	private JDBC jdbc=new JDBC();
	private RecordDao recordDao = new RecordDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookReturn frame = new BookReturn();
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
	public BookReturn() {
		setTitle("图书归还");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("图书归还");
		lblNewLabel.setIcon(new ImageIcon(BookReturn.class.getResource("/image/6590504_and_book_education_learning_remove_icon.png")));
		lblNewLabel.setBounds(75, 43, 283, 58);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 50));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_3 = new JLabel("书号:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_3.setBounds(10, 258, 92, 49);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("卡号:");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1_3_1.setBounds(10, 147, 92, 49);
		contentPane.add(lblNewLabel_1_3_1);
		
		noText = new JTextField();
		noText.setFont(new Font("宋体", Font.PLAIN, 30));
		noText.setColumns(10);
		noText.setBounds(108, 258, 239, 49);
		contentPane.add(noText);
		
		cardNoText = new JTextField();
		cardNoText.setFont(new Font("宋体", Font.PLAIN, 30));
		cardNoText.setColumns(10);
		cardNoText.setBounds(108, 147, 239, 49);
		contentPane.add(cardNoText);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnBook(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(145, 392, 142, 62);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_8 = new JLabel("用户名:");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_8.setBounds(32, 500, 116, 40);
		contentPane.add(lblNewLabel_8);
		
		usernameText = new JLabel("username");
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setBounds(133, 497, 142, 43);
		contentPane.add(usernameText);
		
		this.setLocationRelativeTo(null);
	}

	private void returnBook(ActionEvent e) {
		String cardNoStr=cardNoText.getText();
		int cardNo=0;
		if(!cardNoStr.isEmpty())
		{
			cardNo=Integer.parseInt(cardNoStr);
		}
		
		String username=usernameText.getText();
		String no=noText.getText();
		
		int check = recordDao.checkBlankReturn(cardNo, no);
		if(check==0)
		{
			JOptionPane.showMessageDialog(null, "卡号不能为空！");
			return;
		}
		if(check==1)
		{
			JOptionPane.showMessageDialog(null, "书号不能为空！");
			return;
		}
		Card card=new Card(cardNo,username);
		
		Connection con;
		try {
			con = jdbc.getCon();
			int result = recordDao.checkCard(con, card);
			if(result==0)
			{
				JOptionPane.showMessageDialog(null, "您没有该卡号的借书证！");
			}
			else if(result==3)
			{
				JOptionPane.showMessageDialog(null, "该卡号的借书证没有借阅书籍！");
			}
			else
			{
				recordDao.remove(con, cardNo, no);
				JOptionPane.showMessageDialog(null, "归还成功！");
				dispose();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
	}

}
