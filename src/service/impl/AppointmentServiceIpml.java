package service.impl;

import java.util.List;

import javax.swing.JComboBox;

import dao.impl.AppointmentDaoImpl;
import model.Appointment;
import service.AppointmentService;

public class AppointmentServiceIpml implements AppointmentService {

	public static void main(String[] args) {
		//System.out.print(new AppointmentServiceIpml().addTimeComboBoxByDoctor("姜小果"));

	}

	private static AppointmentDaoImpl appointmentdaoimpl = new AppointmentDaoImpl();

	@Override
	public String findAppointmentByName(String name) {
		List<Appointment> appointment = appointmentdaoimpl.selectAppointmentByName(name);
		StringBuilder sb = new StringBuilder();

		for (Appointment a : appointment) {
			sb.append("編號：").append(a.getId()).append("   科別：").append(a.getSpecialty()).append("   醫生：")
					.append(a.getDoctor()).append("   預約時間：").append(a.getAppointmentTime()).append("   創建時間：")
					.append(a.getCreatTime()).append("\n");
		}
		return sb.toString();
	}

	@Override
	public void addIdComboBoxByName(String name, JComboBox<Integer> idComboBox) {
		List<Appointment> appointment = appointmentdaoimpl.selectAppointmentByName(name);

		for (Appointment a : appointment) {
			idComboBox.addItem(a.getId());
		}
	}

	@Override
	public void addSpecialtyComboBox(JComboBox<String> specialtyComboBox) {
		List<String> specialties = new AppointmentDaoImpl().selectAllSpecialty();

		for (String specialty : specialties) {
			specialtyComboBox.addItem(specialty);
		}

	}

	@Override
	public void addDoctorComboBoxBySpecialty(String specialty, JComboBox<String> doctorComboBox) {
		List<String> doctors = new AppointmentDaoImpl().selectDoctorsBySpecialty(specialty);
		
		for (String doctor : doctors) {
			doctorComboBox.addItem(doctor);
		}
		
	}

	@Override
	public void addTimeComboBoxByDoctor(String Doctor, JComboBox<String> timeComboBox) {
		List<String> times = new AppointmentDaoImpl().selectTimesByDoctor(Doctor);
		for (String time : times) {
			timeComboBox.addItem(time);
		}
		
	}

}
