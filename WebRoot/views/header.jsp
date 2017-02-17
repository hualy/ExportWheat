<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<img src="image/logo-s.png" id="logos">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="views/index.jsp"><img alt="Brand" src="image/logo.png"></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right" id="navRight">
        <li class="index"><a href="views/index.jsp">首页</a></li>
        <li class="detail"><a href="views/detail.jsp">站点详情</a></li>
        <li class="search"><a href="views/search.jsp">站点搜索</a></li>
        <li class="analyze"><a href="views/analyze.jsp">站点分析</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>