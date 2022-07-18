<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/views/jspf/header-user-logged.jspf" %>


    <section class="login-page">
      <h2>Edytuj dane</h2>
      <form:form action="/user/edit" method="post" modelAttribute="user">
        <div class="form-group">
          <form:input path="email" type="email" name="email" placeholder="Email"  />
          <form:errors path="email"/>
        </div>
        <div class="form-group">
          <form:input path="firstName" type="text" name="firstName" placeholder="Imię"  />
          <form:errors path="firstName"/>
        </div>
        <div class="form-group">
          <form:input path="lastName" type="text" name="lastName" placeholder="Nazwisko"  />
          <form:errors path="lastName"/>
        </div>
        <div class="form-group">
          <form:input path="street" type="text" name="street" placeholder="Ulica"  />
          <form:errors path="street"/>
        </div>
        <div class="form-group">
          <form:input path="city" type="text" name="city" placeholder="Miasto"  />
          <form:errors path="city"/>
        </div>
        <div class="form-group">
          <form:input path="zipCode" type="text" name="zipCode" placeholder="Kod pocztowy"  />
          <form:errors path="zipCode"/>
        </div>
        <div class="form-group">
          <form:input path="phoneNumber" type="text" name="phoneNumber" placeholder="Telefon"  />
          <form:errors path="phoneNumber"/>
        </div>

        <form:hidden path="password"/>

        <div class="form-group form-group--buttons">
          <form:button class="btn" type="submit">Zapisz</form:button>
        </div>
      </form:form>
    </section>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>