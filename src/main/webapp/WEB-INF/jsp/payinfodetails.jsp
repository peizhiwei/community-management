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
	</div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="../static/js/vue.min.js"></script>
    <script>
		var app = new Vue({
			el : '#app',
			data : {
				listpayinfo:[],//所有支付方式
				checked:false,
    			arr:[]
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/payinfodetails/getallpayinfodetails',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listpayinfo = result;
						},							
						error : function() {
							console.log("请求失败处理");
						}
					});
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