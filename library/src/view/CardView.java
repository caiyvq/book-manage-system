package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.CardDao;
import dao.RecordDao;
import model.Card;
import util.JDBC;
import javax.swing.ImageIcon;

public class CardView extends JFrame {

	private JPanel contentPane;
	private JTable bookTable;
	private JComboBox comboBox;
	
	private JDBC jdbc = new JDBC();
	private RecordDao recordDao = new RecordDao();
	private CardDao cardDao = new CardDao();

	public JLabel usernameText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardView frame = new CardView();
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
	public CardView() {
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
		
		setTitle("查看借书证");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 885, 802);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("用户名:");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_8.setBounds(24, 689, 116, 40);
		contentPane.add(lblNewLabel_8);
		
		usernameText = new JLabel("username");
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setBounds(125, 686, 142, 43);
		contentPane.add(usernameText);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("宋体", Font.PLAIN, 20));
		scrollPane.setBounds(67, 301, 747, 283);
		contentPane.add(scrollPane);
		
		bookTable = new JTable();
		bookTable.setModel(new DefaultTableModel(
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
		bookTable.setRowHeight(25);
		bookTable.setFont(new Font("宋体", Font.PLAIN, 20));
		bookTable.setFillsViewportHeight(true);
		bookTable.setColumnSelectionAllowed(true);
		bookTable.setCellSelectionEnabled(true);
		scrollPane.setViewportView(bookTable);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable(e);
			}
		});
		comboBox.setFont(new Font("宋体", Font.PLAIN, 30));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(431, 177, 201, 43);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("借书证号：");
		lblNewLabel.setIcon(new ImageIcon(CardView.class.getResource("/image/9057174_menu_left_alt_icon.png")));
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(209, 177, 212, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("借书证查看");
		lblNewLabel_1.setIcon(new ImageIcon(CardView.class.getResource("/image/8664836_id_card_icon.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel_1.setBounds(242, 55, 345, 83);
		contentPane.add(lblNewLabel_1);
		
		
		this.setLocationRelativeTo(null);
	}
	
	private void fillTable(ActionEvent evt) {
		DefaultTableModel dtm=(DefaultTableModel)bookTable.getModel();
		dtm.setRowCount(0);
		int cardNo=(int) comboBox.getSelectedItem();
		Connection con=null;
		try {
			con=jdbc.getCon();
			ResultSet rs = recordDao.getRecords(con, cardNo);
			dtm=recordDao.getBooks(con, rs, dtm);
			
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

	public void fillCardMenu()
	{
		String username = usernameText.getText();
		Connection con=null;
		try {
			con=jdbc.getCon();
			ResultSet rs = cardDao.listCard(con, username);
			Card card=null;
			while(rs.next())
			{
				card=new Card();
				card.setCardNo(rs.getInt("cardNo"));
				card.setUsername(rs.getString("username"));
				card.setAmount(rs.getInt("amount"));
				comboBox.addItem(rs.getInt("cardNo"));
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
	}
}
