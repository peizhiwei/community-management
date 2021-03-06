<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>家庭成员信息</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body style="background-color: rgb(245,245,245)">
    <div class="container-fluid" id="app">
    	<div class="row" style="background-color: white;margin-top: 20px;padding-left: 10px;margin-bottom: 20px">
            <h1>家庭成员</h1>
            <h5><a href="#" onclick="top.location.href ='/community/admin/adminback'">首页&nbsp;&nbsp;</a>/<span>&nbsp;&nbsp;业主信息管理&nbsp;&nbsp;/</span><span>&nbsp;&nbsp;家庭成员</span></h5>
        </div>
        <div class="row" style="background-color: white;padding-left: 10px;margin-bottom: 20px;">
            <h4 data-toggle="collapse" href="#collapseExample">查询条件</h4><hr>
            <form id="collapseExample" class="form-inline collapse" style="padding-bottom: 25px;">
                <input type="text" class="form-control" id="likebuildnumber" placeholder="请输入楼栋编号">
                <input type="text" class="form-control" id="likehouseunit" placeholder="请输入单元号">
                <input type="text" class="form-control" id="likehousenumber" placeholder="请输入房间号">
                <input type="text" class="form-control" id="likeownername" placeholder="请输入业主姓名">
                <input type="text" class="form-control" id="likefamilyname" placeholder="请输入成员姓名">
                <button type="button" class="btn btn-default" @click="getfamilyinfolike()">查询</button>
            </form>
        </div>
        <div class="row">
			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal2" @click="add()">新增</button>
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
						<th class="text-center">姓名</th>
						<th class="text-center">性别</th>
						<th class="text-center">楼栋号</th>
						<th class="text-center">单元号</th>
						<th class="text-center">房间号</th>
						<th class="text-center">电话</th>
						<th class="text-center">出生日期</th>
						<th class="text-center">业主</th>
						<th class="text-center">与业主关系</th>
						<th class="text-center">籍贯</th>
						<th class="text-center">工作单位</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(list,index) in listfamilyinfo">
						<td>
							<input type="checkbox" v-model="arr" :value="list.familyId">
						</td>
						<td>{{index+1}}</td>
						<td>{{list.familyName}}</td>
						<td>{{list.familySex==1?'男':'女'}}</td>
						<td>{{list.houseOwner.houseInfo.buildInfo.buildNumber}}</td>
						<td>{{list.houseOwner.houseInfo.houseUnit}}</td>
						<td>{{list.houseOwner.houseInfo.houseNumber}}</td>
						<td>{{list.familyPhone}}</td>
						<td>{{list.familyBirthday}}</td>
						<td>{{list.houseOwner.ownerName}}</td>
						<td>{{list.familyRelation}}</td>
						<td>{{list.familyNativePlace}}</td>
						<td>{{list.familyWorkPlace}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#myModal1" @click="change(list.familyId,list.familyName,list.familySex,list.familyPhone,list.familyBirthday,list.houseOwner.ownerName,
                                list.familyRelation,list.familyNativePlace,list.familyWorkPlace)">修改</button>
                            <button type="button" class="btn btn-danger btn-sm" @click="deletefamilyinfo(list.familyId)">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
			<!--修改家庭成员信息的模态框-->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">修改家庭成员信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="familyname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="familysex">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">电话</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="familyphone">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期</label>
									<div class="col-sm-9">
										<input id="familybirthday" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">与业主关系</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="familyrelation">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">籍贯</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="familynativeplace">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">工作单位</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="familyworkplace">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" id="savechange" @click="savechange()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 新增家庭成员信息的模态框 -->
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document" style="width: 32%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title text-center" id="myModalLabel">增加家庭成员信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addfamilyname">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">性别</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addfamilysex">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">电话</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addfamilyphone">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">出生日期</label>
									<div class="col-sm-9">
										<input id="addfamilybirthday" class="form-control" type="text" onclick="WdatePicker({skin:'whyGreen',maxDate:'%y-%M-%d'})"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">楼栋号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addbuildnumber" @click="selecthouseunit()">
											<option class="form-control" v-for="list in listbuildnumber">{{list}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">单元号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addhouseunit" @click="selecthousenumber()">
											<option class="form-control" v-for="list in listhouseunit">{{list}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">房间号</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addhousenumber" @click="selectownername()">
											<option class="form-control" v-for="list in listhousenumber">{{list}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">业主</label>
									<div class="col-sm-9">
										<select required="required" class="form-control" id="addfamilyhouseowner">
											<option class="form-control">{{ownerName}}</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">与业主关系</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addfamilyrelation">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">籍贯</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addfamilynativeplace">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">工作单位</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="addfamilyworkplace">
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary" @click="saveaddfamilyinfo()" >确定</button>
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
				listfamilyinfo:[],//所有家庭成员信息
				infotomodal:[],
				changeid:0,
				listonlyhouseownername:[],//所有的业主名称，去掉重复的
				listbuildnumber:[],//业主所属的楼栋编号，去掉重复的
				listhouseunit:[],//业主所属的单元号，去掉重复的
				listhousenumber:[],//业主所属房间号
				ownerName:'',//新增家庭成员模态框中下拉框中的业主名
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
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					var page = this.page;
					var size = this.size;
					$.ajax({
						url : '/community/familyinfo/pagegetallfamilyinfo',
						type : 'GET',
						dataType : 'JSON',
						data:{"page":page,"size":size},
						success : function(result) {
							app.listfamilyinfo = result.rows;
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
							app.getfamilyinfolike();
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
							app.getfamilyinfolike();
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
						app.getfamilyinfolike();
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
							app.getfamilyinfolike();
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
							app.getfamilyinfolike();
						}
					}
				},
				//点击修改按钮，显示信息在模态框上
				change:function(familyId,familyName,familySex,familyPhone,familyBirthday,ownerName,familyRelation,familyNativePlace,familyWorkPlace){
					app.changeid=familyId;
					$("#familyname").val(familyName);
					$("#familysex").val(familySex==1?'男':'女');
					$("#familyphone").val(familyPhone);
					$("#familybirthday").val(familyBirthday);
					$("#familyrelation").val(familyRelation);
					$("#familynativeplace").val(familyNativePlace);
					$("#familyworkplace").val(familyWorkPlace);
				},
				//修改家庭成员信息
				savechange:function(){
					var familyId=app.changeid;
					var familyName=$("#familyname").val();
					var familySex=$("#familysex").val()=='男'?1:0;
					var familyPhone=$("#familyphone").val();
					var familyBirthday=$("#familybirthday").val();
					var familyRelation=$("#familyrelation").val();
					var familyNativePlace=$("#familynativeplace").val();
					var familyWorkPlace=$("#familyworkplace").val();
					if(familyName==''||familyPhone==''||familyBirthday==''||familyRelation==''||familyNativePlace==''||familyWorkPlace==''){
						alert("请将信息填写完整！");
					}else{
						$.ajax({
							url:'/community/familyinfo/updatefamilyinfo',
							type:'POST',
							dataType:'JSON',
							data:{"familyId":familyId,"familyName":familyName,"familySex":familySex,"familyPhone":familyPhone,"familyBirthday":familyBirthday,
								"familyRelation":familyRelation,"familyNativePlace":familyNativePlace,"familyWorkPlace":familyWorkPlace},
							success : function(result) {
								alert(result.msg);
								app.get();
							}
						});
					}
				},
				//点击新增按钮,查询所有有住户的楼栋信息
				add:function(){
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
					var buildNumber = $("#addbuildnumber").val();
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
					var buildNumber = $("#addbuildnumber").val();
					var houseUnit = $("#addhouseunit").val();
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
					var buildNumber = $("#addbuildnumber").val();
					var houseUnit = $("#addhouseunit").val();
					var houseNumber=$("#addhousenumber").val();
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
				//确定新增家庭成员信息
				saveaddfamilyinfo:function(){
					var familyName=$("#addfamilyname").val();
					var familySex=$("#addfamilysex").val()=='男'?1:0;
					var familyPhone=$("#addfamilyphone").val();
					var familyBirthday=$("#addfamilybirthday").val();
					var ownerName=$("#addfamilyhouseowner").val();
					var buildNumber = $("#addbuildnumber").val();
					var houseUnit = $("#addhouseunit").val();
					var houseNumber=$("#addhousenumber").val();
					var familyRelation=$("#addfamilyrelation").val();
					var familyNativePlace=$("#addfamilynativeplace").val();
					var familyWorkPlace=$("#addfamilyworkplace").val();
					if(familyName==''||familyPhone==''||familyBirthday==''||ownerName==''||buildNumber==''||houseUnit==''||houseNumber==''||familyRelation==''||familyNativePlace==''||familyWorkPlace==''){
						alert("请将信息填写完整！");
					}else{
						$.ajax({
							url : '/community/familyinfo/insertfamilyinfo',
							type : 'POST',
							dataType : 'JSON',
							data:{"familyName":familyName,"familySex":familySex,"familyPhone":familyPhone,"familyBirthday":familyBirthday,"ownerName":ownerName,
								"buildNumber":buildNumber,"houseUnit":houseUnit,"houseNumber":houseNumber,"familyRelation":familyRelation,"familyNativePlace":familyNativePlace,"familyWorkPlace":familyWorkPlace},
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
				//删除家庭成员信息
				deletefamilyinfo:function(familyId){
					$.ajax({
						url:'/community/familyinfo/deletefamilyinfo',
						type : 'POST',
						dataType : 'JSON',
						data:{"familyId":familyId},
						success : function(result){
							alert(result.msg);
							app.get();
						},
						error:function(){
							console.log("请求失败处理！");
						}
					});
				},
				//模糊查询家庭成员信息
				getfamilyinfolike : function(){
					app.state=0;
					var buildNumber = $("#likebuildnumber").val();
					var houseUnit = $("#likehouseunit").val();
					var houseNumber = $("#likehousenumber").val();
					var ownerName = $("#likeownername").val();
					var familyName = $("#likefamilyname").val();
					var page = this.likepage;
					var size = this.likesize;
					$.ajax({
						url:'/community/familyinfo/getfamilyinfolike',
						type:'POST',
						dataType:'JSON',
						data:{"buildNumber":"%"+buildNumber+"%","houseUnit":"%"+houseUnit+"%","houseNumber":"%"+houseNumber+"%",
							"ownerName":"%"+ownerName+"%","familyName":"%"+familyName+"%","page":page,"size":size},
						success : function(result) {
							app.listfamilyinfo = result.rows;
							app.total = result.total;
							app.pagetotal=Math.ceil(app.total/app.likesize);//向上取整
							if((app.likepage-1)%5==0){
								app.begin=app.likepage;
								app.end = app.likepage+4;
								var current=app.begin;
								app.pageRange=[];
								for(var i=0;i<=app.end-app.begin;i++){
									if(current>app.pagetotal)break;
									app.pageRange[i]=current;
									current++;
								}
							}
							if(app.likepage%5==0){
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
                        this.listfamilyinfo.forEach( (item) => {
                            this.arr.push(item.familyId);
                        })
                    }
                },
                //批量删除
                checkdelete : function(){
                	var listfamilyId= app.arr;
                	if(listfamilyId==''){
                		alert("请选择要删除的项目");
                	}else{
                		$.ajax({
    						type:'POST',
    						dataType:'JSON',
    						url:'/community/familyinfo/checkdelete',
    						contentType: "application/json;charset=utf-8",
    						data:JSON.stringify(listfamilyId),
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
                        if(this.arr.length==this.listfamilyinfo.length){
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