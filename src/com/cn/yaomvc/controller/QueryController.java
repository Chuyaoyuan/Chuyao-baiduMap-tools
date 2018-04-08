package com.cn.yaomvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.cn.yaomvc.pojo.User;

import net.sf.json.JSONObject;

@Controller
public class QueryController {
	@ResponseBody
	@RequestMapping("/queryaddress")
	public String queryaddress(HttpServletRequest request,Model model, HttpSession session) throws UnsupportedEncodingException{
		JSONObject obj =  new JSONObject();
		String address = "";
		if (request.getParameter("address") != null) {
			address=request.getParameter("address");
		}
		String diqu = "";
		if (request.getParameter("diqu") != null) {
			diqu=request.getParameter("diqu");
		}
		System.out.println("diqu:"+diqu);
		System.out.println("address:"+address);
		String sss = huoqu_lng_lat_info(address,diqu);
		if(sss!=null) {
			obj.put("status", 1);
			String poi_x = sss.substring(0,sss.indexOf(","));
			String  poi_y = sss.substring(sss.indexOf(",")+1,sss.length());
			obj.put("x", poi_x);
			obj.put("y", poi_y);
		}else{
			obj.put("status", 0);
		}
		System.out.println(obj.toString());
		return obj.toString();	
	}
	
	@ResponseBody
	@RequestMapping("/querylng_lat")
	public String querylng_lat(HttpServletRequest request,Model model, HttpSession session) throws UnsupportedEncodingException{
		JSONObject obj =  new JSONObject();
		String lonlat = "";
		if (request.getParameter("lonlat") != null&&request.getParameter("lonlat") .contains(",")) {
			lonlat=request.getParameter("lonlat");
			String poi_x = lonlat.substring(0,lonlat.indexOf(","));
			String  poi_y = lonlat.substring(lonlat.indexOf(",")+1,lonlat.length());
		System.out.println("lonlat:"+lonlat);
		String sss = huoqu_address_info(poi_x,poi_y);
		if(sss!=null) {
			obj.put("status", 1);
			obj.put("address", sss);
		}else {
			obj.put("status", 0);
			obj.put("msg", "未返回结果");
			System.out.println();
		}
		}else{
			obj.put("status", 0);
			obj.put("msg", "您的输入有误");
		}
		System.out.println(obj.toString());
		return obj.toString();	
	}
	
	
	// 根据经纬度获取地址信息
	public static String huoqu_address_info(String x,String y)
			throws UnsupportedEncodingException {
		String tempHtml  = "";
		HttpClient httpClient = new HttpClient();

		String city = 	URLEncoder.encode("中山市", "UTF-8");
		String url = "http://api.map.baidu.com/geocoder/v2/?location="+y+","+x+"&output=json&pois=1&ak=";
		GetMethod getMethod = new GetMethod(url);
		getMethod.addRequestHeader("Host", "api.map.baidu.com");
		getMethod.addRequestHeader("User-Agent",
				"Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; GT-P5210 Build/JDQ39E) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
		getMethod.addRequestHeader("Cookie",
				"BAIDUID=C173C9A4E9456391EDE0AEB59740519C:FG=1; BIDUPSID=C173C9A4E9456391EDE0AEB59740519C; PSTM=1477355786; __cfduid=d35a08f2686d5942039f203d6b4a18cb91477379572; BDUSS=1hjU01YdnJwZnU2S1NrVHY2QTJtNWN1Tm1GVVdVb1FnSFdFWmU5TENiSGp3VUJZSVFBQUFBJCQAAAAAAAAAAAEAAAD1UAcCYXMyNTgzNjk4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOM0GVjjNBlYd; BDSFRCVID=gX4sJeC62CLgQ-oiKA50uRMFIgQ3T-3TH6aooul1qPpt_Z67_VXVEG0Pfx8g0Ku-hILjogKKQmOTHArP; H_BDCLCKID_SF=JbPO_II5JIvbfP0kMRosbDCShGRMXT3eWDTm_DonBn3GKJ7YyULhDbLwXtjAKh_eQPDe-pPKKR7ofJ4lKq325h4ljGJdLqvp3mkjbnQyfn02OPKzMMrdM44syPRI2xRnWg6HbIcJ-J8XMD8mjT5P; Hm_lvt_5650c092812f8659cdfe23eeb42024ef=1478509496,1478658907; Hm_lpvt_5650c092812f8659cdfe23eeb42024ef=1478658915; H_PS_PSSID=1454_17711_18241_21116_21455_21395_21378_21193_20718; locale=zh");
		getMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		getMethod.addRequestHeader("Connection", "Keep-Alive");
		getMethod.addRequestHeader("Accept-Encoding", "gzip, deflate, sdch");
		getMethod.addRequestHeader("Accept-Language", "zh-CN, en-US");
		getMethod.addRequestHeader("Accept-Charset", "utf-8, iso-8859-1, utf-16, *;q=0.7");

		httpClient.getHttpConnectionManager().getParams().setSoTimeout(20000);
		// System.out.println("20秒没反应关闭");
		StringBuffer sb = new StringBuffer();
		try {
			// System.out.println(url);
			int stat = httpClient.executeMethod(getMethod);
			// System.err.println(stat);
			BufferedReader reader = null;
			try {
				GZIPInputStream gzipin = new GZIPInputStream(getMethod.getResponseBodyAsStream());
				reader = new BufferedReader(new InputStreamReader(gzipin, "utf-8"));
			} catch (Exception e) {
				reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(), "utf-8"));
			}
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line.replace("&nbsp", "").replace(";", ""));
				sb.append("\n");
			}
			 tempHtml = sb.toString();
			System.out.println(tempHtml);
			// status Int 本次API访问状态，如果成功返回0，如果失败返回其他数字。
			// message string 对API访问状态值的英文说明，如果成功返回"ok"，并返回结果字段，如果失败返回错误说明。
			// total int
			// 检索总数，用户请求中设置了page_num字段才会出现total字段。当检索总数值大于760时，多次刷新同一请求得到total的值可能稍有不同，属于正常情况。
			if(!tempHtml.replace(" ", "").substring(0, 1).equals("{")){
				 tempHtml = "{\""+ tempHtml  ;
				}
			 JSONObject data = JSONObject.fromObject(tempHtml);
			if (data.containsKey("status")) {
				if (data.getString("status").equals("0")) {
					JSONObject arrayresults = JSONObject.fromObject(data.get("result"));
					if (arrayresults!=null) {
					if(arrayresults.containsKey("formatted_address")) {
						String location = arrayresults.getString("formatted_address");
						System.out.println(location);
						return location;
					}else {
						return null;
					}
					} else {
						System.out.println("arrayresults的数量为0");
						return null;
					}
				} else if(data.getString("status").equals("302")) {
					System.out.println(tempHtml);
					System.out.println("api访问超限了！");
					return null;
				} else {
					System.out.println(tempHtml);
					System.out.println("status不为0，API错误！退出当前程序");
				}

			} else {
				System.out.println(tempHtml);
				System.out.println("无数据返回");
				return null;
			}
		} catch (HttpException e) {
			System.out.println(tempHtml);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(tempHtml);
			e.printStackTrace();
		}
		System.out.println(tempHtml);
		return "6";
	}
	
	// 根据地址获取任务经纬度信息
	public static String huoqu_lng_lat_info(String address,String diqu)
			throws UnsupportedEncodingException {
		String tempHtml  = "";
		HttpClient httpClient = new HttpClient();
		String addressss = address;
		
		address = URLEncoder.encode(address, "UTF-8");
		String city = 	URLEncoder.encode("中山市", "UTF-8");
		String url = "";
		
		if(diqu.equals("guonei")) {
			url ="http://api.map.baidu.com/?qt=s&c=167&wd="+address+"&rn=10&ie=utf-8&oue=1&fromproduct=jsapi&res=api&ak=";
		}else {
			 url = "http://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=after_baidu&pcevaname=pc4.1&qt=s&da_src=searchBox.button&wd="+address+"&c=9002&src=0&wd2=&pn=0&sug=0&l=19";
		}
		System.out.println(url);
		GetMethod getMethod = new GetMethod(url);
		getMethod.addRequestHeader("Host", "map.baidu.com");
		getMethod.addRequestHeader("User-Agent",
				"Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; GT-P5210 Build/JDQ39E) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
		getMethod.addRequestHeader("Cookie",
				"BAIDUID=4C43F663754DC51DD3962AFDFFA54794:FG=1; BIDUPSID=4C43F663754DC51DD3962AFDFFA54794; PSTM=1513392667; BDUSS=ngzU0ZWVVI0dWVvZWxDU21HcU9sc2tvQ2xpNTdubUJFLVoxd2ZZTXMxNFdSbUJhQUFBQUFBJCQAAAAAAAAAAAEAAAD1UAcCYXMyNTgzNjk4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABa5OFoWuThaW; H_PS_PSSID=1431_21116_20929; validate=57017; MCITY=-9019%3A167%3A; detail4C43F663754DC51DD3962AFDFFA54794=yes; PSINO=1; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; M_LG_UID=34033909; M_LG_SALT=5d1bc4ed52e655f53df7f0580779d6a1");
		getMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		getMethod.addRequestHeader("Connection", "Keep-Alive");
		getMethod.addRequestHeader("Accept-Encoding", "gzip, deflate, br");
		getMethod.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.9");
		getMethod.addRequestHeader("Accept-Charset", "utf-8, iso-8859-1, utf-16, *;q=0.7");

		httpClient.getHttpConnectionManager().getParams().setSoTimeout(20000);
		// System.out.println("20秒没反应关闭");
		StringBuffer sb = new StringBuffer();
		try {
			// System.out.println(url);
			int stat = httpClient.executeMethod(getMethod);
			// System.err.println(stat);
			BufferedReader reader = null;
			try {
				GZIPInputStream gzipin = new GZIPInputStream(getMethod.getResponseBodyAsStream());
				reader = new BufferedReader(new InputStreamReader(gzipin, "utf-8"));
			} catch (Exception e) {
				reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(), "utf-8"));
			}
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line.replace("&nbsp", "").replace(";", ""));
				sb.append("\n");
			}
			 tempHtml = sb.toString();
			System.out.println(tempHtml);
			
			if(!tempHtml.replace(" ", "").substring(0, 1).equals("{")){
				 tempHtml = "{\""+ tempHtml  ;
				}
			 JSONObject data = JSONObject.fromObject(tempHtml);
			if (data.containsKey("content")) {
			net.sf.json.JSONArray arrayresults = data.getJSONArray("content");
					JSONObject arrayresultone = JSONObject.fromObject(arrayresults.get(0));
					if (arrayresultone!=null) {
						if(!arrayresultone.containsKey("x")&&arrayresultone.containsKey("code")) {
							//zhixing 
							return huoqu_lng_lat_infoCode(address,arrayresultone.getString("code"));
						}else {
							String x = "";// lat float 纬度值poi经纬度坐标
							String y = "";// lng float 经度值poi经纬度坐标
							x = arrayresultone.getString("x");
							y = arrayresultone.getString("y");
							x= x.substring(0, x.length()-2)+"." + x.substring(x.length()-2, x.length());
							y= y.substring(0, y.length()-2)+"." + y.substring(y.length()-2, y.length());
							 System.out.println("poi_lng:"+x);
							 System.out.println("precise:"+y);
						
						return x+","+y;
						}
						
						
					} else {
						System.out.println("arrayresultone为null");
						return null;
					}
					} else {
						System.out.println("arrayresults的数量为0");
						return null;
					}
		} catch (HttpException e) {
			System.out.println(tempHtml);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(tempHtml);
			e.printStackTrace();
		}
		return null;
	}
	
	// 根据地址获取任务经纬度信息
	public static String huoqu_lng_lat_infoCode(String address,String code)
			throws UnsupportedEncodingException {
		String tempHtml  = "";
		HttpClient httpClient = new HttpClient();
		String addressss = address;
		
		address = URLEncoder.encode(address, "UTF-8");
		String city = 	URLEncoder.encode("中山市", "UTF-8");
		String url = "";
		 url =" http://api.map.baidu.com/?qt=s&c="+code+"&wd="+address+"&rn=10&ie=utf-8&oue=1&fromproduct=jsapi&res=api&ak=";
		
		
		System.out.println(url);
		GetMethod getMethod = new GetMethod(url);
		getMethod.addRequestHeader("Host", "map.baidu.com");
		getMethod.addRequestHeader("User-Agent",
				"Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; GT-P5210 Build/JDQ39E) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
		getMethod.addRequestHeader("Cookie",
				"BAIDUID=4C43F663754DC51DD3962AFDFFA54794:FG=1; BIDUPSID=4C43F663754DC51DD3962AFDFFA54794; PSTM=1513392667; BDUSS=ngzU0ZWVVI0dWVvZWxDU21HcU9sc2tvQ2xpNTdubUJFLVoxd2ZZTXMxNFdSbUJhQUFBQUFBJCQAAAAAAAAAAAEAAAD1UAcCYXMyNTgzNjk4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABa5OFoWuThaW; H_PS_PSSID=1431_21116_20929; validate=57017; MCITY=-9019%3A167%3A; detail4C43F663754DC51DD3962AFDFFA54794=yes; PSINO=1; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; M_LG_UID=34033909; M_LG_SALT=5d1bc4ed52e655f53df7f0580779d6a1");
		getMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		getMethod.addRequestHeader("Connection", "Keep-Alive");
		getMethod.addRequestHeader("Accept-Encoding", "gzip, deflate, br");
		getMethod.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.9");
		getMethod.addRequestHeader("Accept-Charset", "utf-8, iso-8859-1, utf-16, *;q=0.7");

		httpClient.getHttpConnectionManager().getParams().setSoTimeout(20000);
		// System.out.println("20秒没反应关闭");
		StringBuffer sb = new StringBuffer();
		try {
			// System.out.println(url);
			int stat = httpClient.executeMethod(getMethod);
			// System.err.println(stat);
			BufferedReader reader = null;
			try {
				GZIPInputStream gzipin = new GZIPInputStream(getMethod.getResponseBodyAsStream());
				reader = new BufferedReader(new InputStreamReader(gzipin, "utf-8"));
			} catch (Exception e) {
				reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(), "utf-8"));
			}
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line.replace("&nbsp", "").replace(";", ""));
				sb.append("\n");
			}
			 tempHtml = sb.toString();
			System.out.println(tempHtml);
			
			if(!tempHtml.replace(" ", "").substring(0, 1).equals("{")){
				 tempHtml = "{\""+ tempHtml  ;
				}
			 JSONObject data = JSONObject.fromObject(tempHtml);
			if (data.containsKey("content")) {
			net.sf.json.JSONArray arrayresults = data.getJSONArray("content");
					JSONObject arrayresultone = JSONObject.fromObject(arrayresults.get(0));
					if (arrayresultone!=null) {
					
							String x = "";// lat float 纬度值poi经纬度坐标
							String y = "";// lng float 经度值poi经纬度坐标
							x = arrayresultone.getString("x");
							y = arrayresultone.getString("y");
							x= x.substring(0, x.length()-2)+"." + x.substring(x.length()-2, x.length());
							y= y.substring(0, y.length()-2)+"." + y.substring(y.length()-2, y.length());
							 System.out.println("poi_lng:"+x);
							 System.out.println("precise:"+y);
						
						return x+","+y;
			
					} else {
						System.out.println("arrayresultone为null");
						return null;
					}
					} else {
						System.out.println("arrayresults的数量为0");
						return null;
					}
		} catch (HttpException e) {
			System.out.println(tempHtml);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(tempHtml);
			e.printStackTrace();
		}
		return null;
	}
}
