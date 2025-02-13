package controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import service.impl.UserServiceImpl;
import util.FormatTool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;
	private JTextField password;
	private JTextField name;
	private JTextField phone;
	private JButton goToLoginUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI frame = new RegisterUI();
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
	public RegisterUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("註冊");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		email = new JTextField();
		email.setBounds(125, 50, 96, 21);
		contentPane.add(email);
		email.setColumns(10);

		password = new JTextField();
		password.setColumns(10);
		password.setBounds(125, 79, 96, 21);
		contentPane.add(password);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(125, 107, 96, 21);
		contentPane.add(name);

		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(125, 135, 96, 21);
		contentPane.add(phone);

		JLabel lblNewLabel = new JLabel("信箱");
		lblNewLabel.setBounds(69, 53, 46, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(69, 82, 46, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(69, 110, 46, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("電話");
		lblNewLabel_3.setBounds(69, 138, 46, 15);
		contentPane.add(lblNewLabel_3);

		JButton rigester = new JButton("確認");
		rigester.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rigester.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Email = email.getText();
				String Password = password.getText();
				String Name = name.getText();
				String Phone = phone.getText();

				if (!FormatTool.emailFormat(Email)) {
					JOptionPane.showMessageDialog(null, "信箱格式錯誤", "格式錯誤", JOptionPane.WARNING_MESSAGE);
				} else if (!FormatTool.passwordFormat(Password)) {
					JOptionPane.showMessageDialog(null, "密碼須包含英文大小寫及數字，長度最少六位", "格式錯誤", JOptionPane.WARNING_MESSAGE);
				} else if (!FormatTool.phoneFormat(Phone)) {
					JOptionPane.showMessageDialog(null, "電話格式錯誤，應為10位數字", "格式錯誤", JOptionPane.WARNING_MESSAGE);
				} else {
					if (new UserServiceImpl().isEmailBeenUse(Email) || new UserServiceImpl().isPhoneBeenUse(Phone)) {
						JOptionPane.showMessageDialog(null, "信箱或電話已被註冊，請重新輸入", "註冊失敗", JOptionPane.WARNING_MESSAGE);
						email.setText("");
						phone.setText("");
					} else {
						User user = new User(Name, Email, Password, Phone);
						new UserServiceImpl().addUser(user);
						JOptionPane.showMessageDialog(null, "註冊成功，請重新登入", "註冊成功", JOptionPane.WARNING_MESSAGE);
						new LoginUI().setVisible(true);
						dispose();
					}
				}
			}
		});

		goToLoginUI = new JButton("回登入頁");
		goToLoginUI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginUI().setVisible(true);
				dispose();
			}
		});
		goToLoginUI.setBounds(69, 186, 87, 23);
		contentPane.add(goToLoginUI);
		rigester.setBounds(180, 186, 87, 23);
		contentPane.add(rigester);
	}

}
