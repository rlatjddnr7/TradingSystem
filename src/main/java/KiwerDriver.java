public class KiwerDriver implements StockerBrockerDriver {
    private final KiwerAPI kiwerAPI;

    public KiwerDriver() {
        this.kiwerAPI = new KiwerAPI();
    }

    @Override
    public void login(String id, String pass) {
        kiwerAPI.login(id, pass);
    }

    @Override
    public void buy(String stockCode, int price, int count) {
        kiwerAPI.buy(stockCode, count, price);
    }

    @Override
    public void sell(String code, int count, int price) {

    }

    @Override
    public int getPrice(String stockCode) {
        return kiwerAPI.currentPrice(stockCode);
    }
}
