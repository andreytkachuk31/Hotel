<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="room">

        <div class="user-header text-center">

            <h1 class="page-name">
                <spring:message code="users"/>
            </h1>

            <p class="lead">
                This is page provided information about users
            </p>

        </div>

        <hr/>

        <div class="user-body">

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
                                <spring:message code="first.name"/>
                            </th>
                            <th class="sorting" style="width: 207px;">
                                <spring:message code="last.name"/>
                            </th>
                            <th class="sorting_desc" style="width: 188px;" aria-sort="descending">
                                <spring:message code="login"/>
                            </th>
                            <th class="sorting" style="width: 108px;">
                                <spring:message code="status"/>
                            </th>
                            <th class="sorting" style="width: 108px;">
                                <spring:message code="role"/>
                            </th>
                            <th class="sorting" style="width: 108px;">
                                <spring:message code="action"/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr class="gradeC odd" role="row">
                                <td class="center">${user.firstName}</td>
                                <td class="center">${user.lastName}</td>
                                <td class="center">${user.login}</td>
                                <td class="center">${user.statusName}</td>
                                <td class="center">${user.roleName}</td>
                                <td class="center" width="20%">
                                    <a href="/admin/users/show?id=${user.id}">
                                        <button class="btn btn-sm btn-primary">
                                            Edit
                                        </button>
                                    </a>
                                    <c:if test="${user.active}">
                                        <a href="/admin/users/block?id=${user.id}">
                                            <button class="btn btn-sm btn-warning">
                                                Block
                                            </button>
                                        </a>
                                    </c:if>
                                    <c:if test="${user.blocked}">
                                        <a href="/admin/users/unblock?id=${user.id}">
                                            <button class="btn btn-sm btn-warning">
                                                Unblock
                                            </button>
                                        </a>
                                    </c:if>
                                    <a href="/admin/users/delete?id=${user.id}">
                                        <button class="btn btn-sm btn-danger">
                                            Delete
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="table_pagination both_side overflow-hidden">

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

            <div class="add_user center">
                <a href="/admin/users/show">
                    <button class="btn btn-large btn-primary">
                        <spring:message code="user.add"/>
                    </button>
                </a>
            </div>

        </div>

    </div>

</layout:layout>