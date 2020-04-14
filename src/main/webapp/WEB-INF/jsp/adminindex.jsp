<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .Summary{
            background-color: white;
            height: 100%;
            box-shadow: darkgrey 10px 10px 30px 5px;
            padding-top: 10px;
            padding-left: 10px;
            padding-right: 10px;
            padding-bottom: 10px;
            margin-top: 20px;
        }
        .allSummary{
            height: 150px;
        }
    </style>

</head>

<body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
        <div class="row allSummary">
            <div class="col-xs-3 col-sm-3 col-sm-md-3">
                <div class="Summary">
                    <div>
                        <span style="float: right;margin-right: 100px;margin-left: 30px;font-size: 18px;margin-top: -6px;">{{houseInfoSum}}</span>
                        <span class="label label-success" style="float: right;font-size: 13px;margin-top: -4px;">总数</span>
                        <h5>房屋</h5>
                        <hr>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-sm-md-6">
                            <span style="font-size: 20px;">{{nullHouse}}</span><br>
                            <small>空闲</small>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-sm-md-6">
                            <span style="font-size: 20px;">{{houseInfoSum-nullHouse}}</span><br>
                            <small>已售</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-3 col-sm-3 col-sm-md-3">
                <div class="Summary">
                    <div>
                        <span style="float: right;margin-right: 100px;margin-left: 30px;font-size: 18px;margin-top: -6px;">{{ownerSum}}</span>
                        <span class="label label-success" style="float: right;font-size: 13px;margin-top: -4px;">总数</span>
                        <h5>业主</h5>
                        <hr>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-sm-md-6">
                            <span style="font-size: 20px;">{{manOwnerSum}}</span><br>
                            <small>男</small>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-sm-md-6">
                            <span style="font-size: 20px;">{{ownerSum-manOwnerSum}}</span><br>
                            <small>女</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-3 col-sm-3 col-sm-md-3">
                <div class="Summary">
                    <div>
                        <span style="float: right;margin-right: 100px;margin-left: 30px;font-size: 18px;margin-top: -6px;">{{residentSum}}</span>
                        <span class="label label-success" style="float: right;font-size: 13px;margin-top: -4px;">总数</span>
                        <h5>住户</h5>
                        <hr>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-sm-md-6">
                            <span style="font-size: 20px;">{{manResidentSum}}</span><br>
                            <small>男</small>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-sm-md-6">
                            <span style="font-size: 20px;">{{residentSum-manResidentSum}}</span><br>
                            <small>女</small>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-3 col-sm-3 col-sm-md-3">
                <div class="Summary">
                    <div>
                        <span style="float: right;margin-right: 100px;margin-left: 30px;font-size: 18px;margin-top: -6px;">{{parkingSum}}</span>
                        <span class="label label-success" style="float: right;font-size: 13px;margin-top: -4px;">总数</span>
                        <h5>停车位</h5>
                        <hr>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-sm-md-6">
                            <span style="font-size: 20px;">{{nullParkingSum}}</span><br>
                            <small>空闲</small>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-sm-md-6">
                            <span style="font-size: 20px;">{{parkingSum-nullParkingSum}}</span><br>
                            <small>已售</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    	<div class="row">
			<div>
				<h1 style="text-align: center;">报修信息</h1>
			</div>
			<table class="table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th>
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">报修人</th>
						<th class="text-center">楼栋号</th>
						<th class="text-center">单元号</th>
						<th class="text-center">房间号</th>
						<th class="text-center">手机号</th>
						<th class="text-center">报修物品</th>
						<th class="text-center">报修时间</th>
						<th class="text-center">报修原因</th>
						<th class="text-center">状态</th>
						<th class="text-center">受理人</th>
						<th class="text-center">解决时间</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listrepairinfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.houseOwner.ownerName}}</td>
						<td>{{list.houseOwner.houseInfo.buildInfo.buildNumber}}</td>
						<td>{{list.houseOwner.houseInfo.houseUnit}}</td>
						<td>{{list.houseOwner.houseInfo.houseNumber}}</td>
						<td>{{list.houseOwner.ownerPhone}}</td>
						<td>{{list.repairGoods}}</td>
						<td>{{list.repairTime}}</td>
						<td>{{list.repairReason}}</td>
						<td>{{list.repairState==0?'未受理':(list.repairState==1?'已受理':'已解决')}}</td>
						<td>{{list.admin==null?'':list.admin.adminName}}</td>
						<td>{{list.repairSettleTime==null?'':list.repairSettleTime}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" @click="Acceptance(list.repairId)" v-if="list.repairState==0">受理</button>
							<button type="button" class="btn btn-primary btn-sm" disabled="disabled" v-else>受理</button>
                            <button type="button" class="btn btn-success btn-sm" @click="settled(list.repairId)" v-if="list.repairState==1">已解决</button>
                            <button type="button" class="btn btn-success btn-sm" disabled="disabled" v-else>已解决</button>
                            <button type="button" class="btn btn-danger btn-sm" @click="deleterepair(list.repairId)">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="../static/js/vue.min.js"></script>
    <script src="../static/js/vue-resource.min.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				houseInfoSum:0,//房间总数
				nullHouse:0,//空房间数
				ownerSum:0,//业主总数
				manOwnerSum:0,//男业主数
				residentSum:0,//住户总数
				manResidentSum:0,//男住户数
				parkingSum:0,//停车位总数
				nullParkingSum:0,//空闲停车位数
				listrepairinfo:[]//报修信息
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function(){
					//房间总数
					this.$http.get("http://localhost:8080/community/adminindex/gethouseinfosum").then(function(result){
						app.houseInfoSum=result.body;
					},function(result){
						console.log("请求失败处理");
					});
					//房间空闲数
					this.$http.get("http://localhost:8080/community/adminindex/getnullhouseinfo").then(function(result){
						app.nullHouse=result.body;
					},function(result){
						console.log("请求失败处理");
					});
					//业主总数
					this.$http.get("http://localhost:8080/community/adminindex/getownersum").then(function(result){
						app.ownerSum=result.body;
					},function(result){
						console.log("请求失败处理");
					});
					//男业主数
					this.$http.get("http://localhost:8080/community/adminindex/getmanofowner").then(function(result){
						app.manOwnerSum=result.body;
					},function(result){
						console.log("请求失败处理");
					});
					//住户总数
					this.$http.get("http://localhost:8080/community/adminindex/getresidentsum").then(function(result){
						app.residentSum=result.body;
					},function(result){
						console.log("请求失败处理");
					});
					//男住户数
					this.$http.get("http://localhost:8080/community/adminindex/getmanresidentsum").then(function(result){
						app.manResidentSum=result.body;
					},function(result){
						console.log("请求失败处理");
					});
					//停车位总数
					this.$http.get("http://localhost:8080/community/adminindex/getparkingsum").then(function(result){
						app.parkingSum=result.body;
					},function(result){
						console.log("请求失败处理");
					});
					//空闲停车位数
					this.$http.get("http://localhost:8080/community/adminindex/getnullparkingsum").then(function(result){
						app.nullParkingSum=result.body;
					},function(result){
						console.log("请求失败处理");
					});
					this.$http.get("http://localhost:8080/community/repairinfo/getallrepairinfo").then(function(result){
						app.listrepairinfo = result.body;
					},function(result){
						console.log("请求失败处理！");
					});
				},
				//受理报修
    			Acceptance:function(repairId){
    				$.ajax({
    					url:'/community/repairinfo/acceptance',
    					type:'POST',
    					dataType:'JSON',
    					data:{"repairId":repairId},
    					success:function(result){
    						alert(result.msg);
    						app.get();
    					},
    					error:function(){
    						console.log("请求失败处理");
    					}
    				});
    			},
    			//报修已解决
    			settled:function(repairId){
    				$.ajax({
    					url:'/community/repairinfo/settled',
    					type:'POST',
    					dataType:'JSON',
    					data:{"repairId":repairId},
    					success:function(result){
    						alert(result.msg);
    						app.get();
    					},
    					error:function(){
    						console.log("请求失败处理");
    					}
    				});
    			},
    			//根据报修id删除报修信息
    			deleterepair:function(repairId){
    				$.ajax({
    					url:'/community/repairinfo/deleterepair',
    					type:'POST',
    					dataType:'JSON',
    					data:{"repairId":repairId},
    					success:function(result){
    						alert(result.msg);
    						app.get();
    					},
    					error:function(){
    						console.log("请求失败处理");
    					}
    				});
    			}
			}
		});
	</script>
</body>

</html>