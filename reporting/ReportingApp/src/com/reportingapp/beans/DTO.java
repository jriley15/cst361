package com.reportingapp.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class DTO extends DTOBase
{
	private List<Currency> data;
	public DTO(int status, String message, List<Currency> data)
	{
		super(status, message);
		this.data = data;
	}
	public DTO()
	{
		super();
		this.data = new ArrayList<Currency>();
	}
	
	public List<Currency> getData()
	{
		return data;
	}
	public void setData(List<Currency> data)
	{
		this.data = data;
	}
}
