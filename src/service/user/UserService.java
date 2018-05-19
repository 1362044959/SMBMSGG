package service.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.*;

public interface UserService {

	/**
	 * ÓÃ»§µÇÂ½
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception 
	 */
	public User getLoginUser(String userCode,String userPassword);
	
	public int getUserCount(String userName,int userRole);
	
	public List<User> getUserList(String userName,int userRole,int currentPageNo,int pageSize);
	
	public User selectUserCodeExist(String userCode);
	
	public Boolean modify(User user);

	public User getUserById(String id); 
	
	public Boolean deleteUserById(Integer delId);
	
	public Boolean add(User user);
	
	public boolean updatePwd(int id,String pwd);
	
	public User login(String userCode,String userPassword);
}
