<%@ page pageEncoding="utf8" %>

<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<layout:layout>

    <div class="reservation">

        <div class="reservation-header text-center">

            <h1 class="page-name">
                <spring:message code="book.room"/>
            </h1>

            <p class="lead">
                On this page you can make the order
            </p>

        </div>

        <hr/>

		<div class="reservation-body panel-primary text-center">

			<div class="panel-heading">
			  <h3 class="panel-title"><spring:message code="reservation"/></h3>
			</div>

			<c:if test="${status == 'SAVE'}">
				<div id="step1" class="panel-body">
					<legend><spring:message code="reservation.step.1"/></legend>

					<form action="/reservation/save" method="post" class="form-inline">

						<label for="datepicker_check_in"><h4><spring:message code="check.in"/>:</h4></label>
						<input type="text" name="dateCheckIn" id="datepicker_check_in" class="date input-large margin-right10" placeholder="DD/MM/YYYY">

						<label for="datepicker_check_out"><h4><spring:message code="check.out"/>:</h4></label>
						<input type="text" name="dateCheckOut" id="datepicker_check_out" class="date input-large margin-right10" placeholder="DD/MM/YYYY">

						<input type="hidden" name="roomId" value="${roomId}"/>
						<button type="submit" class="btn btn-large btn-primary"><spring:message code="next"/></button>

					</form>
				</div>
			</c:if>

			<c:if test="${status == 'CONFIRM'}">
				<div id="step2" class="panel-body">
					<legend><spring:message code="reservation.step.2"/></legend>

					<div class="order_info">
						<h4><spring:message code="room.category"/> : ${room.categoryName}<h4>
					</div>

					<div class="order_info">
						<h4><spring:message code="rooms.amount"/> : ${room.placeAmount}</h4>
					</div>

					<div class="order_info">
						<h4><spring:message code="check.in"/> : <fmt:formatDate pattern="dd/MM/yyyy" value="${order.dateCheckIn}"/> </h4>
					</div>

					<div class="order_info">
						<h4><spring:message code="check.out"/> : <fmt:formatDate pattern="dd/MM/yyyy" value="${order.dateCheckOut}"/> </h4>
					</div>

					<div class="order_info">
						<h4><spring:message code="days"/> : ${daysBooking}</h4>
					</div>

					<div class="order_info">
						<h4><spring:message code="price.day"/> : ${room.price}$</h4>
					</div>

					<div class="order_info">
						<h4 class="inline-block"><spring:message code="total.to.pay"/>: <span id="order_bill">${order.bill}$</span></h4>
                        <button id="btn-convert" class="btn btn-small btn-info btn-convert-position"><spring:message code="convert.uah"/></button>
					</div>

					<div class="order_btn margin-bottom20">
						<form action="/reservation/confirm" method="get">
							<input type="hidden" name="roomId" value="${room.id}"/>
							<button type="submit" class="btn btn-large btn-warning" formaction="/reservation/show"><spring:message code="back"/></button>
							<button type="submit" class="btn btn-large btn-primary" formaction="/reservation/confirm"><spring:message code="next"/></button>
						<form>
					</div>
				</div>
			</c:if>

			<c:if test="${status == 'SUCCESS'}">
				<div id="step2" class="panel-body">
					<legend><spring:message code="reservation.step.3"/></legend>

					<h3 class="alert alert-success"><spring:message code="reservation.success"/></h3>

				</div>
			</c:if>

		</div>
	</div>

	<script type="text/javascript">

		  $(function() {

			  $("#datepicker_check_in, #datepicker_check_out").datepicker({
				 dateFormat: "dd/mm/yy"
			  });

			  <c:if test="${status == 'CONFIRM'}">
				  $("#btn-convert").click(function() {
					  $.ajax({
						  type: "GET",
						  url: "/exchangeRate/usd/" + ${order.bill},
						  success: function(result) {
							  $("#order_bill").text(result + "₴");
							  $("#btn-convert").prop("disabled", true);
						  }
					  });
				  });
			  </c:if>

		  });

	</script>

</layout:layout>