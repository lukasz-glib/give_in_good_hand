<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 19.12.2019
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="donations">

        <form:checkboxes path="categories"
                         items="${categories}" itemLabel="name"/><br/>

        <form:select path="institution" items="${institutions}"/><br/>
        <form:input path="zipCode" /><br/>
        <form:input path="street" /><br/>
        <form:input path="city"/><br/>
        <form:input path="quantity"/><br/>
        <form:textarea path="pickUpComment"/><br/>
        <form:input type="date" path="pickUpDate"/><br/>
        <form:input type="time" path="pickUpTime" />
        <p><input type="submit" value="Dodaj"/></p>
</form:form>
</body>
</html>
