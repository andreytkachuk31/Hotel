<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="well login">

        <form action="/registration" method="post" class="form-signup">
            <h2 class="form-signin-heading"><spring:message code="registration"/></h2>
            <input type="text" name="first_name" class="input-block-level"
                   placeholder="<spring:message code="first.name"/>"/>
            <input type="text" name="last_name" class="input-block-level"
                   placeholder="<spring:message code="last.name"/>"/>
            <input type="text" name="login" class="input-block-level"
                   placeholder="<spring:message code="login"/>"/>
            <input type="password" name="password" class="input-block-level"
                   placeholder="<spring:message code="password"/>"/>
            <input type="password" name="password_conf" class="input-block-level"
                   placeholder="<spring:message code="confirm.password"/>"/>
            <button class="btn btn-large btn-primary" type="submit"><spring:message code="registration"/></button>
        </form>

    </div>

</layout:layout>
