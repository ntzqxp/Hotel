package by.epam.hotel.util;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.hotel.entity.Nationality;
import by.epam.hotel.util.constant.ValidationConstant;

public class Validator {
	
	public static boolean validateLogin(String login) {
		Pattern pattern = Pattern.compile(ValidationConstant.LOGIN_PATTERN);
		Matcher matcher = pattern.matcher(login);
		return matcher.matches();
	}

	public static boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile(ValidationConstant.EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/*
	 * ^ # start-of-string (?=.*[0-9]) # a digit must occur at least once
	 * (?=.*[a-z]) # a lower case letter must occur at least once (?=.*[A-Z]) # an
	 * upper case letter must occur at least once (?=.*[@#$%^&+=]) # a special
	 * character must occur at least once (?=\S+$) # no whitespace allowed in the
	 * entire string .{8,} # anything, at least eight places though $ #
	 * end-of-string
	 */
	public static boolean validatePassword(String password) {
		Pattern pattern = Pattern.compile(ValidationConstant.PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public static boolean validateEmailConfirmationKey(String emailConfirmationKey) {
		Pattern pattern = Pattern.compile(ValidationConstant.CONFIRMATION_KEY);
		Matcher matcher = pattern.matcher(emailConfirmationKey);
		return matcher.matches();
	}
	

	public static boolean validateCurrency(String currency) {
		Pattern pattern = Pattern.compile(ValidationConstant.PRICE_PATTERN);
		Matcher matcher = pattern.matcher(currency);
		return matcher.matches();
	}
	
	public static boolean validateFName(String fname) {
		Pattern pattern = Pattern.compile(ValidationConstant.FIRST_NAME_PATTERN);
		Matcher matcher = pattern.matcher(fname);
		return matcher.matches();
	}

	public static boolean validateLName(String lname) {
		Pattern pattern = Pattern.compile(ValidationConstant.LAST_NAME_PATTERN);
		Matcher matcher = pattern.matcher(lname);
		return matcher.matches();
	}

	public static boolean validatePassport(String passport) {
		Pattern pattern = Pattern.compile(ValidationConstant.PASSPORT_PATTERN);
		Matcher matcher = pattern.matcher(passport);
		return matcher.matches();
	}

	public static boolean validateNationality(String tempNationalityId, List<Nationality> nationalities) {
		for (Nationality nationality : nationalities) {
			if (nationality.getCountryId().equals(tempNationalityId)) {
				return true;
			}
		}
		return false;
	}

	public static boolean validateFromTo(String from, String to) {
		if (ValidationConstant.EMPTY_STRING.equals(from) || ValidationConstant.EMPTY_STRING.equals(to)) {
			return false;
		}
		LocalDate localDateFrom = LocalDate.parse(from);
		LocalDate localDateTo = LocalDate.parse(to);
		return (localDateFrom.isAfter(LocalDate.now())||localDateFrom.isEqual(LocalDate.now())) 
				&& (localDateFrom.isEqual(localDateTo)||localDateTo.isAfter(localDateFrom));
	}
	
	public static boolean validateNumber(String number) {
		boolean flag = false;
		Pattern pattern = Pattern.compile(ValidationConstant.NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(number);
		if(matcher.matches()) {
			flag = Integer.parseInt(number)<=65535;
		}
		return flag;
	}
	
	public static boolean validateCountryId(String countryId) {
		Pattern pattern = Pattern.compile(ValidationConstant.COUNTRY_ID_PATTERN);
		Matcher matcher = pattern.matcher(countryId);
		return matcher.matches();
	}
	
	public static boolean validateCountry(String country) {
		Pattern pattern = Pattern.compile(ValidationConstant.COUNTRY_PATTERN);
		Matcher matcher = pattern.matcher(country);
		return matcher.matches();
	}
	
	public static boolean validateClassId(String classId) {
		Pattern pattern = Pattern.compile(ValidationConstant.CLASS_ID_PATTERN);
		Matcher matcher = pattern.matcher(classId);
		return matcher.matches();
	}

}
