package org.test;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;

import org.base.AllureReport;
import org.base.BaseClass;
import org.openqa.selenium.support.ui.Select;
import org.pages.Appointment;
import org.pages.LoginPage;
import org.pages.NewService;
import org.pages.Register;
import org.pages.ScheduleAppointment;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class OpenMrs extends BaseClass {
	long starttime;
	long endtime;
	static LoginPage l;
	static Register r;
	static NewService n;
	static Appointment t;
	static ScheduleAppointment sapt;

	@BeforeClass
	private void setUp() {
		browserLaunch(getValueFromPropertyFile("browser"));
		urlLaunch(getValueFromPropertyFile("url"));
		implicitwait(10);
		AllureReport.startReport();
	}

	@AfterClass
	private void tearDown() {
//		quit();
		AllureReport.endReport();
		System.out.println("Report generated");

	}

	@BeforeMethod
	private void beforeMethod() {
		starttime = System.currentTimeMillis();

	}

	@AfterMethod
	private void afterMethod(ITestResult res) {
		endtime = System.currentTimeMillis();
		System.out.println("Duration of Execurtion...." + (endtime - starttime));
		try {
			getScreenshot(res.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		AllureReport.createLog(res);
	}

	@Test(priority = 1)
	private void the_user_has_to_login() {
		l = new LoginPage();
		sendKeys(l.getTxtUsername(), getValueFromPropertyFile("username"));
		sendKeys(l.getTxtPassword(), getValueFromPropertyFile("password"));
		click(l.getRegistrationDesk());
		click(l.getLoginButton());
		Assert.assertTrue(getText(l.getLoggedInMsg()).contains("Logged in as Super User"));
	}

	static String patientId;

	@Test(priority = 2)
	private void register_patient_and_verify_record() throws InterruptedException {
		r = new Register();
		click(r.getRegister());
		sendKeys(r.getFirstName(), getValueFromPropertyFile("firstname"));
		sendKeys(r.getFamilyName(), getValueFromPropertyFile("familyname"));
		click(r.getNextButton());
		selectByValue(r.getGenderDropdown(), getValueFromPropertyFile("gender"));
		click(r.getNextButton());
		sendKeys(r.getBirthDay(), getValueFromPropertyFile("day"));
		selectByText(r.getBirthMonth(), getValueFromPropertyFile("month"));
		sendKeys(r.getBirthYear(), getValueFromPropertyFile("year"));
		click(r.getNextButton());
		sendKeys(r.getAddress_line1(), getValueFromPropertyFile("address1"));
		sendKeys(r.getAddress_line2(), getValueFromPropertyFile("address2"));
		sendKeys(r.getCity(), getValueFromPropertyFile("city"));
		sendKeys(r.getState(), getValueFromPropertyFile("state"));
		sendKeys(r.getCountry(), getValueFromPropertyFile("country"));
		sendKeys(r.getPostalCode(), getValueFromPropertyFile("postal"));
		click(r.getNextButton());
		sendKeys(r.getPhoneNumber(), getValueFromPropertyFile("phno"));
		click(r.getNextButton());
		click(r.getNextButton());
		click(r.getConfirm());
		Thread.sleep(3000);
		patientId = getText(r.getPatientId());
		 Assert.assertTrue(getText(r.getName()).contains(getValueFromPropertyFile("firstname")));
		 Assert.assertTrue(getText(r.getGender()).contains("Male"));
		 Assert.assertTrue(getText(r.getMobile_no()).contains(getValueFromPropertyFile("phno")));
	}

	static String serviceName;

	@Test(priority = 3)
	private void create_new_service_type() {
		n = new NewService();
		click(n.getHomeIcon());
		Assert.assertTrue(getText(l.getLoggedInMsg()).contains("Logged in as Super User"));
		click(n.getAppointmentSchedule());
		click(n.getManageService());
		click(n.getNewServiceType());
		sendKeys(n.getNewServiceName(), getValueFromPropertyFile("servicename"));
		serviceName = getAttribute(n.getNewServiceName(), "value");
		sendKeys(n.getDuration(), getValueFromPropertyFile("duration"));
		sendKeys(n.getDescription(), getValueFromPropertyFile("desc"));
		click(n.getSaveButton());

	}

	@Test(priority = 4)
	private void create_appointment_block() throws InterruptedException {
		t = new Appointment();
		click(n.getHomeIcon());
		click(t.getScheduleAppointment());
		click(t.getManageProviderSchedules());
		Assert.assertTrue(getText(t.getManageAppointmentPage()).contains("Manage Appointment Blocks"));
		String dateSelected = getAttribute(t.getSelectCurrentDate(), "data-date");
		click(t.getSelectCurrentDate());
		Assert.assertTrue(getText(t.getCreateAppointmentBlockPage()).contains("Create Appointment Block"));
		selectByText(t.getLocationDropdown(), getValueFromPropertyFile("location"));
		sendKeys(t.getTxtProvider(), getValueFromPropertyFile("textprovider"));
		String actualCurrentdate = getAttribute(t.getGetCurrentDate(), "value");
		System.out.println(actualCurrentdate + " " + dateSelected);
		Assert.assertTrue(t.compareDates(dateSelected, actualCurrentdate),"Verify the dates");
		sendKeys(t.getStartTimeHours(), getValueFromPropertyFile("starthour"));
		sendKeys(t.getStartTimeMinutes(), getValueFromPropertyFile("startminute"));
		sendKeys(t.getEndTimeHours(), getValueFromPropertyFile("endhour"));
		sendKeys(t.getEndTimeMinutes(), getValueFromPropertyFile("endminute"));

		t.selectServiceType(serviceName);
		click(t.getConfirmButton());

		Thread.sleep(5000);

		Assert.assertTrue(t.verifyTheEvencreation(serviceName),"Verify the Event Created");

	}

	@Test(priority = 5)
	private void scheduleAppointmentforPatient() throws AWTException {
		click(n.getHomeIcon());
		sapt = new ScheduleAppointment();
		click(sapt.getFindAPatient());
		sendKeys(sapt.getSearchPatient(), patientId);
		click(sapt.getSelectParticularPatient());
		click(sapt.getScheduleAppoinmentLink());
		sapt.selectServiceType(serviceName);
		click(sapt.getSearchButton());
		click(sapt.getSelectAppointBlock());
		click(sapt.getNextButton());
		sendKeys(sapt.getComments(), getValueFromPropertyFile("comments"));
		click(sapt.getSaveButton());
		click(sapt.getStartVisit());
		click(sapt.getVisitConfirmButton());
		click(sapt.getAttachments());
		sapt.fileUpload(System.getProperty("user.dir") + "\\src\\test\\resources\\TestDatas\\Sample Data.txt");
		sendKeys(sapt.getEnterCaption(), "File Uploaded");
		click(sapt.getUploadFleButton());
		click(sapt.getNameLink());
		Assert.assertTrue(sapt.verifyRecentEntries(),"Verify Recent Entries");
		click(sapt.getEndVisit());
		click(sapt.getEndVisitConfirm());
		click(sapt.getDeletePatient());
		sendKeys(sapt.getDeleteReason(), getValueFromPropertyFile("deletereason"));
		click(sapt.getDeletConfirm());
		Assert.assertTrue(sapt.verifyDeletedPatientRecord(patientId),"Verify Delete Patient Record");
		click(n.getHomeIcon());
		click(t.getScheduleAppointment());
		Assert.assertTrue(sapt.verifyAppointSchedulePage(),"Verify Appoinment Schedule Page");
		click(sapt.getManageservice());
		sapt.cancelService(serviceName);
		click(n.getHomeIcon());
		click(t.getScheduleAppointment());
		click(sapt.getManageservice());
		Assert.assertTrue(sapt.verifyServiceDeleteStatus(serviceName),"Verify the Service Deletion");
		
	}

}
