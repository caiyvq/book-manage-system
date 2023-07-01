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

import dao.BookDao;
import model.Book;
import util.JDBC;
import util.Str;
import javax.swing.ImageIcon;

public class BookManage extends JFrame {

	private JPanel contentPane;
	private JTextField nameText;
	private JTextField authorText;
	private JTextField typeText;
	private JTextField publisherText;
	private JTextField numText;
	
	private JDBC jdbc=new JDBC();
	private BookDao bookDao=new BookDao();
	private JTextField noText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManage frame = new BookManage();
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
	public BookManage() {
		setTitle("管理图书");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 492, 826);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("书名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(31, 261, 125, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("作者");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(31, 340, 125, 53);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("类别");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(31, 421, 125, 53);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("出版社");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(31, 502, 125, 53);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("数量调整");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(31, 583, 125, 53);
		contentPane.add(lblNewLabel_4);
		
		nameText = new JTextField();
		nameText.setFont(new Font("宋体", Font.PLAIN, 30));
		nameText.setBounds(166, 261, 198, 53);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		authorText = new JTextField();
		authorText.setFont(new Font("宋体", Font.PLAIN, 30));
		authorText.setColumns(10);
		authorText.setBounds(166, 340, 198, 53);
		contentPane.add(authorText);
		
		typeText = new JTextField();
		typeText.setFont(new Font("宋体", Font.PLAIN, 30));
		typeText.setColumns(10);
		typeText.setBounds(166, 421, 198, 53);
		contentPane.add(typeText);
		
		publisherText = new JTextField();
		publisherText.setFont(new Font("宋体", Font.PLAIN, 30));
		publisherText.setColumns(10);
		publisherText.setBounds(166, 502, 198, 53);
		contentPane.add(publisherText);
		
		numText = new JTextField();
		numText.setFont(new Font("宋体", Font.PLAIN, 30));
		numText.setColumns(10);
		numText.setBounds(166, 583, 198, 53);
		contentPane.add(numText);
		
		JLabel lblNewLabel_5 = new JLabel("管理图书");
		lblNewLabel_5.setIcon(new ImageIcon(BookManage.class.getResource("/image/3994420_draw_edit_new_pen_write_icon.png")));
		lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(90, 38, 295, 81);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAdd(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(180, 683, 125, 60);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_6 = new JLabel("(必填)");
		lblNewLabel_6.setFont(new Font("宋体", Font.ITALIC, 25));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(360, 177, 94, 39);
		contentPane.add(lblNewLabel_6);
		
		noText = new JTextField();
		noText.setFont(new Font("宋体", Font.PLAIN, 30));
		noText.setColumns(10);
		noText.setBounds(166, 177, 198, 53);
		contentPane.add(noText);
		
		JLabel lblNewLabel_7 = new JLabel("书号");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_7.setBounds(31, 177, 125, 53);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6_1 = new JLabel("(必填)");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_1.setFont(new Font("宋体", Font.ITALIC, 25));
		lblNewLabel_6_1.setBounds(360, 261, 94, 39);
		contentPane.add(lblNewLabel_6_1);
		
		this.setLocationRelativeTo(null);
	}

	protected void bookAdd(ActionEvent e) {
		String no=noText.getText();
		String name=nameText.getText();
		String author=authorText.getText();
		String type=typeText.getText();
		String publisher=publisherText.getText();
		int num=0;
		
		if(Str.isEmpty(no))
		{
			JOptionPane.showMessageDialog(null, "书号不能为空！");
			return;
		}
		if(Str.isEmpty(name))
		{
			JOptionPane.showMessageDialog(null, "书名不能为空！");
			return;
		}
		
		if(Str.isEmpty(author))
		{
			author="佚名";
		}
		if(Str.isEmpty(type))
		{
			type="其他";
		}
		if(Str.isEmpty(publisher))
		{
			publisher="其他";
		}
		if(Str.isEmpty(numText.getText()))
		{
			num=1;
		}
		else
		{
			num=Integer.parseInt(numText.getText());
		}

		
		Book book=new Book(no,name,author,type,publisher,num);
		Connection con=null;
		try {
			con=jdbc.getCon();
			int result=bookDao.add(con, book);
			if(result==0)
			{
				JOptionPane.showMessageDialog(null, "添加失败！");
				reset();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "添加成功！");
				reset();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败！");
		} finally{
			try {
				jdbc.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void reset() {
		noText.setText("");
		nameText.setText("");
		authorText.setText("");
		typeText.setText("");
		publisherText.setText("");
		numText.setText("");
	}

}
