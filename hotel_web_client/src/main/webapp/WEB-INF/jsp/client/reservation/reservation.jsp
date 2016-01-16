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
			  <h3 class="panel-title">Reservation</h3>
			</div>

			<c:if test="${status == 'SAVE'}">
				<div id="step1" class="panel-body">
					<legend>Step 1 of 3 (Choose date for reservation)</legend>

					<form action="/reservation/save" method="post" class="form-inline">

						<label for="datepicker_check_in"><h4><spring:message code="check.in"/>:</h4></label>
						<input type="text" name="dateCheckIn" id="datepicker_check_in" class="date input-large margin-right10" placeholder="DD/MM/YYYY">

						<label for="datepicker_check_out"><h4><spring:message code="check.out"/>:</h4></label>
						<input type="text" name="dateCheckOut" id="datepicker_check_out" class="date input-large margin-right10" placeholder="DD/MM/YYYY">

						<input type="hidden" name="roomId" value="${roomId}"/>
						<button type="submit" class="btn btn-large btn-primary">Next <span class="arrow-right"></span></button>

					</form>
				</div>
			</c:if>

			<c:if test="${status == 'CONFIRM'}">
				<div id="step2" class="panel-body">
					<legend>Step 2 of 3 (Confirm reservation)</legend>

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
						<h4><spring:message code="total.to.pay"/> : ${order.bill}$</h4>
					</div>

					<div class="order_btn margin-bottom20">
						<form action="/reservation/confirm" method="get">
							<input type="hidden" name="roomId" value="${room.id}"/>
							<button type="submit" class="btn btn-large btn-warning" formaction="/reservation/show">Back</button>
							<button type="submit" class="btn btn-large btn-primary" formaction="/reservation/confirm">Next</button>
						<form>
					</div>
				</div>
			</c:if>

			<c:if test="${status == 'SUCCESS'}">
				<div id="step2" class="panel-body">
					<legend>Step 3 of 3 (Success reservation)</legend>

					<h3 class="alert alert-success">Success reservation</h3>

				</div>
			</c:if>

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