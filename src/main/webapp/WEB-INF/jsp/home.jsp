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

  <title>Home</title>

  <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
  <!-- Custom styles for this template -->
  <link href="css/styles.css" rel="stylesheet">
</head>

<body>
<%@ include file="navbar.jsp" %>
<form action="/home" method="post">
    <div class="container text-center">
      <div class="row">
        <div class="col">
          <div class="row">
            <h1>LeetCode</h1>
          </div>
          <div class="row">
            <button type="submit" name="leetcode" value="leetcode">
                <img src="image/dog-img.jpg" alt="dog-profile" class="testimonialsImg">
            </button>
          </div>
        </div>
        <div class="col">
          <div class="row">
              <h1>Cat</h1>
            </div>
            <div class="row">
              <button type="submit" name="cat" value="cat">
                  <img src="image/Mao.jpg" alt="mao-profile" class="testimonialsImg">
              </button>
            </div>
        </div>
        <div class="col">
          <div class="row">
              <h1>Horror Movie</h1>
            </div>
            <div class="row">
              <button type="submit" name="horror" value="horror">
                  <img class="testimonialsImg" src="image/lady-img.jpg" alt="lady-profile">
              </button>
            </div>
        </div>
      </div>
      <c:forEach items="${userResults}" var="result" varStatus="loop">
        <div class="row">
            <div class="col">quiz_name: ${result.category}</div>
            <div class="col">start_time: ${result.quiz_start}</div>
            <div class="col">end_time: ${result.quiz_end}</div>
            <div class="col">score: ${result.score}</div>
            <div class="col">
                <button type="submit" name="selectedResultId" value="${result.id}"> view </button>
            </div>
        </div>

      </c:forEach>

    </div>


</form>
</body>
</html>