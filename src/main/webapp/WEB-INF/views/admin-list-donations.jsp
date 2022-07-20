<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Przeglądarka darów</h1>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"Lista></h6>
        </div>
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
                        <th>Utworzono</th>
                        <th>Data odebrania</th>
                        <th>Godzina odebrania</th>
                        <th>Status</th>
                        <th>Komentarz</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="donation" items="${donations}">
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
                            </td>
                            <td>${donation.createdOn}</td>
                            <td>${donation.pickUpDate}</td>
                            <td>${donation.pickUpTime}</td>
                            <td>


                                    ${donation.status.value}
                            </td>
                            <td>${donation.pickUpComment}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<%@ include file="/WEB-INF/views/jspf/admin-footer.jspf" %>