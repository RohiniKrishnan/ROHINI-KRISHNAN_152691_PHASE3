package com.cg.paymentwalletjpa.service;

import com.cg.paymentwalletjpa.dao.IPaymentDao;
import com.cg.paymentwalletjpa.dao.PaymentDaoImpl;
import com.cg.paymentwalletjpa.dto.AccountDto;
import com.cg.paymentwalletjpa.dto.CustomerDto;
import com.cg.paymentwalletjpa.exception.IValidateException;
import com.cg.paymentwalletjpa.exception.ValidateException;

public class AccountServiceImpl implements IAccountService {

	CustomerDto customer = new CustomerDto();
	AccountDto account = new AccountDto();
	IPaymentDao dao = new PaymentDaoImpl();

	public boolean validate(CustomerDto customer) throws ValidateException {
		boolean result = false;
		String namePattern = "[A-Z a-z]+";
		String noPattern = "[0-9]{10}";
		String emailPattern = "[a-z]{1}[a-z 0-9_]+@gmail.com";
		if (customer.getName().matches(namePattern)) {
			if (customer.getPhNumber().matches(noPattern)) {
				if (customer.getEmailId().matches(emailPattern)) {
					result = true;
				} else
					throw new ValidateException(IValidateException.Error3);
			} else
				throw new ValidateException(IValidateException.Error2);
		} else
			throw new ValidateException(IValidateException.Error1);
		return result;
	}

	public void createAccount(CustomerDto account) throws ValidateException {
		dao.beginTransaction();
		dao.creatAccount(account);
		dao.commitTransaction();
	}

	public CustomerDto getLogin(String userid, String pass) throws ValidateException {
		CustomerDto account = new CustomerDto();
		if (dao.getLogin(userid, pass) != null) {

			account = dao.getLogin(userid, pass);
		} else
			throw new ValidateException(IValidateException.Error4);

		return account;

	}

	public double showBalance(String userid) {
		double balance = 0;
		dao.beginTransaction();
		balance = dao.showBalance(userid);
		dao.commitTransaction();
		return balance;
	}

	public boolean depositAmount(String userId, double amount) {
		boolean result = false;
		if (amount > 0) {
			dao.beginTransaction();
			dao.depositAmount(userId, amount);
			dao.commitTransaction();
			result = true;
		}
		return result;
	}

	public boolean withDrawAmount(String userId, double amount) {
		boolean result = false;
		if (dao.showBalance(userId) >= amount) {
			dao.beginTransaction();
			dao.withDrawAmount(userId, amount);
			dao.commitTransaction();
			result = true;
		}
		return result;
	}

	public boolean fundTransfer(String senderUserId, String receiverUserId, double amount) throws ValidateException {
		boolean result = false;
		if (dao.showBalance(senderUserId) >= amount) {
			dao.beginTransaction();
			dao.fundTransfer(senderUserId, receiverUserId, amount);
			dao.commitTransaction();
			result = true;
		}

		return result;
	}

	public String printTransactions(String userId) {
		dao.beginTransaction();
		String transaction = dao.printTransactions(userId);
		dao.commitTransaction();
		return transaction;
	}

}
