package dao;

import java.util.List;

import model.Appointment;

public interface AppointmentDao {
	void creatAppointment(Appointment appointment);// 創建預約

	List<Appointment> selectAllAppointment();// 列出所有預約

	List<Appointment> selectAppointmentByName(String name);// 根據email找出該病患所有預約紀錄

	List<String> selectAllSpecialty();// 搜尋所有專業科

	List<String> selectDoctorsBySpecialty(String specialty);// 根據專業科找出醫生

	List<String> selectTimesByDoctor(String doctor);// 根據醫生找出時段

	void updateAppointment(Appointment appointment);// 修改預約

	void deleteAppointment(Integer id);// 刪除預約

}
