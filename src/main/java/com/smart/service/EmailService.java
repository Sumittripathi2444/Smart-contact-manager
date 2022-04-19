package com.smart.service;

import java.util.Properties;
//import java.net.PasswordAuthentication;
import javax.mail.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Authenticator;

import org.springframework.stereotype.Service;

@Service
public class EmailService 
{
   public boolean sendEmail(String subject,String message,String to)
   {
	   boolean f=false;
	   String from="sumittripathi2444@gmail.com";
	   
	   //variable for gmail
	   String host="smtp.gmail.com";
	   
	   //get the system properties
	   Properties properties=System.getProperties();
	   System.out.println("Properties"+properties);
	   
	   //setting important information to properties object
	   
	   //host..set
	   properties.put("mail.smtp.host", host);
	   properties.put("mail.smtp.port", "465");
	   properties.put("mail.smtp.ssl.enable", "true");
	   properties.put("mail.smtp.auth","true");
	   
	   //step 1:to get the session object..
	   Session session=Session.getInstance(properties,new Authenticator()
			   {  
		          @Override
		          protected javax.mail.PasswordAuthentication getPasswordAuthentication()
		          {
		   return new PasswordAuthentication("sumittripathi2444@gmail.com","");
			   }
      });
	   session.setDebug(true);
	   
	   //step 2:compose the message [text,multi media]
	   MimeMessage m=new MimeMessage(session);
	   
	   try {
		   //from email
		   m.setFrom(from);
		   
		   //adding reciptent to message
		   m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		   
		   //adding subject to message
		   m.setSubject(subject);
		   
		   //adding text to message
		  // m.setText(message);
		   m.setContent(message,"text/html");
		   
		   //send
		   
		   //step 3: send the message using Transport class
		   Transport.send(m);
		   
		   System.out.println("Sent success..............");
		   f=true;
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return f;
	   
   }
   
    
    
}
