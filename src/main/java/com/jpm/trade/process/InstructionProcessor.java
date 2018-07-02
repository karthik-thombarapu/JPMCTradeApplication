package com.jpm.trade.process;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.jpm.trade.entity.Instruction;
import com.jpm.trade.enums.InstTypeEnum;
import com.jpm.trade.util.TradeUtil;

/**
 * @author karthik.thombarapu
 *
 */
public class InstructionProcessor implements Runnable {
  
  private static ConcurrentHashMap<LocalDate, List<Instruction>> instructions = new ConcurrentHashMap<LocalDate, List<Instruction>>();
  private double sellAmount = 0.0;
  private double buyAmount = 0.0;

  public static void main(String[] args) {
    
    // Load Dummy data into a HashMap with key as ActualSettlementDate and value will be an Array of Instruction Objects
    TradeUtil.loadDummyData(instructions);
    // Scheduler to log the data every day based on the Settlement date
    ScheduledExecutorService execService
            = Executors.newSingleThreadScheduledExecutor();
    Runnable task = new InstructionProcessor();
    execService.scheduleAtFixedRate(task, 0, 24, TimeUnit.HOURS);
  }

  @Override
  public void run() { 
    List<Instruction> sell =new ArrayList<Instruction>();
    List<Instruction> buy =new ArrayList<Instruction>();
    // segregate based on incoming/outgoing shares and log the instructions for the current date
    segregateAndLogTotalAmountByInstructionType(sell, buy);
    // log instructions after evaluating the rankInstructionsByTypeForGivenDate for Buy & Sell
    rankInstructions(buy, "Outgoing");
    rankInstructions(sell, "Incoming");
  }

  /**
   * @param buy(outgoing)/sell(incoming) - Rank Companies based on Buy/Sell for a given date
   */
  public void rankInstructions(List<Instruction> insts, String type) {
    Collections.sort(insts, (Instruction p1, Instruction p2) -> p2.getTradeAmountInUSD().compareTo(p1.getTradeAmountInUSD()));
    System.out.println("================================================================================");
    System.out.println(type + " entities based on Date :"+ LocalDate.now() + " is as below ==> ");
    for(int i=0; i < insts.size(); i++) {
      System.out.println(type + " Rank :"+ (i+1) + " with value --> " + insts.get(i));
    }
    System.out.println("================================================================================");
  }

  /**
   * @param sell(Incoming)
   * @param buy(Outgoing)
   */
  public void segregateAndLogTotalAmountByInstructionType(List<Instruction> sell, List<Instruction> buy) {
    
    buyAmount=0.0;
    sellAmount=0.0;
    
    // find CurrentDate yyyy-MM-dd
    LocalDate localDate = LocalDate.now();    
    // log Buy/Sell amount of Settlement for Given Date
    List<Instruction> all = instructions.get(localDate);
    all.forEach(x -> {
      if(x.getType()==InstTypeEnum.BUY) {
        buyAmount = buyAmount + x.getTradeAmountInUSD().doubleValue();
        buy.add(x);
      } else {
        sellAmount = sellAmount + x.getTradeAmountInUSD().doubleValue();
        sell.add(x);        
      }      
    });
    System.out.println("================================================================================");    
    System.out.println("Incoming amount in USD settled on "+localDate + " with amount "+ sellAmount);
    System.out.println("Outgoing amount in USD settled on "+localDate + " with amount "+ buyAmount);
  }

  /**
   * @return the instructions
   */
  public static ConcurrentHashMap<LocalDate, List<Instruction>> getInstructions() {
    return instructions;
  }
  
}