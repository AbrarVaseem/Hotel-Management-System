package com.hms.usersystem.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="receptionist")

public class Receptionist {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String gender;
	private String phone;
	private String employeeCode;
	private String address;
	private String password;
	private int salary;
	private int age;
	private String role;
	
	public Receptionist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Receptionist(String id, String name, String email, String gender, String phone, String employeeCode,
			String address, String password, int salary, int age, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.phone = phone;
		this.employeeCode = employeeCode;
		this.address = address;
		this.password = password;
		this.salary = salary;
		this.age = age;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

	
}