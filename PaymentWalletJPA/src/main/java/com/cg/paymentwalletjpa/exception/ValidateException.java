package com.cg.paymentwalletjpa.exception;

public class ValidateException extends Exception {
public ValidateException() {
	super();
}
public ValidateException(String message) {
	System.out.println(message);
}
}