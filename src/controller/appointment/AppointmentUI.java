package controller.appointment;

import java.awt.EventQueue;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dao.impl.AppointmentDaoImpl;
import model.Appointment;
import model.User;
import util.ClockTool;
import util.Tool;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.Color;

public class AppointmentUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static User user = (User) Tool.read("user.txt");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentUI frame = new AppointmentUI();
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
	public AppointmentUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("診所預約");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 414, 51);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("診所預約");
		lblNewLabel_2.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_2.setBounds(155, 10, 83, 31);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("0000-00-00 00:00:00");
		lblNewLabel_3.setBounds(285, 26, 119, 15);
		panel.add(lblNewLabel_3);
		new Thread(new ClockTool.ClockRunnable(lblNewLabel_3)).start();

		JLabel lblNewLabel_4 = new JLabel("歡迎" + user.getName());
		lblNewLabel_4.setBounds(10, 26, 105, 15);
		panel.add(lblNewLabel_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 71, 231, 180);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("科別");
		lblNewLabel.setBounds(23, 14, 36, 15);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("醫生");
		lblNewLabel_1.setBounds(23, 43, 36, 15);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("日期");
		lblNewLabel_1_1.setBounds(23, 78, 36, 15);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("時段");
		lblNewLabel_1_2.setBounds(23, 109, 36, 15);
		panel_1.add(lblNewLabel_1_2);

		JComboBox specialtyComboBox = new JComboBox();
		specialtyComboBox.setBounds(64, 10, 128, 23);
		panel_1.add(specialtyComboBox);
		specialtyComboBox.addItem("請選擇");

		JComboBox doctorComboBox = new JComboBox();
		doctorComboBox.setBounds(64, 39, 128, 23);
		panel_1.add(doctorComboBox);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setMinSelectableDate(new Date());
		dateChooser.setBounds(64, 72, 128, 23);
		panel_1.add(dateChooser);

		JComboBox timesComboBox = new JComboBox();
		timesComboBox.setBounds(64, 105, 128, 23);
		panel_1.add(timesComboBox);

		List<String> specialties = new AppointmentDaoImpl().selectAllSpecialty();
		for (String specialty : specialties) {
			specialtyComboBox.addItem(specialty);
		}

		specialtyComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SpecialtyComboBox = (String) specialtyComboBox.getSelectedItem();
				List<String> doctors = new AppointmentDaoImpl().selectDoctorsBySpecialty(SpecialtyComboBox);
				doctorComboBox.removeAllItems();
				for (String doctor : doctors) {
					doctorComboBox.addItem(doctor);
				}
			}

		});

		doctorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String DoctorComboBox = (String) doctorComboBox.getSelectedItem();
				List<String> times = new AppointmentDaoImpl().selectTimesByDoctor(DoctorComboBox);
				timesComboBox.removeAllItems();
				for (String time : times) {
					timesComboBox.addItem(time);
				}
			}
		});
		/********** button ***********/
		JButton btnNewButton = new JButton("預約");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String DoctorComboBox = (String) doctorComboBox.getSelectedItem();
				String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
				String TimesComboBox = (String) timesComboBox.getSelectedItem();
				String appointmentTime = formattedDate + " " + TimesComboBox;
				List<Appointment> appointment = new AppointmentDaoImpl().selectAllAppointment();
				boolean isDuplicate = false;

				for (Appointment a : appointment) {
					if ((a.getAppointmentTime().equals(appointmentTime)) && (a.getDoctor().equals(DoctorComboBox))) {
						if (a.getName().equals(user.getName())) {
							JOptionPane.showMessageDialog(null, "您已預約該時間", "重複預約", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "該時段已被預約，請選擇其他時間", "預約失敗", JOptionPane.WARNING_MESSAGE);
						}
						isDuplicate = true;
						break;
					}
				}
				if (!isDuplicate) {
					int result = JOptionPane.showConfirmDialog(null, "是否確定預約", "預約確認", JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.YES_OPTION) {
						new AppointmentDaoImpl().creatAppointment(new Appointment(user.getName(),
								(String) specialtyComboBox.getSelectedItem(), DoctorComboBox, appointmentTime));
						JOptionPane.showMessageDialog(null, "預約成功", "預約成功", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(74, 147, 87, 23);
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(272, 71, 152, 180);
		contentPane.add(panel_2);
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setLayout(null);

		JButton btnNewButton_1 = new JButton("預約管理");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AppointmentManageUI().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(32, 10, 95, 74);
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("帳號管理");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UserManageUI().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(32, 96, 95, 74);
		panel_2.add(btnNewButton_1_1);
	}
}
