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
<div class="form-section form-section--columns">
    <div class="form-section--column">
        <table>
            <tr class="form-group form-group--inline">
                <th>Lp.</th>
                <th>Nazwa organizacji</th>
                <th>Opis organizacji</th>
            </tr>
            <tr class="form-group form-group--inline">
                <c:forEach items="${allInstitutionsManagement}" var="institution" varStatus="stat">
                    <td>${stat.count}</td>
                    <td>${institution.name}</td>
                    <td>${institution.description}</td>
                    <td>
                        <c:url value="/admin/institutions/delete" var="deleteURL">
                            <c:param name="id" value="${institution.id}"/>
                        </c:url>
                        <c:url value="/admin/institutions/update" var="updateURL">
                            <c:param name="id" value="${institution.id}"/>
                        </c:url>
                        <li><a href="${deleteURL}" class="btn btn--without-border">Usuń</a></li>
                        <li><a href="${updateURL}" class="btn btn--without-border">Edytuj</a></li>
                    </td>
                </c:forEach>
                <li><a href="/admin/institutions/create" class="btn btn--without-border">Dodaj nową instytucję</a></li>
            </tr>
        </table>
    </div>
</div>
<jsp:include page="../media/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
