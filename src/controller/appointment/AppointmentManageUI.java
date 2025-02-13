package controller.appointment;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.impl.AppointmentDaoImpl;
import model.Appointment;
import model.User;
import service.impl.AppointmentServiceIpml;
import util.Tool;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class AppointmentManageUI extends JFrame {

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
					AppointmentManageUI frame = new AppointmentManageUI();
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
	public AppointmentManageUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(user.getName() + "的預約管理");

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 587, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(user.getName() + "的預約管理");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel.setBounds(95, 10, 394, 25);
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("返回預約");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AppointmentUI().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(492, 12, 85, 23);
		panel.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 65, 587, 188);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("預約紀錄", null, panel_1, null);
		panel_1.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 10, 562, 111);
		textPane.setOpaque(false);
		textPane.setEditable(false);
		panel_1.add(textPane);

		textPane.setText(new AppointmentServiceIpml().findAppointmentByName(user.getName()));

		JButton print = new JButton("列印");
		print.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textPane.print();
				} catch (PrinterException ex) {
					ex.printStackTrace();
				}
			}
		});
		print.setBounds(487, 131, 85, 23);
		panel_1.add(print);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("修改預約", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2_1 = new JLabel("要修改的預約編號");
		lblNewLabel_2_1.setBounds(10, 36, 105, 15);
		panel_2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2 = new JLabel("科別");
		lblNewLabel_2.setBounds(198, 36, 36, 15);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("醫生");
		lblNewLabel_1_1.setBounds(198, 65, 36, 15);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("日期");
		lblNewLabel_1_1_1.setBounds(377, 33, 36, 15);
		panel_2.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("時段");
		lblNewLabel_1_2.setBounds(377, 65, 36, 15);
		panel_2.add(lblNewLabel_1_2);

		JComboBox idComboBox = new JComboBox();
		idComboBox.setBounds(115, 32, 73, 23);
		panel_2.add(idComboBox);
		new AppointmentServiceIpml().addIdComboBoxByName(user.getName(), idComboBox);

		JComboBox specialtyComboBox = new JComboBox();
		specialtyComboBox.setBounds(239, 32, 128, 23);
		panel_2.add(specialtyComboBox);

		JComboBox doctorComboBox = new JComboBox();
		doctorComboBox.setBounds(239, 61, 128, 23);
		panel_2.add(doctorComboBox);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setMinSelectableDate(new Date());
		dateChooser.setBounds(418, 28, 128, 23);
		panel_2.add(dateChooser);

		JComboBox timesComboBox = new JComboBox();
		timesComboBox.setBounds(418, 61, 128, 23);
		panel_2.add(timesComboBox);

		JButton btnNewButton_1 = new JButton("修改預約");
		btnNewButton_1.setBounds(444, 98, 95, 51);
		panel_2.add(btnNewButton_1);

		specialtyComboBox.removeAllItems();
		new AppointmentServiceIpml().addSpecialtyComboBox(specialtyComboBox);

		specialtyComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SpecialtyComboBox = (String) specialtyComboBox.getSelectedItem();
				doctorComboBox.removeAllItems();
				new AppointmentServiceIpml().addDoctorComboBoxBySpecialty(SpecialtyComboBox, doctorComboBox);
			}
		});

		doctorComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String DoctorComboBox = (String) doctorComboBox.getSelectedItem();
				timesComboBox.removeAllItems();
				new AppointmentServiceIpml().addTimeComboBoxByDoctor(DoctorComboBox, timesComboBox);
			}
		});

		btnNewButton_1.addMouseListener(new MouseAdapter() {
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
					int result = JOptionPane.showConfirmDialog(null, "是否確定修改", "修改確認", JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.YES_OPTION) {
						new AppointmentDaoImpl().updateAppointment(
								new Appointment((Integer) idComboBox.getSelectedItem(), user.getName(),
										(String) specialtyComboBox.getSelectedItem(), DoctorComboBox, appointmentTime));
						JOptionPane.showMessageDialog(null, "修改成功", "修改成功", JOptionPane.WARNING_MESSAGE);
						textPane.setText(new AppointmentServiceIpml().findAppointmentByName(user.getName()));
					}
				}
			}
		});
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("取消預約", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_2_1_1 = new JLabel("要取消預約的編號");
		lblNewLabel_2_1_1.setBounds(10, 27, 108, 15);
		panel_3.add(lblNewLabel_2_1_1);

		JComboBox idComboBox_1 = new JComboBox();
		idComboBox_1.setBounds(118, 23, 73, 23);
		panel_3.add(idComboBox_1);
		new AppointmentServiceIpml().addIdComboBoxByName(user.getName(), idComboBox_1);

		JButton btnNewButton_1_1 = new JButton("取消預約");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否確定取消", "取消確認", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					new AppointmentDaoImpl().deleteAppointment((Integer) idComboBox_1.getSelectedItem());
					List<Appointment> Appointment = new AppointmentDaoImpl().selectAppointmentByName(user.getName());
					idComboBox.removeAllItems();
					idComboBox_1.removeAllItems();
					for (Appointment a : Appointment) {
						idComboBox.addItem(a.getId());
						idComboBox_1.addItem(a.getId());
					}

					textPane.setText(new AppointmentServiceIpml().findAppointmentByName(user.getName()));

					JOptionPane.showMessageDialog(null, "取消成功", "取消成功", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_1_1.setBounds(61, 77, 95, 51);
		panel_3.add(btnNewButton_1_1);
	}
}
