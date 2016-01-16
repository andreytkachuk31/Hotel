<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="booking-request-show-all">

        <div class="booking-request-show-all-header text-center">

            <h1 class="page-name">
                <spring:message code="booking.requests"/>
            </h1>

            <p class="lead">
                On this page you can view booking requests
            </p>

        </div>

        <hr/>

        <div class="booking-request-show-all-body text-center">

            <div class="table_search both_side">

                <div class="col-sm-6">
                    <span>Show</span>
                        <select class="input-small">
                            <option value="10">10</option>
                            <option value="25">25</option>
                            <option value="50">50</option>
                        </select>
                    <span>entries</span>
                </div>

                <div class="col-sm-6">
                    <span>Search:</span>
                    <input type="search" name="search"/>
                </div>
            </div>

            <div class="table_wrapper">

                <div class="col-sm-12">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr role="row">
                                <th class="sorting_desc" style="width: 188px;" aria-sort="descending">
                                    <spring:message code="rooms.amount"/>
                                </th>
                                <th class="sorting" style="width: 147px;">
                                    <spring:message code="room.category"/>
                                </th>
                                <th class="sorting" style="width: 147px;">
                                    <spring:message code="check.in"/>
                                </th>
                                <th class="sorting" style="width: 147px;">
                                    <spring:message code="check.out"/>
                                </th>
                                <th class="sorting" style="width: 147px;">
                                    <spring:message code="status"/>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="bookingRequest" items="${bookingRequests}">
                            <tr class="gradeC odd" role="row">
                                <td class="center">${bookingRequest.roomsAmount}</td>
                                <td class="center">${bookingRequest.categoryName}</td>
                                <td class="center">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${bookingRequest.dateCheckIn}"/>
                                </td>
                                <td class="center">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${bookingRequest.dateCheckOut}"/>
                                </td>
                                <td class="center">
                                    <span class="label label-info">${bookingRequest.bookingRequestStatusName}</span>
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

    <script type="text/javascript">

        $(function() {
            $("#datepicker_check_in, #datepicker_check_out").datepicker({
                dateFormat: "dd/mm/yy"
            });
        });

    </script>

</layout:layout>