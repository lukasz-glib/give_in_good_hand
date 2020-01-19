<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <h2>Potwierdzenie usunięcia Administratora</h2>
    <form:form method="post" modelAttribute="deleteAdmin">
        <form:hidden path="id"/>
        <form:hidden path="email"/>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Potwierdź usunięcie konta</button>
        </div>
        <sec:csrfInput/>
        <form:errors path="*"/>
    </form:form>
    <form:form method="get" action="/admin/admins">
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Anuluj</button>
        </div>
    </form:form>
</section>
<jsp:include page="../media/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
