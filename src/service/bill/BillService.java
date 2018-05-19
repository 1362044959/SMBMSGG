package service.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Bill;

public interface BillService {

	/**
	 * 增加订单
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public boolean add(Bill bill)throws Exception;


	/**
	 * 通过查询条件获取供应商列�?-模糊查询-getBillList
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public List<Bill> getBillList(Bill bill)throws Exception;
	
	/**
	 * 通过delId删除Bill
	 * @param connection
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public Boolean deleteBillById(@Param("delId")String delId)throws Exception; 
	
	
	/**
	 * 通过billId获取Bill
	 * @param connection
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bill getBillById(@Param("id")String id)throws Exception; 
	
	/**
	 * 修改订单信息
	 * @param connection
	 * @param bill
	 * @return
	 * @throws Exception
	 */
	public boolean modify(Bill bill)throws Exception;

	/**
	 * 根据供应商ID查询订单数量
	 * @param connection
	 * @param providerId
	 * @return
	 * @throws Exception
	 */
	public int getBillCountByProviderId(@Param("providerId")String providerId)throws Exception;

}
