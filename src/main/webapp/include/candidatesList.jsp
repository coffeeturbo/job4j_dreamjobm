<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Фото</th>
        <th scope="col">Названия</th>
        <th scope="col">Город</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${candidates}" var="candidate">
        <tr>
            <td>
                <c:if test="${candidate.photoId == 0}">
                    <i class="fa fa-camera mr-5 fa-5x" width="100px" height="100px"></i>
                </c:if>
                <c:if test="${candidate.photoId != 0}">
                    <img src="<c:url value='/candidate/download.do?id=${candidate.id}'/>" width="100px" height="100px"/>
                </c:if>
            </td>

            <td>
                <a href='<c:url value="/candidate/edit.do?id=${candidate.id}"/>'><i class="fa fa-edit mr-3"></i></a>
                <c:out value="${candidate.name}"/>
            </td>
            <td>
                <c:forEach items="${cities}" var="city">
                    <c:if test="${city.id == candidate.cityId}">
                        <c:out value="${city.name}"/>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>