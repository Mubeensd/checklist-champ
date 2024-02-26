package dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import helper.AES;

@Entity
public class TodoUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String email;
private long number;
private String gender;
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
private String password;


@Override
public String toString() {
	return "TodoUser [id=" + id + ", name=" + name + ", email=" + email + ", number=" + number + ", gender=" + gender
			+ ", password=" + password + "]";
}
public long getNumber() {
	return number;
}
public void setNumber(long number) {
	this.number = number;
}
public int getId() {
	return id;
}
public void setId(int id) {
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
public String getPassword() {

	return AES.decrypt(password,"123");
}
public void setPassword(String password) {

this.password = AES.encrypt(password, "123");
}
}
