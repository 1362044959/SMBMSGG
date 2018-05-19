package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;


import entity.Role;
import entity.User;
import service.role.RoleService;
import service.role.RoleServiceImpl;
import service.user.UserService;
import tools.Constants;

@Controller
@RequestMapping("ajaxuser")
public class UserAjax {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@RequestMapping(value="ajax1",produces="application/json;charset=utf-8")
	@ResponseBody
	private Object getRoleList(Map map)throws ServletException, IOException {
		List<Role> roleList = null;
		roleList = roleService.getRoleList();
		//把roleList转换成json对象输出
		//response.setContentType("application/json");
		map.put("ku", roleList);
		return JSONArray.toJSONString(roleList);
	}

	/**
	 * 删除用户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="delUser",produces="application/json;charset=utf-8")
	@ResponseBody
	private Object delUser(String uid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer delId = 0;
		try{
			delId = Integer.parseInt(uid);
		}catch (Exception e) {
			// TODO: handle exception
			delId = 0;
		}
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(delId <= 0){
			resultMap.put("delResult", "notexist");
		}else{
			if(userService.deleteUserById(delId)){
				resultMap.put("delResult", "true");
			}else{
				resultMap.put("delResult", "false");
			}
		}
		return  JSONArray.toJSONString(resultMap);
	}
	/**
	 * 验证用户名是否存在
	 * @param userCode
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="userCodeExist",produces="application/json;charset=utf-8")
	@ResponseBody
	private Object userCodeExist(String userCode)
			throws ServletException, IOException {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isNullOrEmpty(userCode)){
			//userCode == null || userCode.equals("")
			resultMap.put("userCode", "exist");
		}else{
			User user = userService.selectUserCodeExist(userCode);
			if(null != user){
				resultMap.put("userCode","exist");
			}else{
				resultMap.put("userCode", "notexist");
			}
		}
		return  JSONArray.toJSONString(resultMap);
	}
	
	/**
	 * 返回日期
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="getRoleList",produces="application/json;charset=utf-8")
	@ResponseBody
	private Object getRoleList()throws ServletException, IOException {
		List<Role> roleList = null;
		roleList = roleService.getRoleList();
	    return  JSONArray.toJSONString(roleList);
	}

	/**
	 * 密码验证
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="getPwdByUserId",produces="application/json;charset=utf-8")
	@ResponseBody
	private Object getPwdByUserId(HttpServletRequest request)throws ServletException, IOException {
		Object o = request.getSession().getAttribute(Constants.USER_SESSION);
		String oldpassword = request.getParameter("oldpassword");
		Map<String, String> resultMap = new HashMap<String, String>();
		
		if(null == o ){//session过期
			resultMap.put("result", "sessionerror");
		}else if(StringUtils.isNullOrEmpty(oldpassword)){//旧密码输入为空
			resultMap.put("result", "error");
		}else{
			String sessionPwd = ((User)o).getUserPassword();
			if(oldpassword.equals(sessionPwd)){
				resultMap.put("result", "true");
			}else{//旧密码输入不正确
				resultMap.put("result", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
}
