<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="error-page-form">
        <h3 class="alert alert-error text-center">${sessionScope.error}</h3>
        <a href="${header.referer}"><spring:message code="back"/></a>
    </div>

</layout:layout>