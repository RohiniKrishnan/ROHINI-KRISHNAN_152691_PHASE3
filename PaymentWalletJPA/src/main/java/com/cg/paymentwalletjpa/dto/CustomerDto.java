package com.cg.paymentwalletjpa.dto;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
@Entity
@Table(name = "paymentwallet")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class CustomerDto implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	private String userId;
	private String name;
	private String phNumber;
	private String emailId;
	@Embedded
	private AccountDto account;
	public CustomerDto() {
		
	}
	public CustomerDto(String userId, String name, String phNumber, String emailId, AccountDto account) {
		super();
		this.userId = userId;
		this.name = name;
		this.phNumber = phNumber;
		this.emailId = emailId;
		this.account = account;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhNumber() {
		return phNumber;
	}
	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public AccountDto getAccount() {
		return account;
	}
	public void setAccount(AccountDto account) {
		this.account = account;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
