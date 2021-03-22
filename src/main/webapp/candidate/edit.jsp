<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:set var="id" value="${requestScope.candidate.id}" scope="session"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <%@include file="../include/js/validation.jsp" %>
    <title>Работа мечты</title>

    <script>


        $(document).ready(function (){
            sendGreeting();
        });

        function sendGreeting() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/job4j_dreamjobm_war_exploded/city',
                dataType: 'json'
            }).done(function (data) {
                let options;
                console.log(data);
                $(data).each(function (i, city){
                    options = options + '<option value="' + city.id + '">' + city.name + '</option>';
                });

                $("#city").html(options);
            }).fail(function (err) {
                alert(err);
            });
        }
    </script>

</head>
<body>
<div class="container pt-3">
    <%--    NAVIGATION INCLUDE--%>
    <%@include file="../include/navigation.jsp" %>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <c:if test="${requestScope.candidate.id == 0}">
                    Новый кандидат.
                </c:if>
                <c:if test="${requestScope.candidate.id != 0}">
                    Редактирование кандидата.
                </c:if>
            </div>
            <div class="card-body">
                <c:if test="${requestScope.candidate.id != 0}">

                    <a href="<c:url value='/candidate/editAvatar.do?id=${candidate.id}' />">
                        <c:if test="${candidate.photoId == 0}">
                            <i class="fa fa-user mr-5 fa-5x"></i>
                        </c:if>
                        <c:if test="${candidate.photoId != 0}">
                            <img src="<c:url value='/candidate/download.do?id=${requestScope.candidate.id}' />" width="200px" height="200px"/>
                        </c:if>
                    </a>
                </c:if>
                <form action="<c:url value="/candidates.do?id=${requestScope.candidate.id}"/>" method="post">
                    <div class="form-group">
                        <label for="nameInput">Имя</label>
                        <input id="nameInput" type="text" title="Имя" class="form-control required" name="name" value="${requestScope.candidate.name}">
                        <input type="hidden" name="photoId" value="${requestScope.candidate.photoId}">
                    </div>
                    <div class="form-group">
                        <label for="city">Выберите город:</label>
                        <select class="form-control" id="city" name="cityId">
                            <option value="1">Москва</option>
                            <option value="2">Питер</option>
                            <option value="3">Подольск</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
                <c:if test="${requestScope.candidate.id != 0}">
                    <form action="delete.do?id=${requestScope.candidate.id}" method="post">
                        <button type="submit" class="btn btn-primary">Удалить кандидата</button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>