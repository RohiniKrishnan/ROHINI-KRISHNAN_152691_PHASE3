package com.cg.paymentwalletjpa.dao;

import com.cg.paymentwalletjpa.dto.CustomerDto;
import com.cg.paymentwalletjpa.exception.ValidateException;

public interface IPaymentDao {
	public void creatAccount(CustomerDto customer) throws ValidateException;

	public CustomerDto getLogin(String userid, String pass) throws ValidateException;

	public double showBalance(String userid);

	public void depositAmount(String userId, double amount);

	public void withDrawAmount(String userId, double amount);

	public void fundTransfer(String senderUserId, String receiverUserId, double amount) throws ValidateException;

	public String printTransactions(String userId);

	public void beginTransaction();

	public void commitTransaction();
}
