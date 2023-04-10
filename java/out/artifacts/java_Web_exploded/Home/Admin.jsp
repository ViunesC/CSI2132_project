<%@ page import="entity.Utility.AccountPackage" %><%--
  Created by IntelliJ IDEA.
  User: Re_Innocence
  Date: 2023/4/10
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Hotel Management System</title>
    <style><%@include file="/Home/style/Admin.css"%></style>
</head>
<body>
    <% AccountPackage userAcc = (AccountPackage) session.getAttribute("userInfo"); %>
    <% String name = userAcc.getName(); %>
    <h1>Welcome, <%=name%></h1>

    <div class="selection-field">
        <div class="btn">
            <button type="button" class="button margin-right" onclick="window.location.replace('Home/Admin_edit_chain.html')">Edit Hotel Chain</button>
            <button type="button" class="button margin-right" onclick="window.location.replace('Home/Admin_edit_hotel.html')">Edit Hotel</button>
            <button type="button" class="button" onclick="window.location.replace('Home/Admin_edit_room.html')">Edit Room</button>
        </div>
    </div>

</body>
</html>
