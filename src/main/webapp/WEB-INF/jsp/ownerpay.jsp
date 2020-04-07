<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>缴费详情</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
  	<div>
		<h1 style="text-align: center;">缴费详情</h1>
	</div>
    <div class="container-fluid" id="app">
    	<div class="row">
    		<button type="button" class="btn btn-success" @click="allpay()">一键缴费</button>
    	</div>
		<div class="row">
			<table class="col-xs-12 col-sm-12 col-md-12 table table-striped table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">缴费项目</th>
						<th class="text-center">金额(元)</th>
						<th class="text-center">发布时间</th>
						<th class="text-center">截止时间</th>
						<th class="text-center">实际缴费时间</th>
						<th class="text-center">状态</th>
						<th class="text-center">缴费方式</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listpayinfo">
						<td>
							<input type="checkbox">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.payInfo.payType.payTypeName}}</td>
						<td>{{list.payInfo.payInfoMoney}}</td>
						<td>{{list.payInfo.payInfoStartTime}}</td>
						<td>{{list.payInfo.payInfoEndTime}}</td>
						<td>{{list.payTime==null?'':list.payTime}}</td>
						<td>{{list.payState==0?'未缴费':'已缴费'}}</td>
						<td>{{list.payMethod==null?'':list.payMethod.methodName}}</td>
						<td>
							<button type="button" class="btn btn-success btn-sm" v-if="list.payState==1" disabled="disabled">缴费</button>
							<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal1" v-else @click="Paid(list.payId,list.payInfo.payInfoMoney)">缴费</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 缴费的模态框 -->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">缴费</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">金额</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="summoney" disabled>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">方式</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="paymethod">
											<option class="form-control" v-for="list in listpaymethod" @click="selectmethod(list.methodId)">{{list.methodName}}</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savepaid()" >确定</button>
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
				listpayinfo:[],//所有支付方式
				listpaymethod:[],
				onepayid:'0',//点击单个的缴费按钮，接收其id
				methodid:'0'//支付方式id
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/ownerpay/getpayinfodetails',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listpayinfo = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击缴费按钮，弹出模态框
				Paid:function(payId,payInfoMoney){
					app.onepayid=payId;
					$("#summoney").val(payInfoMoney);
					//查询出所有非现金的支付方式
					$.ajax({
						url : '/community/ownerpay/getallpaymethod',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listpaymethod=result;
							app.methodid=result[0].methodId;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//选择支付方式，保留支付方式的id
				selectmethod:function(methodId){
					app.methodid=methodId
				},
				//确定缴费
				savepaid : function(){
					var payId = app.onepayid;
					var methodId = app.methodid;
					$.ajax({
						url : '/community/ownerpay/paid',
						type : 'POST',
						dataType : 'JSON',
						data:{"payId":payId,"methodId":methodId},
						success : function(result) {
							alert(result.msg);
							app.get();
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//一键缴费
				allpay : function(){
					
				}
			}
		});
	</script>
  </body>
</html>