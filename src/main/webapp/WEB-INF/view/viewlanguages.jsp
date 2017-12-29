<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-2 sidenav"></div>
		<div class="col-sm-8 text-left home-content">
			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
			<h3>言語の一覧</h3>
			<form:form action="${pageContext.request.contextPath}/newlanguage"
				method="get">
				<button type="submit" class="btn btn-primary">新しい言語</button>
			</form:form>
			<c:choose>
				<c:when test="${listLanguage == null}">
					データがありません。
				</c:when>
				<c:otherwise>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#ID</th>
								<th>名前</th>
								<th>アクション</th>
							</tr>
						</thead>

						<c:forEach var="language" items="${listLanguage}">
							<tr>
								<td>${language.id}</td>
								<td>${language.name}</td>
								<td><form:form
										action="${pageContext.request.contextPath}/languages/${language.id}/update"
										method="get">
										<button type="submit" class="btn btn-primary">アップデート</button>
									</form:form></td>
							</tr>
						</c:forEach>
					</table>
				</c:otherwise>
			</c:choose>

		</div>
		<div class="col-sm-2 sidenav"></div>
	</div>
</div>