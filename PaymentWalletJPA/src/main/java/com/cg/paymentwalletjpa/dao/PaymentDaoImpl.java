package com.cg.paymentwalletjpa.dao;

import java.time.LocalDateTime;

import com.cg.paymentwalletjpa.dto.AccountDto;
import com.cg.paymentwalletjpa.dto.CustomerDto;
import com.cg.paymentwalletjpa.exception.ValidateException;

public class PaymentDaoImpl<EntityManager> implements IPaymentDao {

	private javax.persistence.EntityManager entityManager;

	public PaymentDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}

	public void creatAccount(CustomerDto customer) throws ValidateException {

		entityManager.persist(customer);

	}

	public CustomerDto getLogin(String userid, String pass) throws ValidateException {

		String password = null;
		CustomerDto customer = entityManager.find(CustomerDto.class, userid);
		password = customer.getAccount().getPassword();
		if (password.equals(pass)) {
			return customer;
		}

		return null;

	}

	public double showBalance(String userid) {
		double balance = entityManager.find(CustomerDto.class, userid).getAccount().getBalance();
		return balance;

	}

	public void depositAmount(String userId, double amount) {
		CustomerDto customer = entityManager.find(CustomerDto.class, userId);
		AccountDto account = customer.getAccount();
		account.setBalance(customer.getAccount().getBalance() + amount);

		LocalDateTime time = LocalDateTime.now();
		String transaction = customer.getAccount().getTransaction().concat(amount + "Deposited at : " + time + "vvv");
		account.setTransaction(transaction);
		customer.setAccount(account);
		entityManager.merge(customer);
	}

	public void withDrawAmount(String userId, double amount) {
		CustomerDto customer = entityManager.find(CustomerDto.class, userId);
		AccountDto account = customer.getAccount();
		account.setBalance(customer.getAccount().getBalance() - amount);

		LocalDateTime time = LocalDateTime.now();
		String transaction = customer.getAccount().getTransaction().concat(amount + "WithDraw at : " + time + "vvv");
		account.setTransaction(transaction);
		customer.setAccount(account);
		entityManager.merge(customer);

	}

	public void fundTransfer(String senderUserId, String receiverUserId, double amount) throws ValidateException {
		CustomerDto customer = entityManager.find(CustomerDto.class, receiverUserId);
		AccountDto account = customer.getAccount();
		account.setBalance(customer.getAccount().getBalance() + amount);
		LocalDateTime time = LocalDateTime.now();
		String transaction = customer.getAccount().getTransaction()
				.concat(amount + "Received from : " + senderUserId + " at " + time + "vvv");

		account.setTransaction(transaction);
		customer.setAccount(account);
		entityManager.merge(customer);

		CustomerDto customer2 = entityManager.find(CustomerDto.class, senderUserId);
		AccountDto account2 = customer2.getAccount();
		account2.setBalance(customer2.getAccount().getBalance() - amount);
		String transaction2 = customer2.getAccount().getTransaction()
				.concat(amount + "Received from : " + senderUserId + " at " + time + "vvv");

		account2.setTransaction(transaction2);
		customer2.setAccount(account2);
		entityManager.merge(customer2);
	}

	public String printTransactions(String userId) {

		CustomerDto customer = entityManager.find(CustomerDto.class, userId);
		String transaction = customer.getAccount().getTransaction();
		return transaction;
	}

	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	public void commitTransaction() {
		entityManager.getTransaction().commit();
	}

}
