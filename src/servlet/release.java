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
	
	//��¼ҳ��
	@RequestMapping("/login")
	public String first() {
		return "/login";
	}
	//����û�ҳ��
	@RequestMapping("useradd")
	public String useradd() {
		return "useradd";
	}
	@RequestMapping("/pwdmodify")
	public String pwd() {
		return "/pwdmodify";
	}
	//�Ƴ�����ϵͳ
	@RequestMapping("/frame")
	public String denglu(HttpServletResponse response,HttpServletRequest request,HttpSession session){
		request.getSession().removeAttribute(Constants.USER_SESSION);
		return "redirect:/login";
	}
}
