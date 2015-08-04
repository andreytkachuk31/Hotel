<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
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

            <div class="table_search both_side">

                <div class="col-sm-6">
                    <label>Show
                        <select class="input-small">
                            <option value="10">10</option>
                            <option value="25">25</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                        </select> entries
                    </label>
                </div>

                <div class="col-sm-6">
                    <label>
                        Search:<input type="search" name="search"/>
                    </label>
                </div>
            </div>

            <div class="table_wrapper">

                <div class="col-sm-12">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr role="row">
                                <th class="sorting" style="width: 170px;">
                                    <spring:message code="room.number"/>
                                </th>
                                <th class="sorting" style="width: 207px;">
                                    <spring:message code="room.category"/>
                                </th>
                                <th class="sorting_desc" style="width: 188px;" aria-sort="descending">
                                    <spring:message code="rooms.amount"/>
                                </th>
                                <th class="sorting" style="width: 147px;">
                                    <spring:message code="price.per.day"/>
                                </th>
                                <th class="sorting" style="width: 108px;">
                                    <spring:message code="book.room"/>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="freeRoom" items="${freeRooms}">
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
                </div>
            </div>

            <div class="table_pagination both_side">

                <div class="col-sm-6">
                    Showing 1 to 10 of 57 entries
                </div>

                <div class="col-sm-6">
                    <div class="pagination margin-bottom50">
                        <ul>
                            <li class="previous disabled"><a href="#">Previous</a></li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">6</a></li>
                            <li class="next"><a href="#">Next</a></li>
                        </ul>
                    </div>
                </div>

            </div>

        </div>

    </div>

</layout:layout>