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
    <input type="hidden" name="mealId"
           value="<%=(request.getParameter("mealId")!=null)?request.getParameter("mealId"):"0"%>"/>
    Description : <input type="text" name="description" value="${meal.description}"/> <br/>
    Calories : <input type="number" id="number" name="calories" value="${meal.calories}"/> <br/>
    DateTime : <input type="datetime-local" name="dateTime" value="${meal.dateTime}"/> <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>