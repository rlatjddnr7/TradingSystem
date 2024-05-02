
public class NemoDriver implements StockerBrokerDriver {

    private final NemoAPI nemoAPI = new NemoAPI();

    @Override
    public void login(String id, String pass) {
        nemoAPI.certification(id, pass);
    }

    @Override
    public void buy(String code, int count, int price) {
        nemoAPI.purchasingStock(code, count, price);
    }

    @Override
    public void sell(String code, int count, int price) {
        nemoAPI.sellingStock(code, count, price);
    }

    @Override
    public int getPrice(String stockCode) {
        try {
            return nemoAPI.getMarketPrice(stockCode, 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
