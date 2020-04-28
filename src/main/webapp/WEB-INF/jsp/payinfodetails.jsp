<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>缴费详情</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>缴费详情</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;缴费管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;缴费详情</span></h5>
        </div>
        <div class="row" style="background-color: white;padding-left: 10px;margin-bottom: 20px;">
            <h4 data-toggle="collapse" href="#collapseExample">筛选</h4><hr>
            <form id="collapseExample" class="form-inline collapse" style="padding-bottom: 25px;">
            	<select required="required" class="form-control" id="screen" @click="screen()">
            		<option value="0">请选择筛选条件</option>
					<option value="1">缴费项目</option>
					<option value="2">发布时间</option>
					<option value="3">缴费状态</option>
				</select>
				<!-- 缴费项目 -->
				<select v-if="selectstate==1" required="required" class="form-control" id="selectpayname" v-for="list in listpaytype">
					<option>{{list.payTypeName}}</option>
				</select>
				<!-- 发布时间 -->
				<input type="text" v-if="selectstate==2" id="selectTime" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})" class="form-control"/>
                <!-- 缴费状态 -->
                <select v-if="selectstate==3" required="required" class="form-control" id="selectpaystate">
                	<option value="0">未缴费</option>
					<option value="1">已缴费</option>
				</select>
                <!-- 业主 -->
                
                <button type="button" class="btn btn-default" v-if="selectstate==0" disabled="disabled">查询</button>
                <button type="button" class="btn btn-default" @click="getpayinfodetailsselect()" v-else>查询</button>
            </form>
        </div>
        <div class="row">
			<button type="button" class="btn btn-success" @click="batchpaid()">批量缴费</button>
		</div>
		<div class="row">
			<table class="table table-striped table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="text-center">
							<input type="checkbox" v-model="checked" @click="checkedAll()">
						</th>
						<th class="text-center">序号</th>
						<th class="text-center">业主</th>
						<th class="text-center">楼栋号</th>
						<th class="text-center">单元号</th>
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
							<input type="checkbox" v-model="arr" :value="list.payId">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.houseOwner.ownerName}}</td>
						<td>{{list.houseOwner.houseInfo.buildInfo.buildNumber}}</td>
						<td>{{list.houseOwner.houseInfo.houseUnit}}</td>
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
				listpayinfo:[],//所有支付方式
				checked:false,
    			arr:[],
    			listpaytype:[],//所有缴费项目
    			selectstate:0,//0,查询按钮灰色状态，1，缴费类别，2发布时间，3缴费状态，4业主
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
						url : '/community/payinfodetails/pagegetallpayinfodetails',
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
							app.getpayinfodetailsselect();
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
							app.getpayinfodetailsselect();
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
						app.getpayinfodetailsselect();
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
							app.getpayinfodetailsselect();
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
							app.getpayinfodetailsselect();
						}
					}
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
				},
				checkedAll : function(){
                    if(this.checked){//实现反选
                        this.arr=[];
                    }else{//实现全选
                        this.arr=[];
                        this.listpayinfo.forEach( (item) => {
                            this.arr.push(item.payId);
                        })
                    }
                },
                //批量缴费
                batchpaid : function(){
                	var listpayId= app.arr;
                	if(listpayId==''){
                		alert("请选择要缴费的项目！");
                	}else{
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/payinfodetails/batchpaid',
    						contentType: "application/json;charset=utf-8",
    						data:JSON.stringify(listpayId),
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
                	else if(state==3) app.selectstate=3;
                	else if(state==4) app.selectstate=4;
                },
              	//查询
                getpayinfodetailsselect : function(){
                	if(app.selectstate==1){//缴费项目
                		app.state=0;
                		var payTypeName = $("#selectpayname").val();
                		var page = this.likepage;
    					var size = this.likesize;
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/payinfodetails/selectpayinfoaccordingpaytypename',
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
                	}else if(app.selectstate==2){//发布时间
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
        						url:'/community/payinfodetails/selectpayinfoaccordingpayintostarttime',
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
                		
                	}else if(app.selectstate==3){//缴费状态
                		app.state=0;
                		var payState = $("#selectpaystate").val();
                		var page = this.likepage;
    					var size = this.likesize;
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/payinfodetails/selectpayinfoaccordingpaystate',
    						data:{"payState":payState,"page":page,"size":size},
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