package com.octo.rest;

import java.io.Serializable;

public class OtherBean implements Serializable {

	private static final long serialVersionUID = 4214185754902038575L;
	
	private String otherString;
	
	private Integer otherInteger;

	public String getOtherString() {
		return otherString;
	}

	public void setOtherString(String otherString) {
		this.otherString = otherString;
	}

	public Integer getOtherInteger() {
		return otherInteger;
	}

	public void setOtherInteger(Integer otherInteger) {
		this.otherInteger = otherInteger;
	}

}
