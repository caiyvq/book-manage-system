package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	public JLabel usernameText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setResizable(false);
		setTitle("图书管理系统-主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1433, 963);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("查询图书");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookQuery bookQuery=new BookQuery();
				bookQuery.setVisible(true);
				bookQuery.usernameText.setText(usernameText.getText());
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton.setBounds(532, 233, 288, 98);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("图书管理系统");
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/image/7755372_hipster_lifestyle_book_reading_library_icon.png")));
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(409, 60, 523, 150);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("归还图书");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookReturn bookReturn = new BookReturn();
				bookReturn.setVisible(true);
				bookReturn.usernameText.setText(usernameText.getText());
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton_1.setBounds(532, 363, 288, 98);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("办理借书证");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardApply cardApply=new CardApply();
				cardApply.setVisible(true);
				cardApply.usernameText.setText(usernameText.getText());
			}
		});
		btnNewButton_1_1.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton_1_1.setBounds(532, 496, 288, 98);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("登出");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				goToInitial(e);
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 30));
		btnNewButton_2.setBounds(593, 758, 143, 51);
		contentPane.add(btnNewButton_2);
		
		usernameText = new JLabel("username");
		usernameText.setFont(new Font("宋体", Font.PLAIN, 30));
		usernameText.setBounds(129, 842, 296, 43);
		contentPane.add(usernameText);
		
		JLabel lblNewLabel_1 = new JLabel("用户名:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(25, 844, 116, 40);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1_1_1 = new JButton("查看借书证");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardView cardView=new CardView();
				cardView.usernameText.setText(usernameText.getText());
				cardView.fillCardMenu();
				cardView.setVisible(true);
			}
		});
		btnNewButton_1_1_1.setFont(new Font("宋体", Font.BOLD, 40));
		btnNewButton_1_1_1.setBounds(532, 629, 288, 98);
		contentPane.add(btnNewButton_1_1_1);
		
		this.setLocationRelativeTo(null);
	}

	protected void goToInitial(ActionEvent evt) {
		new Initial().setVisible(true);
	}
	
}
