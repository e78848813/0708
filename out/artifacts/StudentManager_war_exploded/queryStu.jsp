<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        td {
            text-align: center;
            width: 125px;
        }

        .c2 {
            margin-left: 40px;
            margin-bottom: 40px;
        }
    </style>
    <script src="js/jquery.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#checkAll").click(function () {
                var check = this.checked;
                $(".checkAll").each(function (index, element) {
                    element.checked = check;
                })
            })

            $("#batchDel").click(function () {
                if ($(".checkAll:checked").length == 0) {
                    alert("没有选择要删除的学生信息!");
                    return;
                }
                var sureDelete = confirm("您确定要删除所选信息吗?");
                if (sureDelete) {
                    var ids = "";
                    $(".checkAll:checked").each(function () {
                        ids += "," + this.value;
                    })
                    ids = ids.substring(1);
                    location.href = "stu?method=batchDelete&ids=" + ids;
                }
            })

            $("#addStudent").click(function () {
                location.href = "addStu.jsp";
            })
        })
    </script>

</head>
<body>
<br/>
<h2>学生管理系统v0.1</h2>
尊敬的<b>${user.name}</b>,欢迎您
<center>
    <form action="stu" method="get">
        <input type="hidden" name="method" value="queryStudent">
        姓名<input name="sname" value="${student.sname}"/>&nbsp;&nbsp;&nbsp;
        性别
        <select name="gender">
            <option value="">请选择</option>

            <option value="男"
                    <c:if test="${student.gender=='男'}">selected="selected"</c:if> >男
            </option>
            <option value="女"
                    <c:if test="${student.gender=='女'}">selected="selected"</c:if> >女
            </option>
        </select>&nbsp;&nbsp;&nbsp;
        <input type="submit" value="查询"/>
    </form>
    <br/>
</center>
<br/>
<input type="button" id="batchDel" value="删除所选" style="margin-left: 150px"/>
<input type="button" id="addStudent" value="添加信息" style="margin-left: 150px"/>
<br/>
<table border="1px" width="80%" align="center" cellpadding="0"
       cellspacing="0">
    <tr>
        <th><input type="checkbox" id="checkAll"/>全选/全不选</th>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>生日</th>
        <th>爱好</th>
        <th>头像</th>
        <th>操作</th>
    </tr>
    <!-- 遍历学生的信息 -->
    <c:forEach items="${studentPage.pageList}" var="stu">
        <tr>
            <th><input type="checkbox" class="checkAll" value="${stu.sid}"/></th>
            <td>${stu.sid}</td>
            <td>${stu.sname}</td>
            <td>${stu.gender}</td>
            <td><fmt:formatDate value="${stu.sbir}" pattern="yyyy-MM-dd"/></td>
            <td>${stu.hobby}</td>
            <td><img src="http://localhost:8080/upload/${stu.photo}" alt="头像" width="60px" height="60px"></td>
            <td><a href="stu?method=getStudentById&sid=${stu.sid}">修改</a></td>
        </tr>
    </c:forEach>
</table>
<br/><br/>
<center>
    <a href="stu?method=queryStudent&currentPage=1&sname=${student.sname}&gender=${student.gender}" class="c2">首页</a>
    <a href="stu?method=queryStudent&currentPage=${studentPage.prePage}&sname=${student.sname}&gender=${student.gender}"
       class="c2">上一页</a>
    <a href="stu?method=queryStudent&currentPage=${studentPage.nextPage}&sname=${student.sname}&gender=${student.gender}"
       class="c2">下一页</a>
    <a href="stu?method=queryStudent&currentPage=${studentPage.countPage}&sname=${student.sname}&gender=${student.gender}"
       class="c2">尾页</a>
    <span class="c2">当前页码<input size="4" value="${studentPage.currentPage}"/></span>
    <span class="c2">总记录数<input size="4" value="${studentPage.countNum}"/></span>
</center>
</body>
</html>