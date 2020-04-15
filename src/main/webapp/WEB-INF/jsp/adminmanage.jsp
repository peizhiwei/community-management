<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员管理</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>管理员信息</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;管理员信息</span></h5>
        </div>
    	<div class="row">
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal2">新增</button>
			<button type="button" class="btn btn-danger" @click="checkdelete()">批量删除</button>
		</div>
		<div class="row">
			<table class="col-xs-12 col-sm-12 col-md-12 table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" v-model="checked" @click="checkedAll()">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">姓名</th>
						<th class="text-center">性别</th>
						<th class="text-center">编号</th>
						<th class="text-center">电话</th>
						<th class="text-center">出生日期</th>
						<th class="text-center">身份证号</th>
						<th class="text-center">入职时间</th>
						<th class="text-center">籍贯</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listadmininfo">
						<td>
							<input type="checkbox" v-model="arr" :value="list.adminId">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.adminName}}</td>
						<td>{{list.adminSex==1?'男':'女'}}</td>
						<td>{{list.adminNumber}}</td>
						<td>{{list.adminPhone}}</td>
						<td>{{list.adminBirthday}}</td>
						<td>{{list.adminIdCard}}</td>
						<td>{{list.adminEntryTime}}</td>
						<td>{{list.adminNativePlace}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#changeadmininfo" @click="change(list.adminId,list.adminName,list.adminSex,list.adminPhone,
                                list.adminNumber,list.adminBirthday,list.adminIdCard,list.adminEntryTime,list.adminNativePlace)">修改</button>
                            <button type="button" class="btn btn-danger btn-sm" @click="deleteowner(list.adminId)">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!--修改管理员信息的模态框-->
			<div class="modal fade" id="changeadmininfo" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">修改管理员信息</h4>
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
									<label class="col-sm-3 control-label">编号</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="adminnumber">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期</label>
									<div class="col-sm-9">
										<input id="adminbirthday" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">身份证号</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="adminidcard">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">入职时间</label>
									<div class="col-sm-9">
										<input id="adminentrytime" class="form-control" type="text" onclick="WdatePicker()"/>
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
							<button type="button" class="btn btn-primary" id="savechange" @click="savechange()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!--新增管理员的模态框 -->
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">新增管理员</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addadminname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addadminsex">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">电话</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addadminphone">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期</label>
									<div class="col-sm-9">
										<input id="addadminbirthday" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">身份证号</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addadminidcard">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">入职时间</label>
									<div class="col-sm-9">
										<input id="addadminentrytime" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">籍贯</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addadminnativeplace">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="saveaddadmininfo()" >确定</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="../static/js/vue.min.js"></script>
    <script src="../static/datepicker/WdatePicker.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listadmininfo:[],//所有管理员信息
				changeid:0,//要修改的管理员信息的id
				oldadminNumber:'',//修改管理员信息时，该管理员原先的编号
				checked:false,
    			arr:[]
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/adminmanage/getalladmininfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listadmininfo = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击修改按钮，显示信息在模态框中
				change : function(adminId,adminName,adminSex,adminPhone,adminNumber,adminBirthday,adminIdCard,adminEntryTime,adminNativePlace){
					app.changeid=adminId;
					app.oldadminNumber=adminNumber;
					$("#adminname").val(adminName);
					$("#adminsex").val(adminSex==1?'男':'女');
					$("#adminnumber").val(adminNumber);
					$("#adminbirthday").val(adminBirthday);
					$("#adminidcard").val(adminIdCard);
					$("#adminentrytime").val(adminEntryTime);
					$("#adminnativeplace").val(adminNativePlace);
				},
				//确定管理员信息的修改
				savechange : function (){
					var adminId = app.changeid;
					var adminName = $("#adminname").val();
					var adminSex = $("#adminsex").val()=='男'?1:0;
					var adminNumber = $("#adminnumber").val();
					var oldadminNumber = app.oldadminNumber;
					var adminBirthday = $("#adminbirthday").val();
					var adminIdCard = $("#adminidcard").val();
					var adminEntryTime = $("#adminentrytime").val();
					var adminNativePlace = $("#adminnativeplace").val();
					if(adminName==''||adminNumber==''||adminBirthday==''||adminIdCard==''||adminEntryTime==''||adminNativePlace==''){
						alert("请将信息填写完整！");
					}else{
						$.ajax({
							url : '/community/adminmanage/updateadminmanageinfo',
							type : 'POST',
							dataType : 'JSON',
							data : {"adminId":adminId,"adminName":adminName,"adminSex":adminSex,"adminNumber":adminNumber,"oldadminNumber":oldadminNumber,"adminBirthday":adminBirthday,"adminIdCard":adminIdCard,"adminEntryTime":adminEntryTime,"adminNativePlace":adminNativePlace},
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
				//确定添加管理员
				saveaddadmininfo : function(){
					var adminName = $("#addadminname").val();
					var adminSex = $("#addadminsex").val()=='男'?1:0;
					var adminPhone = $("#addadminphone").val();
					var adminBirthday = $("#addadminbirthday").val();
					var adminIdCard = $("#addadminidcard").val();
					var adminEntryTime = $("#addadminentrytime").val();
					var adminNativePlace = $("#addadminnativeplace").val();
					if(adminName==''||adminPhone==''||adminBirthday==''||adminIdCard==''||adminEntryTime==''||adminNativePlace==''){
						alert("请将信息填写完整！");
					}else{
						$.ajax({
							url : '/community/adminmanage/addadminmanageinfo',
							type : 'POST',
							dataType : 'JSON',
							data : {"adminName":adminName,"adminSex":adminSex,"adminPhone":adminPhone,"adminBirthday":adminBirthday,"adminIdCard":adminIdCard,"adminEntryTime":adminEntryTime,"adminNativePlace":adminNativePlace},
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
				//删除管理员
				deleteowner : function(adminId){
					$.ajax({
						url : '/community/adminmanage/deleteadminmanageinfo',
						type : 'POST',
						dataType : 'JSON',
						data : {"adminId":adminId},
						success : function(result) {
							alert(result.msg);
							app.get();
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				checkedAll : function(){
                    if(this.checked){//实现反选
                        this.arr=[];
                    }else{//实现全选
                        this.arr=[];
                        this.listadmininfo.forEach( (item) => {
                            this.arr.push(item.adminId);
                        })
                    }
                },
                //批量删除
                checkdelete : function(){
                	var listadminId= app.arr;
                	if(listadminId==''){
                		alert("请选择要删除的项目！");
                	}else{
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/adminmanage/checkdelete',
    						contentType: "application/json;charset=utf-8",
    						data:JSON.stringify(listadminId),
    						success:function(result){
    							alert(result.msg);
    							app.checked=false;
    							app.get();
    						}
    					});
                	}
                }
			},
			watch:{//深度watcher
                arr:{
                    handler:function(val,oldval){
                        if(this.arr.length==this.listadmininfo.length){
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