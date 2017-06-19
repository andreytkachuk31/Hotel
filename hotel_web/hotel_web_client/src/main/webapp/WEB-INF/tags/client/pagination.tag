<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageable" type="ua.com.hotel.model.pagination.Pageable" required="true" %>
<%@ attribute name="paginatedResults" type="ua.com.hotel.model.pagination.PaginatedResults" required="true" %>
<%@ attribute name="uri" type="java.lang.String" required="true" %>


<div class="col-sm-6">
    Showing 1 to ${pageable.perPage} of ${paginatedResults.totalCount} entries
</div>

<div class="col-sm-6">
    <div class="pagination margin-bottom50">
        <ul>
            <li class="previous <c:if test="${pageable.page == 1}">disabled</c:if>"><a href="${uri}?page=${pageable.page-1}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">Previous</a></li>
            <c:forEach var="i" begin="1" end="${paginatedResults.numberOfPages}" step="1">
                <li <c:if test="${pageable.page == i}">class="active"</c:if>><a href="${uri}?page=${i}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">${i}</a></li>
            </c:forEach>
            <li class="next <c:if test="${pageable.page == paginatedResults.numberOfPages}">disabled</c:if>"><a href="${uri}?page=${pageable.page+1}&perPage=${pageable.perPage}&sort=${pageable.sort}&filter=${pageable.filter}">Next</a></li>
        </ul>
    </div>
</div>

