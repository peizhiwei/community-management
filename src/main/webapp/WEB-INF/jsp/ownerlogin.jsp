<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>业主登录</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<link rel="stylesheet" href="../static/css/login.css">
</head>
<body class="form-bg">
	<div class="htmleaf-container">
		<div style="padding: 100px 0;">
            <div class="container">
                <div class="row">
                    <div class="col-md-offset-3 col-md-6">
                        <form class="form-horizontal">
                            <span class="heading">业主登录</span>
                            <div class="form-group">
                                <input type="text" class="form-control" id="username" placeholder="手机号">
                                <i class="fa fa-user"></i>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="password" placeholder="密　码">
                                <i class="fa fa-lock"></i>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-primary btn-lg btn-block" id="login">登录</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function(){
	  		$("#login").click(function(){
	  			var username=$("#username").val();
	  			var password=$("#password").val();
	  			if(username==""||password==""){
	  				alert("用户名或密码不能为空");
	  			}else{
	  				$.ajax({
	  	  				data:{"username":username,"password":password},
	  	  				url:'/community/owner/checklogin',
	  	  				type:"POST",
	  	  				dataType:'json',
	  	  				success:function(result){
	  	  					if(result.flag==true){
	  	  						window.location.href=result.msg;
	  	  					}else{
	  	  						alert(result.msg);
	  	  					}
	  	  				}
	  	  			});
	  			}
	  			
	  		});
	  	});
	</script>
</body>
</html>