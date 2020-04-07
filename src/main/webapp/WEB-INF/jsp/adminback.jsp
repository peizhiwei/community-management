<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理员</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../static/css/adminback.css" rel="stylesheet">
</head>

<body style="overflow: hidden;">
	<div class="container-fluid f1" id="app">
		<div class="row f2">
			<div class="col-xs-2 col-md-2 f3">
				<div class="co_text">
					<span>小区物业管理系统</span>
				</div>
				<div class="accordion" id="accordion">
					<div onclick="changecolor(this)">
						<div class="manage">
							<a href="#community_manager" data-toggle="collapse"
								data-parent="#accordion">楼房信息管理</a>
						</div>
						<div id="community_manager" class="collapse">
							<div
								style="width: 5px; height: 100%; background-color: rgb(25, 170, 141); color: rgb(25, 170, 141); float: left"></div>
							<div style="margin-left: 50px;">
								<div>
									<a href="/community/in/buildinginfo" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">楼栋信息</a>
								</div>
								<div style="margin-top: 12px;">
									<a href="/community/in/houseinfo" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">房间信息</a>
								</div>
							</div>
						</div>
					</div>
					<div onclick="changecolor(this)">
						<div class="manage">
							<a href="#community_manager2" data-toggle="collapse"
								data-parent="#accordion">业主信息管理</a>
						</div>
						<div id="community_manager2" class="collapse">
							<div
								style="width: 5px; height: 100%; background-color: rgb(25, 170, 141); color: rgb(25, 170, 141); float: left"></div>
							<div style="margin-left: 50px;">
								<div>
									<a href="/community/in/houseownerinfo" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">业主信息</a>
								</div>
								<div style="margin-top: 12px;">
									<a href="/community/in/family" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">家庭成员</a>
								</div>
								<div style="margin-top: 12px;">
									<a href="/community/in/parking" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">停车位</a>
								</div>
							</div>
						</div>
					</div>
					<div onclick="changecolor(this)">
						<div class="manage">
							<a href="#community_manager3" data-toggle="collapse"
								data-parent="#accordion">小区管理</a>
						</div>
						<div id="community_manager3" class="collapse">
							<div
								style="width: 5px; height: 100%; background-color: rgb(25, 170, 141); color: rgb(25, 170, 141); float: left"></div>
							<div style="margin-left: 50px;">
								<div>
									<a href="/community/in/complaint" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">投诉管理</a>
								</div>
								<div style="margin-top: 12px;">
									<a href="/community/in/repair" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">报修管理</a>
								</div>
							</div>
						</div>
					</div>
					<div onclick="changecolor(this)">
						<div class="manage">
							<a href="#community_manager4" data-toggle="collapse"
								data-parent="#accordion">缴费管理</a>
						</div>
						<div id="community_manager4" class="collapse">
							<div
								style="width: 5px; height: 100%; background-color: rgb(25, 170, 141); color: rgb(25, 170, 141); float: left"></div>
							<div style="margin-left: 50px;">
								<div>
									<a href="/community/in/paytype" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">缴费类别</a>
								</div>
								<div style="margin-top: 12px;">
									<a href="/community/in/paymethod" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">缴费方式</a>
								</div>
								<div style="margin-top: 12px;">
									<a href="/community/in/payinfo" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">缴费信息</a>
								</div>
								<div style="margin-top: 12px;">
									<a href="/community/in/payinfodetails" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">缴费详情</a>
								</div>
								<div style="margin-top: 12px;">
									<a href="/community/in/payinfosummary" target="iframe_a"
										style="color: rgb(167, 177, 194); text-decoration: none;">汇总</a>
								</div>
							</div>
						</div>
					</div>
					<div onclick="changecolor(this)">
						<div class="manage">
							<a href="/community/in/adminselfmessage" target="iframe_a">基本信息</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-10 col-md-10 f4">
				<div class="col-xs-12 col-md-12 top">
					<div class="col-xs-6 col-md-6"></div>
					<div class="col-xs-6 col-md-6">
						<div class="col-xs-10 col-md-10">
							<span>欢迎访问香溪花园小区管理系统，</span><span>{{username}}</span>
						</div>
						<div class="col-xs-2 col-md-2">
							<button type="button" class="btn btn-primary" @click="sign_out()">退出</button>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-12 subpage" style="background-color: rgb(245, 245, 245)">
					<iframe src="/community/in/buildinginfo" name="iframe_a" width="100%" height="100%" frameborder="0" allowTransparency="true" style="background-color: #red;"></iframe>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="../static/js/bootstrap-collapse.js"></script>
	<script src="../static/js/vue.min.js"></script>
	<script>
		var perDiv = null;
		function changecolor(_this) {
			if (perDiv)
				perDiv.style.backgroundColor = '';
			_this.style.backgroundColor = 'rgb(41,56,70)';
			perDiv = _this;
		}
	</script>
	<script>
		var app = new Vue({
			el : '#app',
			data : {
				username : ''
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						type : 'POST',
						dataType : 'JSON',
						url : '/community/admin/getusername',
						success : function(result) {
							app.username = result.msg
						},
						error : function() {
							console.log("请求失败处理！");
						}
					});
				},
				//退出
				sign_out : function() {
					$.ajax({
						type : 'POST',
						dataType : 'JSON',
						url : '/community/admin/signout',
						success : function(result) {
							window.location.href = result.msg;
						},
						error : function() {
							console.log("请求失败处理！");
						}
					});
				}
			}
		});
	</script>
</body>

</html>