<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="booking-request">

        <div class="booking-request-header text-center">

            <h1 class="page-name">
                <spring:message code="booking.request"/>
            </h1>

            <p class="lead">
                On this page you can create booking request
            </p>

        </div>

        <hr/>

        <div class="booking-request-body text-center">

           <div class="well">

                <form action="/bookingRequest/save" method="post" class="form-inline">

                    <div class="div_of_1">
                        <label><h4><spring:message code="check.in"/>:</h4></label>
                        <input type="text" name="dateCheckIn" id="datepicker_check_in" class="date input-large margin-right20" placeholder="DD/MM/YYYY">

                        <label><h4><spring:message code="check.out"/>:</h4></label>
                        <input type="text" name="dateCheckOut" id="datepicker_check_out" class="date input-large" placeholder="DD/MM/YYYY">
                    </div>

                    <div class="div_of_2 margin-top10">
                        <label><h4><spring:message code="rooms.amount"/>:</h4></label>
                        <select name="roomsAmount" id="country" class="margin-right10">
                            <option value=""><spring:message code="select.type.room"/></option>
                            <option value="1"><spring:message code="single.room"/></option>
                            <option value="2"><spring:message code="double.room"/></option>
                            <option value="3"><spring:message code="triple.room"/></option>
                            <option value="4"><spring:message code="quadruple.room"/></option>
                        </select>

                        <label><h4><spring:message code="room.category"/>:</h4></label>
                        <select name="roomCategory" id="country">
                            <option value=""><spring:message code="select.category.room"/></option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.getValue()}">${category.getName()}</option>
                            </c:forEach>
                        </select>
                     </div>

                    <div class="div_of_3 margin-top10">
                        <input type="submit" class="btn btn-large btn-primary" value="<spring:message code="create.booking.request"/>"/>
                    </div>

                 </form>
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