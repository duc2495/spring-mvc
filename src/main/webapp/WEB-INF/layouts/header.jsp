<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/homepage">HRソリューション</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/homepage">ホーム</a></li>
				<li><a href="${pageContext.request.contextPath}/viewsurveys">アンケートリスト</a></li>
				<li><a href="${pageContext.request.contextPath}/newsurvey">新しいアンケート</a></li>
				<li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal.name == null}">
						<li><a href="${pageContext.request.contextPath}/login"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user"><span
								class="glyphicon"></span> Profile</a></li>
						<li><a href="${pageContext.request.contextPath}/logout"><span
								class="glyphicon glyphicon-log-in"></span> Logout</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>