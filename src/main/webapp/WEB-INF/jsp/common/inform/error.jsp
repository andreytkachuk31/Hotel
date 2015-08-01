<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <form name="Error_page-form">
        <div>
            <p style="font-size: 25px; text-align: center; padding: 30px; text-indent: 10px; color: blue; font-family: arial, helvetica, sans-serif">
                <h3 class="alert alert-error text-center">Error</h3>
            </p>`
            <p>
                <img style="-webkit-filter: blur(2px); margin-bottom: 85px" align="right" alt="ERROR" src="/images/error.jpg">
                <label style="color: red;" for="error"><fmt:message key="${error}"/></label>
            </p>
            <p style=" margin-top: 70px">
                <a href="${header.referer}"><fmt:message key="back"/></a>
            </p>
        </div>
    </form>

</layout:layout>