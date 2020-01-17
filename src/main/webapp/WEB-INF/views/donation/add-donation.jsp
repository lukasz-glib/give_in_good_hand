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
<section class="form--steps">
        <div class="form--steps-instructions">
                <div class="form--steps-container">
                        <h3>Ważne!</h3>
                        <p data-step="1" class="active">
                                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                                wiedzieć komu najlepiej je przekazać.
                        </p>
                        <p data-step="2">
                                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                                wiedzieć komu najlepiej je przekazać.
                        </p>
                        <p data-step="3">
                                Wybierz jedną, do
                                której trafi Twoja przesyłka.
                        </p>
                        <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
                </div>
        </div>

        <div class="form--steps-container">
                <div class="form--steps-counter">Krok <span>1</span>/4</div>

                <form:form method="post" modelAttribute="donations">

                        <!-- STEP 1: class .active is switching steps -->
                        <div data-step="1" class="active">
                                <h3>Zaznacz co chcesz oddać:</h3>
                                <c:forEach items="${categories}" var="category">
                                <div class="form-group form-group--checkbox">
                                        <label>
                                                <form:radiobutton name="categories" path="categories" class="categoriesAll"
                                                                  value="${category.id}"/>
                                                <span class="checkbox"></span>
                                                <span class="description" id="nameCateg">${category.name}</span>
                                        </label>
                                </div>
                                </c:forEach>

                                <div class="form-group form-group--buttons">
                                        <button type="button" class="btn next-step">Dalej</button>
                                </div>
                        </div>
                        <!-- STEP 2 -->
                        <div data-step="2">
                                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                                <div class="form-group form-group--inline">
                                        <label>
                                                Liczba 60l worków:
                                                <form:input id="bags" path="quantity" name="bags" step="1" min="1"/>
                                        </label>
                                </div>

                                <div class="form-group form-group--buttons">
                                        <button type="button" class="btn prev-step">Wstecz</button>
                                        <button type="button" class="btn next-step">Dalej</button>
                                </div>
                        </div>

                        <!-- STEP 3 -->
                        <div data-step="3">
                                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                                <c:forEach items="${institutions}" var="institution">
                                <div class="form-group form-group--checkbox">
                                        <label>
                                                <form:radiobutton path="institution" value="${institution.id}"
                                                                  name="organization" id="institution"/>
                                                <span class="checkbox radio"></span>
                                                <span class="description">
                                                        <div class="title" id="nameIns">${institution.name}”</div>
                                                        <div class="subtitle">${institution.description}</div>
                                                </span>
                                        </label>
                                </div>
                                </c:forEach>

                                <div class="form-group form-group--buttons">
                                        <button type="button" class="btn prev-step">Wstecz</button>
                                        <button type="button" class="btn next-step">Dalej</button>
                                </div>
                        </div>

                        <!-- STEP 4 -->
                        <div data-step="4">
                                <h3>Podaj adres oraz termin odbioru rzeczy przez kuriera:</h3>

                                <div class="form-section form-section--columns">
                                        <div class="form-section--column">
                                                <h4>Adres odbioru</h4>
                                                <div class="form-group form-group--inline">
                                                        <label>
                                                               Ulica <form:input id="street" path="street" name="street"/>
                                                        </label>
                                                </div>

                                                <div class="form-group form-group--inline">
                                                        <label>
                                                               Miasto <form:input id="city" path="city" name="city"/>
                                                        </label>
                                                </div>

                                                <div class="form-group form-group--inline">
                                                        <label>
                                                                Kod pocztowy <form:input id="zipCode" path="zipCode"
                                                                                         name="zipCode"/>
                                                        </label>
                                                </div>

                                                <div class="form-group form-group--inline">
                                                        <label>
                                                                Numer telefonu <input id="phoneNumber" type="phone"
                                                                                      name="phone" />
                                                        </label>
                                                </div>
                                        </div>

                                        <div class="form-section--column">
                                                <h4>Termin odbioru</h4>
                                                <div class="form-group form-group--inline">
                                                        <label>
                                                                Data <form:input id="date" path="pickUpDate" type="date"
                                                                                 name="date"/>
                                                        </label>
                                                </div>

                                                <div class="form-group form-group--inline">
                                                        <label>
                                                            Godzina <form:input id="time" path="pickUpTime" type="time"
                                                                                name="time"/>
                                                        </label>
                                                </div>

                                                <div class="form-group form-group--inline">
                                                        <label>
                                                                Uwagi dla kuriera
                                                                <form:textarea id="comment" path="pickUpComment" rows="5"
                                                                               name="comment"/>
                                                        </label>
                                                </div>
                                        </div>
                                </div>
                                <div class="form-group form-group--buttons">
                                        <button type="button" class="btn prev-step">Wstecz</button>
                                        <button type="button" class="btn next-step">Dalej</button>
                                </div>
                        </div>

                        <!-- STEP 5 -->
                        <div data-step="5">
                                <h3>Podsumowanie Twojej darowizny</h3>

                                <div class="summary">
                                        <div class="form-section">
                                                <h4>Oddajesz:</h4>
                                                <ul>
                                                        <li>
                                                                <span class="icon icon-bag"></span>
                                                                <span class="summary--text" id="bagsSummary"></span>
                                                        </li>

                                                        <li>
                                                                <span class="icon icon-hand"></span>
                                                                <span class="summary--text" id="organizationSummary">
                                                                </span>
                                                        </li>
                                                </ul>
                                        </div>

                                        <div class="form-section form-section--columns">
                                                <div class="form-section--column">
                                                        <h4>Adres odbioru:</h4>
                                                        <ul>
                                                                <li id="streetSummary"></li>
                                                                <li id="citySummary"></li>
                                                                <li id="zipCodeSummary"></li>
                                                                <li id="phoneSummary"></li>
                                                        </ul>
                                                </div>

                                                <div class="form-section--column">
                                                        <h4>Termin odbioru:</h4>
                                                        <ul>
                                                                <li id="dateSummary"></li>
                                                                <li id="timeSummary"></li>
                                                                <li id="commentSummary"></li>
                                                        </ul>
                                                </div>
                                        </div>
                                </div>

                                <div class="form-group form-group--buttons">
                                        <button type="button" class="btn prev-step">Wstecz</button>
                                        <button type="submit" class="btn">Potwierdzam</button>
                                </div>
                        </div>
                        <form:errors path="*"/>
                </form:form>
        </div>
</section>
<jsp:include page="../media/footer.jsp"/>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
