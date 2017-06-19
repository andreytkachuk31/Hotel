<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageable" type="ua.com.hotel.model.pagination.Pageable" required="true" %>

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