package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import service.role.RoleServiceImpl;
import tools.PageSupport;
import entity.*;
import service.role.RoleService;
import service.user.UserService;
import tools.Constants;

@Controller
@RequestMapping("/user")
public class frameuser {

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	/**
	 * �û���¼
	 * @param userCode
	 * @param userPassword
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("frame")
	public String frame(String userCode,String userPassword,HttpServletRequest request,HttpSession session) throws Exception {
		User user =userService.getLoginUser(userCode, userPassword);
		if(user == null) {
			request.setAttribute("error", "�û��������벻��ȷ");
			return "login";
		}else {
			session.setAttribute(Constants.USER_SESSION,user);
			return "frame";
		}
	}
	
	/**
	 * �û�����
	 * @param queryname
	 * @param queryUserRole
	 * @param pageIndex
	 * @param map
	 * @return
	 */
	@RequestMapping("/userlist")
	public String UserServletAui(String queryname,String queryUserRole,String pageIndex,Map map) {
				//����
				int queryUserRoleAll = 0;
				if(queryUserRole != null && !queryUserRole.equals("")){
					queryUserRoleAll = Integer.parseInt(queryUserRole);
				}
				
				//����ҳ������
		    	int pageSize = Constants.pageSize;
		    	
		    	//��ǰҳ��
		    	int currentPageNo = 1;
			
		    	if(pageIndex != null){
		    		try{
		    			currentPageNo = Integer.valueOf(pageIndex);
		    		}catch(NumberFormatException e){
		    			return "error";
		    		}
		    	}	
		    	//����������	
		    	int totalCount	= userService.getUserCount(queryname,queryUserRoleAll);
		    	
		    	//��ҳ��
		    	PageSupport pages=new PageSupport();
		    	pages.setCurrentPageNo(currentPageNo);
		    	pages.setPageSize(pageSize);
		    	pages.setTotalCount(totalCount);
		   
		    	int totalPageCount = pages.getTotalPageCount();//��ȡ��ҳ��
		    	
		    	//������ҳ��βҳ
		    	if(currentPageNo < 1){
		    		currentPageNo = 1;
		    	}else if(currentPageNo > totalPageCount){
		    		currentPageNo = totalPageCount;
		    	}
		    	List<User> userList = userService.getUserList(queryname,queryUserRoleAll,((currentPageNo-1)*pageSize), pageSize);//����
				List<Role> roleList = roleService.getRoleList();//����
				map.put("userList", userList);
				map.put("roleList", roleList);
				map.put("queryUserName", queryname);
				map.put("queryUserRole", queryUserRoleAll);
				map.put("totalPageCount", totalPageCount);
				map.put("totalCount", totalCount);
				map.put("currentPageNo", currentPageNo);
				return "userlist";
	}
	
	/*
	 * ��ת���鿴���޸�ҳ��
	 */
	@RequestMapping(value="userview")
	private String getUserById(String uid,HttpServletRequest request,String method){
		User user = userService.getUserById(uid);
		request.setAttribute("user", user);
		if(method.equals("view")){
			return "userview";
		}else {
			return "usermodify";
		}
	}
	/**
	 * �޸��û���Ϣ
	 * @param uid
	 * @param userName
	 * @param gender
	 * @param birthday
	 * @param phone
	 * @param address
	 * @param userRole
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="modify", method=RequestMethod.POST)
	private String modify(String uid,String userName,String gender, String birthday,String phone,String address,String userRole,HttpServletRequest
			 request)
			throws ServletException, IOException {
		User user = new User();
		user.setId(Integer.valueOf(uid));
		user.setUserName(userName);
		user.setGender(Integer.valueOf(gender));
		try {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPhone(phone);
		user.setAddress(address);
		user.setUserRole(Integer.valueOf(userRole));
		user.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
		user.setModifyDate(new Date());
		if(userService.modify(user)){
			return "redirect:/user/userlist";
			/*response.sendRedirect(request.getContextPath()+"/jsp/user.do?method=query");*/
		}else{
			return "usermodify";
		}
	}
	/**
	 * ����û�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("add")
	private String add(User user)throws ServletException, IOException {
		if(userService.add(user)){
			return "redirect:/user/userlist";
			/*response.sendRedirect(request.getContextPath()+"/jsp/user.do?method=query");*/
		}else{
			return "useradd";
			/*request.getRequestDispatcher("useradd.jsp").forward(request, response);*/
		}
	}
	
	@RequestMapping("/updatePwd")
	private String updatePwd(HttpServletRequest request,String newpassword)throws ServletException, IOException {
		
		Object o = request.getSession().getAttribute(Constants.USER_SESSION);
		boolean flag = userService.updatePwd(((User)o).getId(),newpassword);
			if(flag){
				request.setAttribute(Constants.SYS_MESSAGE, "�޸�����ɹ�,���˳���ʹ�����������µ�¼��");
				request.getSession().removeAttribute(Constants.USER_SESSION);//sessionע��
			}else{
				request.setAttribute(Constants.SYS_MESSAGE, "�޸�����ʧ�ܣ�");
			}
			return "/pwdmodify";

	}
	
}
