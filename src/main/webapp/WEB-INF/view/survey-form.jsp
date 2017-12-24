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
			<form:form action="savesurvey" method="post"
				modelAttribute="customer">

				<form:hidden path="id" />

				<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label> <label class=control-label>名前</label>
						<form:input path="name" type="text" class="form-control" id="name"
							placeholder="回答を入力" />
						<span class="bar"></span>
						<form:errors path="name" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="birthday">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class=control-label>生年月日</label>
						<form:input path="birthday" type="date" class="form-control" />
						<span class="bar"></span>
						<form:errors path="birthday" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="address">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class=control-label>住所</label>
						<form:textarea path="address" rows="3" class="form-control"
							id="address" placeholder="回答を入力" />
						<span class="bar"></span>
						<form:errors path="address" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="gender">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class="control-label">性別</label>
						<div class="radio">
							<label class=control-label><form:radiobutton
									path="gender" value="男性" name="radio-gender"
									class="option-input radio" />男性</label>
						</div>
						<div class="radio">
							<label class=control-label><form:radiobutton
									path="gender" value="女性" name="radio-gender"
									class="option-input radio" />女性</label>
						</div>
						<form:errors path="gender" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="school">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class="control-label">大学名</label>
						<form:input path="school" type="text" class="form-control"
							id="school" placeholder="回答を入力" />
						<span class="bar"></span>
						<form:errors path="school" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="schoolYear">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="red-label">*</label><label class="control-label">年生</label>
						<form:select path="schoolYear" class="form-control">
							<form:options items="${schoolYearList}" />
						</form:select>
						<span class="bar"></span>
						<form:errors path="schoolYear" class="control-label" />
					</div>
				</spring:bind>

				<div class="form-group">
					<label class="control-label">言語スキル</label>
					<div>
						<form:checkboxes element="div class='checkbox'"
							cssClass="option-input checkbox" path="languages"
							items="${languages}" itemLabel="name" />
					</div>
				</div>


				<button type="submit" class="btn btn-primary button">送信</button>
			</form:form>

		</div>
		<div class="col-sm-4 sidenav"></div>
	</div>
</div>