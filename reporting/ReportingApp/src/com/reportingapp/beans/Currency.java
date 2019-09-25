package com.reportingapp.beans;

import java.sql.Timestamp;
import javax.faces.bean.ManagedBean;
import javax.xml.bind.annotation.XmlRootElement;

@ManagedBean
@XmlRootElement(name="Currency")
public class Currency
{
	private String currencyISOCode = "";
	private String currencyName = "";
	private String currencyCountry = "";
	private String currencySymbol = "";
	private double currencyUSDExchangeRate = 0.0;
	private Timestamp dateRecorded = new Timestamp(0);
	
	public Currency(String currencyISOCode, String currencyName, String currencyCountry, String currencySymbol, double currencyUSDExchangeRate, Timestamp dateRecorded) 
	{
		this.currencyISOCode = currencyISOCode;
		this.currencyName = currencyName;
		this.currencyCountry = currencyCountry;
		this.currencySymbol = currencySymbol;
		this.currencyUSDExchangeRate = currencyUSDExchangeRate;
		this.dateRecorded = dateRecorded;
	}
	
	public String getCurrencyISOCode() {
		return currencyISOCode;
	}
	public void setCurrencyISOCode(String currencyISOCode) {
		this.currencyISOCode = currencyISOCode;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getCurrencyCountry() {
		return currencyCountry;
	}
	public void setCurrencyCountry(String currencyCountry) {
		this.currencyCountry = currencyCountry;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
	public double getCurrencyUSDExchangeRate() {
		return currencyUSDExchangeRate;
	}
	public void setCurrencyUSDExchangeRate(double currencyUSDExchangeRate) {
		this.currencyUSDExchangeRate = currencyUSDExchangeRate;
	}
	public Timestamp getDateRecorded() {
		return dateRecorded;
	}
	public void setDateRecorded(Timestamp dateRecorded) {
		this.dateRecorded = dateRecorded;
	}
}