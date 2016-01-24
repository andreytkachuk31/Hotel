<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

     <div class="well login">

        <form action="/login/process" method="post" class="form-signin">
            <h2 class="form-signin-heading"><spring:message code="authorization"/></h2>
            <input type="text" name="login" class="input-block-level" placeholder="<spring:message code="login"/>"/>
            <input type="password" name="password" class="input-block-level" placeholder="<spring:message code="password"/>"/>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
            </label>
            <button class="btn btn-large btn-primary" type="submit"><spring:message code="login"/></button>
            <a href="/registration"><spring:message code="registration"/></a>
        </form>

    </div>

</layout:layout>