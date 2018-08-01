package com.cg.paymentwalletjpa.ui;

import java.util.Scanner;

import com.cg.paymentwalletjpa.dto.AccountDto;
import com.cg.paymentwalletjpa.dto.CustomerDto;
import com.cg.paymentwalletjpa.exception.ValidateException;
import com.cg.paymentwalletjpa.service.AccountServiceImpl;
import com.cg.paymentwalletjpa.service.IAccountService;

/**
 * Hello world!
 *
 */
public class App {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int choice1 = 0;

		IAccountService service = new AccountServiceImpl();
		do {
			System.out.println("************* Payment Wallet!!! **************");
			System.out.println("Enter 1 to Create Account");
			System.out.println("Enter 2 Login into Account");
			System.out.println("Enter 3 to exit");
			choice1 = scanner.nextInt();
			switch (choice1) {
			case 1:
				System.out.println("Enter your Name");
				String name = scanner.next();
				System.out.println("Enter your Email Id");
				String email = scanner.next();
				System.out.println("Enter your Mobile Number");
				String number = scanner.next();
				System.out.println("Enter the Initial amount to your new Account");
				double amount = scanner.nextDouble();
				System.out.println("Enter User Name");
				String user = scanner.next();
				System.out.println("Enter Password");
				String pass = scanner.next();

				CustomerDto customer = new CustomerDto();
				customer.setName(name);
				customer.setUserId(user);
				customer.setPhNumber(number);
				customer.setEmailId(email);
				AccountDto account = new AccountDto();
				account.setBalance(amount);
				account.setPassword(pass);
				account.setTransaction("vvv");
				customer.setAccount(account);

				boolean result = false;

				try {
					result = service.validate(customer);
					service.createAccount(customer);
					System.out.println("Welcome " + customer.getName());
					System.out.println("Your Account is Created!!");
				}

				catch (ValidateException e) {
					e.getMessage();
				}
				if (!result)
					System.out.println("Cannot create account! Try Again");
				break;
			case 2:
				System.out.println("Enter Username");
				String Username = scanner.next();
				System.out.println("Enter Password");
				String Password = scanner.next();
				CustomerDto loginAccount;

				try {
					loginAccount = service.getLogin(Username, Password);
					login(loginAccount);

				} catch (ValidateException e) {
					e.getMessage();
				}
				break;

			default:
				System.out.println("Exit");
				break;

			}
		} while (choice1 != 3);

	}

	AccountDto accountdto = new AccountDto();

	private static void login(CustomerDto customer) {

		int choice2 = 0;
		System.out.println("$$$$$$$ Welcome" + customer.getName() + "$$$$$$$$$");
		do {
			System.out.println("***********************************************");
			System.out.println("Enter 1 to Show Balance");
			System.out.println("Enter 2 to Deposit Money to Account");
			System.out.println("Enter 3 to Withdraw money from Account");
			System.out.println("Enter 4 to Fund Transfer to another Account");
			System.out.println("Enter 5 to Print Transaction");
			System.out.println("Enter 6 to logout");
			System.out.println("Enter 7 to Exit");
			System.out.println("*************************************************");
			choice2 = scanner.nextInt();
			IAccountService service1 = new AccountServiceImpl();
			String userId = customer.getUserId();

			switch (choice2) {

			case 1:
				double balance = service1.showBalance(userId);
				System.out.println("Your Account Balance is Rs." + balance);
				break;
			case 2:
				System.out.println("Enter the amount to be deposit");
				double amount = scanner.nextDouble();

				if (service1.depositAmount(userId, amount)) {
					System.out.println("Rupees" + amount + " Deposited to your Account");
					System.out.println("Your updated balance is Rs." + service1.showBalance(userId));
				} else
					System.out.println("Failed to Deposit!!");
				break;
			case 3:
				System.out.println("Enter the amount to Withdraw  from the account");
				double withdrawAmount = scanner.nextDouble();
				if (service1.withDrawAmount(userId, withdrawAmount)) {
					System.out.println("Withdraw Amount:Rs." + withdrawAmount);
					System.out.println("Your updated balance is Rs." + service1.showBalance(userId));
				} else
					System.out.println("Failed to Withdraw money!!");
				break;
			case 4:
				System.out.println("Enter the Receiver User ID");
				String receiverid = scanner.next();
				System.out.println("Enter the amount to transfer");
				double fundAmount = scanner.nextDouble();
				try {
					if (service1.fundTransfer(userId, receiverid, fundAmount)) {
						System.out.println("Rupees " + fundAmount + " Succesfully Transfered to " + receiverid);
						System.out.println("Your updated balance is Rs." + service1.showBalance(userId));
					}
				} catch (ValidateException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
				break;
			case 5:

				System.out.println("*************Transaction Details :*************");
				String transaction = service1.printTransactions(userId);
				Scanner scanner = new Scanner(transaction).useDelimiter("vvv");
				while (scanner.hasNext()) {
					String str = scanner.next();
					System.out.println(str);
				}
				break;
			case 6:
				System.out.println("Logged Out Successfully!!");
				System.out.println("******** Thank you!! *********");
				break;
			default:
				System.out.println("Exit!!");
				break;

			}
		} while (choice2 != 7);
	}

}
