package com.hms.usersystem.models;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document(collection="users")

public class Receptionist {
	
	@Id
	private String id;
	private String email;
	private String username;
	private String password;
	private String address;
	private String phone;
	private int age;
	private int salary;
	private String role;
	
	
	public Receptionist() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Receptionist(String id, String email, String username, String password, String address, String phone,
			int age, int salary, String role) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.age = age;
		this.salary = salary;
		this.role = role;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	@Bean
	public String passwordEncoder() {
		return new BCryptPasswordEncoder().encode(username);
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = passwordEncoder();
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
//	private String id;
//	private String name;
//	private String email;
//	private String gender;
//	private String phone;
//	private String employeeCode;
//	private String address;
//	private String password;
//	private int salary;
//	private int age;
//	private String role;
	
	
	
	
	
}