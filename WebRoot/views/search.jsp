<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>站点搜索</title>
  	<meta http-equiv="pragma" content="no-cache">
  	<meta http-equiv="cache-control" content="no-cache">
  	<meta http-equiv="expires" content="0">
  	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  	<meta http-equiv="description" content="This is my page">
    <link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
  	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/commom.css">
    <link rel="stylesheet" type="text/css" href="css/search.css">

  </head>
  <body id="search">
    <div id="container">

  	<%@ include file="header.jsp" %>

    <div class="container" id="mainContent">
      <div class="col-md-9 col-lg-9" id="content">
      </div>
      <div class="col-md-3 col-lg-3" id="rightNav">
        <form class="form">
          <div class="form-group">
            <label class="" for="province">请输入站点：</label>
            <select class="form-control" onchange="ChangeSelect(this)">
              <option>安徽</option>
              <option>河北</option>
              <option>河南</option>
              <option>江苏</option>
              <option>山东</option>
              <option>天津</option>
            </select>
            <select class="form-control" id="site">
              <option>毫州</option>
              <option>砀山</option>
              <option>蚌埠</option>
              <option>阜阳</option>
            </select>
          </div>
          <div class="form-group">
            <label class="" for="year">请输入年份：</label>
            <input type="text" class="form-control" id="startYear" placeholder="起始年份（1961-2013）">
            <input type="text" class="form-control" id="endYear" placeholder="结束年份（1961-2013）">
          </div>
          <button type="button" onclick="getDatilMSM();">查询</button>
        </form>
      </div>
    </div>
	<%@ include file="footer.jsp" %>
  </div>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/echarts.js"></script>
    <script type="text/javascript" src="js/search.js"></script>
  </body>
</html>