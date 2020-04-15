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
<body style="background-color: rgb(245,245,245)">
	<div class="container-fluid" id="vue">
		<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>楼栋信息</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;楼房信息管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;楼栋信息</span></h5>
        </div>
        <div class="row" style="background-color: white;padding-left: 10px;margin-bottom: 20px;">
            <h4>查询条件</h4><hr>
            <form class="form-inline" style="padding-bottom: 25px;">
                <input type="text" class="form-control" id="likebuildnumber" placeholder="请输入楼栋编号">
                <button type="button" class="btn btn-default" @click="getbuildinfooflikebuildnumber()">查询</button>
            </form>
        </div>
		<div class="row">
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal2" @click="add()">新增</button>
			<button type="button" class="btn btn-danger" @click="checkdelete()">批量删除</button>
		</div>
		<div class="row">
			<table class="col-sm-12 col-md-12 table table-striped table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" v-model="checked" @click="checkedAll()">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">编号</th>
						<th class="text-center">单元数</th>
						<th class="text-center">层数</th>
						<th class="text-center">单元单层房间数</th>
						<th class="text-center">房间数</th>
						<th class="text-center">建筑面积(m²)</th>
						<th class="text-center">开工时间</th>
						<th class="text-center">竣工时间</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in infolist">
						<td>
							<input type="checkbox" v-model="arr" :value="list.buildId">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.buildNumber}}</td>
						<td>{{list.buildUnitSum}}</td>
						<td>{{list.buildLayer}}</td>
						<td>{{list.buildUnitSingleLayerRooms}}</td>
						<td>{{list.buildSumHouse}}</td>
						<td>{{list.buildArea}}</td>
						<td>{{list.buildStartTime}}</td>
						<td>{{list.buildEndTime}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal1" @click="changeinfo(list.buildId,list.buildNumber,list.buildArea,list.buildEndTime,list.buildStartTime)">修改</button>
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
									<label class="col-sm-3 control-label">建筑面积(m²)</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="area">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">开工时间</label>
									<div class="col-sm-9">
										<input id="starttime" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">竣工时间</label>
									<div class="col-sm-9">
										<input id="endtime" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
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
									<label class="col-sm-3 control-label">单元数</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="newunitsum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">层数</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="newlayer">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">单元单层房间数</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="newunitsinglelayerrooms">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">建筑面积(m²)</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="newarea">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房间面积(m²)</label>
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
										<input id="newstarttime" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">竣工时间</label>
									<div class="col-sm-9">
										<input id="newendtime" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
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
	<script src="../static/datepicker/WdatePicker.js"></script>
	<script>
		var app = new Vue({
			el : '#vue',
			data : {
				infolist : [],
				changeinfoid:0,
				housetypelist : [],
				oldbuildNumber:'',
				checked:false,
				arr:[]
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
				//点击修改按钮，显示信息在模态框中
				changeinfo:function(buildId,buildNumber,buildArea,buildEndTime,buildStartTime){
					this.changeinfoid=buildId;
					this.oldbuildNumber = buildNumber;
					$("#number").val(buildNumber);
					$("#area").val(buildArea);
					$("#starttime").val(buildStartTime);
					$("#endtime").val(buildEndTime);
				},
				//修改楼栋信息
				savechange:function(){
					var buildId=app.changeinfoid;
					var oldbuildNumber = app.oldbuildNumber;
					var buildNumber=$("#number").val();
					var buildArea=$("#area").val();
					var buildStartTime=$("#starttime").val();
					var buildEndTime=$("#endtime").val();
					if(buildNumber==''||buildArea==''||buildStartTime==''||buildEndTime==''){
						alert("请将信息填写完整！");
					}else{
						$.ajax({
							url:'/community/buildinginfo/changebuildinginfo',
							type:'POST',
							dataType:'JSON',
							data:{"buildId":buildId,"oldbuildNumber":oldbuildNumber,"buildNumber":buildNumber,"buildArea":buildArea,"buildStartTime":buildStartTime,"buildEndTime":buildEndTime},
							success : function(result) {
								alert(result.msg);
								app.get();
							}
						});
					}
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
					var buildUnitSum = $("#newunitsum").val();
					var buildLayer=$("#newlayer").val();
					var buildUnitSingleLayerRooms = $("#newunitsinglelayerrooms").val();
					var buildArea=$("#newarea").val();
					var buildStartTime=$("#newstarttime").val();
					var buildEndTime=$("#newendtime").val();
					var houseArea=$("#housearea").val();
					var houseType=$("#housetype").val();
					if(buildNumber==''||buildUnitSum==''||buildLayer==''||buildUnitSingleLayerRooms==''||buildArea==''||buildStartTime==''||buildEndTime==''){
						alert("请将信息填写完整！");
					}else{
						$.ajax({
							type:'POST',
							dataType:'JSON',
							url:'/community/buildinginfo/addbuildinginfo',
							data:{"houseType":houseType,"houseArea":houseArea,"buildNumber":buildNumber,"buildUnitSum":buildUnitSum,"buildLayer":buildLayer,"buildUnitSingleLayerRooms":buildUnitSingleLayerRooms,"buildArea":buildArea,"buildStartTime":buildStartTime,"buildEndTime":buildEndTime},
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
				},
				checkedAll : function(){
                    if(this.checked){//实现反选
                        this.arr=[];
                    }else{//实现全选
                        this.arr=[];
                        this.infolist.forEach( (item) => {
                            this.arr.push(item.buildId);
                        })
                    }
                },
                //批量删除
                checkdelete : function(){
                	var listbuildId = app.arr;
                	if(listbuildId==''){
                		alert("请选择要删除的项目！");
                	}else{
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/buildinginfo/checkdelete',
    						contentType: "application/json;charset=utf-8",
    						data:JSON.stringify(listbuildId),
    						success:function(result){
    							alert(result.msg);
    							app.get();
    						}
    					});
                	}
                },
                //根据楼栋编号模糊查询楼栋信息
                getbuildinfooflikebuildnumber : function(){
                	var buildNumber = $("#likebuildnumber").val();
                	$.ajax({
						type:'POST',
						dataType:'JSON',
						url:'/community/buildinginfo/selectlikebuildinginfo',
						data:{"buildNumber":"%"+buildNumber+"%"},
						success:function(result){
							app.infolist = result;
						}
					});
                }
			},
			watch:{//深度watcher
                arr:{
                    handler:function(val,oldval){
                        if(this.arr.length==this.infolist.length){
                            this.checked=true;
                        }else{
                            this.checked=false;
                        }
                    },
                    deep:true
                }
            }
		});
	</script>
</body>
</html>