<%@ tag pageEncoding="utf8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<ul class="nav">
    <li><a href="/home"><spring:message code="home"/></a></li>
    <li><a href="/rooms"><spring:message code="free.rooms"/></a></li>
    <li><a href="/reservation/showList"><spring:message code="orders"/></a></li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <spring:message code="booking.request"/> <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="/bookingRequest/show"><spring:message code="create.booking.request"/></a></li>
            <li><a href="/bookingRequest/showList"><spring:message code="show.booking.requests"/></a></li>
        </ul>
    </li>
    <li><a href="/logout"><spring:message code="logout"/></a></li>
</ul>