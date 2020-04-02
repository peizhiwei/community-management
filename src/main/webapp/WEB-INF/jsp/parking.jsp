<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>停车位信息</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
		<div class="row">
			<div>
				<button type="button" class="btn btn-default" style="margin-bottom: -10%" data-toggle="modal" data-target="#myModal2">新增</button>
				<h1 style="text-align: center;">停车位信息</h1>
			</div>
			<table class="table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th>
							<input type="checkbox" value="">
						</th>
						<th class="col-md-1 text-center">序号</th>
						<th class="col-md-1 text-center">车位编号</th>
						<th class="col-md-1 text-center">业主</th>
						<th class="col-md-1 text-center">房间号</th>
						<th class="col-md-2 text-center">手机号</th>
						<th class="col-md-1 text-center">出售时间</th>
						<th class="col-md-1 text-center">价格(万)</th>
						<th class="col-md-1 text-center">状态</th>
						<th class="col-md-3 text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(parkinglist,index) in listparkinginfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{parkinglist.parkingNumber}}</td>
						<td>{{parkinglist.houseOwner==null?'':parkinglist.houseOwner.ownerName}}</td>
						<td>{{parkinglist.houseOwner==null?'':parkinglist.houseOwner.houseInfo.houseNumber}}</td>
						<td>{{parkinglist.houseOwner==null?'':parkinglist.houseOwner.ownerPhone}}</td>
						<td>{{parkinglist.parkingSellTime==''?'':parkinglist.parkingSellTime}}</td>
						<td>{{parkinglist.parkingPrice}}</td>
						<td>{{parkinglist.parkingstate==1?'已售':'待售'}}</td>
						<td>
							<!-- 更改业主按钮是否禁用 -->
							<button type="button" class="btn btn-warning btn-sm" data-toggle="modal"
                                data-target="#changeownerModal" @click="change(parkinglist.parkingId,parkinglist.houseOwner.ownerName,parkinglist.houseOwner.houseInfo.houseNumber,
                                parkinglist.parkingSellTime,parkinglist.parkingPrice)" v-if="parkinglist.houseOwner!=null">更改业主</button>
                            <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" disabled="disabled"
                                data-target="#changeownerModal" @click="change(parkinglist.parkingId,parkinglist.houseOwner.ownerName,parkinglist.houseOwner.houseInfo.houseNumber,
                                parkinglist.parkingSellTime,parkinglist.parkingPrice)" v-else>更改业主</button>
                                
                            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#changepricemodal" @click="changeprice(parkinglist.parkingId,parkinglist.parkingPrice)">调整价格</button>
                            <!-- 出售按钮是否禁用 -->
                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" disabled="disabled"
                                data-target="#sellmodal" @click="sell(parkinglist.parkingId,parkinglist.parkingPrice)" v-if="parkinglist.houseOwner!=null">出售</button>
                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                                data-target="#sellmodal" @click="sell(parkinglist.parkingId,parkinglist.parkingPrice)" v-else>出售</button>
                            <!-- 出售按钮是否禁用 -->
                            <button type="button" class="btn btn-success btn-sm" data-toggle="modal" disabled="disabled"
                                data-target="#Takebackmodal" @click="takeback(parkinglist.parkingId)" v-if="parkinglist.houseOwner==null">收回</button>
                            <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                                data-target="#Takebackmodal" @click="takeback(parkinglist.parkingId)" v-else>收回</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!--更改车位业主的模态框-->
			<div class="modal fade" id="changeownerModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">更改业主</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">业主</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="parkingownername" @click="selecthousenumber()">
											<option class="form-control" v-for="onlyhouseownername in listonlyhouseownername">{{onlyhouseownername.ownerName}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房间号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="parkingownernumber">
											<option class="form-control" v-for="list in listhouseinfo">{{list.houseNumber==''?'':list.houseNumber}}</option>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="savechange" @click="savechangeowner()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!--更改车位价格的模态框-->
			<div class="modal fade" id="changepricemodal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">调整价格</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">价格(万)</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="parkingprice">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="savechange" @click="savechangeprice()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!--出售车位的模态框-->
			<div class="modal fade" id="sellmodal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">出售车位</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">业主</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="sell_parkingownername" @click="selecthousenumber()">
											<option class="form-control" v-for="onlyhouseownername in listonlyhouseownername">{{onlyhouseownername.ownerName}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房间号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="sell_parkingownernumber">
											<option class="form-control" v-for="list in listhouseinfo">{{list.houseNumber==''?'':list.houseNumber}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出售时间</label>
									<div class="col-sm-9">
										<input id="sell_parkingselltime" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">价格(万)</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="sell_parkingprice">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="savechange" @click="savesell()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 新增停车位信息的模态框 -->
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">增加停车位信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">数量</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="sum">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">价格</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="price">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="saveaddparkinginfo()" >确定</button>
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
    <script language="javascript" type="text/javascript" src="../static/datepicker/WdatePicker.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listparkinginfo:[],//所有停车位信息
				infotomodal:[],
				changeownerid:0,//点击修改业主的业主id
				changepriceid:0,//点击调整价格的id
				changesell:0,//点击出售的id
				listonlyhouseownername:[],//所有的业主名称，去掉重复的
				listhouseinfo:[]//根据业主名称查出的所有房间信息
			},
			mounted : function() {
				this.get();
			},
			methods : {
				//获取所有车位信息
				get : function() {
					$.ajax({
						url : '/community/parkinginfo/getallparkinginfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listparkinginfo = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击更改业主按钮，显示信息在模态框上
				change:function(parkingId,ownerName,houseNumber,parkingSellTime,parkingPrice){
					console.log(ownerName==null?'':ownerName);
					app.changeownerid=parkingId;
					$("#parkingownername").val(ownerName==null?'':ownerName);
					$("#parkingownernumber").val(houseNumber);
					//获取所有业主名称，去掉重复的
					$.ajax({
						url : '/community/houseownerinfo/getallonlyhouseownername',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listonlyhouseownername = result;
							var ownerName = result[0].ownerName;
							//点击按钮时根据查询到的业主名称查询所有的房间信息
							$.ajax({
								url : '/community/houseinfo/getallhouseinfoaccordingownername',
								type : 'GET',
								dataType : 'JSON',
								data:{"ownerName":ownerName},
								success : function(result) {
									app.listhouseinfo = result;
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
				//更换车位业主时点击业主下拉框，同步更新房间号信息
				selecthousenumber:function(){
					var ownerName = $("#parkingownername").val();
					$.ajax({
						url : '/community/houseinfo/getallhouseinfoaccordingownername',
						type : 'GET',
						dataType : 'JSON',
						data:{"ownerName":ownerName},
						success : function(result) {
							app.listhouseinfo = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//出售车位时点击业主下拉框，同步更新房间号信息
				selecthousenumber:function(){
					var ownerName = $("#sell_parkingownername").val();
					$.ajax({
						url : '/community/houseinfo/getallhouseinfoaccordingownername',
						type : 'GET',
						dataType : 'JSON',
						data:{"ownerName":ownerName},
						success : function(result) {
							app.listhouseinfo = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//保存修改停车位业主
				savechangeowner:function(){
					var parkingId=app.changeownerid;
					var ownerName=$("#parkingownername").val();
					var houseNumber=$("#parkingownernumber").val();
					$.ajax({
						url:'/community/parkinginfo/updateparkinginfo',
						type:'POST',
						dataType:'JSON',
						data:{"parkingId":parkingId,"houseNumber":houseNumber,},
						success : function(result) {
							alert(result.msg);
							app.get();
						}
					});
				},
				//点击调整价格按钮，显示信息
				changeprice:function(parkingId,parkingPrice){
					app.changepriceid=parkingId;
					$("#parkingprice").val(parkingPrice);
					
				},
				//保存调整的价格
				savechangeprice:function(){
					var parkingPrice = $("#parkingprice").val();
					var parkingId = app.changepriceid;
					$.ajax({
						url:'/community/parkinginfo/updateparkingprice',
						type:'POST',
						dataType:'JSON',
						data:{"parkingId":parkingId,"parkingPrice":parkingPrice},
						success:function(result){
							alert(result.msg);
							app.get();
						},
						error:function(){
							console.log("请求失败处理！");
						}
					});
					
				},
				//点击出售按钮，显示业主信息和价格在模态框中
				sell:function(parkingId,parkingPrice){
					app.changesell=parkingId;
					var myDate = new Date();
					var time = myDate.toLocaleDateString().split('/').join('-');//将1970/08/08转化成1970-08-08
					$("#sell_parkingprice").val(parkingPrice);
					$("#sell_parkingselltime").val(time);
					//获取所有业主名称，去掉重复的
					$.ajax({
						url : '/community/houseownerinfo/getallonlyhouseownername',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listonlyhouseownername = result;
							var ownerName = result[0].ownerName;
							//点击按钮时根据查询到的业主名称查询所有的房间信息
							$.ajax({
								url : '/community/houseinfo/getallhouseinfoaccordingownername',
								type : 'GET',
								dataType : 'JSON',
								data:{"ownerName":ownerName},
								success : function(result) {
									app.listhouseinfo = result;
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
				//车位出售确定按钮
				savesell:function(){
					var parkingId = app.changesell;
					var ownerName = $("#sell_parkingownername").val();
					var houseNumber = $("#sell_parkingownernumber").val();
					var parkingSellTime = $("#sell_parkingselltime").val();
					var parkingPrice = $("#sell_parkingprice").val();
					$.ajax({
						url : '/community/parkinginfo/sellupdateparkinginfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"parkingId":parkingId,"ownerName":ownerName,"houseNumber":houseNumber,"parkingSellTime":parkingSellTime,"parkingPrice":parkingPrice},
						success : function(result) {
							alert(result.msg);
							app.get();
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//收回车位
				takeback:function(parkingId){
					$.ajax({
						url:'/community/parkinginfo/takebackupdateparkinginfo',
						type:'POST',
						dataType:'JSON',
						data:{"parkingId":parkingId},
						success : function(result) {
							alert(result.msg);
							app.get();
						}
					});
				},
				//确定新增车位信息
				saveaddparkinginfo:function(){
					var parkingSum=$("#sum").val();
					var parkingPrice=$("#price").val();
					$.ajax({
						url : '/community/parkinginfo/insertparkinginfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"parkingSum":parkingSum,"parkingPrice":parkingPrice},
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