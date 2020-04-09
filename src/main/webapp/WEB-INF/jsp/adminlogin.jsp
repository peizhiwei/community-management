<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>管理员登录</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div class="modal-dialog" style="margin-top: 10%;" id="app">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title text-center" id="myModalLabel">管理员登录</h1>
      </div>
      <div class="modal-body" id="model-body">
        <div class="form-group">
          <h3>用户名</h3>
          <input id="username" type="text" class="form-control">
        </div>
        <div class="form-group">
          <h3>密&nbsp;&nbsp;&nbsp;&nbsp;码</h3>
          <input id="password" type="password" class="form-control">
        </div>
      </div>
      <div class="modal-footer">
        <div class="form-group" style="background-color: chartreuse;">
          <button id="login" type="button" class="btn btn-primary form-control" @click="login()">登录</button>
        </div>
        <div style="color: red;text-align: center;">注：初始密码为‘000000’，请及时更改</div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
  <script src="../static/js/vue.min.js"></script>
  <script type="text/javascript">
  	var app = new Vue({
  		el : '#app',
		mounted : function() {
			this.get();
		},
		methods : {
			get : function() {
				$.ajax({
					type:"POST",
					url : "/community/admin/checkadmin",
					success : function(result) {
						if(result.flag==true){
							alert(result.msg);
						}else{
							console.log(result.msg);
						}
					},
					error : function() {
						console.log("请求失败处理！");
					}
				});
			},
			login : function(){
				var username=$("#username").val();
	  			var password=$("#password").val();
	  			if(username==""||password==""){
	  				alert("用户名或密码不能为空");
	  			}else{
	  				$.ajax({
	  	  				data:{"username":username,"password":password},
	  	  				url:"/community/admin/checklogin",
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
			}
		}
  	});
  </script>
</body>
	
</html>