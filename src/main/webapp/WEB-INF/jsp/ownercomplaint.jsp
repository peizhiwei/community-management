<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>投诉信息</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
		<div class="row">
			<div>
				<button type="button" class="btn btn-default" style="margin-bottom: -10%" data-toggle="modal" data-target="#myModal1">投诉</button>
				<h1 style="text-align: center;">投诉信息</h1>
			</div>
			<table class="table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" value="">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">投诉内容</th>
						<th class="text-center">投诉时间</th>
						<th class="text-center">解决时间</th>
						<th class="text-center">受理人</th>
						<th class="text-center">状态</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listcomplaintinfo">
						<td>
							<input type="checkbox" value="">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.complaintReason}}</td>
						<td>{{list.complaintTime}}</td>
						<td>{{list.complaintSettleTime==null?'':list.complaintSettleTime}}</td>
						<td>{{list.admin==null?'':list.admin.adminName}}</td>
						<td>{{list.complaintState==0?'未受理':(list.complaintState==1?'已受理':'已解决')}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" @click="changecomplaint(list.complaintId,list.complaintReason)" data-toggle="modal" data-target="#myModal2">修改</button>
                            <button type="button" class="btn btn-danger btn-sm" @click="deletecomplaint(list.complaintId)">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 新增投诉信息的模态框 -->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">投诉</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">投诉内容</label>
									<div class="col-sm-9">
										<textarea class="form-control" id="addcomplaint" rows="6" style="min-width: 90%"></textarea>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="saveaddcomplaint()" >提交</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改投诉信息的模态框 -->
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center">投诉</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">投诉内容</label>
									<div class="col-sm-9">
										<textarea class="form-control" id="changecomplaint" rows="6" style="min-width: 90%"></textarea>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savechangecomplaint()" >提交</button>
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
    		el:'#app',
    		data:{
    			listcomplaintinfo:[],//所有的投诉信息
    			changeid:0//接受点击修改的id
    		},
    		mounted:function(){
    			this.get();
    		},
    		methods:{
    			get:function(){
    				$.ajax({
    					url:'/community/ownercomplaint/getcomplaintinfo',
    					type:'POST',
    					dataType:'JSON',
    					success:function(result){
    						app.listcomplaintinfo=result;
    					},
    					error:function(){
    						console.log("请求失败处理");
    					}
    				});
    			},
    			//提交投诉内容
    			saveaddcomplaint:function(){
    				var complaintReason = $("#addcomplaint").val();
    				if(complaintReason==''){
    					alert("请填写投诉内容！");
    				}else{
    					$.ajax({
        					url:'/community/ownercomplaint/insertcomplaint',
        					type:'POST',
        					dataType:'JSON',
        					data:{"complaintReason":complaintReason},
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
    			//点击修改按钮，显示投诉信息在模态框中
    			changecomplaint:function(complaintId,complaintReason){
    				app.changeid = complaintId;
    				$("#changecomplaint").val(complaintReason);
    			},
    			//点击提交按钮，确定修改投诉信息
    			savechangecomplaint:function(){
    				var complaintId = app.changeid;
    				var complaintReason = $("#changecomplaint").val();
    				if(complaintReason==''){
    					alert("请填写投诉信息");
    				}else{
    					$.ajax({
        					url:'/community/ownercomplaint/updatecomplaint',
        					type:'POST',
        					dataType:'JSON',
        					data:{"complaintId":complaintId,"complaintReason":complaintReason},
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
    			//根据投诉信息id删除投诉信息
    			deletecomplaint:function(complaintId){
    				$.ajax({
    					url:'/community/complaintinfo/deletecomplaint',
    					type:'POST',
    					dataType:'JSON',
    					data:{"complaintId":complaintId},
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