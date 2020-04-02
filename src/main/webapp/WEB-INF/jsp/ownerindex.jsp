<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>业主首页</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/css/ownerindex.css">
    
</head>
<body>
    <div class="container-fluid f1" id="app">
        <div class="row f2">
            <div class="col-xs-2 col-md-2 f3">
                <div class="co_text">
                    <span>小区物业管理系统</span>
                </div>
                <div class="accordion">
                    <div onclick="changecolor(this)">
                        <div class="manage"><a href="/community/owneri/ownerselfmessage" target="iframe_a">基本信息</a></div>
                    </div>
                    <div onclick="changecolor(this)">
                        <div class="manage"><a href="/community/owneri/ownerfamily" target="iframe_a">家庭成员</a></div>
                    </div>
                    <div onclick="changecolor(this)">
                        <div class="manage"><a href="/community/owneri/ownercomplaint" target="iframe_a">投&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;诉</a></div>
                    </div>
                    <div onclick="changecolor(this)">
                        <div class="manage"><a href="/community/owneri/ownerrepair" target="iframe_a">报&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修</a></div>
                    </div>
                    <div onclick="changecolor(this)">
                        <div class="manage"><a href="/community/owneri/ownerpay" target="iframe_a">缴&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费</a></div>
                    </div>
                </div>
            </div>
            <div class="col-xs-10 col-md-10 f4">
                    <div class="col-xs-12 col-md-12 top">
                        <div class="col-xs-6 col-md-6"></div>
                        <div class="col-xs-6 col-md-6">
                        	<div class="col-xs-10 col-md-10"><span>欢迎访问香溪花园小区管理系统，</span><span>{{username}}</span></div>
                            <div class="col-xs-2 col-md-2">
                            	<button type="button" class="btn btn-primary" @click="sign_out()">退出</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-12 subpage" style="background-color: rgb(245,245,245)">
                        <iframe src="/community/owneri/ownerselfmessage" name="iframe_a" width="100%" height="100%" frameborder="0" allowTransparency="true" style="background-color:#red;"></iframe>
                    </div>
            </div>

        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="../static/js/vue.min.js"></script>
    <script>
        var perDiv=null;
        function changecolor(_this){
            if(perDiv)perDiv.style.backgroundColor='';
            _this.style.backgroundColor='rgb(41,56,70)';
            perDiv=_this;
        }
    </script>
    <script>
		var app = new Vue({
			el : '#app',
			data:{
				username:''
			},
			mounted : function() {
				this.get();
			},
			methods : {
				get : function() {
					$.ajax({
						type:'POST',
						dataType:'JSON',
						url:'/community/owner/getusername',
						success:function(result){
							app.username = result.msg
						},
						error:function(){
							console.log("请求失败处理！");
						}
					});
				},
				//退出
				sign_out:function(){
					$.ajax({
						type:'POST',
						dataType:'JSON',
						url:'/community/owner/signout',
						success:function(result){
							window.location.href=result.msg;
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