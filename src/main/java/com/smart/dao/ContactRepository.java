package com.smart.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart.entities.Contact;
import com.smart.entities.User;

public interface ContactRepository extends JpaRepository<Contact,Integer>
{
   //pagination wale implementaion method
	@Query("from Contact as c where c.user.id=:userId")
	//pagebaale ke pass current page hoga and contact per page hoga
	public Page<Contact> findContactsByUser(@Param("userId")int userid,Pageable pePageable); //it return list of contacts
    
	//search by keyword in searchbar
	public List<Contact> findByNameContainingAndUser(String name,User user);//jis contact me keywords wali filed mil jayegi to ye fetch kar dega
}
