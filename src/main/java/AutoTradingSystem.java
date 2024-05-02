import com.sun.xml.internal.ws.util.StringUtils;

public class AutoTradingSystem {
    private StockerBrockerDriver stockerBrockerDriver;

    public void login(String id, String pass) {
        if (this.stockerBrockerDriver != null && isCorrectAuthData(id, pass)) {
            stockerBrockerDriver.login(id, pass);
        }
    }

    private static boolean isCorrectAuthData(String id, String pass) {
        return id != null && pass != null && !id.isEmpty() && !pass.isEmpty();
    }

    public void buy(String code, int count, int price) {
        stockerBrockerDriver.buy(code, count, price);
    }
    
    public int getPrice(String stockCode){
        return stockerBrockerDriver.getPrice(stockCode);
    }
}