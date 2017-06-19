<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags/client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="booking-request">

        <div class="booking-request-header text-center">

            <h1 class="page-name">
                <spring:message code="booking.requests"/>
            </h1>

            <p class="lead">
                On this page you can view booking requests
            </p>

        </div>

        <hr/>

        <div class="booking-request-body text-center">

            <client:table pageable="${pageable}" paginatedResults="${paginatedResults}" uri="/bookingRequest/showList">

                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr role="row">
                            <th class="sorting" data-sort="rooms_amount" style="width: 188px;">
                                <spring:message code="rooms.amount"/>
                            </th>
                            <th style="width: 147px;">
                                <spring:message code="room.category"/>
                            </th>
                            <th style="width: 147px;">
                                <spring:message code="check.in"/>
                            </th>
                            <th style="width: 147px;">
                                <spring:message code="check.out"/>
                            </th>
                            <th class="sorting" data-sort="status" style="width: 147px;">
                                <spring:message code="status"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="bookingRequest" items="${paginatedResults.results}">
                            <tr class="gradeC odd" role="row">
                                <td class="center">${bookingRequest.roomsAmount}</td>
                                <td class="center">${bookingRequest.roomCategory.name}</td>
                                <td class="center">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${bookingRequest.dateCheckIn}"/>
                                </td>`
                                <td class="center">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${bookingRequest.dateCheckOut}"/>
                                </td>
                                <td class="center">
                                    <span class="label label-info">${bookingRequest.status.name}</span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </client:table>

            </div>
        </div>

    </div>

    <script type="text/javascript">

        $(function() {
            $("#datepicker_check_in, #datepicker_check_out").datepicker({
                dateFormat: "dd/mm/yy"
            });
        });

    </script>

</layout:layout>