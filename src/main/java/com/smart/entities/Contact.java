package com.smart.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Table(name="CONTACT")
public class Contact 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//system automatic generated id, we not need to put id
  private int cId;
  private String name;
  private String secondName;
  private String work;
  private String email;
  private String phone;
  private String image;
  @Column(length=500)
  private String description;
		  
   @ManyToOne  //Many contact belongs to one User
   @JsonIgnore  //json ignore for search bar 
   private User user;
		  
   
		public String getWork() {
	return work;
}
public void setWork(String work) {
	this.work = work;
}
		public int getcId() {
			return cId;
		}
		public void setcId(int cId) {
			this.cId = cId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSecondName() {
			return secondName;
		}
		public void setSecondName(String secondName) {
			this.secondName = secondName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		public User getUser() {
			return user;
		}
		
		public void setUser(User user) {
			this.user = user;
		}
		
		//for delete jitne contacts hai match kara rahe hai
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return this.cId==((Contact)obj).getcId();
		}
		
		
//		 
		  
   
  
  
  
}
