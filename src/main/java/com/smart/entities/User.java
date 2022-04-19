package com.smart.entities;

import java.util.*;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name="USER")
public class User
{
	private static final FetchType fetchLAZY = null;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
   private int id;
	
	@NotBlank(message="Name field is required !!")
	@Size(min=2,max=20,message="min 2 and max 20 characters are allowed")
   private String name;
   @Column(unique = true)
   private String email;
   private String password;
   private String role;
   private boolean enabled;
   private String imageUrl;
   @Column(length=500) //5000 letter ke words honge about ka
   private String about;

   //orphan removal is:jab koyi bhi child apne parent se unlink ho jayegi usko remove kar dega
   //1 user have many contacts so OneToMany
   @OneToMany(orphanRemoval=true, cascade=CascadeType.ALL,mappedBy="user")
   private List<Contact> contacts=new ArrayList<>();
   
   
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
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
public String getAbout() {
	return about;
}
public void setAbout(String about) {
	this.about = about;
}
public List<Contact> getContacts() {
	return contacts;
}
public void setContacts(List<Contact> contacts) {
	this.contacts = contacts;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
			+ ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", about=" + about + ", contacts=" + contacts + "]";
}
   
   
   
}
