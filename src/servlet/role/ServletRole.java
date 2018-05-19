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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return list;
	}
	
}
