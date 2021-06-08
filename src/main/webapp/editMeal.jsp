<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meal</h2>
<hr>
<form method="POST" action='meals' accept-charset="UTF-8" name="frmAddMeal">
    <input type="hidden" readonly="readonly" name="mealId" value="<c:out value="${meal.id}" />"/> <br/>
    Description : <input type="text" name="description" value="<c:out value="${meal.description}" />"/> <br/>
    Calories : <input type="text" name="calories" value="<c:out value="${meal.calories}" />"/> <br/>
    DateTime : <input type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}" />"/> <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>