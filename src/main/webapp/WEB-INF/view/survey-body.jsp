<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container-fluid text-center">
	<div class="row content">
		<div class="col-sm-2 sidenav"></div>
		<div class="col-sm-8 text-left">
			<h2>Phiếu điều tra</h2>
			<p>Điều tra thông tin về sinh viên</p>
			<p>* Bắt buộc</p>
			<form:form action="saveSurvey" method="post"
				modelAttribute="customer" data-toggle="validator" role="form">
				<form:hidden path="custId" />
				<div class="form-group">
					<label for="name">Họ tên *:</label>
					<form:input type="text" class="form-control" id="name" path="name"
						required="true" />
				</div>

				<div class="form-group">
					<label for="address">Địa chỉ:</label>
					<form:input type="text" class="form-control" id="address"
						path="address" />
				</div>

				<div class="form-group">
					<label for="school">Giới tính *:</label>
					<div class="radio">
						<label><input type="radio" name="optradio" required>Nam
						</label>
					</div>
					<div class="radio">
						<label><input type="radio" name="optradio" required>Nữ</label>
					</div>
					<div class="form-inline">
						<div class="radio">
							<label><input type="radio" name="optradio" required>
								Khác</label>
						</div>
						<label for="sex">:</label> <input type="text" class="form-control"
							id="sex">
					</div>
				</div>

				<div class="form-group">
					<label for="school">Trường học hiện tại:</label>
					<form:input type="text" class="form-control" id="school"
						path="school" />
				</div>

				<div class="form-group">
					<label for="school">Sinh viên năm thứ mấy:</label> <select
						class="form-control">
						<option value="">Choose one of the following...</option>
						<option value=1>Năm thứ nhất</option>
						<option value=2>Năm thứ hai</option>
						<option value=3>Năm thứ ba</option>
						<option value=4>Năm thứ tư</option>
						<option value=5>Năm thứ năm</option>
					</select>
				</div>

				<div class="form-group">
					<label for="subject">Bạn đã từng học những môn học nào sau
						đây:</label>
					<div class="checkbox">
						<label><input type="checkbox" value="">Tư tưởng Hồ Chí Minh</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" value="">Đường lối cách mạng của Đảng</label>
					</div>
					<div class="checkbox">
						<label><input type="checkbox" value="">Những nguyên lý cơ bản</label>
					</div>
				</div>

				<button type="submit" class="btn btn-primary">Gửi</button>
			</form:form>
		</div>
		<div class="col-sm-2 sidenav"></div>
	</div>
</div>