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
		<div class="row">
			<div>
				<button type="button" class="btn btn-default" style="margin-bottom: -10%" data-toggle="modal" data-target="#myModal1">报修</button>
				<h1 style="text-align: center;">报修信息</h1>
			</div>
			<table class="table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
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
						<td>{{list.repairGoods}}</td>
						<td>{{list.repairTime}}</td>
						<td>{{list.repairReason}}</td>
						<td>{{list.repairState==0?'未受理':(list.repairState==1?'已受理':'已解决')}}</td>
						<td>{{list.admin==null?'':list.admin.adminName}}</td>
						<td>{{list.repairSettleTime==null?'':list.repairSettleTime}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" @click="change(list.repairId,list.repairGoods,list.repairReason)" data-toggle="modal" data-target="#myModal2">修改</button>
                            <button type="button" class="btn btn-danger btn-sm" @click="deleterepair(list.repairId)">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 新增报修信息的模态框 -->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">报修</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">报修物品</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addrepairgood">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">报修原因</label>
									<div class="col-sm-9">
										<textarea class="form-control" id="addrepairresson" rows="6" style="min-width: 90%"></textarea>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="saveaddrepair()" >提交</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改报修信息的模态框 -->
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center">报修</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">报修物品</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="changerepairgood">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">报修原因</label>
									<div class="col-sm-9">
										<textarea class="form-control" id="changerepairresson" rows="6" style="min-width: 90%"></textarea>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savechangerepair()" >提交</button>
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
				listrepairinfo:[],//所有报修信息
				changeid:0
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/ownerrepair/getrepairinfo',
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
				//提交报修信息
				saveaddrepair:function(){
					var repairGoods = $("#addrepairgood").val();
					var repairReason = $("#addrepairresson").val();
					if(repairGoods==''||repairReason==''){
						alert("请把信息填写完整");
					}else{
						$.ajax({
	    					url:'/community/ownerrepair/insertrepair',
	    					type:'POST',
	    					dataType:'JSON',
	    					data:{"repairGoods":repairGoods,"repairReason":repairReason},
	    					success:function(result){
	    						alert(result.msg);
	    						app.get();
	    					},
	    					error:function(){
	    						console.log("请求失败处理");
	    					}
	    				});
					}
				},
				//点击修改按钮，将信息显示在模态框中
				change:function(repairId,repairGoods,repairReason){
					app.changeid = repairId;
					$("#changerepairgood").val(repairGoods);
					$("#changerepairresson").val(repairReason);
				},
				//提交修改信息
				savechangerepair:function(){
					var repairId = app.changeid;
					var repairGoods = $("#changerepairgood").val();
					var repairReason = $("#changerepairresson").val();
					if(repairGoods == ''||repairReason==''){
						alert("请将信息填写完整！");
					}else{
						$.ajax({
	    					url:'/community/ownerrepair/updaterepair',
	    					type:'POST',
	    					dataType:'JSON',
	    					data:{"repairId":repairId,"repairGoods":repairGoods,"repairReason":repairReason},
	    					success:function(result){
	    						alert(result.msg);
	    						app.get();
	    					},
	    					error:function(){
	    						console.log("请求失败处理");
	    					}
	    				});
					}
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