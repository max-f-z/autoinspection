package com.autoinspection.polaris.model.entity;

import com.autoinspection.polaris.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VehicleMileageEntity extends BaseEntity implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String plate;
  private Date month;
  private BigDecimal mile;
  private String period;
  private String averageSpeed;
  private String begMile;
  private String endMile;
  private int hours;
  private int minutes;
  private int seconds;
  private String customerName;
  
public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public String getPeriod() {
	return period;
}

public void setPeriod(String period) {
	this.period = period;
}

public String getAverageSpeed() {
	return averageSpeed;
}

public void setAverageSpeed(String averageSpeed) {
	this.averageSpeed = averageSpeed;
}

public String getBegMile() {
	return begMile;
}

public void setBegMile(String begMile) {
	this.begMile = begMile;
}

public String getEndMile() {
	return endMile;
}

public void setEndMile(String endMile) {
	this.endMile = endMile;
}

public int getHours() {
	return hours;
}

public void setHours(int hours) {
	this.hours = hours;
}

public int getMinutes() {
	return minutes;
}

public void setMinutes(int minutes) {
	this.minutes = minutes;
}

public int getSeconds() {
	return seconds;
}

public void setSeconds(int seconds) {
	this.seconds = seconds;
}

public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public Date getMonth() {
    return month;
  }

  public void setMonth(Date month) {
    this.month = month;
  }

  public BigDecimal getMile() {
    return mile;
  }

  public void setMile(BigDecimal mile) {
    this.mile = mile;
  }
}

