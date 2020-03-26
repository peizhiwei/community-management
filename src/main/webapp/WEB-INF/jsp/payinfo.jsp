<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>缴费信息</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
		<div class="row">
			<div>
				<button type="button" class="btn btn-default" style="margin-bottom: -10%" data-toggle="modal" data-target="#addpaymethod">发布缴费信息</button>
				<h1 style="text-align: center;">缴费信息</h1>
			</div>
			<table class="col-xs-12 col-sm-12 col-md-12 table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">缴费项目</th>
						<th class="text-center">缴费金额</th>
						<th class="text-center">发布时间</th>
						<th class="text-center">截止时间</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listpayinfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.payType.payTypeName}}</td>
						<td>{{list.payInfoMoney}}</td>
						<td>{{list.payInfoStartTime}}</td>
						<td>{{list.payInfoEndTime}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#changepayinfo" @click="changepayinfo(list.payInfoId,list.payType.payTypeName,list.payInfoMoney,list.payInfoEndTime)">修改</button>
							<button type="button" class="btn btn-danger btn-sm" @click="deletepayinfo(list.payInfoId)">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 修改缴费信息模态框 -->
			<div class="modal fade" id="changepayinfo" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">修改缴费信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">缴费项目</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="paytype">
											<option class="form-control" :value="list.payTypeName" v-for="list in listpaytype">{{list.payTypeName}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">金额</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="paymoney">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">截止时间</label>
									<div class="col-sm-9">
										<input id="payendtime" class="form-control" type="text" onclick="WdatePicker({minDate:'%y-%M-{%d+1}'})"/>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savechange()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 发布新的缴费信息模态框 -->
			<div class="modal fade" id="addpaymethod" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">发布缴费信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">缴费项目</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addpaytype">
											<option class="form-control" v-for="list in listpaytype">{{list.payTypeName}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">金额</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="addpaymoney">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">截止时间</label>
									<div class="col-sm-9">
										<input id="addpayendtime" class="form-control" type="text" onclick="WdatePicker({minDate:'%y-%M-{%d+1}'})"/>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="addpayinfo()">确定</button>
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
    <script src="../static/datepicker/WdatePicker.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listpayinfo:[],//所有支付信息
				listpaytype:[],//所有缴费类型
				changeid:0//点击修改按钮的那一项id
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/payinfo/getallpayinfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listpayinfo = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
					//查询所有缴费类别
					$.ajax({
						url : '/community/paytypeinfo/getallpaytypeinfo',
						type : 'GET',
						dataType : 'JSON',
						async:true,
						success : function(result) {
							app.listpaytype = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//发布缴费信息确定按钮
				addpayinfo:function(){
					var payTypeName = $("#addpaytype").val();
					var payMoney = $("#addpaymoney").val();
					var payEndTime = $("#addpayendtime").val();
					$.ajax({
						url : '/community/payinfo/insertpayinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"payTypeName":payTypeName,"payMoney":payMoney,"payEndTime":payEndTime},
						success : function(result) {
							alert(result.msg);
							app.get();
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击修改按钮，显示信息在模态框中
				changepayinfo:function(payInfoId,payTypeName,payInfoMoney,payInfoEndTime){
					app.changeid=payInfoId;
					$("#paytype").val(payTypeName);
					$("#paymoney").val(payInfoMoney);
					$("#payendtime").val(payInfoEndTime);
				},
				//点击确定按钮，保存要修改的内容
				savechange:function(){
					var payInfoId = app.changeid;
					var payTypeName = $("#paytype").val();
					var payMoney = $("#paymoney").val();
					var payEndTime = $("#payendtime").val();
					if(payTypeName==''||payMoney==''||payEndTime==''){
						alert("请将信息输入完整！");
					}else{
						$.ajax({
							url : '/community/payinfo/updatepayinfo',
							type : 'POST',
							dataType : 'JSON',
							data:{"payInfoId":payInfoId,"payTypeName":payTypeName,"payMoney":payMoney,"payEndTime":payEndTime},
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
				//删除缴费信息
				deletepayinfo:function(payInfoId){
					$.ajax({
						url : '/community/payinfo/deletepayinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"payInfoId":payInfoId},
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