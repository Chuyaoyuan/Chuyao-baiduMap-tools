package com.cn.yaomvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cn.yaomvc.utils.ReadExcelTest3;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
public class FileController {
	/*
	 * 创建renwu任务1111
	 */
	@ResponseBody
	@RequestMapping(value="/uploadfile",produces="text/html; charset=UTF-8")
	public String uploadfile1(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws IOException, ParseException, InterruptedException {
		String type = request.getParameter("type");
		String diqu = request.getParameter("diqu");
		System.out.println(diqu);
		  JSONArray ssss = new JSONArray();
		  List listHs = new ArrayList<>();
			JSONObject obj = new JSONObject();
		response.setContentType("text/html");
		// 设置响应所采用的编码方式
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		// 取得参数username的值
		String msg  = "";
		String filename  = "";
		if(type.equals("address")){
		String path1 = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path1
				+ "/";
		java.text.SimpleDateFormat formatter_f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date currentTime_f = new java.util.Date(); // 得到当前系统时间
		String new_date_f = formatter_f.format(currentTime_f); // 将日期时间格式化
		System.out.println(new_date_f);
		String pathRoot = request.getSession().getServletContext().getRealPath("");
		String path = "";
		List<String> listImagePath = new ArrayList<String>();
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 取出一个list的multipartfile
			List<MultipartFile> files = multipartRequest.getFiles("uploadFile");
			String photouuid = UUID.randomUUID().toString();
			for (MultipartFile mf : files) {
				System.out.println(mf);
				if (!mf.isEmpty()) {
					// 生成uuid作为文件名称
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					// 获得文件类型（可以判断如果不是图片，禁止上传）
					String contentType = mf.getContentType();
					System.out.println(contentType);
					if(contentType.equals("application/vnd.ms-excel")){
						System.out.println("文件格式正确");
					
					// 获得文件后缀名称
					String imageName = contentType.substring(contentType.indexOf("/") + 1);
					path = "content/" + uuid + ".xls";
					mf.transferTo(new File(pathRoot + "/" + path));
					listImagePath.add(path);
					// System.out.println(path);
					System.out.println(pathRoot + "/" + path);
					System.out.println(basePath + path);
					}else{
						System.out.println("文件格式不正确");
						msg = "文件格式不正确！";
					}
				} else {
					System.out.println("没有文件");
				}
			}
			System.out.println("进来了,处理文件");
			System.out.println("等待1秒");
			Thread.sleep(1000);
			System.out.println(listImagePath.size());
			//response.getWriter().print(files.size());
			if (listImagePath.size() > 0) {
				System.out.println("有文件，");
				System.out.println(pathRoot + "/" + path);
				File file = new File(pathRoot + "/" + path);

				ReadExcelTest3 excelReader = new ReadExcelTest3();
				
				InputStream is2 = new FileInputStream(pathRoot + "/" + path);
				
				 listHs = excelReader.readExcelContent(is2);
				if (listHs.size() > 0) {
			
					String flag = "0";
					for (int i = 0; i < listHs.size(); i++) {
						 JSONObject map = new JSONObject(); 
						String map1 = (String) listHs.get(i);
						String sss = new QueryController().huoqu_lng_lat_info(map1,diqu);
						map.put("adress",map1);
						if(sss==null) {
							sss = "";
						}
						map.put("xy",sss);
						ssss.add(map);
						}
					msg =ssss.toString();
					msg ="成功";
					filename = basePath + path;
					}else{
						msg ="无数据！";
					}
				} else {
					System.out.println("没有文件！");
					msg = "没有文件！";
					photouuid = "0";
				}
			} else {
				msg = "非法请求！";
				//response.getWriter().print("非法请求");
			}
		} else {
			msg = "submit，parame_bdc1参数不正确！";
			//response.getWriter().print("非法请求");
		}

		System.out.println(msg);
		if(msg.equals("成功")){
				//msg= msg+","+filename;
			obj.put("data", ssss);
			obj.put("status", 1);
		}else{
			msg="请检查你上传的文件内容以及格式，请查看正确后上传。";
			//obj.put("data", listHs);
			obj.put("status", 0);
			obj.put("msg", msg);
		}
		System.out.println(obj.toString());
		return obj.toString();
		}
	
}
