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
<div class="container--90">
    <table id="tabAdmin">
        <tr class="form-group form-group--inline">
            <th>Lp.</th>
            <th>Email</th>
            <th>Imię Użytkownika</th>
            <th>Nazwisko Użytkownika</th>
            <th>Status Użytkownika</th>
            <th>Akcja</th>
        </tr>
        <c:forEach items="${allUsersManagement}" var="user" varStatus="stat">
            <tr class="form-group text-area">
                <td>${stat.count}</td>
                <td>${user.email}</td>
                <td>${user.username}</td>
                <td>${user.lastName}</td>
                <td>${user.active}</td>
                <td>
                    <c:url value="/admin/users/delete" var="deleteURL">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <c:url value="/admin/users/lock" var="lockURL">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <c:url value="/admin/users/unlock" var="unlockURL">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <c:url value="/admin/users/update" var="updateURL">
                        <c:param name="id" value="${user.id}"/>
                    </c:url>
                    <a href="${deleteURL}" class="btn btn--without-border">Usuń</a>
                    <a href="${lockURL}" class="btn btn--without-border">Zablokuj</a>
                    <a href="${unlockURL}" class="btn btn--without-border">Odblokuj</a>
                    <a href="${updateURL}" class="btn btn--without-border">Edytuj</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="../media/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
