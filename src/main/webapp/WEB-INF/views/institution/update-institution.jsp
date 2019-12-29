<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form:form method="post" modelAttribute="institution">
    <form:hidden path="id"/>
    <div class="form-section form-section--columns">
        <div class="form-section--column">
            <h4>Podaj dane nowej instytucji:</h4>
            <div class="form-group form-group--inline">
                <label> Nazwa <form:input path="name"/> </label>
            </div>
            <div class="form-group form-group--inline">
                <label> Opis <form:textarea path="description"/> </label>
            </div>
            <div class="form-group form-group--buttons">
                <a href="/admin/institutions" class="btn">Wstecz</a>
                <button type="submit" class="btn">Edytuj</button>
            </div>
        </div>
    </div>
    <form:errors path="*"/>
</form:form>
<jsp:include page="../media/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
