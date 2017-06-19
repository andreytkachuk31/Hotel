<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>
    <h3 class="alert alert-error text-center"><spring:message code="mlogin_error_${errorCode}"/></h3>
</layout:layout>