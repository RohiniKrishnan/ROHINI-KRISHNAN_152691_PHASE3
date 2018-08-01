package com.cg.paymentwalletjpa.service;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.cg.paymentwalletjpa.dto.AccountDto;
import com.cg.paymentwalletjpa.dto.CustomerDto;
import com.cg.paymentwalletjpa.exception.ValidateException;


public interface IAccountService {
	public void createAccount(CustomerDto account) throws ValidateException;
	public CustomerDto getLogin(String userid, String pass) throws ValidateException;
	public double showBalance(String userid);
	public boolean depositAmount(String userId,double amount);
	public boolean withDrawAmount(String userId, double amount);
	public boolean fundTransfer(String senderNumber,String receiverNumber,double amount) throws ValidateException;
	public String printTransactions(String userId);
	public boolean validate(CustomerDto customer) throws ValidateException;
}
