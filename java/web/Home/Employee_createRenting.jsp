<%--
  Created by IntelliJ IDEA.
  User: Re_Innocence
  Date: 2023/4/9
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-Hotel : Create Renting(Employee)</title>
    <style><%@include file="/Home/style/Employee_rent.css"%></style>
</head>
<body>
    <form id="search_for_room" action="home" method="post" onsubmit="return validate()">
      <p style="text-align: center">Search for Room</p>
      <label for="start_date">Start date(Required):</label>
      <input type="date" name="startD" id="start_date"><br><br>
      <label for="end_date">End date(Required):</label>
      <input type="date" name="endD" id="end_date"><br><br>
      <label for="capacity">Capacity:</label>
      <input type="number" name="room_cap" id="capacity"><br><br>
      <label for="price_range">Price range:</label>
      <select id="price_range" name="price"><br><br>
        <option value="">Not specific</option>
        <option value="<200">less than $200</option>
        <option value="200-500">$200 to $500</option>
        <option value="500-1000">$500 to $1000</option>
        <option value=">1000">greater than $1000</option>
      </select><br><br><br>
      <input type="hidden" name="req_type" value="Search_for_room_employee">
      <button type="submit" id="btn_submit">Search</button>
    </form>

    <script type="text/javascript"><%@include file="/Home/script/Employee_rent.js" %></script>
</body>
</html>
