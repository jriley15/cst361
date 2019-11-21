package com.reportingapp.beans;

import javax.faces.bean.ManagedBean;
import javax.xml.bind.annotation.XmlRootElement;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Bean for storing Currency data.
 * @author Trevor
 *
 */
@ManagedBean
@XmlRootElement(name="Currency")
public class Currency {
	// Properties
	private String currencyISOCode = "";
	private String currencyName = "";
	private String currencyCountry = "";
	private String currencySymbol = "";
	private double currencyUSDExchangeRate = 0.0;
	private String dateRecorded = "";
	public Currency() {
	}
	public Currency(String currencyISOCode, String currencyName, String currencyCountry, String currencySymbol, double currencyUSDExchangeRate, String dateRecorded) {
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
	public String getDateRecorded() {
		return dateRecorded;
	}
	public void setDateRecorded(String dateRecorded) {
		this.dateRecorded = dateRecorded;
	}
}