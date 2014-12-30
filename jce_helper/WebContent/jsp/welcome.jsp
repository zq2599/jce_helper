<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="cache-control" content="no-cache">
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
  	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/js/demo.js"></script>  
  </head>

<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand">Hello,SinaAppEngine is welcome you!</a>
        </div>
    </div>
	    <span>
		    <form class="well" action="${pageContext.request.contextPath }/search" method="post">
			  <input type="text" class="span3" name="user.name"  placeholder="Type name ...">
			  <button type="submit" class="btn">search</button>
			</form>
			</span>
			<span>
				<button class="btn" onclick="window.location.href='${pageContext.request.contextPath }/jsp/addpage.jsp'">add a user</button>
			</span>
<br>
<div align="center">
<table class="table table-striped table-condensed">
    <thead>
    <tr>
        <th style="width: 40px">id</th>
        <th style="width: 40px">name</th>  
        <th style="width: 40px">sex</th>
        <th style="width: 40px">age</th>
        <th style="width: 40px">handle</th>
    </tr>
    </thead>
    <tbody>   
    <!-- 遍历打印从数据库中拿出的数据 -->
        <s:iterator value="list" id="u">
                <tr>
        		<td name="id"><s:property value="#u.id"/></td>
        		<td name="name"><s:property value="#u.name"/></td>
        		<td name="sex"><s:property value="#u.sex"/></td>
        		<td name="age"><s:property value="#u.age"/></td>
   				<td><a href="javascript:void(0)" onclick="deleteUser(this)">delete</a>|
        		<a href="javascript:void(0)" onclick="updateUser(this)">update</a></td>
        		 </tr>
        </s:iterator>
   </tbody>
    </table>
    </div>
</div>
</body>
</html>