<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="http-error">
        <h1>Oops 403!</h1>
        <p class="info">You don't have permission to view this page.</p>
        <p><i class="icon-home"></i></p>
        <p><a href="/login">Back to the login page</a></p>
    </div>

</layout:layout>