<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-2 sidenav"></div>
		<div class="col-sm-8 text-left">
			<h2>Phiếu điều tra</h2>
			<p>Điều tra thông tin về sinh viên</p>
			<p>* Bắt buộc</p>
			<form:form action="saveSurvey" method="post"
				modelAttribute="customerForm">

				<form:hidden path="id" />

				<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class=control-label>Họ tên *:</label>
						<form:input path="name" type="text" class="form-control" id="name"
							placeholder="Họ tên" />
						<form:errors path="name" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="address">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class=control-label>Địa chỉ *:</label>
						<form:textarea path="address" rows="4" class="form-control"
							id="address" placeholder="Địa chỉ" />
						<form:errors path="address" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="sex">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Giới tính *:</label>
						<div class="radio">
							<label class=control-label><form:radiobutton path="sex"
									value="Nam" name="radio-sex" class="radio" />Nam</label>
						</div>
						<div class="radio">
							<label class=control-label><form:radiobutton path="sex"
									value="Nữ" name="radio-sex" class="radio" />Nữ</label>
						</div>
						<form:errors path="sex" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="school">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Trường học hiện tại *:</label>
						<form:input path="school" type="text" class="form-control"
							id="school" placeholder="Đại học bôn ba" />
						<form:errors path="school" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="year">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Sinh viên năm thứ mấy *:</label>
						<form:select path="year" class="form-control">
							<form:options items="${yearList}" />
						</form:select>
						<form:errors path="year" class="control-label" />
					</div>
				</spring:bind>

				<spring:bind path="subjects">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<label class="control-label">Bạn đã từng học những môn học
							nào *:</label>
						<form:checkboxes element="span class='checkbox'" path="subjects"
							items="${subjectList}" />
						<form:errors path="subjects" class="control-label" />
					</div>
				</spring:bind>
				
				<button type="submit" class="btn btn-primary">Gửi</button>
			</form:form>
		</div>
		<div class="col-sm-2 sidenav"></div>
	</div>
</div>