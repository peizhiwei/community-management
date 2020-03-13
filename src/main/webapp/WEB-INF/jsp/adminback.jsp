<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/adminback.css" rel="stylesheet">
</head>

<body>
    <div class="container-fluid f1">
        <div class="row f2">
            <div class="col-xs-2 col-md-2 f3">
                <div class="co_text">
                    <span>小区物业管理系统</span>
                </div>
                <div class="accordion" id="accordion">
                    <div onclick="changecolor(this)">
                        <div class="manage"><a href="#community_manager"
                                data-toggle="collapse" data-parent="#accordion">楼房信息管理</a></div>
                        <div id="community_manager" class="collapse" style="margin-left: 50px;">
                            <div>
                                <a href="/community/in/buildinginfo" target="iframe_a" style="color: rgb(206, 212, 218);text-decoration: none;">楼栋信息</a>
                            </div>
                            <div style="margin-top: 12px;">
                                <a href="/community/in/houseinfo" target="iframe_a" style="color: rgb(206, 212, 218);text-decoration: none;">房间信息</a>
                            </div>
                        </div>
                    </div>
                    <div onclick="changecolor(this)">
                        <div class="manage"><a href="#community_manager2"
                                data-toggle="collapse" data-parent="#accordion">业主信息管理</a></div>
                        <div id="community_manager2" class="collapse" style="margin-left: 50px;">
                            <div>
                                <a href="../admin/wodexiaoqu.html" target="iframe_a" style="color: rgb(206, 212, 218);text-decoration: none;">业主信息</a>
                            </div>
                            <div style="margin-top: 12px;">
                                <a href="../admin/nidexiaoqu.html" target="iframe_a" style="color: rgb(206, 212, 218);text-decoration: none;">家庭成员</a>
                            </div>
                        </div>
                    </div>
                    <div onclick="changecolor(this)">
                        <div class="manage"><a href="#community_manager3"
                                data-toggle="collapse" data-parent="#accordion">小区管理</a></div>
                        <div id="community_manager3" class="collapse" style="margin-left: 50px;">
                            <div>
                                <a href="../admin/wodexiaoqu.html" target="iframe_a" style="color: rgb(206, 212, 218);text-decoration: none;">我的小区</a>
                            </div>
                            <div style="margin-top: 12px;">
                                <a href="../admin/nidexiaoqu.html" target="iframe_a" style="color: rgb(206, 212, 218);text-decoration: none;">你的小区</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-xs-10 col-md-10 f4">
                    <div class="col-xs-12 col-md-12 top">
                        <div class="col-xs-2 col-md-2"></div>
                        <div class="col-xs-10 col-md-10">
                            <span>欢迎访问香溪花园小区管理系统，裴志伟</span>
                            <span style="margin-left: 50px;">当前为超级管理员</span>
                            <a href="#" class="signout" style="text-decoration: none;">退出</a>
                        </div>
                    </div>
                    <div class="col-xs-1s2 col-md-12 subpage">
                        <iframe src="/community/in/buildinginfo" name="iframe_a" width="100%" height="100%" scrolling="yes"></iframe>
                    </div>
            </div>

        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <script src="../static/js/bootstrap-collapse.js"></script>
    <script type="text/javascript">
        var perDiv=null;
        function changecolor(_this){
            if(perDiv)perDiv.style.backgroundColor='';
            _this.style.backgroundColor='rgb(41,56,70)';
            perDiv=_this;
        }
    </script>
</body>

</html>