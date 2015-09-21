package com.mikulin.ma.spring;

import java.util.Properties;

import javax.annotation.Resource;

public class TransactionBoImpl implements TransactionBo {

	@Resource(name="dbProperties")
	private Properties dbProperties;
	
	public String save() {

		System.out.println("host = " + dbProperties.get("host"));
		return "Jersey + Spring example";

	}

}