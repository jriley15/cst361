package com.reportingapp.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
* DTO for carrying Currency data and generic error codes and messages.
* @author Trevor
*
*/
@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class DTO<T> extends DTOBase {
	private T data;
	public DTO(int status, String message, T data) {
		super(status, message);
		this.data = data;
	}
	public DTO() {
		super();
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}