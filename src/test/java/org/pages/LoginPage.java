package org.pages;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(id="username")
	private WebElement txtUsername;
	@FindBy(id="password")
	private WebElement txtPassword;
	@FindBy(id="Registration Desk")
	private WebElement registrationDesk;
	@FindBy(id="loginButton")
	private WebElement loginButton;
	@FindBy(xpath="//h4[contains(text(),'Super User')]")
	private WebElement loggedInMsg;
	
	public WebElement getTxtUsername() {
		return txtUsername;
	}
	public WebElement getTxtPassword() {
		return txtPassword;
	}
	public WebElement getRegistrationDesk() {
		return registrationDesk;
	}
	public WebElement getLoginButton() {
		return loginButton;
	}	
	
	public WebElement getLoggedInMsg() {
		return loggedInMsg;
	}
	
}
