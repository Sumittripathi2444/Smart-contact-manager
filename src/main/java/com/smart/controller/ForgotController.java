package com.smart.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.entities.User;
import com.smart.service.EmailService;
import com.smart.dao.*;
@Controller
public class ForgotController
{
	Random random=new Random(1000);
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
   //email id form open handler
	@RequestMapping("/forgot")
	public String openEmailForm()
	{
		return "forgot_email_form";
	}
	
	
	
	@PostMapping("/send-otp")
	public String sendOtp(@RequestParam("email") String email,HttpSession session)
	{
		System.out.println(email);
		
		//generating otp of 4 digit random number
		//Random random=new Random(1000); //min value 1000
		int otp=random.nextInt(9999); //bound is upper limit
		
		System.out.println("otp "+otp);
		
		
		
		//write code for sent otp to email
		
		String subject="OTP FRom SCM";
		String message="<h1> OTP= "+otp+"</h1>";
		String to=email;
		boolean flag =this.emailService.sendEmail(subject,message, to);
		
		if(flag)
		{
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			//return "change_password";
			return "verify_otp";
		}
		else
		{
			session.setAttribute("message", "check your email id!");
			return "forgot_email_form";
		}
		
	}
		
	//verify otp
			@PostMapping("/verify-otp")
			public String verifyOtp(@RequestParam("otp") int otp,HttpSession session)
			{
				int myOtp=(int)session.getAttribute("myotp");
				String email1=(String)session.getAttribute("email");
				if(myOtp==otp)
				{
					//password changed form
					User user=this.userRepository.getUserByUserName(email1);
					
					if(user==null)
					{
						//send error message to forgot_email_form
						session.setAttribute("message", "user does not exist with this email!");
						return "forgot_email_form";
					}
					else 
					{
					   //send change password form	
						return "password_change_form";
					}
					
				}
				else
				{
					session.setAttribute("message", "you have entered wrong otp!!");
					return "verify_otp";
				}
			}
			
			//change password
			@PostMapping("/change-password")
			public String changePassword(HttpSession session,@RequestParam("newpassword") String newpassword)
			{
				String email=(String)session.getAttribute("email"); //session se get
				User user=this.userRepository.getUserByUserName(email); //user nikal jayega
			  
				user.setPassword(this.bcrypt.encode(newpassword));
				this.userRepository.save(user);
				
				return "redirect:/signin?change=password changed successfully.";
			}
}
