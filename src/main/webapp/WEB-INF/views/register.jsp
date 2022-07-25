<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--<!DOCTYPE html>--%>
<%--<html lang="pl">--%>
<%--  <head>--%>
<%--    <meta charset="UTF-8" />--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0" />--%>
<%--    <meta http-equiv="X-UA-Compatible" content="ie=edge" />--%>
<%--    <title>Document</title>--%>
<%--    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>--%>
<%--  </head>--%>
<%--  <body>--%>
<%--    <header>--%>
<%--      <nav class="container container--70">--%>
<%--        <ul class="nav--actions">--%>
<%--          <li><a href="/login">Zaloguj</a></li>--%>
<%--          <li class="highlighted"><a href="/register">Załóż konto</a></li>--%>
<%--        </ul>--%>

<%--        <%@ include file="/WEB-INF/views/jspf/header-topbar-links.jspf" %>--%>

<%--      </nav>--%>
<%--    </header>--%>
<%@ include file="/WEB-INF/views/jspf/header-login-register.jspf" %>


    <section class="login-page">
      <h2>Załóż konto</h2>
      <form:form action="/register" method="post" modelAttribute="user">
        <div class="form-group">
          <form:input path="email" type="email" name="email" placeholder="Email"  />
          <form:errors path="email"/>
          <c:if test="${userexists != null}">
            <h3>Podany email już istnieje!</h3>
          </c:if>
        </div>



        <div class="form-group">
          <form:input path="password" type="password" name="password" placeholder="Hasło" />
          <form:errors path="password"/>
          <c:if test="${not empty invalidPassword}">
            ${invalidPassword}
          </c:if>

        </div>
        <div class="form-group">
          <input type="password" name="password2" placeholder="Powtórz hasło" />
        </div>

<%--        <input name="role" value="${role}" hidden>--%>

        <div class="form-group form-group--buttons">
          <a href="/login" class="btn btn--without-border">Zaloguj się</a>
          <form:button class="btn" type="submit">Załóż konto</form:button>
        </div>
      </form:form>
    </section>


<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>