<%@ page import="entity.Utility.AccountPackage" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Re_Innocence
  Date: 2023/3/30
  Time: 5:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Hotel : Home(Customer)</title>
    <style><%@include file="/Home/style/Customer.css"%></style>
</head>
<body>
    <% AccountPackage userAcc = (AccountPackage) session.getAttribute("userInfo"); %>
    <% String name = userAcc.getName(); %>
    <h1>Welcome, <%=name%></h1>
    <%List<String> chain_list = (List<String>) session.getAttribute("ChainList");%>
    <%List<String> categories = (List<String>) session.getAttribute("CategoryList");%>
    <form id="search_for_room" action="home" method="post" onsubmit="return validate()">
        <p style="text-align: center">Search for Room</p>
        <label for="start_date">Start date(Required):</label>
        <input type="date" name="startD" id="start_date">
        <label for="end_date">End date(Required):</label>
        <input type="date" name="endD" id="end_date">
        <p>*Please note that you can check in after 4pm local time of the start date</p>
        <p>And you need to check out before 1pm local time of the end date</p><br>
        <label for="area">Area(Required):</label>
        <input type="text" name="room_area" id="area"><br><br>
        <label for="capacity">Capacity:</label>
        <input type="number" name="room_cap" id="capacity"><br><br>
        <label for="hotel_chain">Hotel chain:</label>
        <select id="hotel_chain" name="chain">
            <option value="">Not specific</option>
            <% for (String s : chain_list) { %>
                <option value="<%=s%>"><%=s%></option>
            <%}%>
        </select><br><br>
        <label for="hotel_category">Category:</label>
        <select id="hotel_category" name="category">
            <option value="">Not specific</option>
            <% for (String s : categories) { %>
                <option value="<%=s%>"><%=s%></option>
            <%}%>
        </select><br><br>
        <label for="num_room">Total number of rooms:</label>
        <input type="number" name="room_num" id="num_room"><br><br>
        <label for="price_range">Price range:</label>
        <select id="price_range" name="price">
            <option value="">Not specific</option>
            <option value="<200">less than $200</option>
            <option value="200-500">$200 to $500</option>
            <option value="500-1000">$500 to $1000</option>
            <option value=">1000">greater than $1000</option>
        </select><br><br>
        <input type="hidden" name="req_type" value="Search_for_room">
        <button type="submit" id="btn_submit">Search</button>
    </form>
    <h3>OR</h3><br>
    <div class="btn">
        <form id="customer-info" class="hidden-form" action="home" method="post">
            <input type="hidden" name="req_type" value="Modify_info">
        </form>
        <button type="submit" class="margin-right button" id="btn_jump" form="customer-info">My information</button>
        <button type="button" class="button" onclick="window.location.replace('index.html')">Log out</button>
    </div>

    <script type="text/javascript"><%@include file="/Home/script/Customer.js" %></script>
</body>
</html>
