<%--
  Created by IntelliJ IDEA.
  User: u238
  Date: 18.11.2021
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>User profile update</title>

    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">


    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/user-style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager</h2>
    </div>
</div>
<div id="container">
    <h3>Update Customer</h3>

    <form:form action="saveProfile" modelAttribute="userProfile"
               method="POST">

        <form:hidden path="id"/>

        <table>
            <tbody>
            <tr>

                <td><label>First name:</label></td>

                <td><form:input path="firstName"/></td>
            </tr>
            <tr>
                <td><label>Last name:</label></td>
                <td><form:input path="lastName"/></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>
    </form:form>


    <div style="clear; both;"></div>

    <p>
        <a href="${pageContext.request.contextPath}/userControl/userProfileList">Back to list</a>
    </p>

</div>
</body>
</html>
