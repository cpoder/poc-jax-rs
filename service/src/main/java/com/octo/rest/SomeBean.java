package com.octo.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SomeBean implements Serializable {

	private static final long serialVersionUID = -6176891495186519452L;
	
	private String someString;
	
	private Integer someInteger;
	
	private List<OtherBean> otherBeans = new ArrayList<OtherBean>();

	public String getSomeString() {
		return someString;
	}

	public void setSomeString(String someString) {
		this.someString = someString;
	}

	public Integer getSomeInteger() {
		return someInteger;
	}

	public void setSomeInteger(Integer someInteger) {
		this.someInteger = someInteger;
	}

	public List<OtherBean> getOtherBeans() {
		return otherBeans;
	}

	public void setOtherBeans(List<OtherBean> otherBeans) {
		this.otherBeans = otherBeans;
	}

}
