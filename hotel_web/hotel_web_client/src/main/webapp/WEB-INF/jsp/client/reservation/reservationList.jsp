<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags/client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="orders">

        <div class="orders-header text-center">

            <h1 class="page-name">
                <spring:message code="orders"/>
            </h1>

            <p class="lead">
                On this page you can view orders
            </p>

        </div>

        <hr/>

        <div class="orders-body text-center">

            <client:table pageable="${pageable}" paginatedResults="${paginatedResults}" uri="/reservation/showList">

                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr role="row">
                            <th class="sorting" data-sort="rooms.place_amount" style="width: 188px;">
                                <spring:message code="rooms.amount"/>
                                <c:if test="${pageable.sort == 'rooms.place_amount'}">
                                    <i class='icon-arrow-down'></i>
                                </c:if>
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
                            <th class="sorting" data-sort="bill" style="width: 147px;">
                                <spring:message code="total.to.pay"/>
                                <c:if test="${pageable.sort == 'bill'}">
                                    <i class='icon-arrow-down'></i>
                                </c:if>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${paginatedResults.results}">
                            <tr class="gradeC odd" role="row">
                                <td class="center">${order.room.placeAmount}</td>
                                <td class="center">${order.room.categoryName}</td>
                                <td class="center">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${order.dateCheckIn}"/>
                                </td>
                                <td class="center">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${order.dateCheckOut}"/>
                                </td>
                                <td class="center">${order.bill} $</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </client:table>

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