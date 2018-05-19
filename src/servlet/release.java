package servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.User;
import service.user.UserService;
import tools.Constants;

@Controller
public class release {
	
	@Resource
	private UserService userService;
	
	//登录页面
	@RequestMapping("/login")
	public String first() {
		return "/login";
	}
	//添加用户页面
	@RequestMapping("useradd")
	public String useradd() {
		return "useradd";
	}
	@RequestMapping("/pwdmodify")
	public String pwd() {
		return "/pwdmodify";
	}
	//推出超市系统
	@RequestMapping("/frame")
	public String denglu(HttpServletResponse response,HttpServletRequest request,HttpSession session){
		request.getSession().removeAttribute(Constants.USER_SESSION);
		return "redirect:/login";
	}
}
