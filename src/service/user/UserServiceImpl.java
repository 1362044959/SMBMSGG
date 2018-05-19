package service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.user.UserMapper;
import entity.*;
@Service("userServicei")
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	public User getLoginUser(String userCode, String userPassword){
		return userMapper.getLoginUser(userCode, userPassword);
	}

	@Override
	public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) {
		
		return userMapper.getUserList(userName, userRole, currentPageNo, pageSize);
	}

	@Override
	public int getUserCount(String userName, int userRole) {
		
		return userMapper.getUserCount(userName, userRole);
	}

	@Override
	public User selectUserCodeExist(String userCode) {
		// TODO 自动生成的方法存根
		return userMapper.selectUserCodeExist(userCode);
	}

	@Override
	public User getUserById(String id) {
		
		return userMapper.getUserById(id);
	}

	@Override
	public Boolean modify(User user) {
		
		if(userMapper.modify(user)>0) {
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public Boolean deleteUserById(Integer delId) {
		
		if(userMapper.deleteUserById(delId)>0) {
			return true ;
		}else {
			return false;
		}
	}

	@Override
	public Boolean add(User user) {
		if(userMapper.add(user)>0) {
			return true ;
		}else {
			return false;
		}
	}

	@Override
	public boolean updatePwd(int id, String pwd) {
		// TODO 自动生成的方法存根
		if(userMapper.updatePwd(id, pwd)>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public User login(String userCode, String userPassword) {
		// TODO 自动生成的方法存根
		return null;
	}

}
