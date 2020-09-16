package com.cn.yaomvc.controller;


import com.cn.yaomvc.pojo.User;
import com.cn.yaomvc.service.IUserService;
import com.cn.yaomvc.utils.JiamiMD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户Controller
 */
@Controller
public class UserLoginController {
	
	@Resource
	private IUserService userService;

	/**
	 * 验证登录
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
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

	/**
	 * 退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginout")
    public ModelAndView loginout(HttpSession session){
        session.invalidate();
        ModelAndView mv = new ModelAndView();
			mv.setViewName("index");
		return mv;
    }
}
