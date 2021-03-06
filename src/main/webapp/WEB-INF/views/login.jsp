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
<%--    <title>Login</title>--%>
<%--    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>--%>
<%--  </head>--%>
<%--  <body>--%>
<%--    <header>--%>
<%--      <nav class="container container--70">--%>
<%--        <ul class="nav--actions">--%>
<%--          <li><a href="#">Zaloguj</a></li>--%>
<%--          <li class="highlighted"><a href="#">Załóż konto</a></li>--%>
<%--        </ul>--%>

<%--        <%@ include file="/WEB-INF/views/jspf/header-topbar-links.jspf" %>--%>

<%--      </nav>--%>
<%--    </header>--%>
<%@ include file="/WEB-INF/views/jspf/header-login-register.jspf" %>

    <section class="login-page">
      <h2>Zaloguj się</h2>
      <form:form method="post">
        <div class="form-group">
<%--          <input type="email" name="email" placeholder="Email" />--%>
          <input type="text" name="username" placeholder="Email" />
        </div>
        <div class="form-group">
          <input type="password" name="password" placeholder="Hasło" />
          <a href="/login/resetpassword" class="btn btn--small btn--without-border reset-password">Zresetuj hasło</a>
        </div>

          <div class="about-us--text">
            <p>
              <c:if test="${param.error != null}">
              Zły email lub hasło!
            </c:if>
              <c:if test="${param.inactive != null}">
            Użytkownik nie został aktywowany. Kliknij w otrzymanym mailu.
            </c:if>
              <c:if test="${param.userregistered != null}">
            Użytkownik zarejestrowany. Email został wysłany.
            </c:if>
            </p>
          </div>

        <div class="form-group form-group--buttons">
          <a href="/register" class="btn btn--without-border">Załóż konto</a>
          <button class="btn" type="submit">Zaloguj się</button>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form:form>
    </section>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>