package lmax;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TradeTransactionPublisher implements Runnable {
  private static int LOOP = 10000000;//模拟一千万次交易的发生
  Disruptor<TradeTransaction> disruptor;
  private CountDownLatch latch;

  public TradeTransactionPublisher(CountDownLatch latch, Disruptor<TradeTransaction> disruptor) {
    this.disruptor = disruptor;
    this.latch = latch;
  }

  @Override
  public void run() {
    TradeTransactionEventTranslator tradeTransloator = new TradeTransactionEventTranslator();
    for (int i = 0; i < LOOP; i++) {
      disruptor.publishEvent(tradeTransloator);
    }
    latch.countDown();
  }

}

class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {
  private Random random = new Random();

  @Override
  public void translateTo(TradeTransaction event, long sequence) {
    this.generateTradeTransaction(event);
  }

  private TradeTransaction generateTradeTransaction(TradeTransaction trade) {
    trade.setPrice(random.nextDouble() * 9999);
    return trade;
  }
}  