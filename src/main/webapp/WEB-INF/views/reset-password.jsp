<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/views/jspf/header-login-register.jspf" %>

    <section class="login-page">
      <h2>Resetowanie hasła</h2>
      <form:form action="/login/resetpassword" method="post">
          <h3>Podaj Twoj Email:</h3>
        <div class="form-group">
          <input type="email" name="email" placeholder="Email" />
        </div>
        <c:if test="${emailnotexists != null}">
          <h3>Podany email nie należy do żadnego użytkownika!</h3>
        </c:if>
        <div class="form-group form-group--buttons">
          <button class="btn" type="submit">Wyślij link resetujący hasło.</button>
        </div>
      </form:form>
    </section>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>