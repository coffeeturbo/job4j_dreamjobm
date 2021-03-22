<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Названия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${posts}" var="post">
        <tr>
            <td>
                <a href='<c:url value="/post/edit.do?id=${post.id}"/>'>
                    <i class="fa fa-edit mr-3"></i>
                </a>
                <c:out value="${post.name}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>