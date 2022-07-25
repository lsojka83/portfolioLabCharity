<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ include file="/WEB-INF/views/jspf/header-login-register.jspf" %>

<section class="login-page">
    <h2>Resetowanie hasła - podaj nowe hasło</h2>

    <form:form action="/login/reset" method="post">
        <div class="form-group">
            <div class="form-group">
                <input type="password" name="password" placeholder="Hasło"/>
            </div>
            <c:if test="${not empty invalidPassword}">
                ${invalidPassword}
            </c:if>
        </div>

        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło"/>
        </div>

        <input name="uuid" value="${uuid}" hidden/>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zapisz nowe hasło</button>
        </div>
    </form:form>
</section>


<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>