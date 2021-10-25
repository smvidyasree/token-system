package com.example.demo.token.file.Bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class UserBean {
private int id;
private String name;
private String fullname;
private String emailid;
private String desgination;
private String status_count;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFullname() {
	return fullname;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}
public String getDesgination() {
	return desgination;
}
public void setDesgination(String desgination) {
	this.desgination = desgination;
}
public String getStatus_count() {
	return status_count;
}
public void setStatus_count(String status_count) {
	this.status_count = status_count;
}

}
