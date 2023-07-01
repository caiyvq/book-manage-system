package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.TableRowSorter;

import dao.BookDao;
import model.Book;
import util.JDBC;
import util.Str;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookQueryA extends JFrame {

	private JPanel contentPane;
	private JTable bookTable;
	
	private JDBC jdbc=new JDBC();
	private BookDao bookDao=new BookDao();
	private JLabel lblNewLabel_1;
	private JTextField keywordText;
	private JButton btnNewButton;
	private DefaultTableModel dtm;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookQueryA frame = new BookQueryA();
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
	public BookQueryA() {
		Font font=new Font("宋体",Font.PLAIN,20);
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
		
		setResizable(false);
		setTitle("查询图书-管理员模式");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 909, 901);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("查询图书");
		lblNewLabel.setIcon(new ImageIcon(BookQueryA.class.getResource("/image/4634455_article_content_interface_search_icon.png")));
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(241, 46, 349, 85);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane.setBounds(93, 230, 747, 192);
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
		btnNewButton.setIcon(new ImageIcon(BookQueryA.class.getResource("/image/9056985_play_list_search_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(618, 141, 159, 56);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("管理员模式");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("宋体", Font.ITALIC, 30));
		lblNewLabel_1_1.setBounds(558, 77, 174, 38);
		contentPane.add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("宋体", Font.PLAIN, 30));
		panel.setBorder(null);
		panel.setBounds(93, 432, 747, 398);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("书号:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(22, 34, 91, 40);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("书名:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(22, 113, 91, 40);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("作者:");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(22, 180, 91, 40);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("类别:");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_5.setBounds(370, 180, 91, 40);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("出版社:");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_6.setBounds(22, 245, 91, 40);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("库存量:");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_7.setBounds(370, 245, 91, 40);
		panel.add(lblNewLabel_7);
		
		noText = new JTextField();
		noText.setEditable(false);
		noText.setFont(new Font("宋体", Font.PLAIN, 25));
		noText.setColumns(10);
		noText.setBounds(116, 35, 227, 40);
		panel.add(noText);
		
		nameText = new JTextField();
		nameText.setFont(new Font("宋体", Font.PLAIN, 25));
		nameText.setColumns(10);
		nameText.setBounds(116, 114, 572, 40);
		panel.add(nameText);
		
		authorText = new JTextField();
		authorText.setFont(new Font("宋体", Font.PLAIN, 25));
		authorText.setColumns(10);
		authorText.setBounds(116, 180, 227, 40);
		panel.add(authorText);
		
		typeText = new JTextField();
		typeText.setFont(new Font("宋体", Font.PLAIN, 25));
		typeText.setColumns(10);
		typeText.setBounds(461, 180, 227, 40);
		panel.add(typeText);
		
		publisherText = new JTextField();
		publisherText.setFont(new Font("宋体", Font.PLAIN, 25));
		publisherText.setColumns(10);
		publisherText.setBounds(116, 246, 227, 40);
		panel.add(publisherText);
		
		storageText = new JTextField();
		storageText.setFont(new Font("宋体", Font.PLAIN, 25));
		storageText.setColumns(10);
		storageText.setBounds(461, 246, 227, 40);
		panel.add(storageText);
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.setIcon(new ImageIcon(BookQueryA.class.getResource("/image/6590480_and_book_cog_config_education_icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(noSelectedBook())
				{
					JOptionPane.showMessageDialog(null, "请先选择一本书！");
					return;
				}
				int confirm=JOptionPane.showConfirmDialog(null, "确认修改？");
				if(confirm==0)
				{
					update(e);
				}
			}
		});
		btnNewButton_1.setBackground(new Color(231, 230, 232));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(136, 312, 185, 56);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("隶书", Font.BOLD, 40));
		
		JButton btnNewButton_1_1 = new JButton("删除");
		btnNewButton_1_1.setIcon(new ImageIcon(BookQueryA.class.getResource("/image/6590491_and_book_delete_education_learning_icon.png")));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(noSelectedBook())
				{
					JOptionPane.showMessageDialog(null, "请先选择一本书！");
					return;
				}
				int confirm=JOptionPane.showConfirmDialog(null, "确认删除？");
				if(confirm==0)
				{
					delete(e);
				}
			}
		});
		btnNewButton_1_1.setBackground(new Color(230, 230, 230));
		btnNewButton_1_1.setBounds(404, 312, 192, 56);
		panel.add(btnNewButton_1_1);
		btnNewButton_1_1.setFont(new Font("隶书", Font.BOLD, 40));
		
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

	private void update(ActionEvent e) {
		String no=noText.getText();
		String name=nameText.getText();
		String author=authorText.getText();
		String type=typeText.getText();
		String publisher=publisherText.getText();
		int storage=0;
		
		if(!Str.isEmpty(storageText.getText()))
		{
			storage=Integer.parseInt(storageText.getText());
		}
		if(storage<0)
		{
			JOptionPane.showMessageDialog(null, "库存量不能为负！");
			JOptionPane.showMessageDialog(null, "修改失败！");
			return;
		}
		Book book =new Book(no,name,author,type,publisher,storage);
		Connection con =null;
		try {
			con=jdbc.getCon();
			int result=bookDao.update(con, book);
			if(result>0)
			{
				query();
				JOptionPane.showMessageDialog(null, "修改成功！");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			try {
				jdbc.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
			while(rs.next())
			{
				storageText.setText(Integer.toString(rs.getInt("storage")));
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

	private void delete(ActionEvent e) {
		String no=noText.getText();
		Connection con =null;
		try {
			con=jdbc.getCon();
			int result=bookDao.delete(con, no);
			if(result==1)
			{
				query();
				noText.setText("");
				nameText.setText("");
				authorText.setText("");
				typeText.setText("");
				storageText.setText("");
				publisherText.setText("");
				JOptionPane.showMessageDialog(null, "删除成功！");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "尚有用户借有此书，请在用户归还后再删除！");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			try {
				jdbc.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void query() {
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
