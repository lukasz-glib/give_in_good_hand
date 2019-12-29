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
<section class="login-page">
    <h2>Załóż konto Administratora</h2>
    <form:form method="post" modelAttribute="registrationAdmin">
        <div class="form-group">
            <form:input path="username" name="username" placeholder="First Name" required="true"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" name="lastName" placeholder="Last Name" required="true"/>
        </div>
        <div class="form-group">
            <form:input path="email" name="email" placeholder="Email" required="true"/>
        </div>
        <div class="form-group">
            <form:input path="password" name="password" placeholder="Hasło" required="true"/>
        </div>
        <div class="form-group">
            <form:input path="repassword" name="password2" placeholder="Powtórz hasło" required="true"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto Admina</button>
        </div>
        <form:errors path="*"/>
    </form:form>
</section>
<jsp:include page="../media/footer.jsp"/>
</body>
</html>