package service;

import javax.swing.JComboBox;

public interface AppointmentService {
	
	String findAppointmentByName(String name);//透過名字找預約紀錄
	void addIdComboBoxByName(String name, JComboBox<Integer> idComboBox);//透過名字添加id選單
	void addSpecialtyComboBox(JComboBox<String> specialtyComboBox);//添加所有專業科
	void addDoctorComboBoxBySpecialty(String specialty, JComboBox<String> doctorComboBox);//透過專業添加醫生
	void addTimeComboBoxByDoctor(String Doctor, JComboBox<String> timeComboBox);//透過醫生添加時段
	

}
