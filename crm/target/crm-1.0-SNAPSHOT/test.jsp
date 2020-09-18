<%--
  Created by IntelliJ IDEA.
  User: GW
  Date: 2020/9/18
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<base href="<%=basePath%>">


$.ajax({
url:"",
data:{},
dataType:"json",
success:function () {

}
})
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
