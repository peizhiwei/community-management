<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>汇总</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
		<div class="row">
			<div>
				<h1 style="text-align: center;">汇总</h1>
			</div>
			<table class="col-xs-12 col-sm-12 col-md-12 table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">业主</th>
						<th class="text-center">房间号</th>
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
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.houseOwner.ownerName}}</td>
						<td>{{list.houseOwner.houseInfo.houseNumber}}</td>
						<td>{{list.payInfo.payType.payTypeName}}</td>
						<td>{{list.payInfo.payInfoMoney}}</td>
						<td>{{list.payInfo.payInfoStartTime}}</td>
						<td>{{list.payInfo.payInfoEndTime}}</td>
						<td>{{list.payTime==null?'':list.payTime}}</td>
						<td>{{list.payState==0?'未缴费':'已缴费'}}</td>
						<td>{{list.payMethod==null?'':list.payMethod.methodName}}</td>
						<td>
							<button type="button" class="btn btn-success btn-sm" v-if="list.payState==1" disabled="disabled">已缴费</button>
							<button type="button" class="btn btn-success btn-sm" v-else @click="Paid(list.payId)">已缴费</button>
						</td>
					</tr>
				</tbody>
			</table>
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
				listpayinfo:[]//所有支付方式
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/payinfodetails/getallpayinfodetails',
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
				//点击已缴费按钮，修改缴费状态为已缴费，由管理员操作的缴费方式默认为现金
				Paid:function(payId){
					$.ajax({
						url : '/community/payinfodetails/updatepaystate',
						type : 'POST',
						dataType : 'JSON',
						data:{"payId":payId},
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