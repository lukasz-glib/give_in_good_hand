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
    <section>
        <a href="/user/changeData" class="btn btn--large" style="text-align: center;">Zmiana danych</a>
        <a href="/user/changePassword" class="btn btn--large" style="text-align: center;">Zmiana hasła</a>
    </section>
<jsp:include page="../media/footer.jsp"/>
</body>
</html>
