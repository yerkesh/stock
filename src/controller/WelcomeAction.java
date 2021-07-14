package controller;

import javax.servlet.http.HttpServletRequest;

import formbean.CustomerLoginForm;
import model.Model;


public class WelcomeAction extends Action{

	public WelcomeAction(Model model) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getName() {
		return "welcome.do";
	}
	public String performGet(HttpServletRequest request) {
		request.setAttribute("forma", new CustomerLoginForm());
		return ("index.jsp");
    }

}
