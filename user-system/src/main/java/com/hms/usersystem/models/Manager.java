package com.hms.usersystem.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="managers")
public class Manager {
	
	@Id
	private String id;
	private String email;
	private String name;
	private String password;
	private String address;
	private int age;
	private int salary;
	private String role;
	
	public Manager() {
		super();
	}

	public Manager(String id, String email, String name, String password, String address, int age, int salary,
			String role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
