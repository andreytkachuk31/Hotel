<%@ taglib prefix="client" tagdir="/WEB-INF/tags/client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageable" type="ua.com.hotel.model.pagination.Pageable" required="true" %>
<%@ attribute name="paginatedResults" type="ua.com.hotel.model.pagination.PaginatedResults" required="true" %>
<%@ attribute name="uri" type="java.lang.String" required="true" %>

<div class="table_search both_side">
    <client:search pageable="${pageable}"/>
</div>

<div class="table_wrapper">
    <div class="col-sm-12">
        <jsp:doBody/>
    </div>
</div>

<div class="table_pagination both_side">
    <client:pagination pageable="${pageable}" paginatedResults="${paginatedResults}" uri="${uri}"/>
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
        document.location.href="${uri}?page=" + page + "&perPage=" + perPage + "&sort=" + sort + "&filter=" + filter;
    }

</script>