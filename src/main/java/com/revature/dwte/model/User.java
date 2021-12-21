package com.revature.dwte.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", insertable = false)
	private int userId;

	@Column(name = "first_name", nullable = false)
	private String first_name;

	@Column(name = "last_name", nullable = false)
	private String last_name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "phone_number", nullable = false, unique = true)
	private String phone_number;

	@Column(name = "role", nullable = false)
	private String role;

	public User() {
		super();
	}

	public User(String first_name, String last_name, String email, String password, String phone_number, String role) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.phone_number = phone_number;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, first_name, last_name, password, phone_number, role, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(first_name, other.first_name)
				&& Objects.equals(last_name, other.last_name) && Objects.equals(password, other.password)
				&& Objects.equals(phone_number, other.phone_number) && Objects.equals(role, other.role)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", password=" + password + ", phone_number=" + phone_number + ", role=" + role + "]";
	}

}
