package com.cg.paymentwalletjpa.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.cg.paymentwalletjpa.dao.IPaymentDao;
import com.cg.paymentwalletjpa.dao.PaymentDaoImpl;
import com.cg.paymentwalletjpa.dto.AccountDto;
import com.cg.paymentwalletjpa.dto.CustomerDto;
import com.cg.paymentwalletjpa.exception.ValidateException;
import com.cg.paymentwalletjpa.service.AccountServiceImpl;
import com.cg.paymentwalletjpa.service.IAccountService;

public class ValidateTestCase {

	IAccountService service = new AccountServiceImpl();
	IPaymentDao dao = new PaymentDaoImpl();

	@Test
	public void CheckForZeroDeposittest() throws ValidateException {
		boolean condition = false;
		CustomerDto customer = new CustomerDto();
		customer.setName("Rohini");
		customer.setPhNumber("9789789789");
		customer.setEmailId("rohini@gmail.com");
		customer.setUserId("rohini");
		AccountDto account = new AccountDto();
		account.setPassword("12345678");
		account.setTransaction("vvv");
		customer.setAccount(account);
		dao.creatAccount(customer);
		condition = service.depositAmount("ranjith", 0.0);
		assertFalse(condition);
	}

	@Test
	public void CheckForValidPassword() throws ValidateException {
		CustomerDto customer = new CustomerDto();
		customer.setName("Rohini");
		customer.setPhNumber("9789789789");
		customer.setEmailId("rohini@gmail.com");
		customer.setUserId("abcde");
		AccountDto account = new AccountDto();
		account.setPassword("12345678");
		account.setTransaction("vvv");
		customer.setAccount(account);
		boolean condition = service.validate(customer);
		assertTrue(condition);

	}

	@Test(expected = ValidateException.class)
	public void CheckForInvalidNameTest() throws ValidateException {
		CustomerDto customer = new CustomerDto();
		customer.setName("fd65f46");
		customer.setPhNumber("9786503850");
		customer.setEmailId("rohini@gmail.com");
		service.validate(customer);
	}

	@Test
	public void CheckForValidNameTest() throws ValidateException {
		CustomerDto customer = new CustomerDto();
		customer.setName("Rohini");
		customer.setPhNumber("9786503850");
		customer.setEmailId("rohini@gmail.com");
		boolean condition = service.validate(customer);
		assertTrue(condition);
	}

	@Test(expected = ValidateException.class)
	public void CheckForInvalidPhoneNumberTest() throws ValidateException {
		CustomerDto customer = new CustomerDto();
		customer.setName("Rohini");
		customer.setPhNumber("9789789");
		customer.setEmailId("abcd@gmail.com");
		boolean condition = service.validate(customer);
		assertFalse(condition);
	}

	@Test
	public void CheckForValidPhoneNumberTest() throws ValidateException {
		CustomerDto customer = new CustomerDto();
		customer.setName("Rohini");
		customer.setPhNumber("9789789789");
		customer.setEmailId("rohini@gmail.com");
		boolean condition = service.validate(customer);
		assertTrue(condition);
	}

	@Test(expected = ValidateException.class)
	public void CheckForInvalidEmailTest() throws ValidateException {
		CustomerDto customer = new CustomerDto();
		customer.setName("Rohini");
		customer.setPhNumber("9786503850");
		customer.setEmailId("4gfgaff");
		boolean condition = service.validate(customer);
		assertFalse(condition);
	}

	@Test
	public void CheckForValidEmailTest() throws ValidateException {
		CustomerDto customer = new CustomerDto();
		customer.setName("Rohini");
		customer.setPhNumber("9786503850");
		customer.setEmailId("rohini@gmail.com");
		boolean condition = service.validate(customer);
		assertTrue(condition);
	}

	@Test(expected = AssertionError.class)
	public void CheckForInvalidassword() throws ValidateException {
		CustomerDto customer = new CustomerDto();
		customer.setName("Rohini");
		customer.setPhNumber("9789789779");
		customer.setEmailId("rohni@gmail.com");
		customer.setUserId("abcde");
		AccountDto account = new AccountDto();
		account.setPassword("123");
		account.setBalance(5000);
		account.setTransaction("zzz");
		customer.setAccount(account);
		boolean condition = service.validate(customer);
		assertFalse(condition);

	}
}
