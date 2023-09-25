package com.shopping.model.bean;

public class Member {
	private String id;
	private String name;
	private String password;
	private String gender;
	private String birth; //날짜 형식
	private String marriage;
	private Integer salary ;
	private String address;
	private String manager;
	private Integer mpoint; //적립 포인트
	
	//취미는 차후 데이터 베이스에서 반영안함
	private String hobby;
	
	public Member() {
	}
	
	public Member(String id, String name, String password, String gender, String birth, String marriage, Integer salary,
			String address, String manager, String hobby) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.birth = birth;
		this.marriage = marriage;
		this.salary = salary;
		this.address = address;
		this.manager = manager;
		this.hobby = hobby;
	}

	
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", gender=" + gender + ", birth="
				+ birth + ", marriage=" + marriage + ", salary=" + salary + ", address=" + address + ", manager="
				+ manager + ", mpoint=" + mpoint + ", hobby=" + hobby + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Integer getMpoint() {
		return mpoint;
	}

	public void setMpoint(Integer mpoint) {
		this.mpoint = mpoint;
	}
	
	
	
	
	
}
