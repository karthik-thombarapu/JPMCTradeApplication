package com.jpm.trade.process;

import static org.junit.Assert.assertEquals;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Currency;
import org.junit.Test;

import com.jpm.trade.util.TradeUtil;

/**
 * @author karthik.thombarapu
 *
 */
public class TradeUtilTest {

  // AED - Weekdays are from Sunday to Thursday
  @Test
  public void testAED_ActualSettlementDate() {
    // Case to identify if ActualSettlementDate is moved to starting weekday - Sunday
    assertEquals("ActualSettlementDate should be on Sunday", TradeUtil.settlementDateEvaluator(Currency.getInstance("AED"),LocalDate.parse("2018-07-06")), LocalDate.parse("2018-07-08"));
    
    // Case to evaluate if it slides ActualSettlementDate to 1st working day for AED i.e., Sunday
    assertEquals("ActualSettlementDate should be on Sunday", TradeUtil.settlementDateEvaluator(Currency.getInstance("AED"),LocalDate.parse("2018-07-08")).getDayOfWeek(), DayOfWeek.SUNDAY);
    
    // Case to identify if ActualSettlementDate is same as weekday - Monday
    assertEquals("ActualSettlementDate should be on Monday as it is a weekday", TradeUtil.settlementDateEvaluator(Currency.getInstance("AED"),LocalDate.parse("2018-07-02")), LocalDate.parse("2018-07-02"));
  }
  
  // SAR - Weekdays are from Sunday to Thursday
  @Test
  public void testSAR_ActualSettlementDate() {
    // Case to identify if ActualSettlementDate is moved to starting weekday - Sunday that too with future dates
    assertEquals("ActualSettlementDate should be on Sunday with future date", TradeUtil.settlementDateEvaluator(Currency.getInstance("SAR"),LocalDate.parse("2018-07-06")), LocalDate.parse("2018-07-08"));
    
    // Case to evaluate if it slides ActualSettlementDate to 1st working day for SAR i.e., Sunday
    assertEquals("ActualSettlementDate should be on Sunday", TradeUtil.settlementDateEvaluator(Currency.getInstance("SAR"),LocalDate.parse("2018-07-06")).getDayOfWeek(), DayOfWeek.SUNDAY);
    
    // Case to identify if ActualSettlementDate is moved to starting weekday - Monday that too with past date
    assertEquals("ActualSettlementDate should be on Monday as Settlement date is in the past", TradeUtil.settlementDateEvaluator(Currency.getInstance("SAR"),LocalDate.parse("2018-06-30")), LocalDate.now());
  }
  
  // INR - Weekdays from Monday to Friday
  @Test
  public void testINR_ActualSettlementDate() {
    // Case to identify if ActualSettlementDate is moved to starting weekday - Monday
    assertEquals("ActualSettlementDate should be slided to Monday", TradeUtil.settlementDateEvaluator(Currency.getInstance("INR"),LocalDate.parse("2018-07-07")), LocalDate.parse("2018-07-09"));
    
    // Case to identify if ActualSettlementDate is same as weekday - Friday
    assertEquals("ActualSettlementDate should be on Monday", TradeUtil.settlementDateEvaluator(Currency.getInstance("INR"),LocalDate.parse("2018-07-06")), LocalDate.parse("2018-07-06"));
    
    // Case to identify if ActualSettlementDate to 1st working day for INR i.e., Monday
    assertEquals("ActualSettlementDate should be on Monday", TradeUtil.settlementDateEvaluator(Currency.getInstance("INR"),LocalDate.parse("2018-07-07")).getDayOfWeek(), DayOfWeek.MONDAY);

    // Case to identify if ActualSettlementDate is set to Current date if the settlementDate falls in the past - CurrentDate
    assertEquals("ActualSettlementDate should be CurrentDate as settlement date was in the past ", TradeUtil.settlementDateEvaluator(Currency.getInstance("INR"),LocalDate.parse("2018-06-01")), LocalDate.now());
  }
  
  // Other currencies - Weekdays from Monday to Friday
  @Test
  public void testOtherCurrencies_ActualSettlementDate() {
    // Case to identify if ActualSettlementDate is moved to starting weekday - Monday
    assertEquals("ActualSettlementDate should be slided to Monday", TradeUtil.settlementDateEvaluator(Currency.getInstance("USD"),LocalDate.parse("2018-07-07")), LocalDate.parse("2018-07-09"));
    
    // Case to identify if ActualSettlementDate is same as weekday - Friday
    assertEquals("ActualSettlementDate should be on Monday", TradeUtil.settlementDateEvaluator(Currency.getInstance("CHF"),LocalDate.parse("2018-07-06")), LocalDate.parse("2018-07-06"));
  }
}
