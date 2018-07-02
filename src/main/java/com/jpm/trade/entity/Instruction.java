package com.jpm.trade.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.jpm.trade.enums.InstTypeEnum;

/**
 * @author karthik.thombarapu
 *
 */

public class Instruction implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private FinancialEntity company;
  
  private InstTypeEnum type;
  
  private double agreeFx;
  
  private LocalDate instDate;
  
  private LocalDate settleDate;

  private LocalDate actualSettleDate;
  
  private int units;
  
  private BigDecimal pricePerUnit;
   
  private BigDecimal tradeAmountInUSD;

  /**
   * @return the company
   */
  public FinancialEntity getCompany() {
    return company;
  }

  /**
   * @param company the company to set
   */
  public void setCompany(FinancialEntity company) {
    this.company = company;
  }

  /**
   * @return the type
   */
  public InstTypeEnum getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(InstTypeEnum type) {
    this.type = type;
  }

  /**
   * @return the agreeFx
   */
  public double getAgreeFx() {
    return agreeFx;
  }

  /**
   * @param agreeFx the agreeFx to set
   */
  public void setAgreeFx(double agreeFx) {
    this.agreeFx = agreeFx;
  }

  /**
   * @return the insDate
   */
  public LocalDate getInstDate() {
    return instDate;
  }

  /**
   * @param insDate the insDate to set
   */
  public void setInstDate(LocalDate insDate) {
    this.instDate = insDate;
  }

  /**
   * @return the settleDate
   */
  public LocalDate getSettleDate() {
    return settleDate;
  }

  /**
   * @param settleDate the settleDate to set
   */
  public void setSettleDate(LocalDate settleDate) {
    this.settleDate = settleDate;
  }

  /**
   * @return the actualSettleDate
   */
  public LocalDate getActualSettleDate() {
    return actualSettleDate;
  }

  /**
   * @param actualSettleDate the actualSettleDate to set
   */
  public void setActualSettleDate(LocalDate actualSettleDate) {
    this.actualSettleDate = actualSettleDate;
  }

  /**
   * @return the units
   */
  public int getUnits() {
    return units;
  }

  /**
   * @param units the units to set
   */
  public void setUnits(int units) {
    this.units = units;
  }

  /**
   * @return the pricePerUnit
   */
  public BigDecimal getPricePerUnit() {
    return pricePerUnit;
  }

  /**
   * @param pricePerUnit the pricePerUnit to set
   */
  public void setPricePerUnit(BigDecimal pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  /**
   * @return the tradeAmountInUSD
   */
  public BigDecimal getTradeAmountInUSD() {
    return tradeAmountInUSD;
  }

  /**
   * @param tradeAmountInUSD the tradeAmountInUSD to set
   */
  public void setTradeAmountInUSD(BigDecimal tradeAmountInUSD) {
    this.tradeAmountInUSD = tradeAmountInUSD;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((tradeAmountInUSD == null) ? 0 : tradeAmountInUSD.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Instruction other = (Instruction) obj;
    if (tradeAmountInUSD == null) {
      if (other.tradeAmountInUSD != null)
        return false;
    }
    else if (!tradeAmountInUSD.equals(other.tradeAmountInUSD))
      return false;
    return true;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Instruction [company=" + company + ", type=" + type + ", agreeFx=" + agreeFx + ", instDate=" + instDate + ", settleDate=" + settleDate + ", actualSettleDate=" + actualSettleDate
        + ", units=" + units + ", pricePerUnit=" + pricePerUnit + ", tradeAmountInUSD=" + tradeAmountInUSD + "]";
  }

}