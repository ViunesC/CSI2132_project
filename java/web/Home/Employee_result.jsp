<%@ page import="entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Utility.AccountPackage" %><%--
  Created by IntelliJ IDEA.
  User: Re_Innocence
  Date: 2023/4/9
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Hotel : Search Result</title>
    <style><%@include file="/Home/style/Customer_result.css"%></style>
</head>
<body>
    <h1>Search Result</h1>
    <% List<Order> orders = (List<Order>) session.getAttribute("order_list"); %>
    <% int employee_id = ((AccountPackage) session.getAttribute("userInfo")).getId(); %>
    <% if (orders.isEmpty()) { %>
        <h1>No result found.</h1>
    <%}%>

    <% for (Order order : orders) { %>
        <% String start_date = order.getStart().toString();%>
        <% String end_date = order.getEnd().toString();%>
        <div class="search-result">
            <p class="align-left">From <b><%=start_date%></b> To <b><%=end_date%></b>:</p>
            <p class="align-left">Booking id: <%=order.getId()%></p>
            <p class="align-left">Room id: <%=order.getRoom_id()%></p>
            <p class="align-left">Customer id: <%=order.getCustomer_id()%></p>
            <br>
            <p class="align-left"><button type="submit" form="rent_room">Pay and create rent</button></p>
            <form id="rent_room" action="search" method="post">
                <input type="hidden" name="req_type" value="Rent_room">
                <input type="hidden" name="booking_id" value="<%=order.getId()%>">
                <input type="hidden" name="customer_id" value="<%=order.getCustomer_id()%>">
                <input type="hidden" name="employee_id" value="<%=employee_id%>">
                <input type="hidden" name="room_id" value="<%=order.getRoom_id()%>">
                <input type="hidden" name="startD" value="<%=start_date%>">
                <input type="hidden" name="endD" value="<%=end_date%>">
            </form>
        </div><br><br>
    <%}%>

</body>
</html>
