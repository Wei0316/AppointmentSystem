package model;

public class Appointment {
	private Integer id;
	private String name;
	private String specialty;
	private String doctor;
	private String AppointmentTime;
	private String creatTime;

	public Appointment(Integer id, String name, String specialty, String doctor, String appointmentTime,
			String creatTime) {
		super();
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.doctor = doctor;
		AppointmentTime = appointmentTime;
		this.creatTime = creatTime;
	}

	public Appointment(Integer id, String name, String specialty, String doctor, String appointmentTime) {
		super();
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.doctor = doctor;
		AppointmentTime = appointmentTime;
	}

	public Appointment(String name, String specialty, String doctor, String appointmentTime) {
		super();
		this.name = name;
		this.specialty = specialty;
		this.doctor = doctor;
		AppointmentTime = appointmentTime;
	}

	public Appointment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getAppointmentTime() {
		return AppointmentTime;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public void setAppointmentTime(String appointmentTime) {
		AppointmentTime = appointmentTime;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

}
