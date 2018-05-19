package service.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.role.RoleMapper;
import entity.Role;
@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleMapper roleMapper;

	public List<Role> getRoleList(){
		
		return roleMapper.getRoleList();
	}

}
