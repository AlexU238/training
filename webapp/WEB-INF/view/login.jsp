<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <title>LOGIN</title>


    <style>
        .failed{color: red;}
    </style>

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>


<body>

<h3>Login</h3>

<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
    <!-- Check for login error -->

    <c:if test="${param.error != null}">

        <div class="alert alert-danger col-xs-offset-1 col-xs-10">
            Invalid username and password.
        </div>

    </c:if>


    <!-- Check for logout -->

    <c:if test="${param.logout != null}">

        <div class="alert alert-success col-xs-offset-1 col-xs-10">
            You have been logged out.
        </div>

    </c:if>

    <table>
        <tbody>
        <tr>

            <td>
                <label>Login:</label>
            </td>
            <td>
                <label>
                    <input type="text" name="username"/>
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <label>Password:</label>
            </td>
            <td>
                <label>
                    <input type="password" name="password"/>
                </label>
            </td>
        </tr>

        <tr>
            <td>
                <label></label>
            </td>
            <td>
                <input type="submit" value="Login"/>
            </td>
        </tr>

        </tbody>
    </table>

</form:form>

</body>

</html>