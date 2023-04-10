<%@ page import="entity.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Utility.AccountPackage" %><%--
  Created by IntelliJ IDEA.
  User: Re_Innocence
  Date: 2023/4/9
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Hotel : Create Renting(Employee)</title>
    <style><%@include file="/Home/style/Employee_rent_result.css"%></style>
</head>
<body>
    <h1>Search Result</h1>
    <%List<Room> rooms = (List<Room>) session.getAttribute("available_room_list");%>
    <%String start_date = (String) session.getAttribute("start_date_str");%>
    <%String end_date = (String) session.getAttribute("end_date_str");%>
    <%AccountPackage user = (AccountPackage) session.getAttribute("userInfo");%>

    <% if (rooms.isEmpty()) { %>
        <h1>No result found.</h1>
    <%}%>

    <% for (Room room : rooms) { %>
    <% if (room.getType().equals("null")) { room.setType("No view"); } %>
    <% if (room.getAmenities().equals("")) { room.setAmenities("No amenity"); } %>

    <div class="search-result">
        <p class="align-left">From <b><%=start_date%></b> To <b><%=end_date%></b>:</p>
        <p class="align-left">Room type: <%=room.getType()%><span class="align-right">Capacity: 1~<%=room.getCapacity()%> people</span></p>
        <p class="align-left">View type: <%=room.getView_type()%><span class="align-right">Can be extended?: <%=room.isCan_be_extended()%></span></p>
        <p class="align-left">Amenities: <%=room.getAmenities()%></p>
        <br>
        <p class="align-left">Price(per night): $<%=room.getPrice()%> plus applicable tax</p>
        <p><input type="text" name="customer_name" form="rent_room"><button type="submit" class="btnRent" form="rent_room">Rent</button></p>
        <form id="rent_room" action="search" method="post">
            <input type="hidden" name="req_type" value="Rent_room_with_name">
            <input type="hidden" name="employee_id" value="<%=user.getId()%>">
            <input type="hidden" name="room_id" value="<%=room.getId()%>">
            <input type="hidden" name="startD" value="<%=start_date%>">
            <input type="hidden" name="endD" value="<%=end_date%>">
        </form>
    </div><br><br>
    <%}%>
</body>
</html>
