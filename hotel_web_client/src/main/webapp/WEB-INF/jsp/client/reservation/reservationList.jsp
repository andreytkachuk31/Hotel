<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="booking-request-show-all">

        <div class="booking-request-show-all-header text-center">

            <h1 class="page-name">
                <spring:message code="orders"/>
            </h1>

            <p class="lead">
                On this page you can view orders
            </p>

        </div>

        <hr/>

        <div class="booking-request-show-all-body text-center">

            <div class="table_search both_side">

                <div class="col-sm-6">
                    <span>Show</span>
                    <select id="perPage" name="perPage" class="input-small">
                        <option value="10" <c:if test="${pageable.perPage == 10}">selected</c:if>>10</option>
                        <option value="25" <c:if test="${pageable.perPage == 25}">selected</c:if>>25</option>
                        <option value="50" <c:if test="${pageable.perPage == 50}">selected</c:if>>50</option>
                    </select>
                    <span>entries</span>
                </div>

                <div class="col-sm-6">
                    <span>Search:</span>
                    <input id="search" type="search" name="search"/>
                </div>
            </div>

            <div class="table_wrapper">

                <div class="col-sm-12">
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
                        <c:forEach var="order" items="${paginatedOrders.results}">
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
                </div>
            </div>

            <div class="table_pagination both_side">

                <div class="col-sm-6">
                    Showing 1 to ${pageable.perPage} of ${paginatedFreeRooms.totalCount} entries
                </div>

                <div class="col-sm-6">
                    <div class="pagination margin-bottom50">
                        <ul>
                            <li class="previous <c:if test="${pageable.page == 1}">disabled</c:if>"><a href="/reservation/showList?page=${pageable.page-1}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">Previous</a></li>
                            <c:forEach var="i" begin="1" end="${paginatedFreeRooms.numberOfPages}" step="1">
                                <li <c:if test="${pageable.page == i}">class="active"</c:if>><a href="/reservation/showList?page=${i}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">${i}</a></li>
                            </c:forEach>
                            <li class="next <c:if test="${pageable.page == paginatedFreeRooms.numberOfPages}">disabled</c:if>"><a href="/reservation/showList?page=${pageable.page+1}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">Next</a></li>
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

            $("#perPage").change(function() {
                var perPage = $(this).val();
                var page = 1;
                var sort = '${pageable.sort}';
                var filter = '${pageable.filter}';
                submit(perPage, page, sort, filter)
            });

            $(".sorting").click(function() {
                var perPage = $("#perPage").val();
                var page = '${pageable.page}';
                var sort = $(this).data("sort");
                var filter = '${pageable.filter}';
                submit(perPage, page, sort, filter)
            });

            $("#search").keypress(function(e) {
                var key = e.which;
                if(key == 13) {
                    var perPage = $("#perPage").val();
                    var page = 1;
                    var sort = '${pageable.sort}';
                    var filter = $(this).val();
                    submit(perPage, page, sort, filter)
                }
            });

            function submit(perPage, page, sort, filter) {
                document.location.href="/reservation/showList?page=" + page + "&perPage=" + perPage + "&sort=" + sort + "&filter=" + filter;
            }
        });

    </script>

</layout:layout>