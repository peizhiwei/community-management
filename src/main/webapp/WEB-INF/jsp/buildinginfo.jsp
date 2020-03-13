<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>楼栋信息</title>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid" id="vue">
		<div class="row-fluid">
			<table class="table table-bordered table-hover text-center">
				<div>
					<h1 style="text-align: center;">楼栋信息</h1>
					<button type="button" class="btn btn-default" style="float:right;" data-toggle="modal" data-target="#myModal2" @click="add()">新增</button>
				</div>
				<thead>
					<tr>
						<th class="col-md-1 text-center">编号</th>
						<th class="col-md-1 text-center">层数</th>
						<th class="col-md-1 text-center">房间数</th>
						<th class="col-md-2 text-center">建筑面积(单位:平方米)</th>
						<th class="col-md-2 text-center">开工时间</th>
						<th class="col-md-2 text-center">竣工时间</th>
						<th class="col-md-3 text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="list in infolist">
						<td>{{list.buildNumber}}</td>
						<td>{{list.buildLayer}}</td>
						<td>{{list.buildSumHouse}}</td>
						<td>{{list.buildArea}}</td>
						<td>{{list.buildStartTime}}</td>
						<td>{{list.buildEndTime}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#myModal1" @click="changeinfo(list.buildId,list.buildNumber,list.buildLayer,list.buildSumHouse,list.buildArea,list.buildEndTime,list.buildStartTime)">修改</button>
                            <button type="button" class="btn btn-danger btn-sm" @click="deleteinfo(list.buildId)">删除</button>
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
							<h4 class="modal-title text-center" id="myModalLabel">修改楼栋信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">编号</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="number">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">层数</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="layer">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房间数</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="sum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">建筑面积(单位:m²)</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="area">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">开工时间</label>
									<div class="col-sm-9">
										<input type="date" class="form-control" id="starttime">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">竣工时间</label>
									<div class="col-sm-9">
										<input type="date" class="form-control" id="endtime">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="savechange" @click="savechange()">确定</button>
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
									<label class="col-sm-3 control-label">房间面积(单位:m²)</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="housearea">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房型</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="housetype">
											<option v-for="typelist in housetypelist">{{typelist.houseTypeName}}</option>
										</select>
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
	<script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
	<script	src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
	<script>
		var app = new Vue({
			el : '#vue',
			data : {
				infolist : [],
				changeinfoid:0,
				housetypelist:[]
			},
			mounted : function() {
				this.get();
			},
			methods : {
				//获取所有楼栋信息
				get : function() {
					$.ajax({
						url : '/community/buildinginfo/getallbuildinginfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.infolist = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//设置弹出的模态框相对应的内容
				changeinfo:function(buildId,buildNumber,buildLayer,buildSumHouse,buildArea,buildEndTime,buildStartTime){
					this.changeinfoid=buildId;
					$("#number").val(buildNumber);
					$("#layer").val(buildLayer);
					$("#sum").val(buildSumHouse);
					$("#area").val(buildArea);
					$("#starttime").val(buildStartTime);
					$("#endtime").val(buildEndTime);
				},
				//修改楼栋信息
				savechange:function(){
					var buildId=app.changeinfoid;
					var buildNumber=$("#number").val();
					var buildLayer=$("#layer").val();
					var buildSumHouse=$("#sum").val();
					var buildArea=$("#area").val();
					var buildStartTime=$("#starttime").val();
					var buildEndTime=$("#endtime").val();
					$.ajax({
						url:'/community/buildinginfo/changebuildinginfo',
						type:'POST',
						dataType:'JSON',
						data:{"buildId":buildId,"buildNumber":buildNumber,"buildLayer":buildLayer,"buildSumHouse":buildSumHouse,"buildArea":buildArea,"buildStartTime":buildStartTime,"buildEndTime":buildEndTime},
						success : function(result) {
							alert(result.msg);
							app.get();
						}
					});
				},
				//删除一条楼栋信息
				deleteinfo:function(buildId){
					var buildId=buildId;
					$.ajax({
						type:'POST',
						dataType:'JSON',
						url:'/community/buildinginfo/deletebuildinginfo',
						data:{"buildId":buildId},
						success : function(result) {
							alert(result.msg);
							app.get();
						}
					});
				},
				add:function(){
					//查询所有房型信息
					$.ajax({
						url:"/community/houseinfo/getallhousetype",
						type:'GET',
						dataType:'JSON',
						success:function(result){
							app.housetypelist=result;
						},
						error:function(){
							console.log("请求失败处理");
						}
					});
				},
				//新增一条楼栋信息
				saveaddbuildinfo:function(){
					var buildNumber=$("#newnumber").val();
					var buildLayer=$("#newlayer").val();
					var buildSumHouse=$("#newsum").val();
					var buildArea=$("#newarea").val();
					var buildStartTime=$("#newstarttime").val();
					var buildEndTime=$("#newendtime").val();
					var houseArea=$("#housearea").val();
					var houseType=$("#housetype").val();
					console.log(buildNumber);
					$.ajax({
						type:'POST',
						dataType:'JSON',
						url:'/community/buildinginfo/addbuildinginfo',
						data:{"houseType":houseType,"houseArea":houseArea,"buildNumber":buildNumber,"buildLayer":buildLayer,"buildSumHouse":buildSumHouse,"buildArea":buildArea,"buildStartTime":buildStartTime,"buildEndTime":buildEndTime},
						success:function(result){
							if(result.flag==true){
								alert(result.msg);
							}else{
								alert(result.msg);
							}
							app.get();
						}
					});
				}
			}
		});
	</script>
</body>
</html>