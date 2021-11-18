<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<body>


<h2>Home page</h2>

<hr>

<p>

    User: <security:authentication property="principal.username"/>
    <br><br>
    Role(s): <security:authentication property="principal.authorities"/>
</p>

<security:authorize access="hasRole('EMPLOYEE')">
    <hr>

    <a href="userControl/userProfileList">List of users</a>
</security:authorize>

    <br><br>

<form:form action="${pageContext.request.contextPath}/logout" method="POST">

    <input type="submit" value="Logout"/>

</form:form>

<br><br>





</body>


</html>