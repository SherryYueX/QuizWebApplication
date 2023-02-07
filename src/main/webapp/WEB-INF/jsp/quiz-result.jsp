<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz Result</title>
</head>

<body>
<div>
    <%@ include file="navbar.jsp" %>
    <p>Quiz name: ${sessionScope.result.category}</p>
    <p>User full name: ${sessionScope.selectedUser.firstName}  ${sessionScope.selectedUser.lastName}</p>
    <p>Start time: ${sessionScope.result.quiz_start}</p>
    <p>End time: ${sessionScope.result.quiz_end}</p>
    <p>Username: ${sessionScope.selectedUser.userName}</p>

    <hr class="dashed">

    <p>Question 1:</p>
    <p>${sessionScope.questions.get(0).description}</p>
    <c:forEach items="${sessionScope.choices[0]}" var="choice" varStatus="loop">
        <p>${choice.description}</p>
    </c:forEach>
    <p>Your answer: ${sessionScope.user_choice1.description}</p>
    <p>
        <c:if test="${sessionScope.user_choice1.id==sessionScope.questions.get(0).correctChoiceId}">Your answer was correct</c:if>
        <c:if test="${sessionScope.user_choice1.id!=sessionScope.questions.get(0).correctChoiceId}">Your answer was wrong</c:if>
    </p>

    <hr class="dashed">

    <p>Question 2:</p>
    <p>${sessionScope.questions.get(1).description}</p>
    <c:forEach items="${sessionScope.choices[1]}" var="choice" varStatus="loop">
        <p>${choice.description}</p>
    </c:forEach>
    <p>Your answer: ${sessionScope.user_choice2.description}</p>
    <p>
        <c:if test="${sessionScope.user_choice2.id==sessionScope.questions.get(1).correctChoiceId}">Your answer was correct</c:if>
        <c:if test="${sessionScope.user_choice2.id!=sessionScope.questions.get(1).correctChoiceId}">Your answer was wrong</c:if>
    </p>

    <hr class="dashed">

    <p>Question 3:</p>
    <p>${sessionScope.questions.get(2).description}</p>
    <c:forEach items="${sessionScope.choices[2]}" var="choice" varStatus="loop">
        <p>${choice.description}</p>
    </c:forEach>
    <p>Your answer: ${sessionScope.user_choice3.description}</p>
    <p>
        <c:if test="${sessionScope.user_choice3.id==sessionScope.questions.get(2).correctChoiceId}">Your answer was correct</c:if>
        <c:if test="${sessionScope.user_choice3.id!=sessionScope.questions.get(2).correctChoiceId}">Your answer was wrong</c:if>
    </p>

    <hr class="dashed">

    <p>Question 4:</p>
    <p>${sessionScope.questions.get(3).description}</p>
    <c:forEach items="${sessionScope.choices[3]}" var="choice" varStatus="loop">
        <p>${choice.description}</p>
    </c:forEach>
    <p>Your answer: ${sessionScope.user_choice4.description}</p>
    <p>
        <c:if test="${sessionScope.user_choice4.id==sessionScope.questions.get(3).correctChoiceId}">Your answer was correct</c:if>
        <c:if test="${sessionScope.user_choice4.id!=sessionScope.questions.get(3).correctChoiceId}">Your answer was wrong</c:if>
    </p>

    <hr class="dashed">

    <p>Question 5:</p>
    <p>${sessionScope.questions.get(4).description}</p>
    <c:forEach items="${sessionScope.choices[4]}" var="choice" varStatus="loop">
        <p>${choice.description}</p>
    </c:forEach>
    <p>Your answer: ${sessionScope.user_choice5.description}</p>
    <p>
        <c:if test="${sessionScope.user_choice5.id==sessionScope.questions.get(4).correctChoiceId}">Your answer was correct</c:if>
        <c:if test="${sessionScope.user_choice5.id!=sessionScope.questions.get(4).correctChoiceId}">Your answer was wrong</c:if>
    </p>

    <hr class="dashed">

    <p>Score: ${sessionScope.result.score}%</p>
    <p>Result:
        <c:if test="${sessionScope.result.score>60}">
            You've passed the quiz!
        </c:if>
        <c:if test="${sessionScope.result.score<=60}">
            You've failed the quiz
        </c:if>
    </p>
</div>
</body>

</html>