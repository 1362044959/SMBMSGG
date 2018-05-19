package servlet.bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.support.odps.udf.JSONArrayAdd;
import com.mysql.jdbc.StringUtils;


import entity.Bill;
import entity.Provider;
import service.bill.BillService;
import service.provider.ProviderService;



@Controller
@RequestMapping("/ajaxBill")
public class BillAjax {

	@Resource
	private BillService billService;

	@Resource
	
	ProviderService providerService;
	@ResponseBody
	@RequestMapping(value="ajax1",produces="application/json;charset=utf-8")
	private Object getProviderlist(HttpServletRequest request, HttpServletResponse response)throws Exception {
		List<Provider> providerList = providerService.getProviderList("","");
		return JSONArray.toJSONString(providerList);
	}
	

	//É¾³ý¶©µ¥
	@ResponseBody
	@RequestMapping(value="ajax2",produces="application/json;charset=utf-8")
	private Object delBill(String billid)throws Exception {

		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(!StringUtils.isNullOrEmpty(billid)){
			boolean flag = billService.deleteBillById(billid);
			if(flag){//É¾³ý³É¹¦
				resultMap.put("delResult", "true");
			}else{//É¾³ýÊ§°Ü
				resultMap.put("delResult", "false");
			}
		}else{
			resultMap.put("delResult", "notexit");
		}
		return JSONArray.toJSONString(resultMap);
	}
	
}
