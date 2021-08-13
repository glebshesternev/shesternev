<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<section>
    <h3>User info</h3>
    <jsp:useBean id="user" scope="request" type="com.shesternev.jsp.model.User"/>
    <tr>
        <td>FirstName: <%=user.getFirstName()%> | LastName: <%=user.getLastName()%> | Password: <%=user.getPassword()%></td>
        <td><a href="user?action=update">Update</a></td>
    </tr>
</section>
</body>
</html>
