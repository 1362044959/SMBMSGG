package service.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Provider;

public interface ProviderService {

	/**
	 * 增加供应�?
	 * @param connection
	 * @param provider
	 * @return
	 * @throws Exception
	 */
	public boolean add(Provider provider)throws Exception;


	/**
	 * 通过供应商名称�?�编码获取供应商列表-模糊查询-providerList
	 * @param connection
	 * @param proName
	 * @return
	 * @throws Exception
	 */
	public List<Provider> getProviderList(String proName,String proCode)throws Exception;
	
	/**
	 * 通过proId删除Provider
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public int deleteProviderById(@Param("delId")String delId)throws Exception; 
	
	
	/**
	 * 通过proId获取Provider
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Provider getProviderById(@Param("id")String id)throws Exception; 
	
	/**
	 * 修改用户信息
	 * @param connection
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int modify(Provider provider)throws Exception;
	
}
