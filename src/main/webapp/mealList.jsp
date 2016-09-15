<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="mealList" class="ru.javawebinar.topjava.util.Test"/>
<jsp:useBean id="staticList" class="ru.javawebinar.topjava.util.MealsUtil"/>

<html>
<head>
    <LINK href="mealStyle.css" rel="stylesheet" type="text/css">

    <title>User list</title>
    <c:set var="rawItems" value="${mealList.meals}"/>
    <c:set var="items" value="${mealList.mealsWithExceeded}"/>
    <%
        List<MealWithExceed> listFromGet = (List<MealWithExceed>) request.getAttribute("list");

        //Show add form
        String add = request.getParameter("add");
        boolean isAddPressed = false;
        if (add != null) isAddPressed = true;
        //Show update form
        String update = request.getParameter("update");
        boolean isUpdatePressed = false;
        if (update != null) isUpdatePressed = true;

    %>

</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<div class="userMeals">
    <table class="userMealsTable" border="0">
        <tr>
            <th>Description</th>
            <th>Calories</th>
            <th>Date Time</th>
        </tr>
        <c:forEach var="meal" items="<%=listFromGet%>">
            <c:choose>
                <c:when test="${meal.exceed==true}">
                    <tr class="exceed">
                </c:when>
                <c:otherwise>
                    <tr class="notExceed">
                </c:otherwise>
            </c:choose>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.formattedDate}</td>
                <td>
                    <form method="post" action="meals">
                        <input type="number" name="id" value="${meal.id}" hidden/>
                        <input type="text" name="description" value="${meal.description}" hidden/>
                        <input type="number" name="calories" value="${meal.calories}" hidden/>
                        <input type="datetime-local" name="dateTime" value="${meal.formattedDate}" hidden/>
                        <input type="submit" name="action" value="delete"/>
                        <input type="submit" name="update" value="update"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<form method="post" action="meals">
    <input type="submit" name="add" value="add"/>
</form>
<c:if test="<%=isAddPressed%>">
    <form class="protoForm" method="post" action="meals">
        Description: <input type="text" name="description"/><br>
        Calories: <input type="number" name="calories"/><br>
        Date Time: <input type="datetime-local" name="dateTime"/><br>
        <input type="submit" name="action" value="add"/>
    </form>
</c:if>
<c:if test="<%=isUpdatePressed%>">
    <%=request.getParameter("description")%>
    <%=request.getParameter("id")%>
    <%=request.getParameter("dateTime")%>
    <%=request.getParameter("calories")%>
    <form class="protoForm" method="post" action="meals">
        Description: <input type="text" name="description" value=<%=request.getParameter("description")%>/><br>
        Calories: <input type="number" name="calories" value=<%=request.getParameter("calories")%>/><br>
        Date Time: <input type="datetime-local" name="dateTime" value=<%=request.getParameter("dateTime")%>/><br>
        <input type="number" name="id" value=<%=request.getParameter("id")%>>
        <input type="submit" name="action" value="update"/>
    </form>
</c:if>
</body>
</html>
