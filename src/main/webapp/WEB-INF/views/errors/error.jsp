<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container--90">
    <h1 class="settings-label">
        <strong>Ups...</strong> coś poszło nie tak :(
    </h1>
    <h2 class="settings-label">
        Gdybyś potrzebował wskazówki, to:
    </h2>
    <div class="text">
        <p><i>${exception.message}</i></p>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <div class="content">
            <c:if test="${stackTrace != null}">
                <pre>${stackTrace}</pre>
            </c:if>
        </div>
    </sec:authorize>
</div>
<jsp:include page="../media/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
