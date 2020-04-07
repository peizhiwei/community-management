<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf8">
<title>个人信息</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	.info{
		margin-top: 10px;
		box-shadow: #ccc 0px 10px 10px;
	}
</style>
</head>
<body style="background-color: rgb(245,245,245)">
	<div>
		<h1 style="text-align: center;">基本信息</h1>
	</div>
	<div class="container-fluid" id="app" style="margin-left: 20px;margin-top: 30px;">
		<div class="row">
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal1" @click="change()">修改信息</button>
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal2">修改密码</button>
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal3">更换手机号</button>
		</div>
		<div class="row" style="background-color: white;margin-top: 1px">
			<div style="font-size: 18px;padding-left: 10px;">
				<div class="col-xs-12 col-sm-12 col-md-6 info">
					<span>姓名：</span><span>{{admininfo.adminName}}</span>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 info">
					<span>编号：</span><span>{{admininfo.adminNumber}}</span>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 info">
					<span>性别：</span><span>{{admininfo.adminSex==1?'男':'女'}}</span>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 info">
					<span>电话：</span><span>{{admininfo.adminPhone}}</span>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 info">
					<span>出生日期：</span><span>{{admininfo.adminBirthday}}</span>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 info">
					<span>入职时间：</span><span>{{admininfo.adminEntryTime}}</span>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 info">
					<span>身份证号码：</span><span>{{admininfo.adminIdCard}}</span>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 info">
					<span>籍贯：</span><span>{{admininfo.adminNativePlace}}</span>
				</div>
			</div>
			<!-- 修改业主基本信息的模态框 -->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center">修改基本信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="adminname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="adminsex">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期</label>
									<div class="col-sm-9">
										<input id="adminbirthday" value="1" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">身份证号</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="adminidcard">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">籍贯</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="adminnativeplace">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savechange()" >提交</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改密码的模态框 -->
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center">修改密码</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">原密码</label>
									<div class="col-sm-9">
										<input type="password" class="form-control" id="oldpassword">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">新密码</label>
									<div class="col-sm-9">
										<input type="password" class="form-control" id="newpassword">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">确认密码</label>
									<div class="col-sm-9">
										<input type="password" class="form-control" id="repassword">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savechangepassword()" >提交</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 更换手机号的模态框 -->
			<div class="modal fade" id="myModal3" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center">更换手机号</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">新手机号</label>
									<div class="col-sm-9">
										<input type="password" class="form-control" id="newphone">
									</div>
								</div>
								<div style="text-align: center;color: red;">
									<span>注：手机号即为登录账号，更换手机号，密码不变</span>
								</div>
								
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savechangephonenumber()" >提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<script src="../static/js/vue.min.js"></script>
	<script src="../static/js/vue-resource.min.js"></script>
	<script src="../static/datepicker/WdatePicker.js"></script>
	<script>
		var app = new Vue({
			el : '#app',
			data : {
				admininfo:[]
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function(){
					$.ajax({
						url : '/community/adminselfmessage/getmessageofadmin',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.admininfo = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击修改按钮，显示信息在模态框中
				change : function(){
					$("#adminname").val(app.admininfo.adminName);
					$("#adminsex").val(app.admininfo.adminSex==1?'男':'女');
					$("#adminbirthday").val(app.admininfo.adminBirthday);
					$("#adminidcard").val(app.admininfo.adminIdCard);
					$("#adminnativeplace").val(app.admininfo.adminNativePlace);
				},
				//保存修改的信息
				savechange : function(){
					var adminName = $("#adminname").val();
					var adminSex = $("#adminsex").val()=='男'?1:0;
					var adminBirthday = $("#adminbirthday").val();
					var adminIdCard = $("#adminidcard").val();
					var adminNativePlace = $("#adminnativeplace").val();
					if(adminName==''||adminBirthday==''||adminIdCard==''||adminNativePlace==''){
						alert("请将信息填写完整！");
					}else{
						$.ajax({
							url : '/community/adminselfmessage/updatemessageofadmin',
							type : 'POST',
							dataType : 'JSON',
							data:{"adminName":adminName,"adminSex":adminSex,"adminBirthday":adminBirthday,"adminIdCard":adminIdCard,"adminNativePlace":adminNativePlace},
							success : function(result) {
								alert(result.msg);
								app.get();
							},							
							error : function() {
								console.log("请求失败处理");
							}
						});
					}
				},
				//保存修改密码
				savechangepassword : function(){
					var oldPassword = $("#oldpassword").val();
					var newPassword = $("#newpassword").val();
					var rePassword = $("#repassword").val();
					if(oldPassword==''||newPassword==''||rePassword==''){
						alert("您有信息未填写！");
					}else if(newPassword!=rePassword){
						alert("两次输入的密码不一致！")
					}else if(oldPassword==newPassword){
						alert("新密码与原密码不能相同！")
					}else{
						$.ajax({
							url : '/community/ownerselfmessage/changepassword',
							type : 'POST',
							dataType : 'JSON',
							data:{"oldPassword":oldPassword,"newPassword":newPassword},
							success : function(result) {
								alert(result.msg);
								if(result.flag==true){
									$.ajax({
										type:'POST',
										dataType:'JSON',
										url:'/community/owner/signout',
										success:function(result){
											top.location.href=result.msg;
										},
										error:function(){
											console.log("请求失败处理！");
										}
									});
								}
							},							
							error : function() {
								console.log("请求失败处理");
							}
						});
					}
				},
				//更换手机号
				savechangephonenumber :function(){
					var ownerPhone = $("#newphone").val();
					$.ajax({
						url : '/community/ownerselfmessage/changephone',
						type : 'POST',
						dataType : 'JSON',
						data:{"ownerPhone":ownerPhone},
						success : function(result) {
							alert(result.msg);
							if(result.flag==true){
								$.ajax({
									type:'POST',
									dataType:'JSON',
									url:'/community/owner/signout',
									success:function(result){
										top.location.href=result.msg;
									},
									error:function(){
										console.log("请求失败处理！");
									}
								});
							}
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				}
			}
		});
	</script>
</body>
</html>