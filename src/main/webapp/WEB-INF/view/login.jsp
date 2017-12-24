<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-4 sidenav"></div>
		<div class="col-sm-4 text-left home-content ">
			<h1 class=>ログイン</h1>
			<!-- /login?error=true -->
			<c:if test="${param.error == 'true'}">
				<div style="color: red; margin: 10px 0px;">

					ログイン失敗！！！<br /> 理由 :
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

				</div>
			</c:if>
			<form:form
				action="${pageContext.request.contextPath}/j_spring_security_check"
				method='POST'>
				<div class="form-group center-block">
					<label class=control-label>アカウント</label> <input name="username"
						type="text" class="form-control" placeholder="回答を入力" /> <span
						class="bar"></span>
				</div>
				<div class="form-group center-block">
					<label class=control-label>パスワード</label> <input name="password"
						type="password" class="form-control" placeholder="回答を入力" /> <span
						class="bar"></span>
				</div>
				<button type="submit" class="btn btn-primary button">ログイン</button>
			</form:form>
		</div>
		<div class="col-sm-4 sidenav"></div>
	</div>
</div>