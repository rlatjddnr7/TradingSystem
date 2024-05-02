import com.sun.xml.internal.ws.util.StringUtils;

public class AutoTradingSystem {
    private StockerBrokerDriver stockerBrokerDriver;

    public void selectStockBroker(String stockBroker){
        if(stockBroker.equals("Kiwer"))
            this.stockerBrokerDriver = new KiwerDriver();
        else if(stockBroker.equals("Nemo"))
            this.stockerBrokerDriver = new NemoDriver();
    }

    public void login(String id, String pass) {
        if (this.stockerBrokerDriver != null && isCorrectAuthData(id, pass)) {
            stockerBrokerDriver.login(id, pass);
        }
    }

    private static boolean isCorrectAuthData(String id, String pass) {
        return id != null && pass != null && !id.isEmpty() && !pass.isEmpty();
    }

    public void buy(String code, int count, int price) {
        stockerBrokerDriver.buy(code, count, price);
    }

    public int getPrice(String stockCode){
        return stockerBrokerDriver.getPrice(stockCode);
    }
}