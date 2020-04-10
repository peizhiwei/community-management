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
				nullParkingSum:0//空闲停车位数
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
				}
			}
		});
	</script>
</body>

</html>