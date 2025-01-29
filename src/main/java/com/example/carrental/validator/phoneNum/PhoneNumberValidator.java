package com.example.carrental.validator.phoneNum;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) return true; // @NotNull will handle it

        Phonenumber.PhoneNumber phoneNumber;
        try {
            phoneNumber = phoneNumberUtil.parse(s, Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
        } catch (NumberParseException e) {
            return false;
        }
        return phoneNumberUtil.isValidNumber(phoneNumber);
    }
}
