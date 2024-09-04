package org.pages;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Register extends BaseClass{
	
	public Register() {
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(xpath="//a[normalize-space()='Register a patient']")
	private WebElement register;
	
	@FindBy(name="givenName")
	private WebElement firstName;
	
	@FindBy(name="familyName")
	private WebElement familyName;
	
	@FindBy(id="next-button")
	private WebElement nextButton;
	
	@FindBy(xpath="//input[@id='submit']")
	private WebElement confirmButton;
	
	@FindBy(id="gender-field")
	private WebElement genderDropdown;
	
	@FindBy(name="birthdateDay")
	private WebElement birthDay;
	
	@FindBy(name="birthdateMonth")
	private WebElement birthMonth;
	
	@FindBy(name="birthdateYear")
	private WebElement birthYear;
	
	@FindBy(id="address1")
	private WebElement address_line1;
	
	@FindBy(id="address2")
	private WebElement address_line2;
	
	@FindBy(id="cityVillage")
	private WebElement city;
	
	@FindBy(id="stateProvince")
	private WebElement state;
	
	@FindBy(id="country")
	private WebElement country;
	
	@FindBy(id="postalCode")
	private WebElement postalCode;
	
	@FindBy(name="phoneNumber")
	private WebElement phoneNumber;
	
	@FindBy(id="submit")
	private WebElement confirm;
	
	@FindBy(xpath="//em[text()='Patient ID']/following-sibling::span")
	private WebElement patientId;
	
	@FindBy(xpath="//span[text()='Name: ']/parent::p/text()[normalize-space()]")
	private WebElement name;
	
	@FindBy(xpath="//span[text()='Gender: ']/parent::p/text()[normalize-space()]")
	private WebElement gender;
	
	@FindBy(xpath="//div[@id='dataCanvas']//p[3]")
	private WebElement dob;
	
	@FindBy(xpath="//div[@id='dataCanvas']//p[4]")
	private WebElement address;
	
	@FindBy(xpath="//span[text()='Phone Number: ']/parent::p/text()[normalize-space()]")
	private WebElement mobile_no;
	
	@FindBy(xpath="\"/html[1]/body[1]/div[1]/div[3]/div[6]/div[1]/div[1]/div[2]/span[2]\"")
	private WebElement age;

	public WebElement getRegister() {
		return register;
	}

	public WebElement getFirstName() {
		return firstName;
	}

	public WebElement getFamilyName() {
		return familyName;
	}

	public WebElement getNextButton() {
		return nextButton;
	}
	
	public WebElement getConfirmButton() {
		return confirmButton;
	}

	public WebElement getGenderDropdown() {
		return genderDropdown;
	}

	public WebElement getBirthDay() {
		return birthDay;
	}

	public WebElement getBirthMonth() {
		return birthMonth;
	}

	public WebElement getBirthYear() {
		return birthYear;
	}

	public WebElement getAddress_line1() {
		return address_line1;
	}

	public WebElement getAddress_line2() {
		return address_line2;
	}

	public WebElement getCity() {
		return city;
	}

	public WebElement getState() {
		return state;
	}

	public WebElement getCountry() {
		return country;
	}

	public WebElement getPostalCode() {
		return postalCode;
	}

	public WebElement getPhoneNumber() {
		return phoneNumber;
	}

	public WebElement getConfirm() {
		return confirm;
	}
public WebElement getPatientId() {
	return patientId;
}
	public WebElement getName() {
		return name;
	}

	public WebElement getGender() {
		return gender;
	}

	public WebElement getDob() {
		return dob;
	}

	public WebElement getAddress() {
		return address;
	}

	public WebElement getMobile_no() {
		return mobile_no;
	}

	public WebElement getAge() {
		return age;
	}
	
	
	private void selectGender() {
		selectByValue(gender, "M");

	}
}
	
	