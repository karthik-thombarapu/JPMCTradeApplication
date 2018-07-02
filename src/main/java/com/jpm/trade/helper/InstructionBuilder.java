/**
 * 
 */
package com.jpm.trade.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import com.jpm.trade.entity.FinancialEntity;
import com.jpm.trade.entity.Instruction;
import com.jpm.trade.enums.InstTypeEnum;
import com.jpm.trade.util.TradeUtil;

/**
 * @author karthik.thombarapu
 *
 */

public class InstructionBuilder {

  private Instruction instruction;

  private InstructionBuilder(Instruction instruction) {
    this.instruction = instruction;
  }

  public static InstructionBuilder init() {
    return new InstructionBuilder(new Instruction());
  }

  public InstructionBuilder company(FinancialEntity company) {
    this.instruction.setCompany(company);
    return this;
  }

  public InstructionBuilder agreeFx(double agreeFx) {
    this.instruction.setAgreeFx(agreeFx);
    return this;
  }

  public InstructionBuilder units(int units) {
    this.instruction.setUnits(units);
    return this;
  }

  public InstructionBuilder company(Currency curr, String name) {
    FinancialEntity fe = new FinancialEntity();
    this.instruction.setCompany(fe);
    this.instruction.getCompany().setCurrency(curr);
    this.instruction.getCompany().setName(name);
    return this;
  }

  public InstructionBuilder instType(InstTypeEnum type) {
    this.instruction.setType(type);
    return this;
  }

  public InstructionBuilder instDate(LocalDate instDate) {
    this.instruction.setInstDate(instDate);
    return this;
  }

  public InstructionBuilder settleDate(LocalDate settleDate) {
    this.instruction.setSettleDate(settleDate);
    return this;
  }
  
  public InstructionBuilder pricePerUnit(BigDecimal pricePerUnit) {
    this.instruction.setPricePerUnit(pricePerUnit);
    return this;
  }

  public Instruction build() {
    // set tradeAmountInUSD | USD amount of a trade = Price per unit * Units * Agreed Fx
    this.instruction.setTradeAmountInUSD(new BigDecimal(this.instruction.getPricePerUnit().doubleValue() * this.instruction.getUnits() * this.instruction.getAgreeFx()));
    // set actualSettleDate | A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday. No other holidays to be taken into account.
    this.instruction.setActualSettleDate(TradeUtil.settlementDateEvaluator(this.instruction.getCompany().getCurrency(), this.instruction.getSettleDate()));
    return this.instruction;
  }

}
