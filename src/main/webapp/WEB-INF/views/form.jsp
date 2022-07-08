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

<%--        <form action="form-confirmation.jsp" method="post">--%>
        <form:form action="/form" method="post" modelAttribute="donation" id="donForm">
          <!-- STEP 1: class .active is switching steps -->
          <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>


            <c:forEach var="category" items="${categories}">
              <div class="form-group form-group--checkbox">
                <label>
<%--                  <input--%>
<%--                          type="checkbox"--%>
<%--                          name="categories"--%>
<%--                          value="clothes-to-use"--%>
<%--                  />--%>
                  <form:checkbox path="categories" value="${category}"/>

                  <span class="checkbox"></span>
                  <span class="description">
                          ${category.name}
                  </span>
                </label>
              </div>
            </c:forEach>


<%--            <form:checkboxes path="categories"--%>
<%--                             itemValue="id"--%>
<%--                             itemLabel="name"--%>
<%--                             items="${categories}"--%>
<%--                             element="span class='form-group form-group--checkbox'"--%>
<%--            />--%>
<%--            <form:errors path="categories"/>--%>

              <%--                             cssClass="form-group form-group--checkbox"--%>
            <%--                             element="span class='form-group form-group--checkbox'"--%>


          <%--            <div class="form-group form-group--checkbox">--%>
<%--              <label>--%>
<%--                <input--%>
<%--                  type="checkbox"--%>
<%--                  name="categories"--%>
<%--                  value="clothes-to-use"--%>
<%--                />--%>
<%--                <span class="checkbox"></span>--%>
<%--                <span class="description"--%>
<%--                  >ubrania, które nadają się do ponownego użycia</span--%>
<%--                >--%>
<%--              </label>--%>
<%--            </div>--%>

<%--            <div class="form-group form-group--checkbox">--%>
<%--              <label>--%>
<%--                <input--%>
<%--                  type="checkbox"--%>
<%--                  name="categories"--%>
<%--                  value="clothes-useless"--%>
<%--                />--%>
<%--                <span class="checkbox"></span>--%>
<%--                <span class="description">ubrania, do wyrzucenia</span>--%>
<%--              </label>--%>
<%--            </div>--%>

<%--            <div class="form-group form-group--checkbox">--%>
<%--              <label>--%>
<%--                <input type="checkbox" name="categories" value="toys" />--%>
<%--                <span class="checkbox"></span>--%>
<%--                <span class="description">zabawki</span>--%>
<%--              </label>--%>
<%--            </div>--%>

<%--            <div class="form-group form-group--checkbox">--%>
<%--              <label>--%>
<%--                <input type="checkbox" name="categories" value="books" />--%>
<%--                <span class="checkbox"></span>--%>
<%--                <span class="description">książki</span>--%>
<%--              </label>--%>
<%--            </div>--%>

<%--            <div class="form-group form-group--checkbox">--%>
<%--              <label>--%>
<%--                <input type="checkbox" name="categories" value="other" />--%>
<%--                <span class="checkbox"></span>--%>
<%--                <span class="description">inne</span>--%>
<%--              </label>--%>
<%--            </div>--%>

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
                <form:input path="quantity" name="quantityInput"/>
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

<%--            <form:select path="institution" itemLabel="name" items="${institutions}" name="institution"/>--%>


              <c:forEach var="institution" items="${institutions}">
                  <div class="form-group form-group--checkbox">
                      <label>
<%--                          <input type="radio" name="organization" value="old" />--%>
                <form:radiobutton path="institution" value="${institution}" title="${institution.name}" name="institution"/>
                    <span class="checkbox radio"></span>
                          <span class="description">
                  <div class="title">${institution.name}</div>
                  <div class="subtitle">
                          ${institution.description}
                  </div>
                </span>
                      </label>
                  </div>
              </c:forEach>
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
                  <label> Ulica <form:input path="street" name="street"/>
                    <form:errors path="street"/></label>

                </div>

                <div class="form-group form-group--inline">
                  <label> Miasto <form:input path="city" name="city"/>
                    <form:errors path="city"/>
                  </label>


                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Kod pocztowy   <form:input path="zipCode" name="zipCode"/>
                    <form:errors path="zipCode"/>
                  </label>
                </div>

                <div class="form-group form-group--inline">
                  <label>
                    Numer telefonu  <form:input path="phoneNumber" name="phoneNumber"/>
                    <form:errors path="phoneNumber"/>
                  </label>
                </div>
              </div>

              <div class="form-section--column">
                <h4>Termin odbioru</h4>
                <div class="form-group form-group--inline">
                  <label> Data  <form:input type="date" path="pickUpDate" name="pickUpDate"/>
                    <form:errors path="pickUpDate"/>
                  </label>


                </div>

                <div class="form-group form-group--inline">
                  <label> Godzina   <form:input type="time" path="pickUpTime" name="pickUpTime"/>
                    <form:errors path="pickUpTime"/>
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
                      >4 worki ubrań w dobrym stanie dla dzieci</span
                    >
                  </li>

                  <li>
                    <span class="icon icon-hand"></span>
                    <span id="institutionOut" class="summary--text"
                      >Dla fundacji "Mam marzenie" w Warszawie</span
                    >
                  </li>
                </ul>
              </div>

              <div class="form-section form-section--columns">
                <div class="form-section--column">
                  <h4>Adres odbioru:</h4>
                  <ul>
                    <li id="streetOut">Prosta 51</li>
                    <li id="cityOut">Warszawa</li>
                    <li id="zipCodeOut">99-098</li>
                    <li id="phoneNumberOut">123 456 789</li>
                  </ul>
                </div>

                <div class="form-section--column">
                  <h4>Termin odbioru:</h4>
                  <ul>
                    <li id="pickUpDateOut">13/12/2018</li>
                    <li id="pickUpTimeOut">15:40</li>
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