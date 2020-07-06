<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Пользователи</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Возврат на главную страницу</a>

<br/>
<br/>

<h1>Список пользователей</h1>

<c:if test="${listUsers!=null}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Имя</th>
            <th width="120">E-mail</th>
            <th width="120">Возраст</th>
            <th width="60">Изменить</th>
            <th width="60">Удалить</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td><a href="/userdata/${user.id}" target="_blank">${user.userName}</a></td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td><a href="<c:url value='/edit/${user.id}'/>">Изменить</a></td>
                <td><a href="<c:url value='/remove/${user.id}'/>">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Добавление/Изменение пользователя</h1>

<c:url var="addAction" value="/users/add"/>

<form:form action="${addAction}" modelAttribute="user">
    <table>
        <c:if test="${!empty user.userName}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="userName">
                    <spring:message text="Имя"/>
                </form:label>
            </td>
            <td>
                <form:input path="userName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">
                    <spring:message text="E-mail"/>
                </form:label>
            </td>
            <td>
                <form:input path="email"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="age">
                    <spring:message text="Возраст"/>
                </form:label>
            </td>
            <td>
                <form:input path="age"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.userName}">
                    <input type="submit"
                           value="<spring:message text="Изменить пользователя"/>"/>
                </c:if>
                <c:if test="${empty user.userName}">
                    <input type="submit"
                           value="<spring:message text="Добавить пользователя"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
