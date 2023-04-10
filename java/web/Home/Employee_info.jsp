<%@ page import="entity.Employee" %><%--
  Created by IntelliJ IDEA.
  User: Re_Innocence
  Date: 2023/4/9
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-hotel : Modify info</title>
    <style><%@include file="/Home/style/Customer_info.css"%></style>
</head>
<body>
    <% Employee user = (Employee) session.getAttribute("user_profile"); %>
    <h1>Customer Profile</h1>
    <form id="modify info" class="customer-info" action="info" method="post">
        <div class="content">
            <label for="name">Name: <%=user.getFirst_name() + " " + user.getLast_name()%> | New:</label>
            <input type="text" id="name" name="employee_name"><br><br>
            <label for="role">Role: <%=user.getRole()%> | New:</label>
            <input type="text" id="role" name="employee_role"><br><br>
            <label for="position">Position: <%=user.getPosition()%> | New:</label>
            <input type="text" id="position" name="employee_pos"><br><br>
            <label for="email">Email: <%=user.getEmail()%> | New:</label>
            <input type="email" id="email" name="employee_email"><br><br>
            <label for="ssn">SSN: <%=user.getSsn()%> | New:</label>
            <input type="text" id="ssn" name="employee_ssn"><br><br>
            <label for="password">Password: <%=user.getPassword()%> | New:</label>
            <input type="password" id="password" name="employee_pw"><br><br><br>
            <input type="hidden" name="req_type" value="modify_employee_info">
            <input type="hidden" name="employee_id" value="<%=user.getId()%>">
        </div>

        <div class="btn">
            <button type="submit" class="margin-right button">Submit</button>
            <button type="reset" class="button">Reset</button>
        </div>
    </form>
</body>
</html>
