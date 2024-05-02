public class KiwerDriver implements StockerBrokerDriver {
    private final KiwerAPI kiwerAPI = new KiwerAPI();

    @Override
    public void login(String ID, String password) {

    }

    @Override
    public void buy(String stockCode, int price, int count) {

    }

    @Override
    public void sell(String stockCode, int price, int count) {
        kiwerAPI.sell(stockCode, price, count);
    }

    @Override
    public int getPrice(String stockCode) {
        return kiwerAPI.currentPrice(stockCode);
    }
}