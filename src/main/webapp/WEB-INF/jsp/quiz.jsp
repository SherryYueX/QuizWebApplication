<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
    <!-- Bootstrap core CSS -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
      <!-- Custom styles for this template -->
      <link href="css/styles.css" rel="stylesheet">
</head>

<body>
<div>

    <form method="post" action="/quiz">
    <h5>Quiz Category: ${categorySelected}</h5>
    <div class="tab">
    <div class="modal-header">
        <%-- Question description --%>
        <h3>${q1_ds}</h3>
    </div>
      <%-- Dynamically render the choices --%>
      <c:forEach items="${choices[0]}" var="choice" varStatus="loop">
          <div>
          <label class="element-animation1 btn btn-lg btn-danger btn-block">
              <span class="btn-label">
                  <i class="glyphicon glyphicon-chevron-right">
                  </i>
              </span>
              <input type="radio"
                     name="selectedChoiceId1"
                     value="${choice.id}"/>
                  ${choice.description}
          </label>
          </div>
      </c:forEach>
    </div>

    <div class="tab">
    <div class="modal-header">
        <%-- Question description --%>
        <h3>${q2_ds}</h3>
    </div>
      <%-- Dynamically render the choices --%>
        <c:forEach items="${choices[1]}" var="choice" varStatus="loop">
            <div>
            <label class="element-animation1 btn btn-lg btn-danger btn-block">
                <span class="btn-label">
                    <i class="glyphicon glyphicon-chevron-right">
                    </i>
                </span>
                <input type="radio"
                       name="selectedChoiceId2"
                       value="${choice.id}"/>
                    ${choice.description}
            </label>
            </div>
        </c:forEach>
    </div>

    <div class="tab">
    <div class="modal-header">
        <%-- Question description --%>
        <h3>${q3_ds}</h3>
    </div>
      <%-- Dynamically render the choices --%>
      <c:forEach items="${choices[2]}" var="choice" varStatus="loop">
          <div>
          <label class="element-animation1 btn btn-lg btn-danger btn-block">
              <span class="btn-label">
                  <i class="glyphicon glyphicon-chevron-right">
                  </i>
              </span>
              <input type="radio"
                     name="selectedChoiceId3"
                     value="${choice.id}"/>
                  ${choice.description}
          </label>
          </div>
      </c:forEach>
    </div>

    <div class="tab">
    <div class="modal-header">
        <%-- Question description --%>
        <h3>${q4_ds}</h3>
    </div>
      <%-- Dynamically render the choices --%>
      <c:forEach items="${choices[3]}" var="choice" varStatus="loop">
          <div>
          <label class="element-animation1 btn btn-lg btn-danger btn-block">
              <span class="btn-label">
                  <i class="glyphicon glyphicon-chevron-right">
                  </i>
              </span>
              <input type="radio"
                     name="selectedChoiceId4"
                     value="${choice.id}"/>
                  ${choice.description}
          </label>
          </div>
      </c:forEach>
    </div>

    <div class="tab">
    <div class="modal-header">
        <%-- Question description --%>
        <h3>${q5_ds}</h3>
    </div>
      <%-- Dynamically render the choices --%>
      <c:forEach items="${choices[4]}" var="choice" varStatus="loop">
          <div>
          <label class="element-animation1 btn btn-lg btn-danger btn-block">
              <span class="btn-label">
                  <i class="glyphicon glyphicon-chevron-right">
                  </i>
              </span>
              <input type="radio"
                     name="selectedChoiceId5"
                     value="${choice.id}"/>
                  ${choice.description}
          </label>
          </div>
      </c:forEach>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">submit</button>

    </form>
</div>
</body>
</html>
