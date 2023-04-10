<%@ page import="entity.Utility.AccountPackage" %><%--
  Created by IntelliJ IDEA.
  User: Re_Innocence
  Date: 2023/3/30
  Time: 5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Hotel : Home(Employee)</title>
    <style><%@include file="/Home/style/Employee.css"%></style>
</head>
<body>
    <% AccountPackage userAcc = (AccountPackage) session.getAttribute("userInfo"); %>
    <% String name = userAcc.getName(); %>
    <h1>Welcome, <%=name%></h1>

    <form id="search_for_booking" action="home" method="post" onsubmit="return validate()">
        <p style="text-align: center">Search for Booking</p>
        <label for="customer_name">Customer Name:</label>
        <input type="text" name="customer_name" id="customer_name"><br><br>
        <label for="customer_phone">Customer phone:</label>
        <input type="tel" name="customer_phone" id="customer_phone"><br><br><br>

        <input type="hidden" name="req_type" value="Search_for_booking">
        <button type="submit" id="btn_submit">Search</button>
    </form>

    <h3>OR</h3><br>
    <div class="btn">
        <form id="employee-info" class="hidden-form" action="home" method="post">
            <input type="hidden" name="req_type" value="Modify_info_employee">
        </form>
        <form id="create-rent" class="hidden-form" action="employee_rent" method="post">
            <input type="hidden" name="req_type" value="Create-rent">
        </form>

        <button type="submit" class="margin-right button" id="btn_jump" form="employee-info">My information</button>
        <button type="submit" class="margin-right button" id="btn_rent" form="create-rent">Create rent</button>
        <button type="button" class="button" onclick="window.location.replace('index.html')">Log out</button>
    </div>

    <script type="text/javascript"><%@include file="/Home/script/Employee.js" %></script>
</body>
</html>
