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

public class CardManage extends JFrame {

	private JPanel contentPane;
	private JTextField cardNoText;
	
	private JDBC jdbc = new JDBC();
	private RecordDao recordDao = new RecordDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardManage frame = new CardManage();
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
	public CardManage() {
		setResizable(false);
		setTitle("借书证管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("借书证管理");
		lblNewLabel_5.setIcon(new ImageIcon(CardManage.class.getResource("/image/8664836_id_card_icon.png")));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 50));
		lblNewLabel_5.setBounds(66, 48, 333, 81);
		contentPane.add(lblNewLabel_5);
		
		cardNoText = new JTextField();
		cardNoText.setFont(new Font("宋体", Font.PLAIN, 30));
		cardNoText.setColumns(10);
		cardNoText.setBounds(129, 185, 210, 49);
		contentPane.add(cardNoText);
		
		JLabel lblNewLabel_1 = new JLabel("卡号:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(40, 185, 92, 49);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("全部归还");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnAll(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton.setBounds(129, 284, 177, 62);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("注销");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCard(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton_1.setBounds(134, 386, 172, 62);
		contentPane.add(btnNewButton_1);
		
		this.setLocationRelativeTo(null);
	}

	private void deleteCard(ActionEvent e) {
		String cardNoStr=cardNoText.getText();
		int cardNo=0;
		if(cardNoStr.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "卡号不能为空！");
			return;
		}
		else
		{
			cardNo=Integer.parseInt(cardNoStr);
		}
		try {
			Connection con=jdbc.getCon();
			int result=recordDao.deleteCard(con, cardNo);
			if(result==0)
			{
				JOptionPane.showMessageDialog(null, "成功注销！");
			}
			else if(result==1)
			{
				JOptionPane.showMessageDialog(null, "该借书证有书未还，不可注销！");
			}
			else if(result==-1)
			{
				JOptionPane.showMessageDialog(null, "该借书证不存在！");
			}
			cardNoText.setText("");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void returnAll(ActionEvent e) {
		String cardNoStr=cardNoText.getText();
		int cardNo=0;
		if(cardNoStr.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "卡号不能为空！");
			return;
		}
		else
		{
			cardNo=Integer.parseInt(cardNoStr);
		}
		try {
			Connection con=jdbc.getCon();
			int result=recordDao.returnAll(con, cardNo);
			if(result==0)
			{
				JOptionPane.showMessageDialog(null, "已全部归还！");
			}
			else if(result==1)
			{
				JOptionPane.showMessageDialog(null, "归还成功！");
			}
			else if(result==-1)
			{
				JOptionPane.showMessageDialog(null, "该借书证不存在！");
			}
			cardNoText.setText("");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
