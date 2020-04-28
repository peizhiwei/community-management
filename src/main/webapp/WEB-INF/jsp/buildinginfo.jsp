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
            <h4 data-toggle="collapse" href="#collapseExample">查询条件</h4><hr>
            <form id="collapseExample" class="form-inline collapse" style="padding-bottom: 25px;">
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
		<div class="row">
			<div class="col-md-6">
				<div class="col-md-12" style="padding-top: 20px;">
					<h4>总记录数：{{total}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总页数：{{pagetotal}}</h4>
				</div>
				
			</div>
			<div class="col-md-6 text-right">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li @click="firstpage()"><a style="cursor:pointer;"><span aria-hidden="true">首页</span></a></li>
						<li><a style="cursor:pointer;" @click="previous()"><span>上一页</span></a></li>
						
						<li v-for="n in pageRange" v-if="n==page&&state==1" class="active"><span>{{n}}</span></li>
						<li v-else-if="state==1" @click="tiaozhuan(n)"><a style="cursor:pointer;">{{n}}</a></li>
						<li v-for="n_like in pageRange" v-if="n_like==likepage&&state==0" class="active"><span>{{n_like}}</span></li>
						<li v-else-if="state==0" @click="tiaozhuan(n_like)"><a style="cursor:pointer;">{{n_like}}</a></li>
						
						<li><a style="cursor:pointer;" aria-label="Next" @click="next()"><span aria-hidden="true">下一页</span></a></li>
						<li @click="lastpage()"><a style="cursor:pointer;"><span aria-hidden="true">尾页</span></a></li>
					</ul>
				</nav>
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
				arr:[],
				page:1,//当前页
				size:6,//每页记录条数
				total:0,//记录总数
				pagetotal:0,//总页数
				begin:1,//起始页
				end:1,//显示的最大页
				pageRange:[],//页码显示的范围
				state:1,//1表示执行get方法，查询所有房间信息，0表示执行gethouseinfolike方法，模糊查询房间信息
				likepage:1,//当前页
				likesize:6//每页记录条数
			},
			mounted : function() {
				this.get();
			},
			methods : {
				//获取所有楼栋信息
				get : function() {
					var page = this.page;
					var size = this.size;
					$.ajax({
						url : '/community/buildinginfo/pagegetallbuildinginfo',
						type : 'GET',
						dataType : 'JSON',
						data:{"page":page,"size":size},
						success : function(result) {
							app.infolist = result.rows;
							app.total = result.total;
							app.pagetotal=Math.ceil(app.total/app.size);//向上取整
							if((app.page-1)%5==0){
								app.begin=app.page;
								app.end = app.page+4;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									if(current>app.pagetotal)break;
									app.pageRange[i]=current;
									current++;
								}
							}
							if(app.page%5==0){
								app.begin=app.page-4;
								app.end=app.page;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									app.pageRange[i]=current;
									current++;
								}
							}
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//跳转到第一页，首页
				firstpage : function(){
					if(app.state==1){
						if(app.page==1){
							alert("已经是第一页了！");
						}else{
							app.page=1;
							app.get();
						}
					}else{
						if(app.likepage==1){
							alert("已经是第一页了！");
						}else{
							app.likepage=1;
							app.getbuildinfooflikebuildnumber();
						}
					}
				},
				//点击上一页
				previous : function(){
					if(app.state==1){
						if(app.page==1){
							alert("已经是第一页了！");
						}else{
							app.page -=1;
							app.get();
						}
					}else{
						if(app.likepage==1){
							alert("已经是第一页了！");
						}else{
							app.likepage-=1;
							app.getbuildinfooflikebuildnumber();
						}
					}
					
				},
				//跳转页面
				tiaozhuan : function(n){
					if(app.state==1){
						app.page=n;
						app.get();
					}else{
						app.likepage=n;
						app.getbuildinfooflikebuildnumber();
					}
				},
				//点击下一页
				next : function(){
					if(app.state==1){
						if(app.page==app.pagetotal){
							alert("已经是最后一页了！");	
						}else{
							app.page+=1;
							app.get();
						}
					}else{
						if(app.likepage==app.pagetotal){
							alert("已经是最后一页了！");
						}else{
							app.likepage+=1;
							app.getbuildinfooflikebuildnumber();
						}
					}
				},
				//跳转到最后一页，尾页
				lastpage : function(){
					if(app.state==1){
						if(app.page==app.pagetotal){
							alert("已经是最后一页了！");
						}else{
							app.page=app.pagetotal;
							if(app.page%5!=0&&(app.page-1)%5!=0){
								//跳转到最后一页，可能并不是完整的五个页码
								app.begin=(Math.floor(app.page/5))*5+1;//向下取整。例如：第七页，从第六页开始查询
								app.end=app.page;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									if(current>app.pagetotal)break;
									app.pageRange[i]=current;
									current++;
								}
							}
							app.get();
						}
					}else{
						if(app.likepage==app.pagetotal){
							alert("已经是最后一页了！");
						}else{
							app.likepage=app.pagetotal;
							if(app.likepage%5!=0&&(app.likepage-1)%5!=0){
								//跳转到最后一页，可能并不是完整的五个页码
								app.begin=(Math.floor(app.likepage/5))*5+1;//例如：第七页，从第六页开始查询
								app.end=app.likepage;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									if(current>app.pagetotal)break;
									app.pageRange[i]=current;
									current++;
								}
							}
							app.getbuildinfooflikebuildnumber();
						}
					}
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
                	app.state=0;
                	var page = this.likepage;
					var size = this.likesize;
                	$.ajax({
						type:'POST',
						dataType:'JSON',
						url:'/community/buildinginfo/selectlikebuildinginfo',
						data:{"buildNumber":buildNumber,"page":page,"size":size},
						success:function(result){
							app.infolist = result.rows;
							app.total = result.total;
							app.pagetotal=Math.ceil((app.total)/app.likesize);//向上取整
							if((app.likepage-1)%5==0){
								app.begin=app.likepage;
								app.end = app.likepage+4;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									if(current>app.pagetotal)break;
									app.pageRange[i]=current;
									current++;
								}
							}
							if(app.likepage%5==0){
								app.begin=app.likepage-4;
								app.end=app.likepage;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									app.pageRange[i]=current;
									current++;
								}
							}
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