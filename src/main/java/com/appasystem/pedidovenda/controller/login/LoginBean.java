package com.appasystem.pedidovenda.controller.login;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appasystem.pedidovenda.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest httpServletRequest;
	
	@Inject
	private HttpServletResponse httpServletResponse;
	
	
	private String email;
	
	public void preRender(){
		if("true".equals(httpServletRequest.getParameter("invalid"))){
			FacesUtil.addErrorMessage("Usuário e/ou senha inválido!");
		}
	}
	
	public void login() throws ServletException, IOException{
		
		RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/j_spring_security_check"); 
		dispatcher.forward(httpServletRequest, httpServletResponse);
		facesContext.responseComplete();
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
