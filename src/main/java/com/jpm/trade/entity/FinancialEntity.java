package com.jpm.trade.entity;

import java.io.Serializable;
import java.util.Currency;

/**
 * @author karthik.thombarapu
 *
 */
public class FinancialEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private Currency currency;
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the currency
   */
  public Currency getCurrency() {
    return currency;
  }
  /**
   * @param currency the currency to set
   */
  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "FinancialEntity [name=" + name + ", currency=" + currency + "]";
  }
}
