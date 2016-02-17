<%@ page pageEncoding="utf8" %>

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
                        <c:forEach var="freeRoom" items="${paginatedFreeRooms.results}">
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
                    Showing 1 to ${pageable.perPage} of ${paginatedFreeRooms.totalCount} entries
                </div>

                <div class="col-sm-6">
                    <div class="pagination margin-bottom50">
                        <ul>
                            <li class="previous <c:if test="${pageable.page == 1}">disabled</c:if>"><a href="/rooms?page=${pageable.page-1}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">Previous</a></li>
                            <c:forEach var="i" begin="1" end="${paginatedFreeRooms.numberOfPages}" step="1">
                                <li <c:if test="${pageable.page == i}">class="active"</c:if>><a href="/rooms?page=${i}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">${i}</a></li>
                            </c:forEach>
                            <li class="next <c:if test="${pageable.page == paginatedFreeRooms.numberOfPages}">disabled</c:if>"><a href="/rooms?page=${pageable.page+1}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">Next</a></li>
                        </ul>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <script type="text/javascript">

        $("#perPage").change(function() {
            var perPage = $(this).val();
            var page = 1;
            var sort = '${pageable.sort}';
            var filter = '${pageable.filter}';
            submit(perPage, page, sort, filter)
        });

        $(".sorting").click(function() {
            var perPage = $("#perPage").val();
            var page = ${pageable.page};
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
            document.location.href="/rooms?page=" + page + "&perPage=" + perPage + "&sort=" + sort + "&filter=" + filter;
        }

    </script>

</layout:layout>