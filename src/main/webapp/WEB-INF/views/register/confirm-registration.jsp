<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<jsp:include page="../media/header.jsp"/>
<div class="slogan container--90">
    <h2>Dziękujemy za rejestrację ! Twoje konto zostało utworzone.</h2>
</div>
<section class="steps">
    <a href="/" class="btn btn--large" style="text-align: center">Powrót do strony głównej</a>
</section>
<jsp:include page="../media/footer.jsp"/>
</body>
</html>
