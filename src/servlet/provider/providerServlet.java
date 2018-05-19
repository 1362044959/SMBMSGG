package servlet.provider;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import entity.Provider;
import service.provider.ProviderService;


@Controller
@RequestMapping("/provider")
public class providerServlet {

	@Resource
	private ProviderService providerService;
	//供应商页面
	@RequestMapping("/providerlist")
	private String query(String queryProName,String queryProCode,Map map)throws Exception {
		List<Provider> providerList = new ArrayList<Provider>();
		providerList = providerService.getProviderList(queryProName,queryProCode);
		map.put("providerList",providerList );
		map.put("queryProName",queryProName );
		return "providerlist";
	}
	
	//详情信息和修改页面
	@RequestMapping("/providerview")
	private String getProviderById(String proid,Map map,String method)throws Exception {
		Provider provider  = providerService.getProviderById(proid);
		map.put("provider", provider);
		if(method.equals("view")){
			return "providerview";
		}else {
			return "providermodify";
		}
	}
	//删除
	@ResponseBody
	@RequestMapping("/delProvider")
	private Object delProvider(String proid)throws Exception {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(!StringUtils.isNullOrEmpty(proid)){
			int flag = providerService.deleteProviderById(proid);
			if(flag == 0){
				resultMap.put("delResult", "true");
			}else if(flag == -1){
				resultMap.put("delResult", "false");
			}else if(flag > 0){
				resultMap.put("delResult", String.valueOf(flag));
			}
		}else{
			resultMap.put("delResult", "notexit");
		}
		return JSONArray.toJSONString(resultMap);
	}
	//添加
	@RequestMapping("/add")
	private String add(Provider provider) throws Exception {
		if(providerService.add(provider)){
			return "redirect:/provider/providerlist";
		}else{
			return "";
		}
	}
	
}
