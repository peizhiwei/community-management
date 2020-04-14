<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>缴费类别</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>缴费类别</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;缴费管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;缴费类别</span></h5>
        </div>
        <div class="row">
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#addpaytype">新增</button>
			<button type="button" class="btn btn-danger">一键删除</button>
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">类别</th>
						<th class="text-center">备注</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listpaytypeinfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.payTypeName}}</td>
						<td>{{list.payTypeRemarks==null?'':list.payTypeRemarks}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#changeModal" @click="changemodal(list.payTypeId,list.payTypeName,list.payTypeRemarks)">修改</button>
							<button type="button" class="btn btn-danger btn-sm" @click="deletepaytype(list.payTypeId)">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 修改收费类别信息的模态框 -->
			<div class="modal fade" id="changeModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">修改</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">类别</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="type">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">备注</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="remarks">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savechange()" >确定</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 新增收费类别信息的模态框 -->
			<div class="modal fade" id="addpaytype" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">新增</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">类别</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addtype">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">备注</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addremarks">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="addpaytype()">确定</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="../static/js/vue.min.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listpaytypeinfo:[],//所有缴费项目
				clickbuttonid:0//点击按钮的项目id
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/paytypeinfo/getallpaytypeinfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listpaytypeinfo = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击修改按钮，显示信息在模态框中
				changemodal:function(payTypeId,payTypeName,payTypeRemarks){
					app.clickbuttonid=payTypeId;
					$("#type").val(payTypeName);
					$("#remarks").val(payTypeRemarks);
				},
				//保存修改的内容
				savechange:function(){
					var payTypeId = app.clickbuttonid;
					var payTypeName = $("#type").val();
					var payTypeRemarks = $("#remarks").val();
					$.ajax({
						url : '/community/paytypeinfo/updatepayinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"payTypeId":payTypeId,"payTypeName":payTypeName,"payTypeRemarks":payTypeRemarks},
						success : function(result) {
							alert(result.msg);
							app.get();
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//删除收费类别
				deletepaytype:function(payTypeId){
					$.ajax({
						url : '/community/paytypeinfo/deletepaytypeinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"payTypeId":payTypeId},
						success : function(result) {
							alert(result.msg);
							app.get();
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//新增收费类别
				addpaytype:function(){
					var payTypeName = $("#addtype").val();
					var payTypeRemarks = $("#addremarks").val();
					$.ajax({
						url : '/community/paytypeinfo/insertpaytypeinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"payTypeName":payTypeName,"payTypeRemarks":payTypeRemarks},
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