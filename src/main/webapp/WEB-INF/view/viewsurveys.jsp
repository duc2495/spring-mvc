<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-1 sidenav"></div>
		<div class="col-sm-10 text-left home-content">
			<h3>応答の一覧</h3>
			<c:choose>
				<c:when test="${listCustomer == null}">
					データがありません。
				</c:when>
				<c:otherwise>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#ID</th>
								<th>名前</th>
								<th>生年月日</th>
								<th>アドレス</th>
								<th>性別</th>
								<th>学校</th>
								<th>年生</th>
								<th>言語スキル</th>
								<th>アクション</th>
							</tr>
						</thead>

						<c:forEach var="customer" items="${listCustomer}">
							<tr>
								<td>${customer.id}</td>
								<td>${customer.name}</td>
								<td>${customer.birthday}</td>
								<td>${customer.address}</td>
								<td>${customer.gender}</td>
								<td>${customer.school}</td>
								<td>${customer.schoolYear}</td>
								<td><c:forEach var="languages"
										items="${customer.languages}" varStatus="loop">${languages.name}
    				        <c:if test="${not loop.last}">、</c:if>
									</c:forEach></td>
								<td><form:form
										action="${pageContext.request.contextPath}/delete/${customer.id}"
										method="get">
										<button type="submit" class="btn btn-danger">削除</button>
									</form:form></td>

							</tr>
						</c:forEach>
					</table>
					<tag:paginate max="15" offset="${offset}" count="${count}"
						uri="viewsurveys" next="&raquo;" previous="&laquo;" />
				</c:otherwise>
			</c:choose>

		</div>
		<div class="col-sm-1 sidenav"></div>
	</div>
</div>