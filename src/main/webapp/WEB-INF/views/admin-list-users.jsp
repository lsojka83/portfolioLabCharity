<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">CRUD użytkowników</h1>

    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">List użytkowników</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive-lg">

                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Email</th>
                        <th>Imię</th>
                        <th>Nazwisko</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr role="row" class="odd">
                            <td class="sorting_1">${admin.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.email}</td>
                            <td>${user.lastName}</td>
                            <td>
                                <a href="/admin/edituser?id=${user.id}">Edytuj</a>
                                <a href="/admin/deleteuser?id=${user.id}">Skasuj</a>
                            </td>
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