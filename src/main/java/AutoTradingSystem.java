import java.util.ArrayList;
import java.util.HashMap;

public class AutoTradingSystem {
    private StockerBrokerDriver stockerBrokerDriver;
    private final HashMap<String, ArrayList<Integer>> priceTrendMap = new HashMap<>();

    public void selectStockBroker(String stockBroker) {
        if (stockBroker.equals("Kiwer"))
            this.stockerBrokerDriver = new KiwerDriver();
        else if (stockBroker.equals("Nemo"))
            this.stockerBrokerDriver = new NemoDriver();
    }

    public void selectStockBroker(StockerBrokerDriver stockBrokerDriver){
        this.stockerBrokerDriver = stockBrokerDriver;
    }

    public int getPrice(String stockCode) {
        int price = stockerBrokerDriver.getPrice(stockCode);
        updatePriceTrend(stockCode, price);

        return price;
    }

    private void updatePriceTrend(String stockCode, int price) {
        ArrayList<Integer> priceTrendList = priceTrendMap.computeIfAbsent(stockCode, k -> new ArrayList<>());
        if (priceTrendList.size() >= 3)
            priceTrendList.remove(0);
        priceTrendList.add(price);
    }

    public void sell(String code, int count, int price) {
        stockerBrokerDriver.sell(code, count, price);
    }

    public void login(String id, String pass) {
        if (stockerBrokerDriver != null && isCorrectAuthData(id, pass)) {
            stockerBrokerDriver.login(id, pass);
        }
    }

    private static boolean isCorrectAuthData(String id, String pass) {
        return id != null && pass != null && !id.isEmpty() && !pass.isEmpty();
    }

    public void buy(String code, int count, int price) {
        stockerBrokerDriver.buy(code, count, price);
    }

    public boolean sellNiceTiming(String stockCode, int count) {
        int price = getPrice(stockCode);

        if (!isSellNiceTiming(stockCode)) {
            return false;
        }

        sell(stockCode, count, price);
        return true;
    }

    public boolean isSellNiceTiming(String stockCode) {
        ArrayList<Integer> priceTrendList = priceTrendMap.get(stockCode);
        if (priceTrendList.size() < 3)
            return false;

        for (int i = 1; i < priceTrendList.size(); i++) {
            if (priceTrendList.get(i) > priceTrendList.get(i - 1))
                return false;
        }

        return true;
    }
  
    public void buyNiceTiming (String code, int price) throws InterruptedException {
        int checkFirstPrice = stockerBrokerDriver.getPrice(code);
        Thread.sleep(1);
        int checkSecondPrice = stockerBrokerDriver.getPrice(code);
        if (checkSecondPrice > checkFirstPrice) {
            stockerBrokerDriver.buy(code, checkSecondPrice / price, price);
        }
    }
}