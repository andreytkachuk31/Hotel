<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <c:if test="${user == null}">
        <c:set var="actionForm" value="/admin/users/add"/>
        <c:set var="actionLabel">
            <spring:message code='user.add'/>
        </c:set>
    </c:if>
    <c:if test="${user != null}">
        <c:set var="actionForm" value="/admin/users/edit"/>
        <c:set var="actionLabel">
            <spring:message code='user.edit'/>
        </c:set>
    </c:if>

    <div class="user">

        <div class="user-header text-center">

            <h1 class="page-name">
                    ${actionLabel}
            </h1>

            <p class="lead">
                On this page you can add the user
            </p>

        </div>

        <hr/>

        <div class="user-body well login text-center">
            <form action="${actionForm}" method="post">
                <h2 class="form-signin-heading">${actionLabel}</h2>
                <input type="text" name="first_name" value="${user.firstName}" class="input-block-level"
                       placeholder="<spring:message code="first.name"/>"/>
                <input type="text" name="last_name" value="${user.lastName}" class="input-block-level"
                       placeholder="<spring:message code="last.name"/>"/>
                <c:if test="${user == null}">
                    <input type="text" name="login" value="${user.login}" class="input-block-level"
                           placeholder="<spring:message code="login"/>"/>
                    <input type="password" name="password" class="input-block-level"
                           placeholder="<spring:message code="password"/>"/>
                    <input type="password" name="password_conf" class="input-block-level"
                           placeholder="<spring:message code="confirm.password"/>"/>
                </c:if>
                <select name="role_id" class="input-block-level">
                    <option value="1" ${user.role.roleId == 1 ? 'selected' : ''}>ROLE_ADMIN</option>
                    <option value="2" ${user.role.roleId == 2 ? 'selected' : ''}>ROLE_MANAGER</option>
                    <option value="3" ${user.role.roleId == 3 ? 'selected' : ''}>ROLE_CLIENT</option>
                </select>
                <input type="hidden" name="id" value="${user.id}"/>
                <button class="btn btn-large btn-primary" type="submit">${actionLabel}</button>
            </form>
        </div>

</layout:layout>