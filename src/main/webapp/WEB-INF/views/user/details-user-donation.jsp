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
<div class="container--90" style="text-align: center">
    <table style="text-align: center">
        <h1>Szczegóły daru</h1>
        <h2>
            <tr>
                <td>Organizacja odbierająca</td>
                <td><c:out value="${institutionName}"/></td>
            </tr>
            <tr>
                <td> <b>Data i czas przekazania: </b> <br/>
                <td>${pickUpDate}, ${pickUpTime}</td>
            </tr>
            <tr>
                <td> <b>Status: </b> <br/>
                <td>${status}</td>
                <c:url value="/user/myDonations/changeStatus" var="detailsURL">
                    <c:param name="id" value="${id}"/>
                </c:url>
                <td><b> <a href="${detailsURL}">Zmień status</a></b></td>
            </tr>
        </h2>
    </table>
    <a href="/user/myDonations" class="btn btn--large" style="text-align: center;">Powrót</a>
</div>
<jsp:include page="../media/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
