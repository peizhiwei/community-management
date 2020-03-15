<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>业主信息</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
    <div class="container-fluid" id="app">
		<div class="row-fluid">
			<table class="table table-bordered table-hover text-center">
			<div>
				<button type="button" class="btn btn-default" style="margin-bottom: -10%" data-toggle="modal" data-target="#myModal2" @click="add()">新增</button>
				<h1 style="text-align: center;">业主信息</h1>
			</div>
				
				<thead>
					<tr>
						<th class="col-md-1 text-center">姓名</th>
						<th class="col-md-1 text-center">楼栋</th>
						<th class="col-md-1 text-center">房间号</th>
						<th class="col-md-1 text-center">性别</th>
						<th class="col-md-1 text-center">电话</th>
						<th class="col-md-1 text-center">出生日期</th>
						<th class="col-md-1 text-center">身份证号</th>
						<th class="col-md-1 text-center">籍贯</th>
						<th class="col-md-2 text-center">工作单位</th>
						<th class="col-md-2 text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="ownerlist in listhouseownerinfo">
						<td>{{ownerlist.ownerName}}</td>
						<td>{{ownerlist.buildingInfo.buildNumber}}</td>
						<td>{{ownerlist.houseInfo.houseNumber}}</td>
						<td>{{ownerlist.ownerSex==1?'男':'女'}}</td>
						<td>{{ownerlist.ownerPhone}}</td>
						<td>{{ownerlist.ownerBirthday}}</td>
						<td>{{ownerlist.ownerIdCard}}</td>
						<td>{{ownerlist.ownerNativePlace}}</td>
						<td>{{ownerlist.ownerWorkPlace}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#myModal1" @click="change(ownerlist.ownerId,ownerlist.ownerName,ownerlist.ownerSex,ownerlist.ownerPhone,
                                ownerlist.ownerBirthday,ownerlist.ownerIdCard,ownerlist.ownerNativePlace,ownerlist.ownerWorkPlace)">修改</button>
                            <button type="button" class="btn btn-danger btn-sm">迁出</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!--修改业主信息的模态框-->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">修改业主信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="ownername">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="ownersex">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">电话</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="ownerphone">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期</label>
									<div class="col-sm-9">
										<input type="date" class="form-control" id="ownerbirthday">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">身份证号</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="owneridcard">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">籍贯</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="ownernativeplace">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">工作单位</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="ownerworkplace">
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
			<!-- 新增业主信息的模态框 -->
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
									<label class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addownername">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">楼栋</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addbuildnumber" @click="gethouseinfoofbuild()">
											<option v-for="listbuild in listbuildinginfo">{{listbuild.buildNumber}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房间号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addhousenumber">
											<option v-for="listhouseinfoofbuild in listhouseinfoofbuild">{{listhouseinfoofbuild.houseNumber}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addownersex">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">电话</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addownerphone">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期</label>
									<div class="col-sm-9">
										<input type="date" class="form-control" id="addownerbirthday">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">身份证号</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addowneridcard">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">籍贯</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addownernativeplace">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">工作单位</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addownerworkplace">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="saveaddownerinfo()" >确定</button>
						</div>
					</div>
				</div>
			</div>
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
				listhouseownerinfo:[],//所有业主信息
				infotomodal:[],
				changeid:0,
				listbuildinginfo:[],//所有的楼栋信息
				listhouseinfoofbuild:[]//根据楼栋号查出的所有的房间信息
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/houseownerinfo/getallhouseownerinfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listhouseownerinfo = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				change:function(ownerId,ownerName,ownerSex,ownerPhone,ownerBirthday,ownerIdCard,ownerNativePlace,ownerWorkPlace){
					app.changeid=ownerId;
					$("#ownername").val(ownerName);
					$("#ownersex").val(ownerSex==1?'男':'女');
					$("#ownerphone").val(ownerPhone);
					$("#ownerbirthday").val(ownerBirthday);
					$("#owneridcard").val(ownerIdCard);
					$("#ownernativeplace").val(ownerNativePlace);
					$("#ownerworkplace").val(ownerWorkPlace);
				},
				savechange:function(){
					var ownerId=app.changeid;
					var ownerName=$("#ownername").val();
					var ownerSex=$("#ownersex").val()=='男'?1:0;
					var ownerPhone=$("#ownerphone").val();
					var ownerBirthday=$("#ownerbirthday").val();
					var ownerIdCard=$("#owneridcard").val();
					var ownerNativePlace=$("#ownernativeplace").val();
					var ownerWorkPlace=$("#ownerworkplace").val();
					$.ajax({
						url:'/community/houseownerinfo/updatehouseownerinfo',
						type:'POST',
						dataType:'JSON',
						data:{"ownerId":ownerId,"ownerName":ownerName,"ownerSex":ownerSex,"ownerPhone":ownerPhone,"ownerBirthday":ownerBirthday,
							"ownerIdCard":ownerIdCard,"ownerNativePlace":ownerNativePlace,"ownerWorkPlace":ownerWorkPlace},
						success : function(result) {
							alert(result.msg);
							app.get();
						}
					});
				},
				//点击新增按钮，查询所有楼栋号，所有房间号
				add:function(){
					//获取所有的楼栋信息，主要是楼栋号
					$.ajax({
						url : '/community/buildinginfo/getallbuildinginfo',
						type : 'GET',
						dataType : 'JSON',
						async:false,
						success : function(result) {
							app.listbuildinginfo = result;
							//点击新增按钮时，同时查询默认显示的楼号所属的房间号的所有信息
							var buildNumber=result[0].buildNumber;
							$.ajax({
								url : '/community/houseinfo/getallhouseinfoofbuild',
								type : 'GET',
								dataType : 'JSON',
								data:{"buildNumber":buildNumber},
								success : function(result) {
									app.listhouseinfoofbuild = result;
								},
								error : function() {
									console.log("请求失败处理");
								}
							});
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击楼号下拉框时同步更新另一个房间号下拉框里的内容
				gethouseinfoofbuild:function(){
					//根据楼栋号，获取所有的房间信息，主要是房间号
					var buildNumber=$("#addbuildnumber").val();
					$.ajax({
						url : '/community/houseinfo/getallhouseinfoofbuild',
						type : 'GET',
						dataType : 'JSON',
						data:{"buildNumber":buildNumber},
						success : function(result) {
							app.listhouseinfoofbuild = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//确定新增的业主信息
				saveaddownerinfo:function(){
					var ownerName=$("#addownername").val();
					var houseNumber=$("#addhousenumber").val();
					var ownerSex=$("#addownersex").val()=='男'?1:0;
					var ownerPhone=$("#addownerphone").val();
					var ownerBirthday=$("#addownerbirthday").val();
					var ownerIdCard=$("#addowneridcard").val();
					var ownerNativePlace=$("#addownernativeplace").val();
					var ownerWorkPlace=$("#addownerworkplace").val();
					$.ajax({
						url : '/community/houseownerinfo/inserthouseownerinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"ownerName":ownerName,"houseNumber":houseNumber,"ownerSex":ownerSex,"ownerPhone":ownerPhone,"ownerBirthday":ownerBirthday,
							"ownerIdCard":ownerIdCard,"ownerNativePlace":ownerNativePlace,"ownerWorkPlace":ownerWorkPlace},
						success : function(result) {
							alert(result.msg);
							app.get();
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