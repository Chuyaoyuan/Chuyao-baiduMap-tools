package com.cn.yaomvc.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.yaomvc.pojo.User;

@Controller
public class DefaultController {
	@RequestMapping("/index_xy")
	public String index_xy(HttpServletRequest request,Model model, HttpSession session) throws UnsupportedEncodingException{
String canshu1 = "";
String canshu2 = "";
		if (request.getParameter("canshu1") != null) {
			canshu2=request.getParameter("canshu2");
			canshu1=request.getParameter("canshu1");
		}
		model.addAttribute("canshu1", canshu1);
		model.addAttribute("canshu2", canshu2);
		//model.addAttribute("username", user.getUsername()); 
		
		return "index";	
	}
	@RequestMapping("/index_xxxy")
	public String index_xxxy(HttpServletRequest request,Model model, HttpSession session) throws UnsupportedEncodingException{
String ggg = "";

		if (request.getParameter("ggg") != null) {
			ggg=request.getParameter("ggg");
			
		}
		model.addAttribute("ggg", ggg);

		
		return "indexxxy";	
	}
	
	public static void main(String[] args) {
		
		String ms = "123456";
		System.out.println();
		
		
	}
}
