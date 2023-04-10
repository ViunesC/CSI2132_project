<%@ page import="java.util.List" %>
<%@ page import="entity.Utility.RoomPackage" %>
<%@ page import="entity.Utility.AccountPackage" %><%--
  Created by IntelliJ IDEA.
  User: Re_Innocence
  Date: 2023/4/8
  Time: 19:30
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
    <%List<RoomPackage> rooms = (List<RoomPackage>) session.getAttribute("room_list");%>
    <%String start_date = (String) session.getAttribute("start_date_str");%>
    <%String end_date = (String) session.getAttribute("end_date_str");%>
    <%AccountPackage user = (AccountPackage) session.getAttribute("userInfo");%>

    <% if (rooms.isEmpty()) { %>
        <h1>No result found.</h1>
    <%}%>
    <% for (RoomPackage room : rooms) { %>
        <% if (room.getRoom().getType().equals("null")) { room.getRoom().setType("No view"); } %>
        <% if (room.getRoom().getAmenities().equals("")) { room.getRoom().setAmenities("No amenity"); } %>

        <div class="search-result">
            <p class="align-left">From <b><%=start_date%></b> To <b><%=end_date%></b>:</p>
            <p class="align-left">Hotel name: <%=room.getHotel().getHotel_name()%><span class="align-right">Address: <%=room.getHotel().getAddress()%></span></p>
            <p class="align-left">Area: <%=room.getHotel().getArea()%><span class="align-right">Category: <%=room.getHotel().getCategory()%></span></p>
            <p class="align-left">Room type: <%=room.getRoom().getType()%><span class="align-right">Capacity: 1~<%=room.getRoom().getCapacity()%> people</span></p>
            <p class="align-left">View type: <%=room.getRoom().getView_type()%><span class="align-right">Can be extended?: <%=room.getRoom().isCan_be_extended()%></span></p>
            <p class="align-left">Amenities: <%=room.getRoom().getAmenities()%></p>
            <br>
            <p class="align-left">Price(per night): $<%=room.getRoom().getPrice()%> plus applicable tax<button type="submit" class="btnBook" form="book_room">Book now</button></p>
            <form id="book_room" action="search" method="post">
                <input type="hidden" name="req_type" value="Book_room">
                <input type="hidden" name="customer_id" value="<%=user.getId()%>">
                <input type="hidden" name="room_id" value="<%=room.getRoom().getId()%>">
                <input type="hidden" name="startD" value="<%=start_date%>">
                <input type="hidden" name="endD" value="<%=end_date%>">
            </form>
        </div><br><br>
    <%}%>
</body>
</html>
