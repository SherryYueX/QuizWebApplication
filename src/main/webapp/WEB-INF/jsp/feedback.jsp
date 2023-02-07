<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Feedback</title>
    <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
  <!-- Custom styles for this template -->
  <link href="css/styles.css" rel="stylesheet">
</head>

<body>
    <%@ include file="navbar.jsp" %>
    <form action="/feedback" method="post">
        <div class="ratingMargin">
            <legend>Chose your rating:</legend>
            <input type="radio" name="rating" value="1"/>
                <label for="1">⭐</label>

            <input type="radio" name="rating" value="2"/>
                <label for="2">⭐⭐</label>

            <input type="radio" name="rating" value="3"/>
                <label for="3">⭐⭐⭐</label>

            <input type="radio" name="rating" value="4"/>
                <label for="4">⭐⭐⭐⭐</label>

            <input type="radio" name="rating" value="5" checked/>
                <label for="5">⭐⭐⭐⭐⭐</label>

            <hr class="dashed">
            <span class="input-group-text">Feedback</span>
            <textarea class="form-control" aria-label="With textarea" name="userFeedback"></textarea>

            <button class="btn btn-lg btn-primary btn-block" type="submit">submit</button>
        </div>

    </form>
</body>

</html>