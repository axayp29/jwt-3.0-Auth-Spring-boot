package com.us1.utils;

import lombok.Getter;

@Getter
public enum ErrorDataEnum {
	
	INVALID_CREDENTIALS("invalid.credentials"), NO_DATA_FOUND("no.data.found"),
	EMAIL_NOT_VERIFIED("email.not.verified"), OTP_NOT_VERIFIED("otp.not.verified"), X_APIKEY_MISSING("api.key.missing"),
	EMAIL_EXIST("email.exist"), PHONE_EXIST("phone.exist"), EMPTY_FIELD("empty.field"),
	EMAIL_PHONE_EXIST("email.phone.exist"), INVALID_OTP("invalid.otp"), NAME_EMPTY_FIELD("name.empty.field"),
	TYPE_EMPTY_FIELD("type.empty.field"), PASSWORD_EMPTY_FIELD("password.empty.field"),
	PHONE_EMPTY_FIELD("phone.empty.field"), EMAIL_EMPTY_FIELD("email.empty.field"),
	PHONE_NOT_REGISTERED("phone.not.registered"), TIME_MISMATCH("time.mismatch"),
	EMAIL_NOT_REGISTERED("email.not.registered"), USER_LOGGED_OUT("user.logged.out"), INVALID_USER("invalid.user"),
	USER_HAVENT_AUT("user.unauth"),
	OTP_EXPIRED("otp.expired"), INVALID_CREDENTIALS_PASSWORD("invalid.credentials.password");
	
	private String code;

	private ErrorDataEnum(final String code) {
		this.code = code;
	}

}