package JDBC05;

import java.sql.Date;

public class PersonDto {
	private String personnum;
	private String personname;
	private String phone;
	private Date birth;
	private Date enterdate;
	private int bpoint;
	private int age;
	private String gender;
	
	public String getPersonnum() {
		return personnum;
	}
	public void setPersonnum(String personnum) {
		this.personnum = personnum;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirth() {
		
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getBpoint() {
		return bpoint;
	}
	public void setBpoint(int bpoint) {
		this.bpoint = bpoint;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getEnterdate() {
		return enterdate;
	}
	public void setEnterdate(Date enterdate) {
		this.enterdate = enterdate;
	}
	
	
	
	
}
