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
        <h1>Moje zarejestrowane dary</h1>
        <tr class="form-group form-group--inline">
            <th>Lp.</th>
            <th>Ilość worków</th>
            <th>Miasto</th>
            <th>Informacja o darze</th>
            <th>Szczegółowa data odbioru</th>
        </tr>
        <c:forEach items="${allUsersDonations}" var="donation" varStatus="stat">
            <tr class="form-group text-area">
                <td>${stat.count}</td>
                <td>${donation.email}</td>
                <td>${donation.username}</td>
                <td>${donation.lastName}</td>
                <td>${donation.active}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="../media/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
