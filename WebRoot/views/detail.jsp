<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>站点详情</title>
  	<meta http-equiv="pragma" content="no-cache">
  	<meta http-equiv="cache-control" content="no-cache">
  	<meta http-equiv="expires" content="0">
  	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  	<meta http-equiv="description" content="This is my page">
    <link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
  	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/commom.css">
    <link rel="stylesheet" type="text/css" href="css/detail.css">
  </head>
  <body id="detail">
    <div id="container">
  	<%@ include file="header.jsp" %>
    <div class="container" id="mainContent">
      <div class="col-md-9 col-lg-9" id="content">
      </div>
      <div class="col-md-3 col-lg-3" id="rightNav">
        <ul class="nav nav-pills nav-stacked">
          <li role="presentation" onclick="getDetail(1);"><a >光温生产潜力</a></li>
          <li role="presentation" onclick="getDetail(2);"><a >气候生成潜力</a></li>
          <li role="presentation" onclick="getDetail(3);"><a >生育期内需水量</a></li>
          <li role="presentation" onclick="getDetail(4);"><a >降水盈亏量</a></li>
        </ul>
      </div>
    </div>
	<%@ include file="footer.jsp" %>
    </div>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/echarts.js"></script>
    <script type="text/javascript" src="js/theme/dark.js"></script>
    <script type="text/javascript" src="js/detail.js"></script>
  </body>
</html>

