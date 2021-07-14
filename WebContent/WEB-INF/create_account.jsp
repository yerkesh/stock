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
				<div class="create_accounts"><a href="customerCredential.do" style="text-decoration: none; color: #F9460D;">CREATE ACCOUNTS</a></div>
				<div class="deposit_money"><a href="deposit.do" href="customerFunds.do" style="text-decoration: none; color: #A1A9A9;">DEPOSIT MONEY</a></div>
				<div class="create_fund"><a href="createFund.do" href="customerFunds.do" style="text-decoration: none; color: #A1A9A9;">CREATE FUND</a></div>
				
			</div>
		</div>
		
		<div class="right" style="background-color: #EAF7FD;">
			<hr class="right_top_line">
			<div class="logout_btn">
				<form method="POST" action="employeeLogout.do">
		       		<button style="float: right; width: 5em; margin-right: 10px; background-color: #1AA6E8;  border: none; padding:10px;
		       		 color: white;" type="submit">Logout</button>
		    	</form>
		    </div>
			<h2><jsp:include page="employee_welcome.jsp" /></h2>
			<div class="employee_login_form">
			<h3>New Customer</h3>
				<c:forEach var="error" items="${form.formErrors}">
				<h3 style="color:red"> ${error} </h3>
				</c:forEach>
				<form action="customerCredential.do" method="POST">
					<c:forEach var="field" items="${form.visibleFields}">
						<label>${field.label}</label><br>
						<label style="color: red;">${field.error}</label>
			  			<input type="${field.type}" style="width: 300px; height:20px;" id="${field.name}" name="${field.name}" value="${field.value}" placeholder="" value=""><br><br>
					</c:forEach>
		  			<input class="button" type="submit" name="action" value="Create">
				</form>
			</div>
			<hr class="right_bottom_line">
		</div>
	</div>
</body>
</html>