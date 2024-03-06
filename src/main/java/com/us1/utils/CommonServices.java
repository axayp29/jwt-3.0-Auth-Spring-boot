package com.us1.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CommonServices {

	

	@Autowired
	private MessageSource messageSource;

	private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	/**
	 * The <code>generateBadResponseWithMessageKey</code> is used to generate bad
	 * response with messageKey
	 *
	 * @param messageKey
	 * @return
	 */
	public GenericResponse generateBadResponseWithMessageKey(final String messageKey) {

		
		return new GenericResponse(ApplicationResponseConstants.INVALID_REQUEST, getMessageByCode(messageKey));
	}

	/**
	 * The <code>generateFailureResponse</code> is used to send the failure response
	 * in case of BAD REQUESTS.
	 *
	 * @return
	 */
	public GenericResponse generateFailureResponse() {

		
		return new GenericResponse(ApplicationResponseConstants.INVALID_REQUEST,
				ApplicationResponseConstants.INVALID_REQUEST_MESSAGE, null);
	}

	/**
	 * The <code>generateGenericSuccessResponse</code> is used to send the generic
	 * success response in case of 200 OK.
	 *
	 * @param object
	 * @return
	 */
	public GenericResponse generateGenericSuccessResponse(final Object object) {

		
		return new GenericResponse(ApplicationResponseConstants.SUCCESS_RESPONSE,
				ApplicationResponseConstants.SUCCESS_RESPONSE_MESSAGE, object);

	}

	
	public GenericResponse generateResponseForNoDataFound() {
		return new GenericResponse(ApplicationResponseConstants.NO_DATA_FOUND,
				getMessageByCode(ErrorDataEnum.NO_DATA_FOUND.getCode()));
	}

	/**
	 * The <code>generateResponseWithCodeAndMessage</code> is used to generate
	 * generic response based on code and message key
	 *
	 * @param code
	 * @param keyForMessage
	 * @return
	 */
	public GenericResponse generateResponseWithCodeAndMessage(final String code, final String keyForMessage) {

		
		return new GenericResponse(code, getMessageByCode(keyForMessage));
	}

	/**
	 * The <code>generateSuccessResponseWithMessageKey</code> is used to generate
	 * success response with message key
	 *
	 * @param code
	 * @return
	 */
	public GenericResponse generateSuccessResponseWithMessageKey(final String code) {


		return new GenericResponse(ApplicationResponseConstants.SUCCESS_RESPONSE, getMessageByCode(code));
	}

	/**
	 * The <code>getMessageByCode</code> is used to get the Message according to the
	 * key.
	 *
	 * @param string
	 * @return
	 */
	public String getMessageByCode(final String string) {

		return getMessageSource().getMessage(string, null, Locale.getDefault());
	}

	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public static boolean isEmpty(final String param) {

		return param == null || param.trim().length() <= 0;
	}

	public static boolean matchesWithBcrypt(final String plainText, final String bcryptText) {

		return bCryptPasswordEncoder.matches(plainText, bcryptText);
	}

	public static String convertToBcrypt(final String plainText) {

		return bCryptPasswordEncoder.encode(plainText);
	}

	
	

	

	

	

}