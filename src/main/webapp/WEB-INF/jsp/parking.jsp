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
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>停车位信息</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;业主信息管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;停车位</span></h5>
        </div>
        <div class="row" style="background-color: white;padding-left: 10px;margin-bottom: 20px;">
            <h4>查询条件</h4><hr>
            <form class="form-inline" style="padding-bottom: 25px;">
            	<input type="text" class="form-control" id="likeparkingnumber" placeholder="请输入车位号">
                <input type="text" class="form-control" id="likeownername" placeholder="请输入业主姓名">
                <input type="text" class="form-control" id="likebuildnumber" placeholder="请输入楼栋编号">
                <input type="text" class="form-control" id="likehouseunit" placeholder="请输入单元号">
                <input type="text" class="form-control" id="likehousenumber" placeholder="请输入房间号">
                <button type="button" class="btn btn-default" @click="getparkinginfolike()">查询</button>
            </form>
        </div>
        <div class="row">
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal2">新增</button>
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">车位编号</th>
						<th class="text-center">业主</th>
						<th class="text-center">楼栋号</th>
						<th class="text-center">单元号</th>
						<th class="text-center">房间号</th>
						<th class="text-center">手机号</th>
						<th class="text-center">出售时间</th>
						<th class="text-center">价格(万)</th>
						<th class="text-center">状态</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listparkinginfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.parkingNumber}}</td>
						<td>{{list.houseOwner==null?'':list.houseOwner.ownerName}}</td>
						<td>{{list.houseOwner==null?'':list.houseOwner.houseInfo.buildInfo.buildNumber}}</td>
						<td>{{list.houseOwner==null?'':list.houseOwner.houseInfo.houseUnit}}</td>
						<td>{{list.houseOwner==null?'':list.houseOwner.houseInfo.houseNumber}}</td>
						<td>{{list.houseOwner==null?'':list.houseOwner.ownerPhone}}</td>
						<td>{{list.parkingSellTime==''?'':list.parkingSellTime}}</td>
						<td>{{list.parkingPrice}}</td>
						<td>{{list.parkingstate==1?'已售':'待售'}}</td>
						<td>
                            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#changepricemodal" @click="changeprice(list.parkingId,list.parkingPrice)">调整价格</button>
                            <!-- 出售按钮是否禁用 -->
                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" disabled="disabled"
                                data-target="#sellmodal" v-if="list.houseOwner!=null">出售</button>
                            <button type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                                data-target="#sellmodal" @click="sell(list.parkingId,list.parkingPrice)" v-else>出售</button>
                            <!-- 出售按钮是否禁用 -->
                            <button type="button" class="btn btn-success btn-sm" data-toggle="modal" disabled="disabled"
                                data-target="#Takebackmodal" v-if="list.houseOwner==null">收回</button>
                            <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                                data-target="#Takebackmodal" @click="takeback(list.parkingId)" v-else>收回</button>
						</td>
					</tr>
				</tbody>
			</table>
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
									<label class="col-sm-3 control-label">楼栋号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="sell_buildnumber" @click="selecthouseunit()">
											<option class="form-control" v-for="item in listbuildnumber">{{item}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">单元号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="sell_houseunit" @click="selecthousenumber()">
											<option class="form-control" v-for="item in listhouseunit">{{item}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房间号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="sell_housenumber" @click="selectownername">
											<option class="form-control" v-for="item in listhousenumber">{{item}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">业主</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="sell_ownername">
											<option class="form-control">{{ownerName}}</option>
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
    <script src="../static/js/vue.min.js"></script>
    <script src="../static/datepicker/WdatePicker.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listparkinginfo:[],//所有停车位信息
				changepriceid:0,//点击调整价格的id
				changesell:0,//点击出售的id
				listbuildnumber:[],//所有有住户的楼栋编号
				listhouseunit:[],//所有有住户的单元号
				listhousenumber:[],//所有有住户的房间号
				ownerName:'',//出售下拉框中的业主
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
					//查询所有有住户的楼栋编号，去掉重复的
					$.ajax({
						url : '/community/familyinfo/gethaveownerbuildnumber',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listbuildnumber = result;
							var buildNumber = result[0];
							//根据楼栋编号查询该栋楼中所有有住户的单元号
							$.ajax({
								url : '/community/familyinfo/gethaveownerhouseunitaccordingbuildnumber',
								type : 'GET',
								dataType : 'JSON',
								data:{"buildNumber":buildNumber},
								success : function(result) {
									app.listhouseunit = result;
									var houseUnit = result[0];
									//根据楼栋号，单元号，查询所有有住户的房间号
									$.ajax({
										url : '/community/familyinfo/gethaveownerhousenumber',
										type : 'GET',
										dataType : 'JSON',
										data:{"buildNumber":buildNumber,"houseUnit":houseUnit,},
										success : function(result) {
											app.listhousenumber = result;
											var houseNumber = result[0];
											//根据楼栋编号，单元号，房间号，查询业主名
											$.ajax({
												url : '/community/familyinfo/getownername',
												type : 'GET',
												dataType : 'JSON',
												data:{"buildNumber":buildNumber,"houseUnit":houseUnit,"houseNumber":houseNumber},
												success : function(result) {
													app.ownerName = result.msg;
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
				//点击楼栋编号下拉框，同步更新单元号,房间号，业主名信息
				selecthouseunit : function(){
					var buildNumber = $("#sell_buildnumber").val();
					$.ajax({
						url : '/community/familyinfo/gethaveownerhouseunitaccordingbuildnumber',
						type : 'GET',
						dataType : 'JSON',
						data:{"buildNumber":buildNumber},
						success : function(result) {
							app.listhouseunit = result;
							app.selecthousenumber();
							app.selectownername();
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击单元号下拉框，同步更新房间号，业主名信息
				selecthousenumber : function(){
					var buildNumber = $("#sell_buildnumber").val();
					var houseUnit = $("#sell_houseunit").val();
					$.ajax({
						url : '/community/familyinfo/gethaveownerhousenumber',
						type : 'GET',
						dataType : 'JSON',
						data:{"buildNumber":buildNumber,"houseUnit":houseUnit},
						success : function(result) {
							app.listhousenumber = result;
							app.selectownername();
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击房间号下拉框，同步更新业主名
				selectownername : function(){
					var buildNumber = $("#sell_buildnumber").val();
					var houseUnit = $("#sell_houseunit").val();
					var houseNumber=$("#sell_housenumber").val();
					$.ajax({
						url : '/community/familyinfo/getownername',
						type : 'GET',
						dataType : 'JSON',
						data:{"buildNumber":buildNumber,"houseUnit":houseUnit,"houseNumber":houseNumber},
						success : function(result) {
							app.ownerName = result.msg;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//车位出售确定按钮
				savesell:function(){
					var parkingId = app.changesell;
					var buildNumber = $("#sell_buildnumber").val();
					var houseUnit = $("#sell_houseunit").val();
					var houseNumber = $("#sell_housenumber").val();
					var ownerName = $("#sell_ownername").val();
					var parkingSellTime = $("#sell_parkingselltime").val();
					var parkingPrice = $("#sell_parkingprice").val();
					$.ajax({
						url : '/community/parkinginfo/sellupdateparkinginfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"parkingId":parkingId,"buildNumber":buildNumber,"houseUnit":houseUnit,"houseNumber":houseNumber,"ownerName":ownerName,"parkingSellTime":parkingSellTime,"parkingPrice":parkingPrice},
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
				},
				//模糊查询车位信息
				getparkinginfolike : function(){
					var parkingNumber = $("#likeparkingnumber").val();
					var ownerName = $("#likeownername").val();
					var buildNumber = $("#likebuildnumber").val();
					var houseUnit = $("#likehouseunit").val();
					var houseNumber = $("#likehousenumber").val();
					$.ajax({
						url:'/community/parkinginfo/getparkinginfolike',
						type:'POST',
						dataType:'JSON',
						data:{"parkingNumber":"%"+parkingNumber+"%","ownerName":"%"+ownerName+"%","buildNumber":"%"+buildNumber+"%",
							"houseUnit":"%"+houseUnit+"%","houseNumber":"%"+houseNumber+"%"},
						success : function(result) {
							app.listparkinginfo = result;
						}
					});
					
				}
			}
		});
	</script>
  </body>
</html>