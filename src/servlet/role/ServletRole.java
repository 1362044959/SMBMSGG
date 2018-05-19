package servlet.role;

import java.util.List;

import javax.annotation.Resource;

import entity.Role;
import service.role.RoleService;

public class ServletRole {

	@Resource
	private RoleService roleService;
	
	public List<Role> RoleList(){
		List<Role> list=null;
		try {
			list= roleService.getRoleList();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return list;
	}
	
}
