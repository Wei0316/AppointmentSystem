package controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.appointment.AppointmentUI;
import model.User;
import service.impl.UserServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField account;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("登入");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("信箱/手機");
		lblNewLabel.setBounds(121, 88, 61, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(148, 117, 34, 15);
		contentPane.add(lblNewLabel_1);
		
		account = new JTextField();
		account.setBounds(192, 85, 96, 21);
		contentPane.add(account);
		account.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(192, 114, 96, 21);
		contentPane.add(password);
		
		JButton register = new JButton("註冊");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterUI register=new RegisterUI();
				register.setVisible(true);
				dispose();
			}
		});
		register.setBounds(95, 171, 87, 23);
		contentPane.add(register);
		
		JButton login = new JButton("登入");
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account=account.getText();
				String Password=password.getText();
				
				User user=new UserServiceImpl().Login(Account,Password);

				if(user!=null)
				{
					Tool.save(user, "user.txt");
					new AppointmentUI().setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "帳號密碼錯誤", "登入失敗", JOptionPane.WARNING_MESSAGE);
					account.setText("");
					password.setText("");
				}
			}
		});
		login.setBounds(241, 171, 87, 23);
		contentPane.add(login);
		
		JLabel lblNewLabel_2 = new JLabel("忘記密碼？");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ForgetPasswordUI().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2.setBounds(316, 88, 67, 15);
		contentPane.add(lblNewLabel_2);
	}
}
