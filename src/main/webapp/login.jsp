<%--
  Created by IntelliJ IDEA.
  User: win7
  Date: 2017-07-10
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${SPRING_SECURITY_LAST_EXCEPTION.message}
<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message!=null}">
    <c:out value="密码或用户名错误"></c:out>
</c:if>
<%--如果是get请求则不管成功还是不成功都是302，spring security不会对get进行拦截   --%>
<%--对于post会拦截--%>

    <form action="<%=application.getContextPath()%>/login" method="post">
        <input type="text" name="username"></br>
        <input type="password" name="password"><br>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input type="submit" value="login">
    </form>
</body>
</html>
