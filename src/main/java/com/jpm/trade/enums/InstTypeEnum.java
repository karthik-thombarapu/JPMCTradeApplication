package com.jpm.trade.enums;

/**
 * @author karthik.thombarapu
 *
 */
public enum InstTypeEnum {

  BUY(1),
  SELL(2);
  
  private final int type;

  InstTypeEnum(int type) {
      this.type = type;
  }
  
  public int getInstType() {
      return this.type;
  }
}
