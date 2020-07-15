<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>地图经纬度查询</title>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link href="content/Content/bootstrap.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="content/fileinput/fileinput.css" />
 <link rel="stylesheet" href="content/dataTables.bootstrap.min.css" />
<script src="content/jquery-2.2.4.js"></script>
  <script src="content/Scripts/bootstrap.min.js"></script>
	<style type="text/css">
	body, html,#baidumap {
	width: 100%;
	height: 500px;
}
	</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=E4805d16520de693a3fe707cdc962045"></script>
   <script type="text/javascript" src="content/fileinput/fileinput.js"></script>
<script type="text/javascript" src="content/fileinput/fileinput_locale_zh.js"></script>
      <script type="text/javascript" charset="utf8" src="content/jquery.dataTables.js"></script>
</head>
<body >
<div class="container-fluid">
 <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right" style="margin-top: 10px;">
            <li role="presentation" ><a href="#">Hello：${user.realname }</a></li>
               <li role="presentation" ><a href="loginout">退出</a></li>
           
          </ul>
        </nav>
        <h3 class="text-muted">Map Tools</h3>
      </div>
 <div class="row">
  <div class="col-md-12">
	<table class="table table-bordered">
      <thead>
        <tr>
          <th>#</th>
          <th>地址查询</th>
          <th>经纬度查询</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">1</th>
          <td>
          <form class="form-inline">
			  <div class="form-group">
			    <label for="exampleInputName2">输入地址：</label>
			    <input type="text" class="form-control" id="address" placeholder="如未查到可尝试去掉门牌号" style="width:320px">
			  </div>
			  <button type="button" class="btn btn-default" onclick = "queryaddress('guonei')">国内地址查询</button>
			   <button type="button" class="btn btn-default" onclick = "queryaddress('guoji')">国际地址查询</button>
			</form>
		</td>
          <td>    
          <form class="form-inline">
			  <div class="form-group">
			    <label for="exampleInputName2">输入经纬度：</label>
			    <input type="text" class="form-control" id = "lonlat" id="exampleInputName2" placeholder="逗号区分" style="width:320px">
			  </div>
			  <button type="button" class="btn btn-default" onclick = "querylonlat()">经纬度查询</button>
			</form>
			</td>
        </tr>
        <tr>
          <th scope="row">2</th>
          <td>          <form class="form-inline">
			  <div class="form-group" action="uploadfile" enctype="multipart/form-data" method="post" id ="fromyy1">
			    <input type="hidden" name="type" value= "address"> 
			    <label for="exampleInputName2">地址批量文件上传：</label>
			         <input type="file" name="myfile" id="addressfile">  
			  </div>
			  <button type="button" class="btn btn-default" onclick = "queryListaddress('guonei')" id="addressqqgn">上传(国内)</button>
			   <button type="button" class="btn btn-default" onclick = "queryListaddress('guoji')" id="addressqqgj">上传(国际)</button>
			  
			  <button type="button" class="btn btn-default">查询(未完成)</button>
			</form></td>
          <td>          <form class="form-inline">
			  <div class="form-group" action="uploadfile" enctype="multipart/form-data" method="post" id ="fromyy1">
			    <input type="hidden" name="type" value= "lonlat"> 
			    <label for="exampleInputName2">经纬度批量文件上传：</label>
			    <input type="text" class="form-control" id="exampleInputName2" placeholder="逗号区分，分号分隔">
			  </div>
			  <button type="button" class="btn btn-default">上传(未完成)</button>
			  <button type="button" class="btn btn-default">查询(未完成)</button>
			</form></td>
    
        </tr>
    
      </tbody>
    </table>
	<table class="table table-bordered">
	 <tbody>
        <tr>
         <td>结果展示： </td>
	      </tr>
        <tr>
         <td id="jieguo"> >>: </td>
	      </tr>
      </tbody>
	 </table>
	</div>

</div>
 <div class="row" id= "kuang" style="padding-right: 20px;padding-left: 20px;">

  <div id="baidumap"></div>
  

</div>
 <div class="row">
  <div class="col-md-6">页面结束</div>
  <div class="col-md-6">.</div>
</div>
</div>


<div id = "sss"></div>
<script>
function queryListaddress(diqu){
	if(diqu=='guonei'){
			$("#addressqqgn").append('<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>');
		}else if(diqu=='guoji'){
			$("#addressqqgj").append('<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>');
		}
//var form = new FormData(document.getElementById("fromyy1"));
var formData = new FormData();  
var uploadFile = $('#addressfile').get(0).files[0];  

formData.append("uploadFile",uploadFile);  
formData.append("type", "address");  
formData.append("diqu", diqu);  
console.log(uploadFile);  
//开始发送数据
$.ajax
({ //请求登录处理页
 url: "uploadfile", //修改用户
 type: "POST",
 data:formData,
 processData:false,
 contentType:false,
  error: function(request) {
      alert("出现错误，添加失败。");
 
  },
 success: function (data,status) { //登录成功后返回的数据
  //根据返回值进行状态显示
  data =eval("(" + data + ")");
  	//alert(data);
  	//alert(data.status);
  	if(data.status==1){
  		if(diqu=='guonei'){
  			$("#addressqqgn").html('上传(国内)');
  		}else if(diqu=='guoji'){
  			$("#addressqqgj").html('上传(国际)');
  		}
  	
  		$("#jieguo").html('');
  		map.clearOverlays();
  		 var datas =data.data;
  		// alert(datas.length); 
  		 for(var i = 0 ;i<datas.length;i++){
  			console.warn(datas[i]);
			var address =datas[i].adress; 
  			 var dd = datas[i].xy;
  			console.warn(dd);
  			 if(dd==null||dd.indexOf(",")<0||dd==''){
  				$("#jieguo").append((i+1)+'.未查询到经纬度----'+address); 
  	        	$("#jieguo").append('<br>')
  				 
  			 }else{
  	  			var poi_x = dd.substring(0,dd.indexOf(","));
  	        	var  poi_y = dd.substring(dd.indexOf(",")+1,dd.length);
  	        	var b = new BMap.MercatorProjection().pointToLngLat(new BMap.Pixel(poi_x, poi_y));
  	        	console.warn(b);
  	        	$("#jieguo").append((i+1)+'.'+b.lng+ ',' + b.lat  +'----'+address); 
  	        	$("#jieguo").append('<br>')
  	        	
  	        	var num = i+1;
  	        	 var point =new BMap.Point(b.lng, b.lat);     
  	           var  marker = new BMap.Marker(point);     
  	           var label = new BMap.Label(num, {
  	                    offset : new BMap.Size(5, 4)
  	                }); 
  	           label.setStyle({
  	               background:'none',color:'#fff',border:'none'//只要对label样式进行设置就可达到在标注图标上显示数字的效果
  	             });
  	           marker.setLabel(label);//显示地理名称 a 
  	           map.addOverlay(marker);   
  	         map.centerAndZoom(point, 5);
  	           //给标注点添加点击事件。使用立
//   	        	var point = new BMap.Point(b.lng, b.lat);
//   	        	map.centerAndZoom(point, 20);
//   	        	var marker = new BMap.Marker(point);  // 创建标注
//   	        	map.addOverlay(marker);               // 将标注添加到地图中
//   	        	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
  	  			// alert(dd.xy);  
  	        	
  			 }
  			
  		 }

  	}else{
  		alert(data.msg); 
  	}

 }
})
}

var map = new BMap.Map("baidumap", {enableMapClick:false});//关闭地图poi事件
//百度地图API功能
map.centerAndZoom("大连", 15);  
map.enableScrollWheelZoom(true);
function queryaddress(diqu){
	var address = 	$("[id='address']").val();
	console.warn(address);
	console.warn(diqu);
	$.ajax({
		 type:'post',      
		 url:'queryaddress?address='+address + '&diqu='+diqu,      
		 data:{},      
		cache:false,      
		 dataType:'json',      
		 success:function(data){  
			 var data = eval(data)
        	//alert(data);
	            if(data.status==1){
	            	//alert("1,you");
	            	var b = new BMap.MercatorProjection().pointToLngLat(new BMap.Pixel(data.x, data.y));
	            	var g = [ b.lng, b.lat ];
	            	$("#jieguo").html(b.lng+ ',' + b.lat ); 
	            	map.clearOverlays(); 
	            	var point = new BMap.Point(b.lng, b.lat);
	            	map.centerAndZoom(point, 20);
	            	var marker = new BMap.Marker(point);  // 创建标注
	            	map.addOverlay(marker);               // 将标注添加到地图中
	            	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	            }else if(data.status==0) {
	            	$("#jieguo").html(''); 
	            	map.clearOverlays(); 
	            	alert("为搜索到相关信息");
	                //alert(data.msg);
	            }
	        },
	        error:function (data) {
	        	 var data = eval(data)
	      		alert("error:"+data);
	        }
	}); 
}
function querylonlat(){
	var lonlat = 	$("[id='lonlat']").val();
	console.warn(lonlat);
	$.ajax({
		 type:'post',      
		 url:'querylng_lat?lonlat='+lonlat,      
		 data:{},      
		cache:false,      
		 dataType:'json',      
		 success:function(data){  
			 var data = eval(data)
        	//alert(data);
	            if(data.status==1){
	            	//alert("1,you");
	            	var poi_x = lonlat.substring(0,lonlat.indexOf(","));
	            	var  poi_y = lonlat.substring(lonlat.indexOf(",")+1,lonlat.length);
	            	$("#jieguo").html(data.address); 
	            	map.clearOverlays(); 
	            	
	            	var point = new BMap.Point(poi_x, poi_y);
	            	map.centerAndZoom(point, 20);
	            	var marker = new BMap.Marker(point);  // 创建标注
	            	map.addOverlay(marker);               // 将标注添加到地图中
	            	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	            }else if(data.status==0) {
	            	$("#jieguo").html(''); 
	            	map.clearOverlays(); 
	            	alert("为搜索到相关信息");
	                //alert(data.msg);
	            }
	        },
	        error:function (data) {
	        	 var data = eval(data)
	      		alert("error:"+data);
	        }
	}); 
}

	function GetAddressFieldParam(name) { 
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) {  
		  return unescape(r[2]); 
		} 
		return null; 
	}

	</script>
</body>
</html>