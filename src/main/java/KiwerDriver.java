public class KiwerDriver implements StockerBrockerDriver {

    private final KiwerAPI kiwerAPI;

    public KiwerDriver() {
        this.kiwerAPI = new KiwerAPI();
    }

    @Override
    public void login(String ID, String password) {

    }

    @Override
    public void buy(String stockCode, int price, int count) {
        kiwerAPI.buy(code, count, price);
    }

    @Override
    public void sell(String stockCode, int price, int count) {

    }

    @Override
    public int getPrice(String stockCode) {
        return kiwerAPI.currentPrice(stockCode);
    }
}
