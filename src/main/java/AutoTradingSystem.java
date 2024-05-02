public class AutoTradingSystem {
    private StockerBrokerDriver stockerBrokerDriver;

    public void selectStockBroker(String stockBroker){
        if(stockBroker.equals("Kiwer"))
            this.stockerBrokerDriver = new KiwerDriver();
        else if(stockBroker.equals("Nemo"))
            this.stockerBrokerDriver = new NemoDriver();
    }

    public int getPrice(String stockCode){
        return stockerBrokerDriver.getPrice(stockCode);
    }

    public void sell(String code, int count, int price){
        stockerBrokerDriver.sell(code, count, price);
    }
}