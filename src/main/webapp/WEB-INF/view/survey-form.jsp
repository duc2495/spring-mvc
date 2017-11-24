<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-4 sidenav"></div>
		<div class="col-sm-4 text-left survey-content">
			<div class="free-bg"></div>
			<h2 class="text-center">アンケート</h2>
			<br>
			<h4>大学生の情報について調査します。</h4>
			<p class="red-label">* 項目を必須入力。</p>
			<form:form action="saveSurvey" method="post"
				modelAttribute="customerForm">

				<form:hidden path="id" />

				<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label> <label class=control-label>名前</label>
						<form:input path="name" type="text" class="form-control" id="name"
							placeholder="回答を入力" />
						<form:errors path="name" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="birthday">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class=control-label>生年月日</label>
						<form:input path="birthday" type="date" class="form-control" />
						<form:errors path="birthday" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="address">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class=control-label>住所</label>
						<form:textarea path="address" rows="3" class="form-control"
							id="address" placeholder="回答を入力" />
						<form:errors path="address" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="sex">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class="control-label">性別</label>
						<div class="radio">
							<label class=control-label><form:radiobutton path="sex"
									value="男性" name="radio-sex" class="option-input radio" />男性</label>
						</div>
						<div class="radio">
							<label class=control-label><form:radiobutton path="sex"
									value="女性" name="radio-sex" class="option-input radio" />女性</label>
						</div>
						<form:errors path="sex" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="school">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class="control-label">大学名</label>
						<form:input path="school" type="text" class="form-control"
							id="school" placeholder="回答を入力" />
						<form:errors path="school" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="year">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class="control-label">年生</label>
						<form:select path="year" class="form-control">
							<form:options items="${yearList}" />
						</form:select>
						<form:errors path="year" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="subjects">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">勉強した科目</label>
						<form:checkboxes element="span class='checkbox'" path="subjects"
							items="${subjectList}" />
						<form:errors path="subjects" class="control-label" />
					</div>
				</spring:bind>

				<button type="submit" class="btn btn-primary button">送信</button>
			</form:form>

		</div>
		<div class="col-sm-4 sidenav"></div>
	</div>
</div>