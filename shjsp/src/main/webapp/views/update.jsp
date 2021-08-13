<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<section>
    <jsp:useBean id="user" scope="request" type="com.shesternev.jsp.model.User"/>
    <form method="post" action="user?action=submit">
        <dl>
            <dt>FirstName:</dt>
            <dd><input type="text" name="firstName" value="<%=user.getFirstName()%>" placeholder="<%=user.getFirstName()%>"/></dd>
        </dl>
        <dl>
            <dt>LastName:</dt>
            <dd><input type="text" name="lastName" value="<%=user.getLastName()%>" placeholder="<%=user.getLastName()%>"/></dd>
        </dl>
        <dl>
            <dt>FirstName:</dt>
            <dd><input type="text" name="password" value="<%=user.getPassword()%>" placeholder="<%=user.getPassword()%>"/></dd>
        </dl>
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>
