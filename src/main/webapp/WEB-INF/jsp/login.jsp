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

  <title>Quiz Login</title>

  <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

  <!-- Custom styles for this template -->
  <link href="css/styles.css" rel="stylesheet">
</head>

<body>
<%@ include file="navbar.jsp" %>
<form class="form-signin" action="/login" method="post">
  <div class="container text-center">
    <img class="mb-4" src="image\favicon.ico" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Login to Quiz!</h1>
    <input type="text" name="userName" class="form-control top" placeholder="username" required autofocus>
    <input type="password" name="password" class="form-control bot" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
    <p class="mt-5 mb-3 text-muted">BeaconFire PP1 - Sherry</p>
  </div>

</form>
</body>
</html>
