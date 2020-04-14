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
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>汇总</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;缴费管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;汇总</span></h5>
        </div>
        <div class="row">
			<button type="button" class="btn btn-success">一键缴费</button>
		</div>
		<div class="row">
			<table class="col-xs-12 col-sm-12 col-md-12 table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">楼栋号</th>
						<th class="text-center">单元号</th>
						<th class="text-center">房间号</th>
						<th class="text-center">业主</th>
						<th class="text-center">应缴金额(元)</th>
						<th class="text-center">实缴金额(元)</th>
						<th class="text-center">欠费金额(元)</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listpaysuminfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.houseOwner.houseInfo.buildInfo.buildNumber}}</td>
						<td>{{list.houseOwner.houseInfo.houseUnit}}</td>
						<td>{{list.houseOwner.houseInfo.houseNumber}}</td>
						<td>{{list.houseOwner.ownerName}}</td>
						<td>{{list.payInfoSumPayable==null?0:list.payInfoSumPayable}}</td>
						<td>{{list.payInfoSumPaid==null?0:list.payInfoSumPaid}}</td>
						<td>{{list.payInfoSumPayable-list.payInfoSumPaid}}</td>
						<td>
							<button type="button" class="btn btn-success btn-sm" v-if="list.payState==1" disabled="disabled">已缴费</button>
							<button type="button" class="btn btn-success btn-sm" v-else @click="Paid(list.payId)">一键缴费</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="../static/js/vue.min.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listpaysuminfo:[]//所有汇总信息
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/payinfosum/getallpayinfosum',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listpaysuminfo = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击已缴费按钮，修改缴费状态为已缴费，由管理员操作的缴费方式默认为现金
				Paid:function(payId){
					$.ajax({
						url : '/community/payinfosum/updatepaystate',
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