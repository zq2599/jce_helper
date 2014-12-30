
var G_URL_PREFIX = "/jce_helper/";
/*
 * 这是处理delete点击事件的函数
 * 获取id的值，然后根据id执行删除操作
 */
function deleteUser(obj){
	var p=$(obj).parents("tr");
	var id=p.find("td[name='id']").text();
	window.location=G_URL_PREFIX + "handledelete?user.id="+id;
}
/*
 * 这是处理update点击事件的函数
 * 获取到id的值，然后将id传到更新页面继续操作
 */
function updateUser(obj){
	var p=$(obj).parents("tr");
	var id=p.find("td[name='id']").text();
	var name=p.find("td[name='name']").text();
	var sex=p.find("td[name='sex']").text();
	var age=p.find("td[name='age']").text();
	document.write("<form action='" + G_URL_PREFIX + "jsp/updatepage.jsp' method='post' name='formx1' style='display:none'>");
	document.write("<input type=hidden name='user.id' value='"+id+"'>");
	document.write("<input type=hidden name='user.name' value='"+name+"'>");
	document.write("<input type=hidden name='user.sex' value='"+sex+"'>");
	document.write("<input type=hidden name='user.age' value='"+age+"'>");
	document.write("</form>");
	document.formx1.submit();
}