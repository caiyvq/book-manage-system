package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import dao.BookDao;
import model.Book;
import util.JDBC;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookQuery extends JFrame {

	private JPanel contentPane;
	private JTable bookTable;
	
	private JDBC jdbc=new JDBC();
	private BookDao bookDao=new BookDao();
	private JLabel lblNewLabel_1;
	private JTextField keywordText;
	private JButton btnNewButton;
	private DefaultTableModel dtm;
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField noText;
	private JTextField nameText;
	private JTextField authorText;
	private JTextField typeText;
	private JTextField publisherText;
	private JTextField storageText;
	public JButton btnNewButton_1;
	public JLabel visitor;
	public JLabel lblNewLabel_8;
	public JLabel usernameText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookQuery frame = new BookQuery();
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
	public BookQuery() {
		setResizable(false);
		Font font=new Font("Dialog",Font.PLAIN,20);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements())
		{
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource)
			{
				UIManager.put(key, font);
			}
		}
		
		setTitle("查询图书");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 909, 948);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("查询图书");
		lblNewLabel.setIcon(new ImageIcon(BookQuery.class.getResource("/image/4634455_article_content_interface_search_icon.png")));
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(249, 46, 341, 85);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane.setBounds(93, 219, 747, 192);
		contentPane.add(scrollPane);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTableMousePressed(e);
			}
		});
		bookTable.setRowHeight(25);
		bookTable.setColumnSelectionAllowed(true);
		bookTable.setCellSelectionEnabled(true);
		bookTable.setFillsViewportHeight(true);
		bookTable.setFont(new Font("宋体", Font.PLAIN, 20));
		bookTable.setModel(dtm=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u4E66\u53F7", "\u4E66\u540D", "\u4F5C\u8005", "\u7C7B\u522B", "\u51FA\u7248\u793E"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane.setViewportView(bookTable);
		
		lblNewLabel_1 = new JLabel("关键词:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(142, 140, 125, 56);
		contentPane.add(lblNewLabel_1);
		
		keywordText = new JTextField();
		keywordText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='\n')
				{
					btnNewButton.doClick();
				}
			}
		});
		keywordText.setFont(new Font("宋体", Font.PLAIN, 30));
		keywordText.setBounds(277, 141, 331, 56);
		contentPane.add(keywordText);
		keywordText.setColumns(10);
		
		btnNewButton = new JButton("查询");
		btnNewButton.setIcon(new ImageIcon(BookQuery.class.getResource("/image/9056985_play_list_search_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query(e);
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 30));
		btnNewButton.setBounds(618, 140, 152, 56);
		contentPane.add(btnNewButton);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setFont(new Font("宋体", Font.PLAIN, 30));
		panel.setBorder(null);
		panel.setBounds(93, 421, 747, 430);
		contentPane.add(panel);
		
		lblNewLabel_2 = new JLabel("书号:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(22, 34, 91, 40);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("书名:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(22, 120, 91, 40);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("作者:");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(22, 200, 91, 40);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("类别:");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(370, 200, 91, 40);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("出版社:");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_6.setBounds(22, 290, 91, 40);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("库存量:");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_7.setBounds(370, 290, 91, 40);
		panel.add(lblNewLabel_7);
		
		noText = new JTextField();
		noText.setFont(new Font("宋体", Font.PLAIN, 25));
		noText.setEditable(false);
		noText.setColumns(10);
		noText.setBounds(116, 35, 227, 40);
		panel.add(noText);
		
		nameText = new JTextField();
		nameText.setEditable(false);
		nameText.setFont(new Font("宋体", Font.PLAIN, 25));
		nameText.setColumns(10);
		nameText.setBounds(116, 121, 572, 40);
		panel.add(nameText);
		
		authorText = new JTextField();
		authorText.setEditable(false);
		authorText.setFont(new Font("宋体", Font.PLAIN, 25));
		authorText.setColumns(10);
		authorText.setBounds(116, 201, 227, 40);
		panel.add(authorText);
		
		typeText = new JTextField();
		typeText.setEditable(false);
		typeText.setFont(new Font("宋体", Font.PLAIN, 25));
		typeText.setColumns(10);
		typeText.setBounds(461, 201, 227, 40);
		panel.add(typeText);
		
		publisherText = new JTextField();
		publisherText.setEditable(false);
		publisherText.setFont(new Font("宋体", Font.PLAIN, 25));
		publisherText.setColumns(10);
		publisherText.setBounds(123, 291, 227, 40);
		panel.add(publisherText);
		
		storageText = new JTextField();
		storageText.setEditable(false);
		storageText.setFont(new Font("宋体", Font.PLAIN, 25));
		storageText.setColumns(10);
		storageText.setBounds(461, 291, 227, 40);
		panel.add(storageText);
		
		btnNewButton_1 = new JButton("借阅");
		btnNewButton_1.setIcon(new ImageIcon(BookQuery.class.getResource("/image/6590489_add_and_book_education_learning_icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(noSelectedBook())
				{
					JOptionPane.showMessageDialog(null, "请先选择一本书！");
					return;
				}
				BookBorrow bookBorrow=new BookBorrow();
				bookBorrow.setVisible(true); 
				bookBorrow.noText.setText(noText.getText());
				bookBorrow.nameText.setText(nameText.getText());
				bookBorrow.usernameText.setText(usernameText.getText());
				
				fillTable(new Book());
			}
		});
		btnNewButton_1.setEnabled(true);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("隶书", Font.BOLD, 40));
		btnNewButton_1.setBackground(new Color(231, 230, 232));
		btnNewButton_1.setBounds(277, 364, 184, 56);
		panel.add(btnNewButton_1);
		
		visitor = new JLabel("访客模式");
		visitor.setVisible(false);
		visitor.setHorizontalAlignment(SwingConstants.CENTER);
		visitor.setFont(new Font("宋体", Font.ITALIC, 30));
		visitor.setBounds(577, 75, 174, 38);
		contentPane.add(visitor);
		
		lblNewLabel_8 = new JLabel("用户名:");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_8.setBounds(29, 861, 116, 40);
		contentPane.add(lblNewLabel_8);
		
		usernameText = new JLabel("username");
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setBounds(125, 861, 296, 43);
		contentPane.add(usernameText);
		
		this.setLocationRelativeTo(null);
		this.fillTable(new Book());
	}
	
	private boolean noSelectedBook() {
		String judge=noText.getText()
				+nameText.getText()
				+authorText.getText()
				+typeText.getText()
				+publisherText.getText()
				+storageText.getText();
		if(judge=="")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

	private void bookTableMousePressed(MouseEvent evt) {
		int rowNo = bookTable.getSelectedRow();
		String no="";
		for(int i=0;i<5;i++)
		{
			String str=bookTable.getColumnName(i);
			switch(str)
			{
			case "书号":
				noText.setText(no=(String)bookTable.getValueAt(rowNo, i));
				break;
			case "书名":
				nameText.setText((String)bookTable.getValueAt(rowNo, i));
				break;
			case "作者":
				authorText.setText((String)bookTable.getValueAt(rowNo, i));
				break;
			case "类别":
				typeText.setText((String)bookTable.getValueAt(rowNo, i));
				break;
			case "出版社":
				publisherText.setText((String)bookTable.getValueAt(rowNo, i));
				break;
			}
		}
		String sql="select * from book where no = '"+no+"'";
		Connection con=null;
		try {
			con=jdbc.getCon();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			int storage=0;
			while(rs.next())
			{
				storageText.setText(Integer.toString(storage=rs.getInt("storage")));
			}
			if(!visitor.isVisible())
			{
				if(storage==0)
				{
					btnNewButton_1.setEnabled(false);
				}
				else
				{
					btnNewButton_1.setEnabled(true);
				}
			}
			else
			{
				btnNewButton_1.setEnabled(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				jdbc.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void query(ActionEvent e) {
		String str=keywordText.getText();
		Book book =new Book(str,str,str,str,str);
		fillTable(book);
	}

	private void fillTable(Book book)
	{
		DefaultTableModel dtm=(DefaultTableModel)bookTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=jdbc.getCon();
			ResultSet rs=bookDao.list(con, book);
			dtm=getRows(rs,dtm);
			
			final TableRowSorter sorter = new TableRowSorter(dtm);
			bookTable.setRowSorter(sorter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbc.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private DefaultTableModel getRows(ResultSet rs,DefaultTableModel dtm) {
		try {
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(rs.getString("no"));
				v.add(rs.getString("name"));
				v.add(rs.getString("author"));
				v.add(rs.getString("type"));
				v.add(rs.getString("publisher"));
				dtm.addRow(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtm;
	}
}
