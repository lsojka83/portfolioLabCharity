<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/views/jspf/header-form.jspf" %>

      <div class="slogan container container--90">
        <div class="slogan--item">
          <h1>
            Oddaj rzeczy, których już nie chcesz<br />
            <span class="uppercase">potrzebującym</span>
          </h1>

          <div class="slogan--steps">
            <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
            <ul class="slogan--steps-boxes">
              <li>
                <div><em>1</em><span>Wybierz rzeczy</span></div>
              </li>
              <li>
                <div><em>2</em><span>Spakuj je w worki</span></div>
              </li>
              <li>
                <div><em>3</em><span>Wybierz fundację</span></div>
              </li>
              <li>
                <div><em>4</em><span>Zamów kuriera</span></div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </header>

    <section class="form--steps">
      <div class="form--steps-instructions">
        <div class="form--steps-container">
          <h3>Ważne!</h3>
          <p data-step="1" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="2">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
          <p data-step="3">
           Wybierz jedną, do
            której trafi Twoja przesyłka.
          </p>
          <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
      </div>

      <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form action="/form" method="post" modelAttribute="donation" id="donForm">
          <!-- STEP 1: class .active is switching steps -->
          <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>

            <c:forEach var="category" items="${categories}">
              <div class="form-group form-group--checkbox">
                <label>
                    <%--form:checkbox IS NOT SHOW SELECTION - WHY??--%>
                <form:checkbox path="categories" value="${category}"/>
<%--                  <input type="checkbox" name="categories" value="${category.id}" id=" ${category.name}"/>--%>
                  <span class="checkbox"></span>
                  <span class="description">
                          ${category.name}
                  </span>
                </label>
              </div>
            </c:forEach>
            <div class="form-group form-group--checkbox">
            <form:errors path="categories"/>
             <span id="categories" class="e"></span>
            </div>

            <%--HOW TO SET CSS CLASS FOR  form:checkboxes?? NO--%>
<%--                      <form:checkboxes path="categories"--%>
<%--                             itemValue="id"--%>
<%--                             itemLabel="name"--%>
<%--                             items="${categories}"--%>
<%--                             element="span class='form-group form-group--checkbox'"--%>
<%--            />--%>
<%--            <form:errors path="categories"/>--%>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 2 -->
          <div data-step="2">
            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

            <div class="form-group form-group--inline">
              <label>
                Liczba 60l worków:
                <form:input type="number" path="quantity"/>
                <form:errors path="quantity"/>
              <%--                <input type="number" name="bags" step="1" min="1" />--%>
              </label>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 4 -->
          <div data-step="3">
            <h3>Wybierz organizacje, której chcesz pomóc:</h3>

                <%--DO NOT DELETE--%>
                <%--Institution picker generated in JSP from backed--%>

<%--              <c:forEach var="institution" items="${institutions}">--%>
<%--                  <div class="form-group form-group--checkbox">--%>
<%--                      <label>--%>
<%--                <form:radiobutton path="institution" value="${institution}" title="${institution.name}" name="institution"/>--%>
<%--                    <span class="checkbox radio"></span>--%>
<%--                          <span class="description">--%>
<%--                  <div class="title">${institution.name}</div>--%>
<%--                  <div class="subtitle">--%>
<%--                          ${institution.description}--%>
<%--                  </div>--%>
<%--                </span>--%>
<%--                      </label>--%>
<%--                  </div>--%>
<%--              </c:forEach>--%>
<%--            <div class="form-group form-group--checkbox">--%>
<%--              <form:errors path="institution"/>--%>
<%--            </div>--%>
              <%--DO NOT DELETE--%>

            <div id="institution-list">
              <%--        here institution list will be added by JS         --%>
            </div>

                  <%--pagination--%>
            <div id="pagination" class="help--slides-pagination">
              <a href="" id="institutionPreviousPageLink"><< Previous&nbsp</a>
                <%--       here pages links will be generated by JS       --%>
              <a href="" id="institutionNextPageLink">Next >></a>
            </div>
            <form:errors path="institution"/>


            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 5 -->
          <div data-step="4">
            <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

            <div class="form-section form-section--columns">
              <div class="form-section--column">
                <h4>Adres odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Ulica <form:input path="street" name="street" />
                    <form:errors path="street" cssClass="error-message"/></label>

                </div>

                <div class="form-group form-group--inline">
                  <label> Miasto <form:input path="city" name="city"/>
                    <form:errors path="city" cssClass="error-message"/>
                  </label>


                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Kod pocztowy   <form:input path="zipCode" name="zipCode"/>
                    <form:errors path="zipCode" cssClass="error-message"/>
                  </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Numer telefonu  <form:input path="phoneNumber" name="phoneNumber"/>
                    <form:errors path="phoneNumber" cssClass="error-message"/>
                  </label>
                </div>
              </div>

              <div class="form-section--column">
                <h4>Termin odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Data  <form:input type="date" path="pickUpDate" name="pickUpDate"/>
                    <form:errors path="pickUpDate" cssClass="error-message"/>
                  </label>


                </div>

                <div class="form-group form-group--inline">
                  <label> Godzina   <form:input type="time" path="pickUpTime" name="pickUpTime"/>
                    <form:errors path="pickUpTime" cssClass="error-message"/>
                  </label>


                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Uwagi dla kuriera
                    <form:textarea path="pickUpComment" name="moreInfo" rows="5"/>
                  <%--                    <textarea name="more_info" rows="5"></textarea>--%>
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="button" class="btn next-step">Dalej</button>
            </div>
          </div>

          <!-- STEP 6 -->
          <div data-step="5">
            <h3>Podsumowanie Twojej darowizny</h3>

            <div class="summary">
              <div class="form-section">
                <h4>Oddajesz:</h4>
                <ul>
                  <li>
                    <span class="icon icon-bag"></span>
                    <span class="summary--text" id="quantityOutput"
                      ></span
                    >
                  </li>

                  <li>
                    <span class="icon icon-hand"></span>
                    <span id="institutionOut" class="summary--text"
                      ></span
                    >
                  </li>
                </ul>
              </div>

              <div class="form-section form-section--columns">
                <div class="form-section--column">
                  <h4>Adres odbioru:</h4>
                  <ul>
                    <li id="streetOut"></li>
                    <li id="cityOut"></li>
                    <li id="zipCodeOut"></li>
                    <li id="phoneNumberOut"></li>
                  </ul>
                </div>

                <div class="form-section--column">
                  <h4>Termin odbioru:</h4>
                  <ul>
                    <li id="pickUpDateOut"></li>
                    <li id="pickUpTimeOut"></li>
                    <li id="moreInfoOut">Brak uwag</li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="form-group form-group--buttons">
              <button type="button" class="btn prev-step">Wstecz</button>
              <button type="submit" class="btn">Potwierdzam</button>
            </div>
          </div>
        </form:form>
      </div>
    </section>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>