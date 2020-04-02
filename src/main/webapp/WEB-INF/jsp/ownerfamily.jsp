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
		<div class="row">
			<div>
				<button type="button" class="btn btn-default" style="margin-bottom: -10%" data-toggle="modal" data-target="#myModal2">新增</button>
				<h1 style="text-align: center;">家庭成员信息</h1>
			</div>
			<table class="table table-bordered table-hover text-center" style="background-color: white;">
				<thead>
					<tr>
						<th class="col-md-1 text-center">姓名</th>
						<th class="col-md-1 text-center">性别</th>
						<th class="col-md-1 text-center">电话</th>
						<th class="col-md-1 text-center">出生日期</th>
						<th class="col-md-1 text-center">关系</th>
						<th class="col-md-1 text-center">籍贯</th>
						<th class="col-md-1 text-center">工作单位</th>
						<th class="col-md-3 text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="list in listfamilyinfo">
						<td>{{list.familyName}}</td>
						<td>{{list.familySex==1?'男':'女'}}</td>
						<td>{{list.familyPhone}}</td>
						<td>{{list.familyBirthday}}</td>
						<td>{{list.familyRelation}}</td>
						<td>{{list.familyNativePlace}}</td>
						<td>{{list.familyWorkPlace}}</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                data-target="#myModal1" @click="change(list.familyId,list.familyName,list.familySex,list.familyPhone,list.familyBirthday,
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
				changeid:0,
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						url : '/community/ownerfamily/getfamilyofowner',
						type : 'GET',
						dataType : 'JSON',
						success : function(result) {
							app.listfamilyinfo = result;
						},
						error : function() {
							console.log("请求失败处理");
						}
					});
				},
				//点击修改按钮，显示信息在模态框上
				change:function(familyId,familyName,familySex,familyPhone,familyBirthday,familyRelation,familyNativePlace,familyWorkPlace){
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
					if(familyName==''||familySex==''||familyPhone==''||familyBirthday==''||familyRelation==''||familyNativePlace==''||familyWorkPlace==''){
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
				//确定新增家庭成员信息
				saveaddfamilyinfo:function(){
					var familyName=$("#addfamilyname").val();
					var familySex=$("#addfamilysex").val()=='男'?1:0;
					var familyPhone=$("#addfamilyphone").val();
					var familyBirthday=$("#addfamilybirthday").val();
					var familyRelation=$("#addfamilyrelation").val();
					var familyNativePlace=$("#addfamilynativeplace").val();
					var familyWorkPlace=$("#addfamilyworkplace").val();
					if(familyName==''||familyPhone==''||familyBirthday==''||familyRelation==''||familyNativePlace==''){
						alert("请将信息填写完整");
					}else{
						$.ajax({
							url : '/community/ownerfamily/insertfamily',
							type : 'POST',
							dataType : 'JSON',
							data:{"familyName":familyName,"familySex":familySex,"familyPhone":familyPhone,"familyBirthday":familyBirthday,
								"familyRelation":familyRelation,"familyNativePlace":familyNativePlace,"familyWorkPlace":familyWorkPlace},
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
				}
			}
		});
	</script>
  </body>
</html>