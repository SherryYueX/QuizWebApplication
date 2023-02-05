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
<form class="form-signin" action="/addQuiz" method="post">

    <div class="container text-center">
        <img class="mb-4" src="image\favicon.ico" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal"> Add a question</h1>
        <input type="text" name="q_des" class="form-control top" placeholder="question description" required autofocus>
        <fieldset>
            <legend>Select a question category:</legend>
            <div>
              <input type="radio" name="category" value="horror"
                     checked>
              <label for="huey">Horror movie</label>
            </div>
            <div>
              <input type="radio" name="category" value="cat">
              <label for="dewey">Cat</label>
            </div>

            <div>
              <input type="radio" name="category" value="leetcode">
              <label for="louie">Leetcode</label>
            </div>
        </fieldset>
        <input type="text" name="choice1" class="form-control mid" placeholder="Correct choice content" required>
        <input type="text" name="choice2" class="form-control mid" placeholder="choice 1 content" required>
        <input type="text" name="choice3" class="form-control mid" placeholder="choice 2 content" required>
        <input type="text" name="choice4" class="form-control mid" placeholder="choice 3 content" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        <p class="mt-5 mb-3 text-muted">BeaconFire PP1 - Sherry</p>
    </div>
</form>
</body>
</html>