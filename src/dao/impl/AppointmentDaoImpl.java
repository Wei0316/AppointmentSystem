package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AppointmentDao;
import model.Appointment;
import util.ClockTool;
import util.DbConnection;

public class AppointmentDaoImpl implements AppointmentDao{

	public static void main(String[] args) {
		//new AppointmentDaoImpl().creatAppointment(new Appointment("wEkk","外科","fee","13:24"));
		
		List<Appointment> appointment = new AppointmentDaoImpl().selectAppointmentByName("QwQ");
		for (Appointment a : appointment) {
			System.out.println("id："+a.getId()+"\t科別："+a.getSpecialty()+"\t醫生："+a.getDoctor()+"\t預約時間："+a.getAppointmentTime()+"\t創建時間："+a.getCreatTime());
		}
		
		/*List<String> times = new AppointmentDaoImpl().selectTimesByDoctor("陳信義");
		for(String s:times) {
			System.out.println(s);
		}*/
		
		//new AppointmentDaoImpl().updateAppointment(new Appointment(1, "QQ","內科", "李醫生", "2025-02-15 10:00", "2025-02-12 09:00"));
		
		new AppointmentDaoImpl().deleteAppointment(4);

	}
	
	private static Connection connection = DbConnection.getDb();
	
	@Override
	public void creatAppointment(Appointment appointment) {
		String SQL="insert into appointments(name,specialty,doctor,appointmentTime,creatTime) values(?,?,?,?,?)";
		
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1,appointment.getName());
			preparedstatement.setString(2,appointment.getSpecialty());
			preparedstatement.setString(3,appointment.getDoctor());
			preparedstatement.setString(4,appointment.getAppointmentTime());        		
			preparedstatement.setString(5,ClockTool.getTime());
			
			preparedstatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Appointment> selectAllAppointment() {
		String SQL="select * from appointments";
		List<Appointment> l = new ArrayList();
		
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while (resultset.next()) {
				Appointment appointment = new Appointment();
				appointment.setId(resultset.getInt("id"));
				appointment.setName(resultset.getString("name"));
				appointment.setSpecialty(resultset.getString("specialty"));
				appointment.setDoctor(resultset.getString("doctor"));
				appointment.setAppointmentTime(resultset.getString("appointmentTime"));
				appointment.setCreatTime(resultset.getString("creatTime"));

				l.add(appointment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public List<Appointment> selectAppointmentByName(String name) {
		String SQL="select * from appointments where name=?";
		List<Appointment> l = new ArrayList();
		
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1,name);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while (resultset.next()) {
				Appointment appointment = new Appointment();
				appointment.setId(resultset.getInt("id"));
				appointment.setName(resultset.getString("name"));
				appointment.setSpecialty(resultset.getString("specialty"));
				appointment.setDoctor(resultset.getString("doctor"));
				appointment.setAppointmentTime(resultset.getString("appointmentTime"));
				appointment.setCreatTime(resultset.getString("creatTime"));

				l.add(appointment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public List<String> selectAllSpecialty() {
		String SQL="select distinct specialty from doctors";
		List<String> specialties = new ArrayList<>();
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(SQL);
            ResultSet resultset = preparedstatement.executeQuery();
            while (resultset.next()) {
                specialties.add(resultset.getString("specialty"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialties;
	}
	
	
	
	@Override
	public List<String> selectDoctorsBySpecialty(String specialty) {
		String SQL="select distinct name from doctors where specialty=?";
		List<String> doctors = new ArrayList<>();
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(SQL);
            preparedstatement.setString(1,specialty);
            ResultSet resultset = preparedstatement.executeQuery();
            while (resultset.next()) {
                doctors.add(resultset.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
		
	}
	
	@Override
	public List<String> selectTimesByDoctor(String doctor) {
		String SQL="select distinct time from doctors where name=?";
		List<String> times = new ArrayList<>();
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(SQL);
            preparedstatement.setString(1,doctor);
            ResultSet resultset = preparedstatement.executeQuery();
            while (resultset.next()) {
            	times.add(resultset.getString("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return times;
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		String SQL="update appointments set name=?,specialty=?,doctor=?,appointmentTime=?,creatTime=? where id=?";
		
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setString(1,appointment.getName());
			preparedstatement.setString(2,appointment.getSpecialty());
			preparedstatement.setString(3,appointment.getDoctor());
			preparedstatement.setString(4,appointment.getAppointmentTime());
			preparedstatement.setString(5,ClockTool.getTime());
			preparedstatement.setInt(6,appointment.getId());
			preparedstatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAppointment(Integer id) {
		String SQL="delete from appointments where id=?";
		
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(SQL);
			preparedstatement.setInt(1,id);
			
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	


}
