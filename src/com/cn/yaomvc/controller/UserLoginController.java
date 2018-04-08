package com.cn.yaomvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cn.yaomvc.pojo.User;
import com.cn.yaomvc.service.IUserService;
import com.cn.yaomvc.utils.JiamiMD5;


@Controller
public class UserLoginController {
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			/*
			 * 获取统计日期前一天
			 */
			public static String getlosedateTJRQ() {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -1); // 得到前一天
				java.util.Date date = calendar.getTime();
				java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
				return df.format(date);

			}
			
	@Resource
	private IUserService userService;


	
	@RequestMapping("/checkLogin")
	public String toIndexLogin(HttpServletRequest request,Model model, HttpSession session){
	
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		System.out.println(password);
		if(username==null||password==null||"".equals(username)||"".equals(password)){
			System.err.println( "登录失败");
			 request.getSession().invalidate();
			model.addAttribute("msg", "登录失败");
			return "index";
		}else{
			System.err.println( "登录不失败");
		User user1 = new User();
		user1.setUsername(username);
		user1.setPassword(JiamiMD5.getMD5(password));  
		User user = this.userService.getUserselectBycheckUsername(user1);
		if(request.getSession().getAttribute("user")!=null){
			System.out.println("request.getSession().getAttribute(user)不为空");
			System.out.println(request.getSession().getAttribute("user").toString());
			user=(User) request.getSession().getAttribute("user");
		}
		if(user!=null){
		System.out.println(user.getUsername());
		System.out.println(user.getStats());
		model.addAttribute("user", user);
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("username", user.getUsername());
		return "maptools";
		}else{
			System.err.println( "user等于null");
			model.addAttribute("msg", "登录失败");
			return "index";
		}
	}
	}
	
	@RequestMapping("/loginout")
    public ModelAndView loginout(HttpSession session){
        session.invalidate();
        ModelAndView mv = new ModelAndView();
			mv.setViewName("index");
		return mv;
    }
}
