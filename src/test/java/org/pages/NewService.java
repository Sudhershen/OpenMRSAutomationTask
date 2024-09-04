package org.pages;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewService extends BaseClass {
	public NewService() {
		PageFactory.initElements(driver, this);
	}

		@FindBy(xpath="//i[@class='icon-home small']/parent::a")
		private WebElement homeIcon;
		
		@FindBy(linkText="Appointment Scheduling")
		private WebElement appointmentSchedule;
		
		@FindBy(id="appointmentschedulingui-manageAppointmentTypes-app")
		private WebElement manageService;
		
		@FindBy(xpath="//button[@class='confirm appointment-type-label right']")
		private WebElement newServiceType;
		
		@FindBy(id="name-field")
		private WebElement newServiceName;
		
		@FindBy(id="duration-field")
		private WebElement duration;
		
		@FindBy(id="description-field")
		private WebElement description;
		
		@FindBy(id="save-button")
		private WebElement saveButton;
		
		@FindBy(xpath="//tr[td/text()='New Service']")
		private WebElement addedService;

		public WebElement getHomeIcon() {
			return homeIcon;
		}

		public WebElement getAppointmentSchedule() {
			return appointmentSchedule;
		}

		public WebElement getManageService() {
			return manageService;
		}

		public WebElement getNewServiceType() {
			return newServiceType;
		}

		public WebElement getNewServiceName() {
			return newServiceName;
		}

		public WebElement getDuration() {
			return duration;
		}

		public WebElement getDescription() {
			return description;
		}

		public WebElement getSaveButton() {
			return saveButton;
		}

		public WebElement getAddedService() {
			return addedService;
		}
		
		
}
