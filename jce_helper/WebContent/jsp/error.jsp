<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
 <%@ taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>errorPage</title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand">Hello,SinaAppEngine is welcome you!</a>
        </div>
    </div>
Sorry...
<font color="ff0000">
<s:fielderror/>
</font>
<a href="<%=request.getContextPath()%>/">Go back to the home page.</a>
    </div>
</body>
</html>