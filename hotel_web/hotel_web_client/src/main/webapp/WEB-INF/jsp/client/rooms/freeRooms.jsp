<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="client" tagdir="/WEB-INF/tags/client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="room">

        <div class="room-header text-center">

            <h1 class="page-name">
                <spring:message code="free.rooms"/>
            </h1>

            <p class="lead">
                This is page provided information about available rooms
            </p>

        </div>

        <hr/>

        <div class="room-body">

            <client:table pageable="${pageable}" paginatedResults="${paginatedResults}" uri="/rooms">

                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr role="row">
                            <th class="sorting" data-sort="number" style="width: 170px;">
                                <spring:message code="room.number"/>
                                <c:if test="${pageable.sort == 'number'}">
                                    <i class='icon-arrow-down'></i>
                                </c:if>
                            </th>
                            <th style="width: 207px;">
                                <spring:message code="room.category"/>
                            </th>
                            <th class="sorting" data-sort="place_amount" style="width: 188px;" aria-sort="descending">
                                <spring:message code="rooms.amount"/>
                                <c:if test="${pageable.sort == 'place_amount'}">
                                    <i class='icon-arrow-down'></i>
                                </c:if>
                            </th>
                            <th class="sorting" data-sort="price" style="width: 147px;">
                                <spring:message code="price.per.day"/>
                                <c:if test="${pageable.sort == 'price'}">
                                    <i class='icon-arrow-down'></i>
                                </c:if>
                            </th>
                            <th style="width: 108px;">
                                <spring:message code="book.room"/>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="freeRoom" items="${paginatedResults.results}">
                            <tr class="gradeC odd" role="row">
                                <td class="center">${freeRoom.number}</td>
                                <td class="center">${freeRoom.categoryName}</td>
                                <td class="center">${freeRoom.placeAmount}</td>
                                <td class="center">${freeRoom.price}</td>
                                <td class="center">
                                    <a href="/reservation/show?roomId=${freeRoom.id}">
                                        <spring:message code="book.room"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </client:table>

        </div>
    </div>

</layout:layout>