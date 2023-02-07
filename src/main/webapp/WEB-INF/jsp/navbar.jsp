<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" >
    <div class="collapse navbar-collapse" id="navbarNav">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <ul class="navbar-nav">
            <li class="nav-item">
                <c:choose>
                    <c:when test="${sessionScope.login == 0}">
                        <a class="nav-link" href="/logout">Logout</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="/login">Login</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/home"> |  home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/signup"> |  Sign Up</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/feedback"> |  Feedback</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="message"> |  Contact Us</a>
            </li>

            <c:if test="${sessionScope.is_admin == 0}">
                <li class="nav-item">
                    <a class="nav-link" href="/addQuiz"> |  Add Question</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/deleteQuestion"> |  Delete question</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/allUsers"> |  Manage users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/allMessages"> |  Messages</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/allFeedback"> |  View Feedback</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/allResults"> |  All Results</a>
                </li>
            </c:if>

        </ul>
    </div>
</nav>
</body>
</html>