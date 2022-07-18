<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/views/jspf/header-user-logged.jspf" %>


    <section class="login-page">
      <h2>Lita darów</h2>
        <div class="container-fluid">
        <div class="card shadow mb-4">
            <div class="card-body">
                <div class="table-responsive-lg">

                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Liczba</th>
                            <th>Kategorie</th>
                            <th>Instytucja</th>
                            <th>Adres</th>
                            <th>Data odebrania</th>
                            <th>Godzina odebrania</th>
                            <th>Komentarz</th>
                            <th>Akcje</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="donation" items="${user.donations}">
                            <tr role="row" class="odd">
                                <td class="sorting_1">${donation.id}</td>
                                <td>${donation.quantity}</td>
                                <td>
                                    <ul>
                            <c:forEach var="category" items="${donation.categories}">
                                <li>${category.name}</li>
                            </c:forEach>
                                    </ul>
                            </td>
                                <td>${donation.institution.name}</td>
                                <td>
                                        ${donation.street}
                                        ${donation.city}
                                        ${donation.zipCode}
                                        ${donation.phoneNumber}
                                </td>
                                <td>${donation.pickUpDate}</td>
                                <td>${donation.pickUpTime}</td>
                                <td>${donation.pickUpComment}</td>
                                <td>
                                    <a href="/edit?id=${donation.id}">Edytuj</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        </div>

    </section>

<%@ include file="/WEB-INF/views/jspf/footer.jspf" %>