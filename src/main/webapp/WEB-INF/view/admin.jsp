<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-2 sidenav"></div>
		<div class="col-sm-8 text-left home-content">
			<ul>
				<li><a href="${pageContext.request.contextPath}/newlanguage">Create
						language</a></li>
				<li><a href="${pageContext.request.contextPath}/newlanguage">Update
						language</a></li>
			</ul>

		</div>
		<div class="col-sm-2 sidenav"></div>
	</div>
</div>