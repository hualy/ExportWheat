<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
  	<meta http-equiv="pragma" content="no-cache">
  	<meta http-equiv="cache-control" content="no-cache">
  	<meta http-equiv="expires" content="0">
  	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  	<meta http-equiv="description" content="This is my page">
    <link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/commom.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
  </head>
  <body id="index">
    <div id="container">
      
  	<%@ include file="header.jsp" %>


   	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" class="container"></div>
    <!-- 分隔线 -->
    <div class="container" id="hrid">
      <hr>
      <img src="image/hr.png" alt="">
    </div>
    <!-- 轮播 -->
    <div class="container">
      <div id="carouselid" class="carousel slide" data-ride="carousel" data-interval="12000">
          <!-- Indicators -->
          <ol class="carousel-indicators">
            <li data-target="#carouselid" data-slide-to="0" class="active"></li>
            <li data-target="#carouselid" data-slide-to="1"></li>
            <li data-target="#carouselid" data-slide-to="2"></li>
          </ol>

          <!-- Wrapper for slides -->
          <div class="carousel-inner" role="listbox">
            <div class="item active">
              <img src="image/index.gif" alt="...">
              <div class="carousel-caption">
                首页
              </div>
            </div>
            <div class="item">
              <img src="image/detail.gif" alt="...">
              <div class="carousel-caption" >
                站点详情
              </div>
            </div>
            <div class="item">
              <img src="image/search.gif" alt="...">
              <div class="carousel-caption">
                站点搜索
              </div>
            </div>
            <div class="item">
              <img src="image/analyze.gif" alt="...">
              <div class="carousel-caption">
                站点分析
              </div>
            </div>
          </div>

          <!-- Controls -->
          <a class="left carousel-control" href="#carouselid" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="right carousel-control" href="#carouselid" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>
    <!-- 相关连接 -->
    <div class="container" id="about">
      <img src="image/about.png" alt="">
      <div>
        <p><a href="#" title="">国家科技基础条件平台</a></p>
        <p><a href="#" title="">国家科技资源网</a></p>
        <p><a href="#" title="">国家农业科学数据共享中心</a></p>
      </div>
    </div>


	   <%@ include file="footer.jsp" %>
    </div>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/echarts.js"></script>
    <script type="text/javascript" src="js/china.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
  </body>
</html>
