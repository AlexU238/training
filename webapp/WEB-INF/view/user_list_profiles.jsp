<%--
  Created by IntelliJ IDEA.
  User: u238
  Date: 04.11.2021
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.u238.training.utils.SortUtilsUserProfile"%>
<!DOCTYPE html>
<html>
<head>
    <title>User profiles</title>

    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div id="wrapper">

    <div id="header">
        <h2>User Profiles</h2>
    </div>

</div>

<div id="container">
    <div id="content">

        <!--Hide or move or remove-->
        <%--<input type="button" value="Create user"--%>
               <%--onclick="window.location.href='createNewUser';return false;"--%>
               <%--class="add-button" />--%>

        <form:form action="search" method="GET">
            Search customer: <input type="text" name="theSearchName" />

            <input type="submit" value="Search" class="add-button" />
        </form:form>


        <c:url var="sortLinkFirstName" value="/userControl/userProfileList">
            <c:param name="sort"
                     value="<%=Integer.toString(SortUtilsUserProfile.FIRST_NAME)%>" />
        </c:url>


        <c:url var="sortLinkLastName" value="/userControl/userProfileList">
            <c:param name="sort"
                     value="<%=Integer.toString(SortUtilsUserProfile.LAST_NAME)%>" />
        </c:url>


        <c:url var="sortLinkEmail" value="/userControl/userProfileList">
            <c:param name="sort"
                     value="<%=Integer.toString(SortUtilsUserProfile.EMAIL)%>" />
        </c:url>

        <table>
            <tr>
                <th><a href="${sortLinkFirstName}">First Name</a></th>
                <th><a href="${sortLinkLastName}">Last Name</a></th>
                <th><a href="${sortLinkEmail}">Email</a></th>
                <th>Action</th>
            </tr>

            <!-- Hide or move -->
            <c:forEach var="tempUserProf" items="${userProfiles}">

                <c:url var="updateLink" value="/userControl/updateProfile">
                    <c:param name="userProfileId" value="${tempUserProf.id}" />
                </c:url>

                <%--<c:url var="deleteLink" value="/userControl/delete">--%>
                    <%--<c:param name="userProfileId" value="${tempUserProf.id}" />--%>
                <%--</c:url>--%>

                <tr>
                    <td>${tempUserProf.firstName}</td>
                    <td>${tempUserProf.lastName}</td>
                    <td>${tempUserProf.email}</td>
                    <td><a href="${updateLink}">Update</a>
                    </td>
                </tr>

            </c:forEach>
        </table>


    </div>
</div>

<br><br>

<a href="${pageContext.request.contextPath}/home">Home</a>

</body>
</html>



<%--| <a--%>
        <%--href="${deleteLink}"--%>
        <%--onclick="if(!(confirm('Are you sure you want to delete this user'))) return false">Delete</a>--%>