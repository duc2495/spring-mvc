<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-4 sidenav"></div>
		<div class="col-sm-4 text-left home-content ">
			<c:choose>
				<c:when test="${language == null}">
					<h3 class=>新しい言語を追加します。</h3>
				</c:when>
				<c:otherwise>
					<h3 class=>言語を編集します。</h3>
				</c:otherwise>
			</c:choose>
			<h3 class=>新しい言語を追加</h3>
			<form:form action="${pageContext.request.contextPath}/savelanguage"
				method='POST' modelAttribute="language">
				<div class="form-group center-block">
					<form:hidden path="id" />
					<spring:bind path="name">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="red-label">*</label> <label class=control-label>言語</label>
							<form:input path="name" type="text" class="form-control"
								id="name" placeholder="回答を入力" />
							<span class="bar"></span>
							<form:errors path="name" class="control-label" />
						</div>
					</spring:bind>
				</div>
				<button type="submit" class="btn btn-primary button">追加</button>
			</form:form>
		</div>
		<div class="col-sm-4 sidenav"></div>
	</div>
</div>
