<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.11.2021
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register New User</title>
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

    <form:form action="saveNewUser" modelAttribute="registerUser"
               method="POST">
        <table>
            <tbody>

            <tr>
                <td><label>Username:</label></td>
                <td><form:input path="user.username"/>
                    <form:errors path="user.username"/>
                </td>
            </tr>
            <tr>
                <td><label>Password:</label></td>
                <td>
                    <form:input path="user.password"/>
                    <form:errors path="user.password"/>
                <td>
            </tr>

            <tr>
                <td><label>Password:</label></td>
                <td>
                    <form:input path="user.confirmPassword"/>
                    <form:errors path="user.confirmPassword"/>
                <td>
            </tr>
            <tr>
                <td><label>First name:</label></td>

                <td>
                    <form:input path="userProfile.firstName"/>
                    <form:errors path="userProfile.firstName"/>
                </td>
            </tr>
            <tr>
                <td><label>Last name:</label></td>
                <td>
                    <form:input path="userProfile.lastName"/>
                    <form:errors path="userProfile.lastName"/>
                </td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td>
                        <form:input path="userProfile.email"/>
                        <form:errors path="userProfile.email"/>
                <td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
            </tbody>
        </table>
    </form:form>
    <div style="clear; both;"></div>
</div>
</body>
</html>
