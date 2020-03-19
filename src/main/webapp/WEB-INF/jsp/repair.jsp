<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>报修信息</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
		<div class="row">
			<h1>报修页面</h1>
			<h1>这是报修页面</h1>
			<div>
				<button type="button" class="btn btn-default" style="margin-bottom: -10%" data-toggle="modal" data-target="#myModal2" @click="add()">新增</button>
				<h1 style="text-align: center;">报修信息</h1>
			</div>
			<table class="table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th>
							<input type="checkbox" value="">
						</th>
						<th class="col-md-1 text-center">序号</th>
						<th class="col-md-1 text-center">投诉人</th>
						<th class="col-md-1 text-center">业主</th>
						<th class="col-md-1 text-center">房间号</th>
						<th class="col-md-2 text-center">手机号</th>
						<th class="col-md-1 text-center">出售时间</th>
						<th class="col-md-1 text-center">价格(万)</th>
						<th class="col-md-1 text-center">状态</th>
						<th class="col-md-3 text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(parkinglist,index) in listparkinginfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{parkinglist.parkingNumber}}</td>
						<td>{{parkinglist.houseOwner==null?'':parkinglist.houseOwner.ownerName}}</td>
						<td>{{parkinglist.houseOwner==null?'':parkinglist.houseOwner.houseInfo.houseNumber}}</td>
						<td>{{parkinglist.houseOwner==null?'':parkinglist.houseOwner.ownerPhone}}</td>
						<td>{{parkinglist.parkingSellTime==''?'':parkinglist.parkingSellTime}}</td>
						<td>{{parkinglist.parkingPrice}}</td>
						<td>{{parkinglist.parkingstate==1?'已售':'待售'}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
    <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listfamilyinfo:[],//所有家庭成员信息
				infotomodal:[],
				changeid:0,
				listonlyhouseownername:[],//所有的业主名称，去掉重复的
				listhouseinfo:[]//根据业主名称查出的所有房间信息
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/familyinfo/getallfamilyinfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listfamilyinfo = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				}
	</script>
  </body>
</html>