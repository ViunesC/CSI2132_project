<%@ page import="entity.Customer" %><%--
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
    <% Customer user = (Customer) session.getAttribute("user_profile"); %>
    <h1>Customer Profile</h1>
    <form id="modify info" class="customer-info" action="info" method="post">
        <div class="content">
            <label for="name">Name: <%=user.getFirst_name() + " " + user.getLast_name()%> | New:</label>
            <input type="text" id="name" name="customer_name"><br><br>
            <label for="address">Address: <%=user.getAddress()%> | New:</label>
            <input type="text" id="address" name="customer_address"><br><br>
            <label for="phone">Phone number: <%=user.getPhone_num()%> | New:</label>
            <input type="tel" id="phone" name="customer_phone"><br><br>
            <label for="email">Email: <%=user.getEmail()%> | New:</label>
            <input type="email" id="email" name="customer_email"><br><br>
            <label for="ssn">SSN: <%=user.getSsn()%> | New:</label>
            <input type="text" id="ssn" name="customer_ssn"><br><br>
            <label for="password">Password: <%=user.getPassword()%> | New:</label>
            <input type="password" id="password" name="customer_pw"><br><br><br>
            <input type="hidden" name="req_type" value="modify_customer_info">
            <input type="hidden" name="customer_id" value="<%=user.getId()%>">
        </div>

        <div class="btn">
            <button type="submit" class="margin-right button">Submit</button>
            <button type="reset" class="button">Reset</button>
        </div>
    </form>
</body>
</html>
