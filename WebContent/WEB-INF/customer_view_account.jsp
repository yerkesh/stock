<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Login</title>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td {
  border: 1px solid #dddddd;
  text-align: left;
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
				<div class="cust_login"><a href="viewCustomerAccount.do" style="text-decoration: none; color: #F9460D;">VIEW ACCOUNT</a></div>
				<div class="emp_login"><a href="customerFunds.do" style="text-decoration: none; color: #A1A9A9;">BUY FUND</a></div>
			</div>
		</div>
		
		<div class="right" style="background-color: #EAF7FD;">
		<div class="logout_btn">
				<form method="POST" action="customerLogout.do">
		       		<button style="float: right; width: 5em; margin-top: 10px; margin-top: 10px; margin-right: 10px; background-color: #1AA6E8;  border: none; padding:10px;
		       		 color: white;" type="submit">Logout</button>
		    	</form>
		    </div>
			<hr class="right_top_line">
			
			<div class="right_info">
				
				<div class="customer_photo_email">
					
						<img class="user_icon" src="user_icon.png" alt="User Icon" width=150px height=150px>
						<div class="bold_email"> ${ customerInfo.getCustomer_email()} </div>
						<div class="light_email">${ customerInfo.getCustomer_email() } </div>
						
				</div>
				
				<div class="my_funds">
					<h2>Customer Information</h2>
					<div class="cust_info_form">
						<form>
							<label>Name</label><br>
							<input type="text" value="${ customerInfo.getName() }"><br><br>
							
							<label>Address</label><br>
							<input type="text" value="${ customerInfo.getAddress() }"><br><br>
							
							<div class="divdiv">
								<label class="label">$ Cash Balance</label><br>
								<input class="input"type="text" value="${ customerInfo.getBalance() }">
							</div>
							
						</form>
					</div>
				</div>
				
			</div>
			
			<div class="funds_information" style="background-color: white; margin-left:30px; margin-right:30px; margin-top:0px;">
				<h2>My Funds</h2>
				<table>
					<tr>
						<th style="padding:0px; text-align: left;">Fund Name</th>
						<th style="padding:0px; text-align: left; padding-right:0px;">Number of Shares</th>
						<th style="padding:0px; text-align: left; padding-right:80px;">Price</th>
						<th style="padding:0px; text-align: left; padding-right:150px;">Total Value</th>
					</tr>
				</table>
					
				
				<c:forEach var="item" items="${items}">
 
                <table>
                	<tr>
                		<td>
                			${ item.fund_name }
                		</td>
                		
                		<td>
                			${ item.shares_number }
                		</td>
                		
                		<td style="text-align: center;">
                			${ item.total_value / item.shares_number }
                		</td>
                		
                		<td style="text-align: center;">
                			${ item.total_value }
                		</td>
                		
                		<td>
                			<form class="delete-form" method="POST" action="refund.do">
			                    <input type="hidden" name="id" value="${ item.id }" />
			                    <button type="submit">Refund</button>
                			</form>
                		</td>
                	</tr>
                	
                </table>
				</c:forEach>
			</div>
			
			
			
			<hr class="right_bottom_line">
		</div>
	</div>
</body>
</html>