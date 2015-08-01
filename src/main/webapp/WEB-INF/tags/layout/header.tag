<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="manager" tagdir="/WEB-INF/tags/manager" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags/client" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="header">

    <div class="menu">

        <div class="navbar">
            <div class="navbar-inner">
                <a href="/home" class="brand">Hotel</a>
                <c:if test="${user != null}">
                    <c:choose>
                        <c:when test="${user.roleId == '1' }">
                            <admin:menu/>
                        </c:when>

                        <c:when test="${user.roleId == '2' }">
                            <manager:menu/>
                        </c:when>

                        <c:when test="${user.roleId == '3' }">
                            <client:menu/>
                        </c:when>
                    </c:choose>
                </c:if>
            </div>
        </div>
    </div>

    <div class="locale">
        <layout:locale/>
    </div>

</div>
