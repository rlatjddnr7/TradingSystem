public class AutoTradingSystem {

    private StockerBrockerDriver stockerBrockerDriver;

    public void buy(String code, int count, int price) {
        driver.buy(code, count, price);
    }
    

    public int getPrice(String stockCode){
        return stockerBrockerDriver.getPrice(stockCode);
    }
}

