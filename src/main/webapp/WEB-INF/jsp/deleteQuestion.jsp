<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

<title>Add Quiz</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="css/styles.css" rel="stylesheet">
</head>

<body>
<%@ include file="navbar.jsp" %>
<form action="/deleteQuestion" method="post">

    <div class="container">
        <h1 class="h3 mb-3 font-weight-normal text-center"> Delete a question</h1>

        <legend>Select the question you want to delete:</legend>
        <c:forEach items="${existingQs}" var="q" varStatus="loop">
            <input type="radio" name="selectedQ" value="${q.id}" checked/> ${q.category} quiz: ${q.description}
            <br>
        </c:forEach>
        <div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </div>
</form>
</body>
</html>