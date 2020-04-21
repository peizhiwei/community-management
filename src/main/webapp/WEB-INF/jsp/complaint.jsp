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
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>投诉管理</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;小区管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;投诉管理</span></h5>
        </div>
        <div class="row" style="background-color: white;padding-left: 10px;margin-bottom: 20px;">
            <h4>查询条件</h4><hr>
            <form class="form-inline" style="padding-bottom: 25px;">
            	<input type="text" class="form-control" id="likeownername" placeholder="请输入投诉人姓名">
                <input type="text" class="form-control" id="likebuildnumber" placeholder="请输入楼栋编号">
                <input type="text" class="form-control" id="likehouseunit" placeholder="请输入单元号">
                <input type="text" class="form-control" id="likehousenumber" placeholder="请输入房间号">
                <input type="text" class="form-control" id="likecomplaintreason" placeholder="请输入投诉内容">
                <button type="button" class="btn btn-default" @click="getcomplaintinfolike()">查询</button>
            </form>
        </div>
        <div class="row">
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
						<th class="text-center">投诉人</th>
						<th class="text-center">楼栋号</th>
						<th class="text-center">单元号</th>
						<th class="text-center">房间号</th>
						<th class="text-center">手机号</th>
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
							<input type="checkbox" v-model="arr" :value="list.complaintId">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.houseOwner.ownerName}}</td>
						<td>{{list.houseOwner.houseInfo.buildInfo.buildNumber}}</td>
						<td>{{list.houseOwner.houseInfo.houseUnit}}</td>
						<td>{{list.houseOwner.houseInfo.houseNumber}}</td>
						<td>{{list.houseOwner.ownerPhone}}</td>
						<td>{{list.complaintReason}}</td>
						<td>{{list.complaintTime}}</td>
						<td>{{list.complaintSettleTime==null?'':list.complaintSettleTime}}</td>
						<td>{{list.admin==null?'':list.admin.adminName}}</td>
						<td>{{list.complaintState==0?'未受理':(list.complaintState==1?'已受理':'已解决')}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" @click="Acceptance(list.complaintId)" v-if="list.complaintState==0">受理</button>
							<button type="button" class="btn btn-primary btn-sm" disabled="disabled" v-else>受理</button>
                            <button type="button" class="btn btn-success btn-sm" @click="settled(list.complaintId)" v-if="list.complaintState==1">已解决</button>
                            <button type="button" class="btn btn-success btn-sm" disabled="disabled" v-else>已解决</button>
                            <button type="button" class="btn btn-danger btn-sm" @click="deletecomplaint(list.complaintId)">删除</button>
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
    <script>
    	var app = new Vue({
    		el:'#app',
    		data:{
    			listcomplaintinfo:[],//所有的投诉信息
    			checked:false,
    			arr:[],
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
    		mounted:function(){
    			this.get();
    		},
    		methods:{
    			get:function(){
    				var page = this.page;
					var size = this.size;
    				$.ajax({
    					url:'/community/complaintinfo/pagegetallcomplaintinfo',
    					type:'POST',
    					dataType:'JSON',
    					data:{"page":page,"size":size},
    					success:function(result){
    						app.listcomplaintinfo=result.rows;
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
    					error:function(){
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
							app.getcomplaintinfolike();
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
							app.getcomplaintinfolike();
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
						app.getcomplaintinfolike();
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
							app.getcomplaintinfolike();
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
							app.getcomplaintinfolike();
						}
					}
				},
    			//受理投诉信息
    			Acceptance:function(complaintId){
    				$.ajax({
    					url:'/community/complaintinfo/acceptance',
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
    			},
    			//已解决投诉
    			settled:function(complaintId){
    				$.ajax({
    					url:'/community/complaintinfo/settled',
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
    			},
    			//模糊查询投诉信息(投诉人姓名，楼栋编号，单元号，房间号，投诉内容)
    			getcomplaintinfolike : function(){
    				app.state=0;
    				var ownerName = $("#likeownername").val();
    				var buildNumber = $("#likebuildnumber").val();
    				var houseUnit = $("#likehouseunit").val();
    				var houseNumber = $("#likehousenumber").val();
    				var complaintReason = $("#likecomplaintreason").val();
    				var page = this.likepage;
					var size = this.likesize;
    				$.ajax({
						url:'/community/complaintinfo/getcomplaintinfolike',
						type:'POST',
						dataType:'JSON',
						data:{"ownerName":"%"+ownerName+"%","buildNumber":"%"+buildNumber+"%","houseUnit":"%"+houseUnit+"%",
							"houseNumber":"%"+houseNumber+"%","complaintReason":"%"+complaintReason+"%","page":page,"size":size},
						success : function(result) {
							app.listcomplaintinfo = result.rows;
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
    			},
    			checkedAll : function(){
                    if(this.checked){//实现反选
                        this.arr=[];
                    }else{//实现全选
                        this.arr=[];
                        this.listcomplaintinfo.forEach( (item) => {
                            this.arr.push(item.complaintId);
                        })
                    }
                },
                //批量删除
                checkdelete : function(){
                	var listcomplaintId= app.arr;
                	if(listcomplaintId==''){
                		alert("请选择要删除的项目！");
                	}else{
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/complaintinfo/checkdelete',
    						contentType: "application/json;charset=utf-8",
    						data:JSON.stringify(listcomplaintId),
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
                        if(this.arr.length==this.listcomplaintinfo.length){
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