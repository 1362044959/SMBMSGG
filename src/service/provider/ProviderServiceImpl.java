package service.provider;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.provider.ProviderMapper;
import entity.Provider;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService{

	@Resource
	private ProviderMapper providerMapper;
	
	@Override
	public boolean add(Provider provider) throws Exception {
		// TODO �Զ����ɵķ������
		if(providerMapper.add(provider)>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public List<Provider> getProviderList(String proName, String proCode) throws Exception {
		// TODO �Զ����ɵķ������
		return providerMapper.getProviderList(proName, proCode);
	}

	@Override
	public int deleteProviderById(String delId) throws Exception {
		// TODO �Զ����ɵķ������
		return providerMapper.deleteProviderById(delId);
	}

	@Override
	public Provider getProviderById(String id) throws Exception {
		// TODO �Զ����ɵķ������
		return providerMapper.getProviderById(id);
	}

	@Override
	public int modify(Provider provider) throws Exception {
		// TODO �Զ����ɵķ������
		return providerMapper.modify(provider);
	}

}
