<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>缴费方式</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
		<div class="row">
			<div>
				<button type="button" class="btn btn-default" style="margin-bottom: -10%" data-toggle="modal" data-target="#addpaymethod">新增</button>
				<h1 style="text-align: center;">缴费方式</h1>
			</div>
			<table class="col-xs-12 col-sm-12 col-md-12 table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">方式</th>
						<th class="text-center">备注</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listpaymethodinfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.methodName}}</td>
						<td>{{list.methodRemarks==null?'':list.methodRemarks}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#changeModal" @click="changemodal(list.methodId,list.methodName,list.methodRemarks)">修改</button>
							<button type="button" class="btn btn-danger btn-sm" @click="deletepaymethod(list.methodId)">删除</button>
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
									<label class="col-sm-3 control-label">方式</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="methodname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">备注</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="methodremarks">
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
			<div class="modal fade" id="addpaymethod" tabindex="-1" role="dialog"
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
									<label class="col-sm-3 control-label">方式</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addmethodname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">备注</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addmethodremarks">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="addpaymethod()">确定</button>
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
				listpaymethodinfo:[],//所有支付方式
				clickbuttonid:0//点击按钮的项目id
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/paymethodinfo/getallpaymethodinfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listpaymethodinfo = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击修改按钮，显示信息在模态框中
				changemodal:function(methodId,methodName,methodRemarks){
					app.clickbuttonid=methodId;
					$("#methodname").val(methodName);
					$("#methodremarks").val(methodRemarks);
				},
				//保存修改的内容
				savechange:function(){
					var methodId = app.clickbuttonid;
					var methodName = $("#methodname").val();
					var methodRemarks = $("#methodremarks").val();
					$.ajax({
						url : '/community/paymethodinfo/updatepaymethodinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"methodId":methodId,"methodName":methodName,"methodRemarks":methodRemarks},
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
				deletepaymethod:function(methodId){
					$.ajax({
						url : '/community/paymethodinfo/deletepaymethodinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"methodId":methodId},
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
				addpaymethod:function(){
					var methodName = $("#addmethodname").val();
					var methodRemarks = $("#addmethodremarks").val();
					$.ajax({
						url : '/community/paymethodinfo/insertpaymethodinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"methodName":methodName,"methodRemarks":methodRemarks},
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