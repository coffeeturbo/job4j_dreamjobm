<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <ul class="nav">
        <c:if test="${user.name == null}">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
            </li>
        </c:if>
        <c:if test="${user.name != null}">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | Выйти</a>
            </li>
        </c:if>

        <li class="nav-item">
            <a class="nav-link" href="<c:url value="${request.getContextPath()}/posts.do"/>">Вакансии</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="${request.getContextPath()}/candidates.do"/>">Кандидаты</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="${request.getContextPath()}/post/edit.do"/>">Добавить вакансию</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="${request.getContextPath()}/candidate/edit.do"/>">Добавить кандидата</a>
        </li>
    </ul>
</div>