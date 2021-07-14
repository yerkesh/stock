<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Login</title>
<link rel="stylesheet" href="my_style.css">
<style type="text/css">
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px 22px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin-left: 100px;
  cursor: pointer;
  border-radius: 20px;
}
</style>
</head>
<body>
	<div class="content">
		<div class="left">
			<div class="left_top_sdu">SDU FINANCIAL <br> SERVICES</div>
			<hr class="left_line">
			<div class="login_option">
				<div class="cust_login"><a href="customerLogin.do" style="text-decoration: none; color: #A1A9A9;">CUSTOMER LOGIN</a></div>
				<div class="emp_login"><a href="employeeCredential.do"  style="text-decoration: none; color: #F9460D;">EMPLOYEE LOGIN</a></div>
			</div>
		</div>
		
		<div class="right" style="background-color: #EAF7FD;">
			<h2 style="padding:10px; margin-left:30px;">Employee Login</h2>
			<hr class="right_top_line">
			
			<div class="employee_login_form">
				<c:forEach var="error" items="${form.formErrors}">
				<h3 style="color:red"> ${error} </h3>
				</c:forEach>
				<form action="employeeCredential.do" method="POST">
					<c:forEach var="field" items="${form.visibleFields}">
						<label>${field.label}</label><br>
						<label style="color: red;">${field.error}</label>
			  			<input type="${field.type}" style="width: 300px; height:20px;" id="${field.name}" name="${field.name}" value="${field.value}" placeholder="" value=""><br><br>
					</c:forEach>
		  			<input class="button" type="submit" name="action" value="Login">
				</form>
			</div>
			<hr class="right_bottom_line" style="margin-top: 305px;">
		</div>
	</div>
</body>
</html>