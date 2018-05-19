package service.bill;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.bill.BillMapper;
import entity.Bill;
@Service("billService")
public class BillServiceImpl implements BillService {

	@Resource
	private BillMapper billMapper;
	
	@Override
	public boolean add(Bill bill) throws Exception {
		if(billMapper.add(bill)>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public List<Bill> getBillList(Bill bill) throws Exception {
		// TODO �Զ����ɵķ������
		return billMapper.getBillList(bill);
	}

	@Override
	public Boolean deleteBillById(String delId) throws Exception {
		// TODO �Զ����ɵķ������
		if(billMapper.deleteBillById(delId)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Bill getBillById(String id) throws Exception {
		// TODO �Զ����ɵķ������
		return billMapper.getBillById(id);
	}

	@Override
	public boolean modify(Bill bill) throws Exception {
		if(billMapper.modify(bill)>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public int getBillCountByProviderId(String providerId) throws Exception {
		// TODO �Զ����ɵķ������
		return billMapper.getBillCountByProviderId(providerId);
	}

}
