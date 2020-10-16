package com.ssafy.happyhouse.model;

public class ClinicDto {

	private String city;
	private String gugun;
	private String hospitalName;
	private String weekDayOp;
	private String satOp;
	private String sunOp;
	private String tel;
	
	public ClinicDto() {

	}

	public ClinicDto(String city, String gugun, String hospitalName, String weekDayOp, String satOp, String sunOp,
			String tel) {

		this.city = city;
		this.gugun = gugun;
		this.hospitalName = hospitalName;
		this.weekDayOp = weekDayOp;
		this.satOp = satOp;
		this.sunOp = sunOp;
		this.tel = tel;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getWeekDayOp() {
		return weekDayOp;
	}

	public void setWeekDayOp(String weekDayOp) {
		this.weekDayOp = weekDayOp;
	}

	public String getSatOp() {
		return satOp;
	}

	public void setSatOp(String satOp) {
		this.satOp = satOp;
	}

	public String getSunOp() {
		return sunOp;
	}

	public void setSunOp(String sunOp) {
		this.sunOp = sunOp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "ClinicDto [city=" + city + ", gugun=" + gugun + ", hospitalName=" + hospitalName + ", weekDayOp="
				+ weekDayOp + ", satOp=" + satOp + ", sunOp=" + sunOp + ", tel=" + tel + "]";
	}
	
	
	
}
