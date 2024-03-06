package com.us1.utils;

/**
 * The <code>SuccessMsgEnum</code> is used to hold all the success key enums for
 * success messages to send in response in the application.
 *
 * @author Hyperlink Infosystem
 *
 */
public enum SuccessMsgEnum {

	LOGOUT_SUCCESS("logout.success.message"), LOGIN_SUCCESS("login.success.message"),
	LOGIN_FAIL_MESSAGE("login.fail.message"), BAD_CREDENTIALS_MESSAGE("bad.credentials.message")
	,INVALID_PHONE_CREDENTIALS_MSG("invalid.phone.credentials.message"),
	ACCOUNT_LOCK_MESSAGE("account.lock.message"), EMAIL_VERIFIED("email.verified.message"),
	LOCK_ADD_SUCCESS("lock.added.success.msg"), OTP_VERIFIED("otp.verified"), PASSWORD_UPDATED("password.updated"),ACEWISE_FORGOT_PASS_SUBJ("acewise.forgot.password.subject"),
	PASSWORD_RESET("password.reset"), MAIL_SENT("mail.sent"), BOAT_ADDED("boat.added"), PROFILE_PHOTO_UPLOAD("profile.photo.upload"), PROFILE_PHOTO_UPDATE("profile.photo.update"), FULL_NAME_UPDATE("full.name.update"), PHONE_UPDATE_MSG("phone.update.message"), UPDATE_EMAIL_SENT("update.email.sent"), REGISTER_EMAIL_VERIFY("register.email.verify"),

	USER_ADDED("user.added"), USER_UPDATED("user.updated"), ABOUT_US_ADDED("about.us.added"), TERMS_AND_PRIVACY_POLICY_ADDED("terms.privacy.policy.added"), USER_DELETED("user.deleted"), USER_BLOCKED("user.blocked"), USER_UNBLOCKED("user.unblocked"), VALID_CREDENTIALS("valid.credentials"), OTP_SENT_SUCCESS("otp.sent.success"), TEMP_PASSWORD_SENT_PHONE("temp.password.sent.phone"), OTP_SENT_PHONE_SUCCESS("otp.sent.phone.success"), OTP_SENT_EMAIL_SUCCESS("otp.sent.email.success"), DETAILS_STORE_SUCCESSFULLY("details.store.successfully"), CONTACT_US_FEEDBACK("contact.us.feedback"), TEMP_PASSWORD_SENT_MAIL("temp.password.sent.email");
	
	String code;

	private SuccessMsgEnum(final String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

}