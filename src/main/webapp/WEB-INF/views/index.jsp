<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="/WEB-INF/views/jspf/header-index.jspf" %>

<div class="slogan container container--90">
    <div class="slogan--item">
        <h1>
            <spring:message code="index.title.line1"/>
           <br/>
            <spring:message code="index.title.line2"/>
        </h1>
    </div>
</div>
</header>

<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>
                    ${totalQuantity}
            </em>

            <h3>Oddanych worków</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
                tempora!</p>
        </div>

        <div class="stats--item">
            <em>
                ${donationCount}
            </em>
            <h3>Przekazanych darów</h3>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
                quam.</p>
        </div>

    </div>
</section>

<section id="steps" class="steps">
    <h2>Wystarczą 4 proste kroki</h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3>Wybierz rzeczy</h3>
            <p>ubrania, zabawki, sprzęt i inne</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3>Spakuj je</h3>
            <p>skorzystaj z worków na śmieci</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3>Zdecyduj komu chcesz pomóc</h3>
            <p>wybierz zaufane miejsce</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3>Zamów kuriera</h3>
            <p>kurier przyjedzie w dogodnym terminie</p>
        </div>
    </div>

    <a href="#" class="btn btn--large">Załóż konto</a>
</section>

<section id="about-us" class="about-us">
    <div class="about-us--text">
        <h2>O nas</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas vitae animi rem pariatur incidunt libero
            optio esse quisquam illo omnis.</p>
        <img src="<c:url value="resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="resources/images/about-us.jpg"/>" alt="People in circle"/>
    </div>
</section>

<section id="help" class="help">
    <h2>Komu pomagamy?</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.
            Możesz sprawdzić czym się zajmują.</p>


        <%--institution list--%>
        <ul class="help--slides-items">
        <%--Option 1--%>
<%--            <c:forEach items="${institutions}" var="institution" varStatus="loopStatus">--%>
<%--                <c:if test="${loopStatus.count % 2 != 0}">--%>
<%--                    <li>--%>
<%--                </c:if>--%>
<%--                    <div class="col">--%>
<%--                        <div class="title">${institution.name}</div>--%>
<%--                        <div class="subtitle">${institution.description}</div>--%>
<%--                    </div>--%>
<%--                <c:if test="${loopStatus.count % 2 == 0}">--%>
<%--                    </li>--%>
<%--                </c:if>--%>
<%--            </c:forEach>--%>
            <%--Option 2--%>
            <c:forEach items="${page.content}" var="institution" varStatus="loopStatus">
                <c:if test="${loopStatus.count % 2 != 0}">
                    <li>
                </c:if>
                <div class="col">
                    <div class="title">${institution.name}</div>
                    <div class="subtitle">${institution.description}</div>
                </div>
                <c:if test="${loopStatus.count % 2 == 0}">
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>

    <%--pagination--%>

        <%--Option 1    --%>
<%--    <div class="help--slides-pagination">--%>
<%--        <a href="/?institutionPageNumber=${previousPageNumber}#help"><< Previous&nbsp</a>--%>
<%--        <c:forEach begin="1" end="${numberOfInstitutionPages}" varStatus="loopStatus">--%>
<%--            <a href="/?institutionPageNumber=${loopStatus.count - 1}#help" title="">${loopStatus.count}&nbsp</a>--%>
<%--        </c:forEach>--%>
<%--        <a href="/?institutionPageNumber=${nextPageNumber}#help">Next >></a>--%>
<%--    </div>--%>
        <%--Option 2--%>
    <div class="help--slides-pagination">
        <c:if test="${page.first == true}">
            <a href="/?institutionPageNumber=${page.pageable.pageNumber}#help"><< Previous&nbsp</a>
        </c:if>
        <c:if test="${page.first == false}">
            <a href="/?institutionPageNumber=${page.pageable.pageNumber-1}#help"><< Previous&nbsp</a>
        </c:if>

        <c:forEach begin="1" end="${page.totalPages}" varStatus="loopStatus">
            <a href="/?institutionPageNumber=${loopStatus.count - 1}#help" title="">${loopStatus.count}&nbsp</a>
        </c:forEach>
        <c:if test="${page.last == true}">
            <a href="/?institutionPageNumber=${page.pageable.pageNumber}#help">Next >></a>
        </c:if>
        <c:if test="${page.last == false}">
            <a href="/?institutionPageNumber=${page.pageable.pageNumber+1}#help">Next >></a>
        </c:if>
    </div>


</section>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>