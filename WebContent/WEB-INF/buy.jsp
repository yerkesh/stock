<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer</title>
<style>

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

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  text-align: center;
  width:30%;
}

td {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 8px;
}



tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
<link rel="stylesheet" href="my_style2.css">

</head>
<body>
	<div class="content">
		<div class="left">
			<div class="left_top_sdu">SDU FINANCIAL <br> SERVICES</div>
			<hr class="left_line">
			<div class="login_option">
				<div class="cust_login"><a href="viewCustomerAccount.do" href="customerFunds.do" style="text-decoration: none; color: #A1A9A9;">VIEW ACCOUNT</a></div>
				<div class="emp_login"><a href="customerFunds.do" style="text-decoration: none; color: #F9460D;">BUY FUND</a></div>
			</div>
		</div>
		
		<div class="right" style="background-color: #EAF7FD;">
		<div class="logout_btn">
				<form method="POST" action="customerLogout.do">
		       		<button style="float: right; width: 5em; margin-top: 10px; margin-right: 10px; background-color: #1AA6E8;  border: none; padding:10px;
		       		 color: white;" type="submit">Logout</button>
		    	</form>
		    </div>
		<h2><jsp:include page="customer_welcome.jsp" /></h2>
			<hr class="right_top_line" >
			
			<table style="background-color: white; margin-left: 30px; padding: 10px;">
				<tr>
					<th>Funds Name</th>
					<th style="padding-right: 30px;">Price</th>
				</tr>
			</table>
			<c:forEach var="item" items="${ all_funds }">
 
                <table style="background-color: white; margin-left: 30px; padding: 0px;">
                	<tr>
                		<td>
                			<h5>${ item.fund_name }</h5>
                		</td>
                		
                		<td>
                			<h5>${ item.price }</h5>
                		</td>
                	</tr>
                	
                </table>
				</c:forEach>
			
			<div class="buy_fund_form" style="background-color: white; margin-left: 30px; margin-top: 40px; padding: 30px; width:50%">
			<div class="right_info_balance">
				<div class="show_balance" style="display: flex;"><h2>Available balance: <div style="color: green;"> ${ customerInfo.getBalance() } </div></h2></div>
			</div>
				<c:forEach var="error" items="${form.formErrors}">
				<h3 style="color:red"> ${error} </h3>
				</c:forEach>
				<form action="customerFunds.do" method="POST">
					<c:forEach var="field" items="${form.visibleFields}">
						<label>${field.label}</label><br>
						<label style="color: red;">${field.error}</label>
			  			<input type="${field.type}" style="width: 300px; height:20px;" id="${field.name}" name="${field.name}" value="${field.value}" placeholder="" value=""><br><br>
					</c:forEach>
		  			<input class="button" type="submit" name="action" value="Submit">
				</form>
			</div>
			
			
			
			<hr class="right_bottom_line">
		</div>
	</div>
</body>
</html>