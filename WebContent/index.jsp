<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
<link href="content/Content/bootstrap.css" rel="stylesheet">
<script src="content/jquery-2.2.4.js"></script>
  <script src="content/Scripts/bootstrap.min.js"></script>
  <style>  
  body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}
.col-center-block {  
    float: none;  
    display: block; 
   	margin-left: auto;  
    margin-right: auto;  
    margin-top: 8%; 
}  
</style>  
</head>
<body>
    <div class="container">
 <div class="row col-xs-6 col-md-4  col-center-block">
   <h3 class="form-signin-heading col-center-block ">Log-in to Map Tools </h3>
  <div class="panel panel-default ">
  <div class="panel-body">
    <form class="form-signin" action="checkLogin" method="post">
     
         <div>
        <label  class="sr-only">用户名</label>
        <input type="text" id="username" name="username"  class="form-control" placeholder="User name" required autofocus>
        </div>
        <div style="margin-top: 15px;">
        <label  class="sr-only">密码</label>
        <input type="password" id="password"  name="password" class="form-control" placeholder="Password" required>
        </div>
        <button class="btn  btn-primary btn-block" type="submit"  style="margin-top: 15px;">登陆</button>
         <div style="margin-top: 15px;color:#F00;" id="error" >
        </div>
      </form>
  </div>
</div>
<div class="panel panel-default col-center-block" style="margin-top: 2px;">
 <p class="text-center" style="margin-top: 8px;">联系我：<a>chuyaoyuan@gmail.com</a> .</p>
    <p class="text-center" style="margin-top: 8px;"><a>yaoyuan.io</a> & <a>www.chuyaoyuan.com</a> .</p>


</div>
 </div>
    </div> <!-- /container -->
    
</body>

<script>
var msg = '${msg }'
;if(msg!=''){
	$("#error").html(msg);
	}
</script>
</html>