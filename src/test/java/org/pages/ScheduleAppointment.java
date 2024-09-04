package org.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ScheduleAppointment extends BaseClass {

	public ScheduleAppointment() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='icon-search']/parent::a")
	private WebElement findAPatient;

	@FindBy(xpath = "//input[@id='patient-search']")
	private WebElement searchPatient;

	@FindBy(xpath = "(//table[@id='patient-search-results-table']//tbody/tr/td)[1]")
	private WebElement selectParticularPatient;

	@FindBy(xpath = "//div[normalize-space(text()) ='Schedule Appointment']")
	private WebElement scheduleAppoinmentLink;

	@FindBy(xpath = "//div[normalize-space(text()) ='Start Visit']")
	private WebElement startVisit;

	@FindBy(xpath = "//button[@class='confirm']")
	private WebElement searchButton;

	@FindBy(xpath = "//div[@ng-style='rowStyle(row)']")
	private WebElement selectAppointBlock;

	@FindBy(xpath = "//button[normalize-space(text())='Next']")
	private WebElement nextButton;

	@FindBy(xpath = "//button[normalize-space(text())='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//textarea[@id='appointmentReason']")
	private WebElement comments;

	@FindBy(xpath = "//button[@id='start-visit-with-visittype-confirm']")
	private WebElement visitConfirmButton;

	@FindBy(xpath = "//textarea[@placeholder='Enter a caption']")
	private WebElement enterCaption;

	@FindBy(id = "attachments.attachments.visitActions.default")
	private WebElement attachments;

	@FindBy(xpath = "//button[text()='Upload file']")
	private WebElement uploadFleButton;

	@FindBy(xpath = "//i[@class='icon-chevron-right link']/following-sibling::a")
	private WebElement nameLink;

	@FindBy(xpath = "//h3[text()='RECENT VISITS']/parent::div/following-sibling::div//ul/li")
	private WebElement recentVisit;

	@FindBy(xpath = "(//div[normalize-space(text())='End Visit'])[2]")
	private WebElement endVisit;

	@FindBy(xpath = "//input[@id='visitId']/..//button[text()='Yes']")
	private WebElement endVisitConfirm;

	@FindBy(xpath = "//div[normalize-space(text())='Delete Patient']")
	private WebElement deletePatient;

	@FindBy(id = "delete-reason")
	private WebElement deleteReason;

	@FindBy(xpath = "//div[@id='delete-patient-creation-dialog']/div/button[text()='Confirm']")
	private WebElement deletConfirm;
	
	@FindBy(xpath = "//td[@class='dataTables_empty']")
	private WebElement nomatchMsg;
	
	@FindBy(id = "appointmentschedulingui-manageAppointmentTypes-app")
	private WebElement manageservice;

	// -------------------------------------------------------------------------------------------------------------//
	public WebElement getFindAPatient() {
		return findAPatient;
	}

	public WebElement getSearchPatient() {
		return searchPatient;
	}

	public WebElement getSelectParticularPatient() {
		return selectParticularPatient;
	}

	public WebElement getScheduleAppoinmentLink() {
		return scheduleAppoinmentLink;
	}

	public WebElement getStartVisit() {
		return startVisit;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getSelectAppointBlock() {
		return selectAppointBlock;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getComments() {
		return comments;
	}

	public WebElement getVisitConfirmButton() {
		return visitConfirmButton;
	}

	public WebElement getEnterCaption() {
		return enterCaption;
	}

	public WebElement getAttachments() {
		return attachments;
	}

	public WebElement getUploadFleButton() {
		return uploadFleButton;
	}

	public WebElement getNameLink() {
		return nameLink;
	}

	public WebElement getRecentVisit() {
		return recentVisit;
	}

	public WebElement getEndVisit() {
		return endVisit;
	}

	public WebElement getEndVisitConfirm() {
		return endVisitConfirm;
	}

	public WebElement getDeletePatient() {
		return deletePatient;
	}

	public WebElement getDeleteReason() {
		return deleteReason;
	}

	public WebElement getDeletConfirm() {
		return deletConfirm;
	}
	public WebElement getNomatchMsg() {
		return nomatchMsg;
	}
	public WebElement getManageservice() {
		return manageservice;
	}

	// -------------------------------------------------------------------------------------------------------------//

	public void fileUpload(String file) throws AWTException {
		WebElement upload = driver.findElement(By.id("visit-documents-dropzone"));
		upload.click();
		StringSelection s = new StringSelection(file);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot r = new Robot();
		r.delay(5000);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);

	}

	public boolean verifyRecentEntries() {
		List<WebElement> recentVisits = driver
				.findElements(By.xpath("//h3[text()='RECENT VISITS']/parent::div/following-sibling::div//ul/li"));
		boolean result = false;
		if (recentVisits.size() == 1) {
			System.out.println("Have Only One Recent Visit Entry");
			result = true;
		}
		return result;
	}

	public boolean verifyDeletedPatientRecord(String patientid) {
		sendKeys(getSearchPatient(), patientid);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = getText(getNomatchMsg());
		System.out.println(text);
		return text.equals("No matching records found");
	}
	public boolean verifyAppointSchedulePage() {
		String text = getText(driver.findElement(By.xpath("//i[@class='icon-chevron-right link']/..")));
		return text.trim().equals("Appointment Scheduling");
	}

	public void selectServiceType(String servicename) {
		click(driver.findElement(By.xpath("//a[text()='View all types']")));
		List<WebElement> allServies = driver.findElements(By.xpath("//div[@id='allAppointmentTypesModal']//a"));
		for (WebElement x : allServies) {
			if (getText(x).equals(servicename)) {
				click(x);
				break;
			}
		}

	}
	
	public void cancelService(String service) {
		WebElement next = driver.findElement(By.xpath("//a[text()='Next']"));
		outerloop:
		while(next.isDisplayed()) {
//			List<WebElement> allServicenames = ;
		try{for (WebElement x : driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"))) {
			if(getText(x).equals(service)) {
				WebElement deleteservice = driver.findElement(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[4]//i[@id='appointmentschedulingui-delete-"+service+"']"));
				click(deleteservice);
				click(driver.findElement(By.xpath("(//button[@class='confirm right'])[last()]")));
				break outerloop;
			}
		}
		click(driver.findElement(By.xpath("//a[text()='Next']")));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		}

	}
	public boolean verifyServiceDeleteStatus(String service) {
		WebElement next = driver.findElement(By.xpath("//a[text()='Next']"));
		int count=0;
		boolean result=false;
		while(next.isDisplayed()) {
			List<WebElement> allServicenames = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
		for (WebElement x : allServicenames) {
			if(getText(x).equals(service)) {
			count++;
			System.out.println("The service was Not Deleted");
			break;
			}
		}
		click(driver.findElement(By.xpath("//a[text()='Next']")));
		}
		if(count==0) {
			result=true;
		}
		else{
			result=false;
		}
		return result;

	}
}
