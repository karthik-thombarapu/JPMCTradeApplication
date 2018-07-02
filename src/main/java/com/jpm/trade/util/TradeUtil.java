package com.jpm.trade.util;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import com.jpm.trade.entity.Instruction;
import com.jpm.trade.enums.InstTypeEnum;
import com.jpm.trade.helper.InstructionBuilder;

/**
 * @author karthik.thombarapu
 *
 */
public class TradeUtil {

  /*public static void loadCSV() {    
  }*/
  
  public static LocalDate settlementDateEvaluator(Currency curr, LocalDate settDate) {
    LocalDate localDay = null;
    // if settDate is on past date then it is demarked to current date
    if(settDate==null || settDate.compareTo(LocalDate.now()) < 0) {
      settDate = LocalDate.now();
    }
    if(curr.getCurrencyCode().contentEquals("AED") || curr.getCurrencyCode().contentEquals("SAR")) {
      if(settDate.getDayOfWeek().compareTo(DayOfWeek.FRIDAY) == 0 ){ 
        localDay = settDate.plusDays(2);
      }
      else if(settDate.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0) {
        localDay = settDate.plusDays(1);
      }
      else {
        localDay = settDate;
      }
    } else {
      if(settDate.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0 ){ 
        localDay = settDate.plusDays(2);
      }
      else if(settDate.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) {
        localDay = settDate.plusDays(1);
      } 
      else {
        localDay = settDate;
      }
    }
    return localDay;
  } 
  
  public static void loadDummyData(Map<LocalDate, List<Instruction>> instructions) {
    
    Instruction ib = InstructionBuilder.init() 
        .agreeFx(0.50)
        .units(200)
        .company(Currency.getInstance("SAR"),"EuroUnion")
        .instType(InstTypeEnum.BUY)
        .instDate(LocalDate.now().minusDays(2))
        .settleDate(LocalDate.now().plusDays(4))
        .pricePerUnit(BigDecimal.valueOf(100.25))
        .build();
    populateInstructions(instructions, ib);
    ib = InstructionBuilder.init()
        .agreeFx(0.33)
        .units(250)
        .company(Currency.getInstance("SAR"),"African")
        .instType(InstTypeEnum.SELL)
        .instDate(LocalDate.now().minusDays(2))
        .settleDate(LocalDate.now())
        .pricePerUnit(BigDecimal.valueOf(110.75))
        .build();
    populateInstructions(instructions, ib);
    ib = InstructionBuilder.init() 
        .agreeFx(0.60)
        .units(120)
        .company(Currency.getInstance("HKD"),"HKong")
        .instType(InstTypeEnum.SELL)
        .instDate(LocalDate.now().minusDays(2))
        .settleDate(LocalDate.now().plusDays(1))
        .pricePerUnit(BigDecimal.valueOf(90))
        .build();
    populateInstructions(instructions, ib);
    
    ib = InstructionBuilder.init() 
        .agreeFx(0.45)
        .units(150)
        .company(Currency.getInstance("INR"),"Bharat")
        .instType(InstTypeEnum.BUY)
        .instDate(LocalDate.now().minusDays(1))
        .settleDate(LocalDate.now())
        .pricePerUnit(BigDecimal.valueOf(77))
        .build();
    populateInstructions(instructions, ib);
    
    ib = InstructionBuilder.init() 
        .agreeFx(0.50)
        .units(200)
        .company(Currency.getInstance("EUR"),"Eurozone")
        .instType(InstTypeEnum.BUY)
        .instDate(LocalDate.now().minusDays(2))
        .settleDate(LocalDate.now().plusDays(2))
        .pricePerUnit(BigDecimal.valueOf(83))
        .build();
    populateInstructions(instructions, ib);
    
    ib = InstructionBuilder.init() 
        .agreeFx(0.80)
        .units(110)
        .company(Currency.getInstance("GBP"),"Angels")
        .instType(InstTypeEnum.SELL)
        .instDate(LocalDate.now().minusDays(7))
        .settleDate(LocalDate.now().minusDays(2))
        .pricePerUnit(BigDecimal.valueOf(87))
        .build();
    populateInstructions(instructions, ib);
    
    ib = InstructionBuilder.init() 
        .agreeFx(0.50)
        .units(140)
        .company(Currency.getInstance("CAD"),"Candana")
        .instType(InstTypeEnum.SELL)
        .instDate(LocalDate.now().minusDays(1))
        .settleDate(LocalDate.now())
        .pricePerUnit(BigDecimal.valueOf(110.25))
        .build();
    populateInstructions(instructions, ib);
    
    ib = InstructionBuilder.init() 
        .agreeFx(0.75)
        .units(250)
        .company(Currency.getInstance("JPY"),"Yen")
        .instType(InstTypeEnum.BUY)
        .instDate(LocalDate.now().minusDays(2))
        .settleDate(LocalDate.now().plusDays(1))
        .pricePerUnit(BigDecimal.valueOf(100.25))
        .build();
    populateInstructions(instructions, ib);
    
    ib = InstructionBuilder.init() 
        .agreeFx(0.70)
        .units(400)
        .company(Currency.getInstance("CHF"),"CZechia")
        .instType(InstTypeEnum.BUY)
        .instDate(LocalDate.now().minusDays(2))
        .settleDate(LocalDate.now())
        .pricePerUnit(BigDecimal.valueOf(100.55))
        .build();
    populateInstructions(instructions, ib);
    
    ib = InstructionBuilder.init() 
        .agreeFx(0.65)
        .units(330)
        .company(Currency.getInstance("AED"),"Aussie")
        .instType(InstTypeEnum.SELL)
        .instDate(LocalDate.now().minusDays(1))
        .settleDate(LocalDate.now().plusDays(5))
        .pricePerUnit(BigDecimal.valueOf(120.25))
        .build();
    populateInstructions(instructions, ib);
  }

  /**
   * @param instructions
   * @param ib
   */
  private static void populateInstructions(Map<LocalDate, List<Instruction>> instructions, Instruction ib) {
    if(ib.getActualSettleDate()!=null) {
    if(instructions.get(ib.getActualSettleDate()) != null)
      instructions.get(ib.getActualSettleDate()).add(ib);
    else {
      List<Instruction> ins = new ArrayList();
      ins.add(ib);
      instructions.put(ib.getActualSettleDate(), ins);
    }
  }
  }
}
