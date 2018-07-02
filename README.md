# JPMCTradeApplication
## Problem Statement: Report the total incoming & outgoing for JPMC while performing brokerage from many companies across geograhies with varying currencies and working market days. Also, need to rank the companies based on their transaction amount.

## Solution: A simple maven JEE project with minimal dependencies(Junit jars only) setout to handle the above problem and more details below

###InstructionProcessor - is the main class which initializes the instructions of companies(test data), initiates SingleThread with a recurring delay of every 24hrs. So, segregateAndLogTotalAmountByInstructionType(..) method segregates the dataset by instruction type and based on the actualSettlementDate calculates the total amount in USD for outgoing(buying of shares), incoming(selling of shares). Also, rankInstructions(..) will order the list into desc order and prints the Rank with the data.

###TradeUtil - settlementDateEvaluator(..) method evaluates the actualSettlementDate from settlementDate. Incase of weekend then immediate working day is chosen.
a. AED, SAR - Working days Sunday to Thursday.
b. Other currency - Working days Monday to Friday.
c. Assumption - if the settlementDate is in the past w.r.t currentDate then currentDate is considered.
loadDummyData(..) to load the dummy data using the InstructionBuilder.

###InstructionBuilder - it is implemetation of Builder design pattern. As part of the build() method it 
a. evaluates actualSettlementDate by calling TradeUtil.settlementDateEvaluator(..). 
b. Also, TradeUtil.setTradeAmountInUSD() for calculating the tradeAmountInUSD for the given instruction

##Build & Run - Since java project run the InstructionProcessor.java as a Java Application and validate the results.
##Unit Test - 
InstructionProcessorTest.testSegregateAndLogTotalAmountByInstructionType() to validate the number of instructions for the current date.
InstructionProcessorTest.testRankInstructions() to rank instructions based on the tradeAmountInUSD & w.r.t instruction type
TradeUtilTest.testAED_ActualSettlementDate() & testSAR_ActualSettlementDate() were to determine whether right ActualSettlementDate is being set given the Currency & instructed settlement date mainly for AED, SAR for varying workingdays.
TradeUtilTest.testINR_ActualSettlementDate() & testOtherCurrencies_ActualSettlementDate() to determine whether right ActualSettlementDate is being for other Currencies & instructed settlement date mainly for typical workingdays. 


###Sample run results:
================================================================================
Incoming amount in USD settled on 2018-07-02 with amount 24510.375
Outgoing amount in USD settled on 2018-07-02 with amount 33351.5
================================================================================
Outgoing entities based on Date :2018-07-02 is as below ==> 
Outgoing Rank :1 with value --> Instruction [company=FinancialEntity [name=CZechia, currency=CHF], type=BUY, agreeFx=0.7, instDate=2018-06-30, settleDate=2018-07-02, actualSettleDate=2018-07-02, units=400, pricePerUnit=100.55, tradeAmountInUSD=28154]
Outgoing Rank :2 with value --> Instruction [company=FinancialEntity [name=Bharat, currency=INR], type=BUY, agreeFx=0.45, instDate=2018-07-01, settleDate=2018-07-02, actualSettleDate=2018-07-02, units=150, pricePerUnit=77, tradeAmountInUSD=5197.5]
================================================================================
================================================================================
Incoming entities based on Date :2018-07-02 is as below ==> 
Incoming Rank :1 with value --> Instruction [company=FinancialEntity [name=African, currency=SAR], type=SELL, agreeFx=0.33, instDate=2018-06-30, settleDate=2018-07-02, actualSettleDate=2018-07-02, units=250, pricePerUnit=110.75, tradeAmountInUSD=9136.875]
Incoming Rank :2 with value --> Instruction [company=FinancialEntity [name=Candana, currency=CAD], type=SELL, agreeFx=0.5, instDate=2018-07-01, settleDate=2018-07-02, actualSettleDate=2018-07-02, units=140, pricePerUnit=110.25, tradeAmountInUSD=7717.5]
Incoming Rank :3 with value --> Instruction [company=FinancialEntity [name=Angels, currency=GBP], type=SELL, agreeFx=0.8, instDate=2018-06-25, settleDate=2018-06-30, actualSettleDate=2018-07-02, units=110, pricePerUnit=87, tradeAmountInUSD=7656]
================================================================================
