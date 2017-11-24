<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-2 sidenav"></div>
		<div class="col-sm-8 text-left home-content">
			<h1>Welcome</h1>

			<h3>All Surveys</h3>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#ID</th>
						<th>Họ tên</th>
						<th>Ngày sinh</th>
						<th>Địa chỉ</th>
						<th>Giới tính</th>
						<th>Trường học hiện tại</th>
						<th>Năm thứ</th>
						<th>Các môn đã học</th>
					</tr>
				</thead>

				<c:forEach var="customer" items="${listCustomer}">
					<tr>
						<td>${customer.id}</td>
						<td>${customer.name}</td>
						<td>${customer.birthday}</td>
						<td>${customer.address}</td>
						<td>${customer.sex}</td>
						<td>${customer.school}</td>
						<td>${customer.year}</td>
						<td><c:forEach var="subjects" items="${customer.subjects}"
								varStatus="loop">
					${subjects}
    				        <c:if test="${not loop.last}">,</c:if>
							</c:forEach></td>

					</tr>
				</c:forEach>
			</table>

		</div>
		<div class="col-sm-2 sidenav"></div>
	</div>
</div>