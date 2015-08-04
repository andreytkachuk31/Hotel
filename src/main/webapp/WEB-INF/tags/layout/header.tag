<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="manager" tagdir="/WEB-INF/tags/manager" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags/client" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="header">

    <div class="menu">

        <div class="navbar">
            <div class="navbar-inner">
                <a href="/home" class="brand">Hotel</a>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <admin:menu/>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <manager:menu/>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_CLIENT')">
                        <client:menu/>
                    </sec:authorize>
            </div>
        </div>
    </div>

    <div class="locale">
        <layout:locale/>
    </div>

</div>
