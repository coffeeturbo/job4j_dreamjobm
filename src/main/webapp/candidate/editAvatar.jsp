<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <title>Работа мечты</title>
</head>
<body>
<div class="container pt-3">
    <%--    NAVIGATION INCLUDE--%>
    <%@include file="../include/navigation.jsp" %>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                    Редактирование фото.
            </div>
            <div class="card-body">
                <a href="<c:url value='/candidate/download.do?id=${candidate.id}'/>">
                    <c:if test="${candidate.photoId == 0}">
                        <i class="fa fa-user mr-5 fa-5x"></i>
                    </c:if>
                    <c:if test="${candidate.photoId != 0}">
                        <img src="<c:url value='/candidate/download.do?id=${candidate.id}' />" width="300px" height="300px"/>
                    </c:if>
                </a>
            </div>
            <form action="<c:url value='/candidate/upload.do?id=${sessionScope.id}'/>" method="post" enctype="multipart/form-data">
                <div class="checkbox">
                    <input type="file" name="file">
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
