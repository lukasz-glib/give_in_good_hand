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
            <th>Data odbioru</th>
            <th>Godzina odbioru</th>
            <th>Status darów</th>
            <th>Akcja</th>
        </tr>
        <c:forEach items="${allUserDonations}" var="donation" varStatus="stat">
            <tr class="form-group text-area">
                <td>${stat.count}</td>
                <td>${donation.quantity}</td>
                <td>${donation.city}</td>
                <td>${donation.pickUpComment}</td>
                <td>${donation.pickUpDate}</td>
                <td>${donation.pickUpTime}</td>
                <c:if test="${donation.status == true}">
                    <td>Dary <b>odebrane</b></td>
                </c:if>
                <c:if test="${donation.status == false}">
                    <td>Dary <b>nieodebrane</b></td>
                </c:if>
                <td>
                    <c:url value="/user/myDonations/delete" var="deleteURL">
                        <c:param name="id" value="${donation.id}"/>
                    </c:url>
                    <c:url value="/user/myDonations/update" var="updateURL">
                        <c:param name="id" value="${donation.id}"/>
                    </c:url>
                    <a href="${deleteURL}" class="btn btn--without-border">Usuń</a>
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
