package servlet.bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import entity.*;

import service.bill.BillService;
import service.provider.ProviderService;
@Controller
@RequestMapping("/bill")
public class billServlet {

	@Resource
	private BillService billService;
	@Resource
	private ProviderService providerService;
	
	//订单页面
	@RequestMapping("/billlist")
	private String query(String queryProductName, String queryProviderId,String queryIsPayment,Map map)throws Exception {
		List<Provider> providerList = providerService.getProviderList(null,null);
		map.put("providerList", providerList);
		
		Bill bill = new Bill();
		if(StringUtils.isNullOrEmpty(queryIsPayment)){
			bill.setIsPayment(0);
		}else{
			bill.setIsPayment(Integer.parseInt(queryIsPayment));
		}
		
		if(StringUtils.isNullOrEmpty(queryProviderId)){
			bill.setProviderId(0);
		}else{
			bill.setProviderId(Integer.parseInt(queryProviderId));
		}
		bill.setProductName(queryProductName);
		List<Bill> billList  = billService.getBillList(bill);
		map.put("billList", billList);
		map.put("queryProductName", queryProductName);
		map.put("queryProviderId", queryProviderId);
		map.put("queryIsPayment", queryIsPayment);
		return "billlist";
	}
	
	//修改订单页面
	@RequestMapping("/billmodify")
	public String getBillById(String billid, Map map,String method)throws Exception {
		Bill bill  = billService.getBillById(billid);
		map.put("bill", bill);
		if(method.equals("view")) {
			return "billview";
		}else {
			return "billmodify";
		}
	}
	@RequestMapping("/billadd")
	private String billadd() {
		return "billadd";
	}

	//修改订单
	@RequestMapping("/modify")
	private String modify(Bill bill)throws Exception {
		if(billService.modify(bill)){
			return "redirect:/bill/billlist";
		}else{
			return "billmodify";
		}
	}
	//添加订单
	@RequestMapping("/add")
	private String add(Bill bill)throws Exception {
		if(billService.add(bill)){
			return "redirect:/bill/billlist";
			
		}else{
			return "";
		}
	}
}
