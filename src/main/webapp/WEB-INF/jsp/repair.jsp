<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>报修信息</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>报修管理</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;小区管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;报修管理</span></h5>
        </div>
        <div class="row" style="background-color: white;padding-left: 10px;margin-bottom: 20px;">
            <h4>查询条件</h4><hr>
            <form class="form-inline" style="padding-bottom: 25px;">
            	<input type="text" class="form-control" id="likeownername" placeholder="请输入报修人姓名">
                <input type="text" class="form-control" id="likebuildnumber" placeholder="请输入楼栋编号">
                <input type="text" class="form-control" id="likehouseunit" placeholder="请输入单元号">
                <input type="text" class="form-control" id="likehousenumber" placeholder="请输入房间号">
                <input type="text" class="form-control" id="likerepairgoods" placeholder="请输入报修物品名">
                <button type="button" class="btn btn-default" @click="getrepairinfolike()">查询</button>
            </form>
        </div>
        <div class="row">
			<button type="button" class="btn btn-danger" @click="checkdelete()">批量删除</button>
		</div>
		<div class="row">
			<table class="table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" v-model="checked" @click="checkedAll()">
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
							<input type="checkbox" v-model="arr" :value="list.repairId">
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
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listrepairinfo:[],//所有报修信息
				checked:false,
    			arr:[]
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/repairinfo/getallrepairinfo',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listrepairinfo = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
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
    			},
    			//模糊查询报修信息(报修人，楼栋号，单元号，房间号，报修物品)
    			getrepairinfolike : function(){
    				var ownerName = $("#likeownername").val();
    				var buildNumber = $("#likebuildnumber").val();
    				var houseUnit = $("#likehouseunit").val();
    				var houseNumber = $("#likehousenumber").val();
    				var repairGoods = $("#likerepairgoods").val();
    				$.ajax({
						url:'/community/repairinfo/getrepairinfolike',
						type:'POST',
						dataType:'JSON',
						data:{"ownerName":"%"+ownerName+"%","buildNumber":"%"+buildNumber+"%","houseUnit":"%"+houseUnit+"%",
							"houseNumber":"%"+houseNumber+"%","repairGoods":"%"+repairGoods+"%"},
						success : function(result) {
							app.listrepairinfo = result;
						}
					});
    			},
    			checkedAll : function(){
                    if(this.checked){//实现反选
                        this.arr=[];
                    }else{//实现全选
                        this.arr=[];
                        this.listrepairinfo.forEach( (item) => {
                            this.arr.push(item.repairId);
                        })
                    }
                },
                //批量删除
                checkdelete : function(){
                	var lisrepairId= app.arr;
                	if(lisrepairId==''){
                		alert("请选择要删除的项目！");
                	}else{
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/repairinfo/checkdelete',
    						contentType: "application/json;charset=utf-8",
    						data:JSON.stringify(lisrepairId),
    						success:function(result){
    							alert(result.msg);
    							app.get();
    						}
    					});
                	}
                }
			},
			watch:{//深度watcher
                arr:{
                    handler:function(val,oldval){
                        if(this.arr.length==this.listrepairinfo.length){
                            this.checked=true;
                        }else{
                            this.checked=false;
                        }
                    },
                    deep:true
                }
            }
		});
	</script>
  </body>
</html>