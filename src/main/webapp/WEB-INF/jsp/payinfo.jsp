<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>缴费信息</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>缴费信息</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;缴费管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;缴费信息</span></h5>
        </div>
        <div class="row" style="background-color: white;padding-left: 10px;margin-bottom: 20px;">
            <h4 data-toggle="collapse" href="#collapseExample">筛选</h4><hr>
            <form id="collapseExample" class="form-inline collapse" style="padding-bottom: 25px;">
            	<select required="required" class="form-control" id="screen" @click="screen()">
            		<option value="0">请选择筛选条件</option>
					<option value="1">缴费项目</option>
					<option value="2">发布时间</option>
				</select>
				<select v-if="selectstate==1" required="required" class="form-control" id="selectpayname" v-for="list in listpaytype">
					<option>{{list.payTypeName}}</option>
				</select>
				<input type="text" v-if="selectstate==2" id="selectTime" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" class="form-control"/>
                <button type="button" class="btn btn-default" v-if="selectstate==0" disabled="disabled">查询</button>
                <button type="button" class="btn btn-default" @click="getpayintoselect()" v-else>查询</button>
            </form>
        </div>
        <div class="row">
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#addpaymethod">发布缴费信息</button>
			<button type="button" class="btn btn-danger" @click="checkdelete()">批量删除</button>
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" v-model="checked" @click="checkedAll()">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">缴费项目</th>
						<th class="text-center">缴费金额</th>
						<th class="text-center">发布时间</th>
						<th class="text-center">截止时间</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listpayinfo">
						<td>
							<input type="checkbox" v-model="arr" :value="list.payInfoId">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.payType.payTypeName}}</td>
						<td>{{list.payInfoMoney}}</td>
						<td>{{list.payInfoStartTime}}</td>
						<td>{{list.payInfoEndTime}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#changepayinfo" @click="changepayinfo(list.payInfoId,list.payType.payTypeName,list.payInfoMoney,list.payInfoEndTime)">修改</button>
							<button type="button" class="btn btn-danger btn-sm" @click="deletepayinfo(list.payInfoId)">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- 修改缴费信息模态框 -->
			<div class="modal fade" id="changepayinfo" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">修改缴费信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">缴费项目</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="paytype">
											<option class="form-control" :value="list.payTypeName" v-for="list in listpaytype">{{list.payTypeName}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">金额</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="paymoney">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">截止时间</label>
									<div class="col-sm-9">
										<input id="payendtime" class="form-control" type="text" onclick="WdatePicker({minDate:'%y-%M-{%d+1}'})"/>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="savechange()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 发布新的缴费信息模态框 -->
			<div class="modal fade" id="addpaymethod" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">发布缴费信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">缴费项目</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addpaytype">
											<option class="form-control" v-for="list in listpaytype">{{list.payTypeName}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">金额</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="addpaymoney">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">截止时间</label>
									<div class="col-sm-9">
										<input id="addpayendtime" class="form-control" type="text" onclick="WdatePicker({minDate:'%y-%M-{%d+1}'})"/>
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="addpayinfo()">确定</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="col-md-12" style="padding-top: 20px;">
					<h4>总记录数：{{total}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总页数：{{pagetotal}}</h4>
				</div>
			</div>
			<div class="col-md-6 text-right">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li @click="firstpage()"><a style="cursor:pointer;"><span aria-hidden="true">首页</span></a></li>
						<li><a style="cursor:pointer;" @click="previous()"><span>上一页</span></a></li>
						
						<li v-for="n in pageRange" v-if="n==page&&state==1" class="active"><span>{{n}}</span></li>
						<li v-else-if="state==1" @click="tiaozhuan(n)"><a style="cursor:pointer;">{{n}}</a></li>
						<li v-for="n_like in pageRange" v-if="n_like==likepage&&state==0" class="active"><span>{{n_like}}</span></li>
						<li v-else-if="state==0" @click="tiaozhuan(n_like)"><a style="cursor:pointer;">{{n_like}}</a></li>
						
						<li><a style="cursor:pointer;" aria-label="Next" @click="next()"><span aria-hidden="true">下一页</span></a></li>
						<li @click="lastpage()"><a style="cursor:pointer;"><span aria-hidden="true">尾页</span></a></li>
					</ul>
				</nav>
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
				listpayinfo:[],//所有支付信息
				listpaytype:[],//所有缴费类型
				changeid:0,//点击修改按钮的那一项id
				checked:false,
    			arr:[],
    			selectstate:0,//0,查询按钮灰色状态，1，缴费类别，2发布时间
    			page:1,//当前页
				size:6,//每页记录条数
				total:0,//记录总数
				pagetotal:0,//总页数
				begin:1,//起始页
				end:1,//显示的最大页
				pageRange:[],//页码显示的范围
				state:1,//1表示执行get方法，查询所有房间信息，0表示执行gethouseinfolike方法，模糊查询房间信息
				likepage:1,//当前页
				likesize:6//每页记录条数
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					var page = this.page;
					var size = this.size;
					$.ajax({
						url : '/community/payinfo/pagegetallpayinfo',
						type : 'GET',
						dataType : 'JSON',
						data:{"page":page,"size":size},
						success : function(result) {
							app.listpayinfo = result.rows;
							app.total = result.total;
							app.pagetotal=Math.ceil(app.total/app.size);//向上取整
							if((app.page-1)%5==0){
								app.begin=app.page;
								app.end = app.page+4;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									if(current>app.pagetotal)break;
									app.pageRange[i]=current;
									current++;
								}
							}
							if(app.page%5==0){
								app.begin=app.page-4;
								app.end=app.page;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									app.pageRange[i]=current;
									current++;
								}
							}
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
					//查询所有缴费类别
					$.ajax({
						url : '/community/paytypeinfo/getallpaytypeinfo',
						type : 'GET',
						dataType : 'JSON',
						async:true,
						success : function(result) {
							app.listpaytype = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//跳转到第一页，首页
				firstpage : function(){
					if(app.state==1){
						if(app.page==1){
							alert("已经是第一页了！");
						}else{
							app.page=1;
							app.get();
						}
					}else{
						if(app.likepage==1){
							alert("已经是第一页了！");
						}else{
							app.likepage=1;
							app.getpayintoselect();
						}
					}
				},
				//点击上一页
				previous : function(){
					if(app.state==1){
						if(app.page==1){
							alert("已经是第一页了！");
						}else{
							app.page -=1;
							app.get();
						}
					}else{
						if(app.likepage==1){
							alert("已经是第一页了！");
						}else{
							app.likepage-=1;
							app.getpayintoselect();
						}
					}
					
				},
				//跳转页面
				tiaozhuan : function(n){
					if(app.state==1){
						app.page=n;
						app.get();
					}else{
						app.likepage=n;
						app.getpayintoselect();
					}
				},
				//点击下一页
				next : function(){
					if(app.state==1){
						if(app.page==app.pagetotal){
							alert("已经是最后一页了！");	
						}else{
							app.page+=1;
							app.get();
						}
					}else{
						if(app.likepage==app.pagetotal){
							alert("已经是最后一页了！");
						}else{
							app.likepage+=1;
							app.getpayintoselect();
						}
					}
				},
				//跳转到最后一页，尾页
				lastpage : function(){
					if(app.state==1){
						if(app.page==app.pagetotal){
							alert("已经是最后一页了！");
						}else{
							app.page=app.pagetotal;
							if(app.page%5!=0&&(app.page-1)%5!=0){
								//跳转到最后一页，可能并不是完整的五个页码
								app.begin=(Math.floor(app.page/5))*5+1;//向下取整。例如：第七页，从第六页开始查询
								app.end=app.page;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									if(current>app.pagetotal)break;
									app.pageRange[i]=current;
									current++;
								}
							}
							app.get();
						}
					}else{
						if(app.likepage==app.pagetotal){
							alert("已经是最后一页了！");
						}else{
							app.likepage=app.pagetotal;
							if(app.likepage%5!=0&&(app.likepage-1)%5!=0){
								//跳转到最后一页，可能并不是完整的五个页码
								app.begin=(Math.floor(app.likepage/5))*5+1;//例如：第七页，从第六页开始查询
								app.end=app.likepage;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									if(current>app.pagetotal)break;
									app.pageRange[i]=current;
									current++;
								}
							}
							app.getpayintoselect();
						}
					}
				},
				//发布缴费信息确定按钮
				addpayinfo:function(){
					var payTypeName = $("#addpaytype").val();
					var payMoney = $("#addpaymoney").val();
					var payEndTime = $("#addpayendtime").val();
					$.ajax({
						url : '/community/payinfo/insertpayinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"payTypeName":payTypeName,"payMoney":payMoney,"payEndTime":payEndTime},
						success : function(result) {
							alert(result.msg);
							app.get();
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击修改按钮，显示信息在模态框中
				changepayinfo:function(payInfoId,payTypeName,payInfoMoney,payInfoEndTime){
					app.changeid=payInfoId;
					$("#paytype").val(payTypeName);
					$("#paymoney").val(payInfoMoney);
					$("#payendtime").val(payInfoEndTime);
				},
				//点击确定按钮，保存要修改的内容
				savechange:function(){
					var payInfoId = app.changeid;
					var payTypeName = $("#paytype").val();
					var payMoney = $("#paymoney").val();
					var payEndTime = $("#payendtime").val();
					if(payTypeName==''||payMoney==''||payEndTime==''){
						alert("请将信息输入完整！");
					}else{
						$.ajax({
							url : '/community/payinfo/updatepayinfo',
							type : 'POST',
							dataType : 'JSON',
							data:{"payInfoId":payInfoId,"payTypeName":payTypeName,"payMoney":payMoney,"payEndTime":payEndTime},
							success : function(result) {
								alert(result.msg);
								app.get();
							},							
							error : function() {
								console.log("请求失败处理");
							}
						});
					}
				},
				//删除缴费信息
				deletepayinfo:function(payInfoId){
					$.ajax({
						url : '/community/payinfo/deletepayinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"payInfoId":payInfoId},
						success : function(result) {
							alert(result.msg);
							app.get();
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				checkedAll : function(){
                    if(this.checked){//实现反选
                        this.arr=[];
                    }else{//实现全选
                        this.arr=[];
                        this.listpayinfo.forEach( (item) => {
                            this.arr.push(item.payInfoId);
                        })
                    }
                },
                //批量删除
                checkdelete : function(){
                	var listpayInfoId= app.arr;
                	if(listpayInfoId==''){
                		alert("请选择要删除的项目！");
                	}else{
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/payinfo/checkdelete',
    						contentType: "application/json;charset=utf-8",
    						data:JSON.stringify(listpayInfoId),
    						success:function(result){
    							alert(result.msg);
    							app.get();
    						}
    					});
                	}
                },
                //筛选条件下拉框
                screen : function(){
                	var state=$("#screen").val();
                	if(state==0) app.selectstate=0;
                	else if(state==1) app.selectstate=1;
                	else if(state==2) app.selectstate=2;
                },
                //查询
                getpayintoselect : function(){
                	if(app.selectstate==1){
                		app.state=0;
                		var payTypeName = $("#selectpayname").val();
                		var page = this.likepage;
    					var size = this.likesize;
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/payinfo/selectpayinfoaccordingpaytypename',
    						data:{"payTypeName":payTypeName,"page":page,"size":size},
    						success:function(result){
    							app.listpayinfo = result.rows;
    							app.total = result.total;
    							app.pagetotal=Math.ceil(app.total/app.likesize);//向上取整
    							if((app.likepage-1)%5==0){//下一页，到6,11,16,……页
    								app.begin=app.likepage;
    								app.end = app.likepage+4;
    								var current=app.begin;
    								app.pageRange=[];
    								for(var i=0;i<=app.end-app.begin;i++){
    									if(current>app.pagetotal)break;
    									app.pageRange[i]=current;
    									current++;
    								}
    							}else if(app.likepage%5==0){//上一页，从6，11,16到5,10,15……页
    								app.begin=app.likepage-4;
    								app.end=app.likepage;
    								var current=app.begin;
    								app.pageRange=[];
    								for(var i=0;i<=app.end-app.begin;i++){
    									app.pageRange[i]=current;
    									current++;
    								}
    							}
    							
    						}
    					});
                	}else if(app.selectstate==2){
                		if($("#selectTime").val()==''){
                			alert("请选择时间");
                		}else{
                			app.state=0;
                    		var payInfoStartTime = $("#selectTime").val();
                    		var page = this.likepage;
        					var size = this.likesize;
                    		$.ajax({
        						type:'POST',
        						dataType:'JSON',
        						url:'/community/payinfo/selectpayinfoaccordingpayintostarttime',
        						data:{"payInfoStartTime":payInfoStartTime,"page":page,"size":size},
        						success:function(result){
        							app.listpayinfo = result.rows;
        							app.total = result.total;
        							app.pagetotal=Math.ceil(app.total/app.likesize);//向上取整
        							if((app.likepage-1)%5==0){//下一页，到6,11,16,……页
        								app.begin=app.likepage;
        								app.end = app.likepage+4;
        								var current=app.begin;
        								app.pageRange=[];
        								for(var i=0;i<=app.end-app.begin;i++){
        									if(current>app.pagetotal)break;
        									app.pageRange[i]=current;
        									current++;
        								}
        							}else if(app.likepage%5==0){//上一页，从6，11,16到5,10,15……页
        								app.begin=app.likepage-4;
        								app.end=app.likepage;
        								var current=app.begin;
        								app.pageRange=[];
        								for(var i=0;i<=app.end-app.begin;i++){
        									app.pageRange[i]=current;
        									current++;
        								}
        							}
        							
        						}
        					});
                		}
                		
                	}
                }
			},
			watch:{//深度watcher
                arr:{
                    handler:function(val,oldval){
                        if(this.arr.length==this.listpayinfo.length){
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