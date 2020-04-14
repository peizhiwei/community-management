<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>房间信息</title>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="../static/css/bootstrap-table.min.css" rel="stylesheet">
</head>
<body style="background-color: rgb(245,245,245)">
	<div class="container-fluid" id="vue">
		<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>房间信息</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;楼房信息管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;房间信息</span></h5>
        </div>
        <div class="row" style="background-color: white;padding-left: 10px;margin-bottom: 20px;">
            <h4>查询条件</h4><hr>
            <form class="form-inline" style="padding-bottom: 25px;">
                <input type="text" class="form-control" id="likebuildnumber" placeholder="请输入楼栋编号">
                <input type="text" class="form-control" id="likehouseunit" placeholder="请输入单元号">
                <input type="text" class="form-control" id="likehousenumber" placeholder="请输入房间号">
                <input type="text" class="form-control" id="likeownername" placeholder="请输入业主姓名">
                <button type="button" class="btn btn-default" @click="gethouseinfolike()">查询</button>
            </form>
        </div>
		<div class="row">
			<button type="button" class="btn btn-default"data-toggle="modal" data-target="#myModal2">新增</button>
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover text-center col-md-12" id="mytb" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">序号</th>
						<th class="text-center">楼栋</th>
						<th class="text-center">单元</th>
						<th class="text-center">房间号</th>
						<th class="text-center">业主</th>
						<th class="text-center">面积(m²)</th>
						<th class="text-center">房型</th>
						<th class="text-center">用途</th>
						<th class="text-center">状态</th>
						<th class="text-center">入住时间</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in houseinfolist">
						<td>{{index+1}}</td>
						<td>{{list.buildInfo.buildNumber}}</td>
						<td>{{list.houseUnit}}</td>
						<td>{{list.houseNumber}}</td>
						<td>{{list.houseOwner==null?'':list.houseOwner.ownerName}}</td>
						<td>{{list.houseArea}}</td>
						<td>{{list.houseType.houseTypeName}}</td>
						<td>{{list.houseUse}}</td>
						<td>{{list.houseState==1?'有人居住':'待售'}}</td>
						<td>{{list.houseInTime==''?'':list.houseInTime}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#myModal1" @click="changeinfo(list.houseId,list.houseNumber,list.houseArea,list.houseType.houseTypeName,list.houseInTime)">修改</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!--修改信息的模态框-->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">修改房间信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">面积(m²)</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="housearea">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房型</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="housetype" v-model="selectdefaultval">
											<option v-for="typelist in housetypelist" :value="typelist.houseTypeName">{{typelist.houseTypeName}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">入住时间</label>
									<div class="col-sm-9">
										<input type="date" class="form-control" id="houseintime">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="savehouseinfochange" @click="savehouseinfochange()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 新增楼栋信息的模态框 -->
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">增加楼栋信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">编号</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="newnumber">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">层数</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="newlayer">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房间数</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="newsum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">建筑面积(单位:m²)</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="newarea">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">开工时间</label>
									<div class="col-sm-9">
										<input type="date" class="form-control" id="newstarttime">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">竣工时间</label>
									<div class="col-sm-9">
										<input type="date" class="form-control" id="newendtime">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="saveaddbuildinfo()" >确定</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script	src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<script src="../static/js/vue.min.js"></script>
	<script src="../static/js/bootstrap-table.min.js"></script>
	<script src="../static/js/bootstrap-table-zh-CN.min.js"></script>
	<script>
		var app = new Vue({
			el : '#vue',
			data : {
				houseinfolist : [],
				housetypelist:[],
				selectdefaultval:'',
				changehouseinfoid:0
			},
			mounted : function() {
				this.get();
			},
			methods : {
				//获取所有楼栋信息
				get : function() {
					$.ajax({
						url : '/community/houseinfo/getallhouseinfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.houseinfolist = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击修改按钮，将该条信息显示在模态框中
				changeinfo:function(buildId,houseNumber,houseArea,houseTypeName,houseInTime){
					app.changehouseinfoid=buildId;
					$.ajax({
						url:"/community/houseinfo/getallhousetype",
						type:'GET',
						dataType:'JSON',
						async:false,
						success:function(result){
							app.housetypelist=result;
						},
						error:function(){
							console.log("请求失败处理");
						}
					});
					$("#housenumber").val(houseNumber);
					$("#housearea").val(houseArea);
					$("#housetype").val(houseTypeName);
					$("#houseintime").val(houseInTime);
					app.selectdefaultval=houseTypeName;
				},
				savehouseinfochange:function(){
					var houseId=app.changehouseinfoid;
					var houseArea=$("#housearea").val();
					var houseType=$("#housetype").val();
					var houseInTime=$("#houseintime").val();
					$.ajax({
						url:'/community/houseinfo/changehouseinfo',
						type:'POST',
						dataType:'JSON',
						data:{"houseId":houseId,"houseArea":houseArea,"houseType":houseType,"houseInTime":houseInTime},
						success : function(result) {
							alert(result.msg);
							app.get();
						}
					});
				},
				//模糊查询房间信息
				gethouseinfolike : function(){
					var buildNumber = $("#likebuildnumber").val();
					var houseUnit = $("#likehouseunit").val();
					var houseNumber = $("#likehousenumber").val();
					var ownerName = $("#likeownername").val();
					$.ajax({
						url:'/community/houseinfo/gethouseinfolike',
						type:'POST',
						dataType:'JSON',
						data:{"buildNumber":"%"+buildNumber+"%","houseUnit":"%"+houseUnit+"%","houseNumber":"%"+houseNumber+"%","ownerName":"%"+ownerName+"%"},
						success : function(result) {
							app.houseinfolist = result;
						}
					});
				}
			}
		});
	</script>
</body>
</html>