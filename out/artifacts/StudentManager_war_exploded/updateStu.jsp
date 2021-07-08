<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="stu" method="post" enctype="multipart/form-data">
		<input type="hidden" name="method" value="updateStudentById">
	    <input type="hidden" name="sid" value="${stu.sid}"/>
		姓名:<input type="text" name="sname" value="${stu.sname}"/><br/><br/>
		性别:男<input type="radio"  value="男" name="gender" <c:if test="${stu.gender=='男'}">checked</c:if> />
		          女<input type="radio"  value="女" name="gender" <c:if test="${stu.gender=='女'}">checked</c:if> /><br/><br/>
		生日:<input class="Wdate" value="${stu.sbir}" name="sbir" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"><br/><br/>
		爱好:
		抽烟<input type="checkbox"  value="抽烟" name="hobby"
				 <c:if test="${fn:contains(stu.hobby, '抽烟')}">checked</c:if> />
			喝酒<input type="checkbox" value="喝酒" name="hobby"
					 <c:if test="${fn:contains(stu.hobby, '喝酒')}">checked</c:if>/>
			rap<input type="checkbox"  value="rap" name="hobby"
					  <c:if test="${fn:contains(stu.hobby, 'rap')}">checked</c:if>/>
			烫头<input type="checkbox"  value="烫头" name="hobby"
					 <c:if test="${fn:contains(stu.hobby, '烫头')}">checked</c:if>/><br/><br/>
	             头像:
		<img src="http://localhost:8080/upload/${stu.photo}" alt="头像" width="60px" height="60px">
		<input type="hidden" name="oldPhoto" value="${stu.photo}">
	        <input type="file" name="file">
	        <br/>
	     <input type="submit" value="修改学生">
	</form>
</body>
</html>