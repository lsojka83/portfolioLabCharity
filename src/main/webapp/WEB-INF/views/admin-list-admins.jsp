<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/jspf/admin-header.jspf" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Admin CRUD</h1>
        <a href="/admin/addadmin" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i>Dodaj admina</a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">List adminów</h6>
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
                    <c:forEach var="admin" items="${admins}">
                        <tr role="row" class="odd">
                            <td class="sorting_1">${admin.id}</td>
                            <td>${admin.email}</td>
                            <td>${admin.firstName}</td>
                            <td>${admin.lastName}</td>
                            <td>
                                <a href="/admin/edituser?id=${admin.id}&group=admins"">Edytuj</a>
                                <a href="/admin/deleteuser?id=${admin.id}&group=admins" onclick="return confirm('Czy skasować?')">Skasuj</a>

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