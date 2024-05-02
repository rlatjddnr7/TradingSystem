public class AutoTradingSystem {
    private StockerBrockerDriver stockerBrockerDriver;

    public int getPrice(String stockCode){
        return stockerBrockerDriver.getPrice(stockCode);
    }
}
