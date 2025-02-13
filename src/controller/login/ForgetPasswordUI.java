package controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import service.impl.UserServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ForgetPasswordUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField account;
	User user = new User();
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPasswordUI frame = new ForgetPasswordUI();
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
	public ForgetPasswordUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("輸入信箱或電話");
		lblNewLabel.setBounds(83, 89, 96, 15);
		contentPane.add(lblNewLabel);

		account = new JTextField();
		account.setBounds(189, 86, 96, 21);
		contentPane.add(account);
		account.setColumns(10);

		JButton findPassword = new JButton("找回密碼");
		findPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account = account.getText();

				User user = new UserServiceImpl().findPassword(Account);

				if (user!=null) {
					String Password=user.getPassword();
					JOptionPane.showMessageDialog(null, "密碼為 " + Password, "", JOptionPane.WARNING_MESSAGE);
					new LoginUI().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "信箱或電話未被註冊", "", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		findPassword.setBounds(200, 138, 85, 23);
		contentPane.add(findPassword);
		
		btnNewButton = new JButton("回登入頁");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginUI().setVisible(true);
				dispose();			
			}
		});
		btnNewButton.setBounds(94, 138, 85, 23);
		contentPane.add(btnNewButton);
	}
}
