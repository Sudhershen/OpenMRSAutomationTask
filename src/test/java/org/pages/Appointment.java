package org.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Appointment extends BaseClass {

	public Appointment() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Appointment Scheduling")
	private WebElement scheduleAppointment;

	@FindBy(linkText = "Manage Provider Schedules")
	private WebElement manageProviderSchedules;

	@FindBy(xpath = "//div[@ng-show='showCalendar']/h1")
	private WebElement manageAppointmentPage;

	@FindBy(xpath = "//*[@id=\"calendar\"]/div/div/table/tbody/tr[1]/td[1]/div")
	private WebElement sep02;

	@FindBy(xpath = "//div[@id='appointment-block-form-header']/h1[1]")
	private WebElement createAppointmentBlockPage;

	@FindBy(xpath = "//div[@id='select-location']/select")
	private WebElement locationDropdown;
	
	@FindBy(xpath = "//div[@id='select-provider']/input")
	private WebElement txtProvider;

	@FindBy(xpath = "//input[@class='ng-pristine ng-valid ng-isolate-scope ng-valid-date ng-not-empty ng-touched']")
	private WebElement selectedDate;

	@FindBy(xpath = "//td[contains(@class,'today ')]")
	private WebElement selectCurrentDate;
	
	@FindBy(xpath = "//div[@id='select-date']//input")
	private WebElement getCurrentDate;

	@FindBy(xpath = "//div[@id='start-time']//table//td/input[@ng-model='hours']")
	private WebElement startTimeHours;

	@FindBy(xpath = "//div[@id='start-time']//table//td/input[@ng-model='minutes']")
	private WebElement startTimeMinutes;

	@FindBy(xpath = "//div[@id='end-time']//table//td/input[@ng-model='hours']")
	private WebElement endTimeHours;

	@FindBy(xpath = "//div[@id='end-time']//table//td/input[@ng-model='minutes']")
	private WebElement endTimeMinutes;

	@FindBy(xpath = "(//a[text()='View all types'])[2]")
	private WebElement viewAllTypes;

	@FindBy(xpath = "//div[@id='appointment-block-form-buttons']/button[@class='confirm']")
	private WebElement confirmButton;

	@FindBy(xpath = "//span[@class='fc-event-title']")
	private WebElement bookingConfirmation;

	public WebElement getScheduleAppointment() {
		return scheduleAppointment;
	}

	public WebElement getManageProviderSchedules() {
		return manageProviderSchedules;
	}

	public WebElement getManageAppointmentPage() {
		return manageAppointmentPage;
	}

	public WebElement getSep02() {
		return sep02;
	}

	public WebElement getCreateAppointmentBlockPage() {
		return createAppointmentBlockPage;
	}

	public WebElement getLocationDropdown() {
		return locationDropdown;
	}

	public WebElement getTxtProvider() {
		return txtProvider;
	}

	public WebElement getSelectedDate() {
		return selectedDate;
	}

	public WebElement getSelectCurrentDate() {
		return selectCurrentDate;
	}
	
	public WebElement getGetCurrentDate() {
		return getCurrentDate;
	}

	public WebElement getStartTimeHours() {
		return startTimeHours;
	}
	public WebElement getStartTimeMinutes() {
		return startTimeMinutes;
	}
	
	public WebElement getEndTimeHours() {
		return endTimeHours;
	}
	
	public WebElement getEndTimeMinutes() {
		return endTimeMinutes;
	}

	public WebElement getViewAllTypes() {
		return viewAllTypes;
	}

	public WebElement getConfirmButton() {
		return confirmButton;
	}

	public WebElement getBookingConfirmation() {
		return bookingConfirmation;
	}
	
	public boolean compareDates(String d1,String d2) {
		LocalDate date1 = LocalDate.parse(d1);

        // Parse the second date (03-September-2024)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", Locale.ENGLISH);
        LocalDate date2 = LocalDate.parse(d2, formatter);

        // Compare the dates
        if (date1.equals(date2)) {
            System.out.println("Both dates are equal.");
            return true;
        } else if (date1.isBefore(date2)) {
            System.out.println("The first date is before the second date.");
        } else {
            System.out.println("The first date is after the second date.");
            
        }
		return false;

	}
	
	public void selectServiceType(String servicename) {
		click(driver.findElement(By.xpath("(//a[text()='View all types'])[2]")));
		List<WebElement> allServies = driver.findElements(By.xpath("//div[@id='appointment-block-form-time']/following-sibling::selectmultipleappointmenttypes//div[@ng-repeat='type in allAppointmentTypes']/a"));
		for (WebElement x : allServies) {
			if(getText(x).equals(servicename)) {
				click(x);
			}
		}

	}
	
	public boolean verifyTheEvencreation(String servicename) {
		List<WebElement> events = driver.findElements(By.xpath("//span[@class='fc-event-title']"));
		
		for (WebElement x : events) {
			if(getText(x).equals(servicename)) {
				System.out.println("The Evenet was Created");
				return true;
			}
		}
		return false;
		

	}
public static void main(String[] args) {
	Appointment a= new Appointment();
	a.compareDates("2024-09-04", "04-September-2024");
}
}
