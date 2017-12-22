<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-4 sidenav"></div>
		<div class="col-sm-4 text-left home-content ">
			<h1 class=>Add language</h1>
			<form:form action="${pageContext.request.contextPath}/savelanguage"
				method='POST' modelAttribute="language">
				<div class="form-group center-block">
					<form:hidden path="id" />
					<label class=control-label>Language</label>
					<form:input path="name" type="text" class="form-control"
						placeholder="回答を入力" />
					<span class="bar"></span>
				</div>
				<button type="submit" class="btn btn-primary button">Submit</button>
			</form:form>
		</div>
		<div class="col-sm-4 sidenav"></div>
	</div>
</div>