<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>站点分析</title>
  	<meta http-equiv="pragma" content="no-cache">
  	<meta http-equiv="cache-control" content="no-cache">
  	<meta http-equiv="expires" content="0">
  	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  	<meta http-equiv="description" content="This is my page">
    <link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
  	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/commom.css">
    <link rel="stylesheet" type="text/css" href="css/analyze.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/analyze.js"></script>
  </head>
  <body id="analyze">
    <div id="container">
  	<%@ include file="header.jsp" %>
    <div class="container" id="mainContent">
      <div class="col-md-9 col-lg-9" id="content">
        <!-- 分隔线 -->
        <div class="hrid">
          <hr>
          <img src="image/info.png" alt="">
        </div>
        <!-- 基本信息 -->
          <!-- 表单  -->
          <form>
            <div class="row" id="information">
              <div class="col-md-3">
                <div class="form-group form-flag">
                  <select class="form-control " onchange="ChangeSelect1(this)">
                    <option>安徽</option>
                    <option>河北</option>
                    <option>河南</option>
                    <option>江苏</option>
                    <option>山东</option>
                    <option>天津</option>
                  </select>
                  <select class="form-control">
                    <option>毫州</option>
                    <option>砀山</option>
                    <option>蚌埠</option>
                    <option>阜阳</option>
                  </select>
                </div>
                <button type="button" class="removeBtn" onclick='deleteFunction(this);'>删除</button>
              </div>
              <div class="col-md-3">
                <div class="form-group form-flag">
                  <select class="form-control" onchange="ChangeSelect1(this)">
                    <option>安徽</option>
                    <option>河北</option>
                    <option>河南</option>
                    <option>江苏</option>
                    <option>山东</option>
                    <option>天津</option>
                  </select>
                  <select class="form-control" id="dd">
                    <option>毫州</option>
                    <option>砀山</option>
                    <option>蚌埠</option>
                    <option>阜阳</option>
                  </select>
                </div>
                <button type="button" class="removeBtn" onclick='deleteFunction(this);'>删除</button>
              </div>
              <!-- 添加按钮 -->
              <button type="button" id="addBtn" class="col-md-2" onclick="addFunction(this)">添加站点</button>
              <!-- 时间选择 -->
            </div>
            <hr>
            <div class="row" id="timeRow">
              <div class="col-md-2">
                <p>请输入时间：</p>
              </div>
              <div class="form-group col-md-3">
                <label class="sr-only" for="beginTime">起始年份（1961-2013）</label>
                <input type="email" class="form-control" id="beginTime" placeholder="起始年份（1961-2013）" >
              </div>
              <div class="form-group col-md-3">
                <label class="sr-only" for="endTime">结束年份（1961-2013）</label>
                <input type="email" class="form-control" id="endTime" placeholder="结束年份（1961-2013）">
              </div>
              <button type="button" class="btn col-md-3" onclick="getMessage();">站点对比</button>
            </div>
          </form>
        <!-- 分隔线 -->
        <div class="hrid">
          <hr>
          <img src="image/hr.png" alt=""  id="hrid">
        </div>
        <!-- 四个图表 -->
        <div class="chart" id="chart1"></div>
        <div class="chart" id="chart2"></div>
        <div class="chart" id="chart3"></div>
        <div class="chart" id="chart4"></div>
      </div>
      <!-- 右边的导航 -->
      <div class="col-md-3 col-lg-3" id="rightNav">
        <ul class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="125">
          <li role="presentation" class="new"><a href="views/analyze.jsp#logos">信息输入</a></li>
          <li role="presentation"><a href="views/analyze.jsp#hrid">光温生产潜力</a></li>
          <li role="presentation"><a href="views/analyze.jsp#chart2">气候生成潜力</a></li>
          <li role="presentation"><a href="views/analyze.jsp#chart3">生育期内需水量</a></li>
          <li role="presentation"><a href="views/analyze.jsp#chart4">降水盈亏量</a></li>
          <li role="presentation" class="new"><a href="views/analyze.jsp#logos">回到顶部</a></li>
        </ul>
      </div>
    </div>
	<%@ include file="footer.jsp" %>
  </div>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/echarts.js"></script>
    <script type="text/javascript" src="js/theme/dark.js"></script>
    <script type="text/javascript" src="js/theme/macarons.js"></script>
    <script type="text/javascript" src="js/theme/vintage.js"></script>
    <script type="text/javascript" src="js/theme/walden.js"></script>
  </body>
</html>