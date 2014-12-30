<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>addPage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">    
  </head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand">Hello,SinaAppEngine is welcome you!</a>
        </div>
    </div>
<form class="well" action="${pageContext.request.contextPath }/handleinseart" method="post">
  <label>Add user</label>
    name:<input type="text" class="span3" name="user.name" placeholder="Type name..."><br>
    age:<input type="text" class="span3" name="user.age" placeholder="Type age..."><br>
      sex:man<input type="radio" name="user.sex" value="man">
  woman<input type="radio" name="user.sex" value="woman"><br>
  <button type="submit" class="btn">add</button>
</form>
</body>
<div align="left"><a href="/">go back</a></div>
    </div>
</html>