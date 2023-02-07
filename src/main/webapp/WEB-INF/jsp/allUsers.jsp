<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Sherry Xie">
  <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

  <title>Manage Users</title>

  <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
  <!-- Custom styles for this template -->
  <link href="css/styles.css" rel="stylesheet">
</head>

<body>
    <%@ include file="navbar.jsp" %>
    <form action="/allUsers" method="post">
        <table>
            <tr>
                <th>username </th>
                <th> first name </th>
                <th> last name </th>
                <th> email address </th>
                <th>physical address</th>
                <th>phone number</th>
                <th>status</th>
              </tr>

        <c:forEach items="${allUsers}" var="user" varStatus="loop">
            <tr>
                <td>${user.userName}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.emailAddr}</td>
                <td>${user.physicalAddr}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.status}</td>
                <td>
                    <c:if test="${user.userName!='admin'}">
                        <button type="submit" name="statusAction" value="${user.userName}">
                            <c:choose>
                                <c:when test="${user.status=='active'}">
                                    Suspend
                                </c:when>
                                <c:otherwise>
                                    Activate
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </table>
    </form>
</body>
</html>