<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/2/2
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form method="post" action="/login">
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Username" required="" autofocus="">
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Password" required="">
        </p>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
        <button type="submit">S</button>
    </form>
</div>

</body>
</html>
