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
    <h2>Edycja danych Użytkownika</h2>
    <form:form method="post" modelAttribute="editDataLoggedUser">
        <div class="form-group">
            <form:input path="username" name="username" placeholder="Podaj nowe Imię" required="true"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" name="lastName" placeholder="Podaj nowe Nazwisko" required="true"/>
        </div>
        <div class="form-group">
            <form:input path="email" name="email" placeholder="Nowy email" required="true"/>
        </div>
        <div class="form-group form-group--buttons">
            <a href="/user/edit" class="btn btn">Wstecz</a>
            <button class="btn" type="submit">Edytuj dane Użytkownika</button>
        </div>
        <form:errors path="*"/>
    </form:form>
</section>
<jsp:include page="../media/footer.jsp"/>
</body>
</html>
