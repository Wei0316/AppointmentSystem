package controller.appointment;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.login.LoginUI;
import dao.impl.AppointmentDaoImpl;
import dao.impl.UserDaoImpl;
import model.Appointment;
import model.User;
import util.FormatTool;
import util.Tool;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserManageUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static User user = (User) Tool.read("user.txt");
	private JTextField name;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManageUI frame = new UserManageUI();
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
	public UserManageUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 256);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(user.getName() + "的帳號管理");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 414, 49);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(user.getName() + "的帳號管理");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel.setBounds(88, 10, 230, 35);
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("返回預約");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AppointmentUI().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(319, 17, 85, 23);
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 69, 414, 140);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(22, 23, 30, 15);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_2 = new JLabel("信箱     " + user.getEmail());
		lblNewLabel_1_2.setBounds(22, 48, 136, 15);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1 = new JLabel("密碼");
		lblNewLabel_1_1.setBounds(22, 76, 30, 15);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2_1 = new JLabel("電話     " + user.getPhone());
		lblNewLabel_1_2_1.setBounds(22, 101, 136, 15);
		panel_1.add(lblNewLabel_1_2_1);

		name = new JTextField(user.getName());
		name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (name.getText().equals(user.getName())) {
					name.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (name.getText().isEmpty() || name.getText().equals(user.getName())) {
					name.setText(user.getName());
				}
			}
		});
		name.setBounds(62, 20, 96, 21);
		panel_1.add(name);
		name.setColumns(10);

		passwordField = new JPasswordField(user.getPassword());
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (passwordField.getText().equals(user.getPassword())) {
					passwordField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (passwordField.getText().isEmpty() || passwordField.getText().equals(user.getPassword())) {
					passwordField.setText(user.getPassword());
				}
			}
		});
		passwordField.setBounds(62, 73, 96, 21);
		panel_1.add(passwordField);

		JButton btnNewButton_1 = new JButton("儲存");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!FormatTool.passwordFormat(passwordField.getText())) {
					JOptionPane.showMessageDialog(null, "密碼須包含英文大小寫及數字，長度最少六位", "格式錯誤", JOptionPane.WARNING_MESSAGE);
				} else {
					int result = JOptionPane.showConfirmDialog(null, "是否確定修改", "修改確認", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						String password = new String(passwordField.getPassword());
						new UserDaoImpl().updateUser(
								new User((String) name.getText(), user.getEmail(), password, user.getPhone()));
						JOptionPane.showMessageDialog(null, "修改成功，請重新登入", "修改成功", JOptionPane.WARNING_MESSAGE);
						new LoginUI().setVisible(true);
						dispose();
					}
				}
			}
		});
		btnNewButton_1.setBounds(167, 19, 85, 72);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("註銷帳號");
		btnNewButton_2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否確定註銷", "註銷確認", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					new UserDaoImpl().deleteUser(user.getEmail());
					JOptionPane.showMessageDialog(null, "帳號已註銷，請重新註冊", "註銷成功", JOptionPane.WARNING_MESSAGE);
					new LoginUI().setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_2.setBounds(296, 19, 85, 23);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("登出");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否確定登出", "登出確認", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "登出成功，請重新登入", "登出成功", JOptionPane.WARNING_MESSAGE);
					new LoginUI().setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_2_1.setBounds(296, 68, 85, 23);
		panel_1.add(btnNewButton_2_1);
	}
}
