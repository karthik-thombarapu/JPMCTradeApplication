package com.jpm.trade.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;

import com.jpm.trade.entity.Instruction;
import com.jpm.trade.util.TradeUtil;

/**
 * @author karthik.thombarapu
 *
 */
public class InstructionProcessorTest {
  
  private ConcurrentHashMap<LocalDate, List<Instruction>> instructions = null;
  private InstructionProcessor it =null;
  
  @Before
  public void setUp() {
    it = new InstructionProcessor();
    instructions = it.getInstructions();
    TradeUtil.loadDummyData(instructions);
  }
  
  @Test
  public void testSegregateAndLogTotalAmountByInstructionType() {
    List<Instruction> sell = new ArrayList();
    List<Instruction> buy = new ArrayList();
    it.segregateAndLogTotalAmountByInstructionType(sell, buy);
    
    // check if the incoming instructions of size 3 for today's
    assertEquals("Total Number of Incoming instructions are wrong for current date", sell.size(), 6);
    
    // check if the outgoing instructions of size 3 for today's
    assertEquals("Total Number of outgoing instructions are wrong for current date", buy.size(), 4);
  }
  
  @Test
  public void testRankInstructions() {
    List<Instruction> sell = new ArrayList();
    List<Instruction> buy = new ArrayList();
    it.segregateAndLogTotalAmountByInstructionType(sell, buy);
    assertTrue("Incoming instuctions should not be empty",sell.size()> 0);
    assertTrue("Outgoing instuctions should not be empty",buy.size()> 0);
    // check if the incoming instructions of size 3 for today's
    assertEquals("Total Number of Incoming instructions are wrong for current date", sell.size(), 3);
    assertEquals("Wrong Company listed for 1st ranking ", sell.get(0).getCompany().getName(), "African");
    assertEquals("Wrong Currency listed for 1st ranking ", sell.get(0).getCompany().getCurrency(), Currency.getInstance("SAR"));
    // check if the Outgoing instructions of size 2 for today's
    assertEquals("Total Number of Outgoing instructions are wrong for current date", buy.size(), 2);
    assertEquals("Wrong Company listed for 1st ranking ", buy.get(0).getCompany().getName(), "Bharat");
    assertEquals("Wrong Currency listed for 1st ranking ", buy.get(0).getCompany().getCurrency(), Currency.getInstance("INR"));
  }
  
}
