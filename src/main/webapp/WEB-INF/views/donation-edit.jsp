<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/views/jspf/header-user-logged.jspf" %>


    <section class="login-page">
      <h2>Edytuj dane</h2>
      <form:form action="/user/donation" method="post" modelAttribute="donation">
        <div class="form-group">
          <c:if test="${donation.status.value=='złożone'}">

          <form:input path="quantity" type="number" name="quantity" placeholder="Liczba"  />
          <form:errors path="quantity"/>
        </div>
        <div class="form-group">
            <form:select path="categories"  items="${categories}" itemLabel="name" itemValue="id" multiple="true"></form:select>
            <form:errors path="categories"/>
        </div>

                <div class="form-group">
          <form:select path="institution"  items="${institutions}" itemLabel="name" itemValue="id" multiple="false"></form:select>
          <form:errors path="institution"/>
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

        <div class="form-group">
          <form:input path="pickUpComment" type="text" name="pickUpComment" placeholder="Komentarz"  />
          <form:errors path="pickUpComment"/>
        </div>
          </c:if>
        <c:if test="${donation.status.value!='złożone'}">
          <div class="form-group">
            Ilość worków:<br>
            ${donation.quantity}
          </div>
          <div class="form-group">
            Kategorie:<br>
            <c:forEach var="category" items="${donation.categories}">
              <li>${category.name}</li>
            </c:forEach>          </div>
          <div class="form-group">
            Fundacja:<br>
          ${donation.institution.name}
          </div>
          <div class="form-group">
            Ulica:<br>
          ${donation.street}
          </div>
          <div class="form-group">
            Miasto:<br>

          ${donation.city}
          </div>
          <div class="form-group">
            Kod pocztowy:<br>

          ${donation.zipCode}
          </div>
          <div class="form-group">
            Nr tel.:<br>

          ${donation.phoneNumber}
          </div>
          <div class="form-group">
            Komentarz:<br>

          ${donation.pickUpComment}
          </div>
          <form:hidden path="quantity"/>
          <form:hidden path="categories"/>
          <form:hidden path="institution"/>
          <form:hidden path="street"/>
          <form:hidden path="city"/>
          <form:hidden path="zipCode"/>
          <form:hidden path="phoneNumber"/>
          <form:hidden path="pickUpComment"/>
        </c:if>


        <form:hidden path="pickUpDate"/>
        <form:hidden path="pickUpTime"/>
        <form:hidden path="createdOn"/>
        <form:hidden path="status"/>
        <form:hidden path="actualPickUpDate"/>


        <div class="form-group">
          Status daru:
        ${donation.status.value}
        </div>

        <c:if test="${donation.status.value=='złożone'}">
          Czy dar został odebrany?
          <input type="checkbox" id="pickedUp" name="pickedUp" value="1">
        </c:if>

        <c:if test="${not empty donation.actualPickUpDate}">
          Data odebrania daru:
          ${donation.actualPickUpDate}
        </c:if>
        <c:if test="${donation.status.value=='złożone'}">
        <div class="form-group form-group--buttons">
          <form:button class="btn" type="submit">Zapisz</form:button>
        </div>
        </c:if>



<%--        <div class="form-group">--%>
<%--          <form:select path="status"  items="${statuses}" itemLabel="value" itemValue="id" multiple="false"></form:select>--%>
<%--          <form:errors path="status"/>--%>
<%--        </div>--%>

        <form:hidden path="id"/>
      </form:form>
    </section>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>
