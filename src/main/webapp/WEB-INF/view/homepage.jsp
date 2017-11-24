<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-2 sidenav"></div>
		<div class="col-sm-8 text-left home-content">
			<h3>応答の一覧</h3>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#ID</th>
						<th>名前</th>
						<th>誕生日</th>
						<th>アドレス</th>
						<th>性別</th>
						<th>学校</th>
						<th>年生</th>
						<th>科目</th>
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