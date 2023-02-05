<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Failed</title>
  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <!-- Custom styles for this template -->
  <link href="css/styles.css" rel="stylesheet">
</head>
<body>
    <%@ include file="navbar.jsp" %>
  <div class="container text-center">
    <h1> Failed to sign up </h1>
    <span>${userName}</span><span> has already been taken</span><br/>
    <a href="/login">login here</a>
  </div>

</body>
</html>