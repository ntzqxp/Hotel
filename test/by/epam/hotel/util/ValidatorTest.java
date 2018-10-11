package by.epam.hotel.util;

import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ValidatorTest {

	//test valid login
	@DataProvider
	public Object[][] validLogins() {
		return new Object[][] { 
			new Object[] { "log-3_" },
			new Object[] { "Login_characters_Равен-25" }, 
			};
	}

	@Test(dataProvider = "validLogins")
	public void validateLoginPositiveTest (String validLogin) {
		assertTrue(Validator.validateLogin(validLogin));
	}
	
	//test invalid login
	@DataProvider
	public Object[][] invalidLogins() {
		return new Object[][] { 
			new Object[] { "C2" },
			new Object[] { "wrong_characters:*!" },
			new Object[] { "Login_characters_Равен_more_25" }, 
			};
	}

	@Test(dataProvider = "invalidLogins")
	public void validateLoginNegativeTest (String invalidLogin) {
		assertFalse(Validator.validateLogin(invalidLogin));
	}
	
	//test valid password
	@DataProvider
	public Object[][] validPasswords() {
		return new Object[][] { 
			new Object[] { "MoiseyenkoEA#1" },
			new Object[] { "Moiseyenko@Evgeniy1992" },
			new Object[] { "123Моисеенко++" },
			};
	}

	@Test(dataProvider = "validPasswords")
	public void validatePasswordPositiveTest (String validPassword) {
		assertTrue(Validator.validatePassword(validPassword));
	}
	
	//test invalid password
	@DataProvider
	public Object[][] invalidPasswords() {
		return new Object[][] { 
			new Object[] { "Меньше8" },
			new Object[] { "No_spechial_Chars123" },
			new Object[] { "нет_заглавной-буквы@" }, 
			};
	}
	
	@Test(dataProvider = "invalidPasswords")
	public void validatePasswordNegativeTest (String invalidPassword) {
		assertFalse(Validator.validatePassword(invalidPassword));
	}
	
	
	//test valid email
	@DataProvider
	public Object[][] validEmails() {
		return new Object[][] { 
			new Object[] { "Moiseyenko_E-A@gmail.com" },
			new Object[] { "moiseyenkoe.e.a@mail.ru" },
			new Object[] { "Moiseyenko_e_a.1992@EPAM.by" },
			};
	}

	@Test(dataProvider = "validEmails")
	public void validateEmailPositiveTest (String validEmail) {
		assertTrue(Validator.validateEmail(validEmail));
	}
	
	//test invalid email
	@DataProvider
	public Object[][] invalidEmails() {
		return new Object[][] { 
			new Object[] { "только_латынь@mail.ru" },
			new Object[] { "@moreThanTenChar.com" },
			new Object[] { "Moiseyenko.e.a@gmail.c" }, 
			};
	}

	@Test(dataProvider = "invalidEmails")
	public void validateEmailNegativeTest (String invalidEmail) {
		assertFalse(Validator.validateEmail(invalidEmail));
	}
	
	//test valid confirmation key
	@DataProvider
	public Object[][] validConfirmationKeys() {
		return new Object[][] { 
			new Object[] { "a2b4c6df10" },
			new Object[] { "abcdef1234" },
			new Object[] { "97af2303f1" },
			};
	}

	@Test(dataProvider = "validConfirmationKeys")
	public void validateEmailConfirmationKeyPositiveTest (String validConfirmationKey) {
		assertTrue(Validator.validateEmailConfirmationKey(validConfirmationKey));
	}
	
	//test invalid confirmation key
	@DataProvider
	public Object[][] invalidConfirmationKeys() {
		return new Object[][] { 
			new Object[] { "abcdefghi11" },
			new Object[] { "AbCdefghij" },
			new Object[] { "abcdefg12Ы" }, 
			};
	}

	@Test(dataProvider = "invalidConfirmationKeys")
	public void validateEmailConfirmationKeyNegativeTest (String invalidConfirmationKey) {
		assertFalse(Validator.validateEmailConfirmationKey(invalidConfirmationKey));
	}
	
	//test valid currency
	@DataProvider
	public Object[][] validCurrencies() {
		return new Object[][] { 
			new Object[] { "22,33" },
			new Object[] { "22." },
			new Object[] { "1234567891.12" },
			};
	}

	@Test(dataProvider = "validCurrencies")
	public void validateCurrencyPositiveTest (String validCurrency) {
		assertTrue(Validator.validateCurrency(validCurrency));
	}
	
	//test invalid currency
	@DataProvider
	public Object[][] invalidCurrencies() {
		return new Object[][] { 
			new Object[] { "12345678910" },
			new Object[] { "12.333" },
			new Object[] { "12:33" }, 
			};
	}
	
	@Test(dataProvider = "invalidCurrencies")
	public void validateCurrencyNegativeTest (String invalidCurrency) {
		assertFalse(Validator.validateCurrency(invalidCurrency));
	}

}
